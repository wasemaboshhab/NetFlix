
import java.util.Scanner;

public class Account {
    private String userName;
    private String password;
    private Data creation;
    private Data runOut;
    private Series[] seriesWatched;

    public Account(String userName, String password) {
        Scanner scanner = new Scanner(System.in);
        this.userName = userName;
        this.password = password;
        while (!isStrongPassword(this.password)) {
            System.out.println("Enter a Strong Password\n(atleast 6 digits one char, one alphabetic digit)");
            this.password = scanner.next();
        }
        this.creation = new Data(0, 0, 0).creating();
        this.runOut = new Data(creation.getDay(), creation.getMonth(), creation.getYear() + 1);
    }

    private boolean isStrongPassword(String userPassword) {
        if (password.length() < 6) {
            return false;
        }
        char[] passwordChar = userPassword.toCharArray();
        int countDig = 0;
        int countChar = 0;
        for (int i = 0; i < userPassword.length(); i++) {
            if (Character.isDigit(passwordChar[i])) {
                countDig++;
            }
            if (Character.isAlphabetic(passwordChar[i])) {
                countChar++;
            }
        }
        return countDig > 0 && countChar > 0;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Data getCreation() {
        return creation;
    }

    public void setCreation(Data creation) {
        this.creation = creation;
    }

    public Data getRunOut() {
        return runOut;
    }

    public void setRunOut(Data runOut) {
        this.runOut = runOut;
    }

    public Series[] getSeriesWatched() {
        return seriesWatched;
    }

    public void setSeriesWatched(Series[] seriesWatched) {
        this.seriesWatched = seriesWatched;
    }

    public void accountDetails() {
        System.out.println("                        ►Account Details◄");
        System.out.println("☇ created in:\n"+ creation);
        System.out.println("¯¯");
        System.out.println("Subscription is valid until:\n" + runOut);
        System.out.println("¯¯");
        System.out.println("                 ►Viewing status◄");
        countSeriesUserStartedWatching();
        countEpisodesWatchedSoFar();
        countSeriesUserFinishedWatching();
        System.out.println("\n");
    }
        private void countSeriesUserStartedWatching() {
        int countSeriesUserStartWatching = 0;
        if (seriesWatched == null) {
            System.out.println("Series you started watching: " + countSeriesUserStartWatching);
        } else {
            for (int i = 0; i < seriesWatched.length; i++) {
                if (seriesWatched[i] != null) {
                    countSeriesUserStartWatching++;
                }
            }
            System.out.println("Series you started watching: " + countSeriesUserStartWatching);
        }

    }
        private void countEpisodesWatchedSoFar() {
        int countEpisodeHaveBeenWatched = 0;
        if (isSeriesListAnEmpty()) {
            System.out.println("Episodes have been watched so far :" + countEpisodeHaveBeenWatched);
        } else {
            for (int i = 0; i < seriesWatched.length ; i++) {
                if (seriesWatched[i] != null) {
                    Episode[] seriesEpisodes = seriesWatched[i].getEpisodes();
                    for (int j = 0; j < seriesEpisodes.length; j++) {
                        if (seriesEpisodes[j] != null) {
                            countEpisodeHaveBeenWatched++;
                        }
                    }
                }
            }
            System.out.println("Episodes have been watched so far :" + countEpisodeHaveBeenWatched);
        }
    }
        private void countSeriesUserFinishedWatching() {
        int countSeries_userFinishWatching = 0;
        int checkIfUserWatchedAllTheEpisode;

        if (isSeriesListAnEmpty()) {
            System.out.println("Series you finished watching: " + countSeries_userFinishWatching);
        } else {
            for (int i = 0; i < seriesWatched.length; i++) {
                if (seriesWatched[i] != null) {
                    Episode[] episodes = seriesWatched[i].getEpisodes();
                    if (episodes != null) {
                        checkIfUserWatchedAllTheEpisode = 0;
                        for (int j = 0; j < episodes.length; j++) {
                            if (episodes[j] != null) {
                                checkIfUserWatchedAllTheEpisode++;
//                                countSeries_userFinishWatching++;
                            }
                        }
                        if (checkIfUserWatchedAllTheEpisode == episodes.length) {
                            countSeries_userFinishWatching++;
                        }
                    }
                }
            }
            System.out.println("Series you finished watching: " + countSeries_userFinishWatching);




        }

    }




    public   void seriesWatchedIncludingLastEpisode() {
        System.out.println();
        if (isSeriesListAnEmpty()) {
            System.out.println("You didn't Watched any Series yet ❗❗❗\n");

        } else {
            System.out.println("Series You started watching including last episode where you watched each series ❮ ❯");
            for (int i = 0; i < seriesWatched.length; i++) {
                Series currentSeries = seriesWatched[i];
                if (currentSeries != null) {
                    System.out.println("❮" + currentSeries.getName() + "❯");
                    System.out.println(currentSeries.getLastEpisodeWatched().getName());
                }
            }
        }
    }

    private boolean isSeriesListAnEmpty() {
        return seriesWatched == null;

    }
}
