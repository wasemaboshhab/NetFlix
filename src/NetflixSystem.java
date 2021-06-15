import java.util.Scanner;

public class NetflixSystem {
    private Series[] series;
    private Account[] accounts;
    private int lastAccountIndexLoggedIn;
    private boolean stop;




    public NetflixSystem(Series[] series) {
        this.series = series;

    }

    public void actions(int userOption) {
        switch (userOption) {
            case Def.PRINT_SERIES_NAMES:
                printSeriesNames();
                break;
            case Def.SERIES_USER_STARTED_WATCHING:
                accounts[lastAccountIndexLoggedIn].seriesWatchedIncludingLastEpisode();
                break;
            case Def.SUBSCRIPTION_DETAILS:
                accounts[lastAccountIndexLoggedIn].accountDetails();
                break;
            case Def.SELECT_SERIES_TO_WATCH:
                addSeriesToUserList();
                break;
            case Def.EXIST:
                stop = false;
                break;

        }

    }

    private void addEpisodeOrNewSeries() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Series you would like to watch\n>");
        String seriesUserWouldWatch = scanner.nextLine();
        // check if series Existed
        if (checkIfSeriesExisted(seriesUserWouldWatch)) {
            //check if user start watch this series
            int seriesIndex = isUserStartWatchThisSeries(seriesUserWouldWatch);
            if (seriesIndex != -1) {// remind him last Episode he watched
                Account onlineAccount = accounts[lastAccountIndexLoggedIn];
                //seriesIndex selectedBy user
                Series seriesSelectedByUser = onlineAccount.getSeriesWatched()[seriesIndex];
                remindUserLastEpisodeHeWatched(seriesUserWouldWatch);
                // and ask him which next Episode he would watch
                int episodeIndex = askUserWhichNextEpisodeHeWouldWatch();
                episodeIndex -= 1;
                boolean episodeNotExisted = episodeIndex < 0 || episodeIndex >= seriesSelectedByUser.getEpisodes().length;
                if (episodeNotExisted) {
                    System.out.println("Episode has not been uploaded yet ⤩⤩\n\n");
                } else {
                    Episode[] userEpisodesList = seriesSelectedByUser.getEpisodes();
                    Episode episodeSelectedByUser = series[seriesIndex].getEpisodes()[episodeIndex];
                    int netFLixEpisodeIndex =  getEpisodeIndexFromNetflixList(seriesIndex ,episodeIndex);
                    userEpisodesList[netFLixEpisodeIndex] = episodeSelectedByUser;
                    seriesSelectedByUser.setLastEpisodeWatched(userEpisodesList[netFLixEpisodeIndex]);
                }
            } else {
                addNewSeriesToUserList(seriesUserWouldWatch);
            }
        }
    }
            private  void addFirstSeriesToUserList() {
        Scanner input = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Series you would like to watch\n>");
        String seriesUserWouldWatch = scanner.nextLine();
        if (checkIfSeriesExisted(seriesUserWouldWatch)) {
            for (int i = 0; i < series.length; i++) {
                String currentSeriesName = series[i].getName();
                if (currentSeriesName.equals(seriesUserWouldWatch)) {
                    System.out.println("Which Episode you would Watch ��");
                    series[i].printEpisodesNames();
                    System.out.println(">");
                    int episodeIndex = input.nextInt();
                    episodeIndex -= 1;
                    boolean episodeNotExisted = episodeIndex < 0 || episodeIndex >=series[i].getEpisodes().length;
                    if (episodeNotExisted) {
                        System.out.println("Episode has not been uploaded yet ⤩⤩");
                    } else {
                        Account onlineAccount = accounts[lastAccountIndexLoggedIn];
                        Episode[] openNewEpisodeArray = new Episode[series[i].getEpisodes().length];
                        Series[] userSeriesList = onlineAccount.getSeriesWatched();
                        openNewEpisodeArray[getEpisodeIndexFromNetflixList(i , episodeIndex)] = series[i].getEpisodes()[episodeIndex];
                        Series seriesUserStartWatch = new Series(seriesUserWouldWatch, openNewEpisodeArray);
                        seriesUserStartWatch.setLastEpisodeWatched(openNewEpisodeArray[getEpisodeIndexFromNetflixList(i , episodeIndex)]);
                        userSeriesList[i] = seriesUserStartWatch;
                        break;
                    }
                    break;
                }
            }
        }

    }
            private void addSeriesToUserList() {
        if (!isUserStartWatchAnySeries()) {
            openAnArraySeriesList();
            addFirstSeriesToUserList();
        } else {
            addEpisodeOrNewSeries();
        }

    }
            private int getEpisodeIndexFromNetflixList(int seriesIndex , int userEpisodeIndex) {
        Series seriesSelected = series[seriesIndex];
        Episode[] seriesEpisodes = seriesSelected.getEpisodes();
        for (int i = 0; i < seriesEpisodes.length; i++) {
            if (i == userEpisodeIndex) {
                return i;
            }
        }
        return -1 ;
    }
            private void addNewSeriesToUserList(String seriesUserWouldWatch) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < series.length; i++) {
            String currentSeriesName = series[i].getName();
            if (currentSeriesName.equals(seriesUserWouldWatch)) {
                System.out.println("Which Episode you would Watch ��");
                Series seriesChosen = series[i];
                series[i].printEpisodesNames();
                int episodeIndex = scanner.nextInt();
                boolean episodeNotExisted = episodeIndex < 0 || episodeIndex > series[i].getEpisodes().length;
                if (episodeNotExisted) {
                    System.out.println("This episode has not been uploaded yet ⤩⤩");
                } else {
                    Account onlineAccount = accounts[lastAccountIndexLoggedIn];
                    Series[] userSEriesList = onlineAccount.getSeriesWatched();
                    for (int j = 0; j <userSEriesList.length ; j++) {
                        if (userSEriesList[i] == null) {
                            Episode[] episodesList = new Episode[seriesChosen.getEpisodes().length];
                            episodesList[0] = seriesChosen.getEpisodes()[episodeIndex - 1];
                            userSEriesList[i] = new Series(currentSeriesName, episodesList);
                            userSEriesList[i].setLastEpisodeWatched(episodesList[0]);

                            break;
                        }
                    }
                        }
                break;
            }
        }
    }
            private int askUserWhichNextEpisodeHeWouldWatch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which Episode you would Watch?");
        System.out.println(">");
        return scanner.nextInt();


    }
            private void remindUserLastEpisodeHeWatched(String seriesUserWouldWatch) {
        Account onlineAccount = accounts[lastAccountIndexLoggedIn];
        Series[] userSeriesList = onlineAccount.getSeriesWatched();
        for (int i = 0; i < userSeriesList.length ; i++) {
            if (userSeriesList[i] != null) {
                String currentSeriesName = userSeriesList[i].getName();
                if (currentSeriesName.equals(seriesUserWouldWatch)) {
                    Series currentSeries = userSeriesList[i];
                    System.out.println(" ✔. You already Started watch this Series");
                    System.out.println("last Episode you came to: "+currentSeries.getLastEpisodeWatched().getName());
                    currentSeries.getLastEpisodeWatched().episodeDetails();

                    break;
                }
            }

        }
    }
            private int isUserStartWatchThisSeries(String seriesUserWouldWatch) {
        int seriesIndex = -1;
        Account onlineAccount = accounts[lastAccountIndexLoggedIn];
        Series[] userSeriesList = onlineAccount.getSeriesWatched();
        for (int i = 0; i < userSeriesList.length; i++) {
            if (userSeriesList[i] != null ) {
                String currentSeriesName = userSeriesList[i].getName();
                if (currentSeriesName.equals(seriesUserWouldWatch)) {
                    seriesIndex = i;
                    return seriesIndex;
                }
            }
        }
        return seriesIndex;
    }
            private void openAnArraySeriesList() {
            accounts[lastAccountIndexLoggedIn].setSeriesWatched(new Series[this.series.length]);
        }
            private boolean checkIfSeriesExisted(String seriesUserWouldWatch) {
        boolean isSeriesExisted = false;
        for (int i = 0; i < series.length ; i++) {
            String currentSeriesName = series[i].getName();
            if (currentSeriesName.equals(seriesUserWouldWatch)) {
                isSeriesExisted = true;
                break;
            }
        }
        if (!isSeriesExisted)
        System.out.println("There are no such series ⧝");

        return isSeriesExisted;
    }
            private boolean isUserStartWatchAnySeries() {
        Account onlineAccount = accounts[lastAccountIndexLoggedIn];
                return onlineAccount.getSeriesWatched() != null;


    }



    public boolean login() {
        Scanner scanner = new Scanner(System.in);
        if (accounts == null) {
            System.out.println("there is no account Registered yet ‼ ‽\n");
            return false;
        } else {
            System.out.println("userName\n>");
            String userName = scanner.next();
            System.out.println("Password\n>");
            String password = scanner.next();
            if (isAccountExisted(userName, password)) {
                return true;
            } else
                return false;
        }

    }
        private boolean isAccountExisted(String userName, String password) {
        boolean accountExist;
        for (int i = 0; i < accounts.length; i++) {
            String currentUsername = accounts[i].getUserName();
            String currentPassword = accounts[i].getPassword();
            accountExist = currentUsername.equals(userName) && currentPassword.equals(password);
            if (accountExist) {
                this.lastAccountIndexLoggedIn = i;
                return true;
            }
        }
        return false;
    }

    public void addAccount() {
        if (this.accounts == null) {
            this.accounts = new Account[1];
            Account newAccount = openNewAccount();
            this.accounts[0] = newAccount;
        } else {
            Account[] biggerList = new Account[this.accounts.length + 1];
            for (int i = 0; i < this.accounts.length; i++) {
                biggerList[i] = this.accounts[i];
            }
            this.accounts = biggerList;
            this.accounts[accounts.length - 1] = openNewAccount();


        }

    }
    private Account openNewAccount() {
        Scanner scanner = new Scanner(System.in);
        if (this.accounts.length == 1) {
            System.out.println("username:\n>");
            String userName = scanner.next();
            System.out.println("Enter your Password:\n>");
            String password = scanner.next();
            return new Account(userName, password);
        } else {
            System.out.println("username:\n>");

            String userName = checkIfUserNameIsExisted(scanner.next());

            System.out.println("Enter your Password:\n>");
            String password = scanner.next();
            scanner.nextLine();
            return new Account(userName, password);
        }
    }
    private String checkIfUserNameIsExisted(String userName) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < accounts.length - 1; i++) {
            String currentUserName = accounts[i].getUserName();
            if (currentUserName.equals(userName)) {
                System.out.println("this username is used,try another username\n>");
                userName = checkIfUserNameIsExisted(scanner.next());
            }
        }
        return userName;
    }









    private void printSeriesNames() {
        System.out.println("                            ► Series List ◄ ");
        for (int i = 0; i < this.series.length; i++) {
            String seriesName = series[i].getName();
            System.out.println("➪" + seriesName);
        }
        System.out.println();
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public Series[] getSeries() {
        return series;
    }

    public void setSeries(Series[] series) {
        this.series = series;
    }

    public Account[] getAccounts() {
        return accounts;
    }

    public void setAccounts(Account[] accounts) {
        this.accounts = accounts;
    }

    public int getLastAccountIndexLoggedIn() {
        return lastAccountIndexLoggedIn;
    }

    public void setLastAccountIndexLoggedIn(int lastAccountIndexLoggedIn) {
        this.lastAccountIndexLoggedIn = lastAccountIndexLoggedIn;
    }
}