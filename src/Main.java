import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        NetflixSystem netflix = initNetflixSeries();
        while (true) {
            switch (createOrLogin()) {
                case Def.CREATE_ACCOUNT:
                    netflix.addAccount();
                    break;

                case Def.LOGIN:
                    if (netflix.login()) {
                        netflix.setStop(true);
                        while (netflix.isStop())
                                netflix.actions(optionsMenu());
                    } else
                        System.out.println(" ✕ ✖ Account did not  found ✕ ✖");
                    break;
            }
        }


    }




    private static int optionsMenu() {
        int optionSelected = -1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("        Options menu:");
        System.out.println("1-Series List");
        System.out.println("2-View the list of series you started watching");
        System.out.println("3-View subscription details");
        System.out.println("4- Select a series to watch");
        System.out.println("5-Exit");
        System.out.println(">");
        try {
             optionSelected = scanner.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  checkCorrectChosen(optionSelected, 5);
    }
    private static int createOrLogin() {
        int userNumber = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 : Create Account");
        System.out.println("2 : Login");
        System.out.println(">");
        try {
            userNumber = scanner.nextInt();
        } catch (InputMismatchException e) {
           e.printStackTrace();
        }
        return checkCorrectChosen(userNumber, 2);
    }
    private static int checkCorrectChosen(int userNumber, int limit) {
        Scanner scanner = new Scanner(System.in);
        boolean invalid  = userNumber < 1 || userNumber > limit ;
        if (invalid)
            do {
                System.out.println("invalid Selection");
                System.out.println(">");
                    userNumber = scanner.nextInt();
                    scanner.nextLine();

                invalid  = userNumber < 1 || userNumber > limit;
            } while (invalid);
        return userNumber;
    }
    private static NetflixSystem initNetflixSeries() {


        Data broadcastTimeSerpent1 = new Data(20, 6, 2021);
        Data broadcastTimeSerpent2 = new Data(22, 6, broadcastTimeSerpent1.getYear());
        Data broadcastTimeSerpent3 = new Data(24, 6, broadcastTimeSerpent2.getYear());

        Episode[] serpentEpisodes = {
                new Episode("The Serpent episode 1", "brings a baseline into Charles Sobhraj’s sinister world.", broadcastTimeSerpent1),
                new Episode("The Serpent episode 2", "presents the power of indoctrination as the focus shifts to Marie-Andrée", broadcastTimeSerpent2),
                new Episode("The Serpent episode 3", "shows how Charles used highly manipulative tactics to make his victims feel trapped.", broadcastTimeSerpent3)

        };


        Data broadcastTimeSnabbaCash1 = new Data(28, 7, 2021);
        Data broadcastTimeSnabbaCash2 = new Data(broadcastTimeSnabbaCash1.getDay() + 1, broadcastTimeSnabbaCash1.getMonth(), broadcastTimeSnabbaCash1.getYear());
        Data broadcastTimeSnabbaCash3 = new Data(broadcastTimeSnabbaCash2.getDay() + 1, broadcastTimeSnabbaCash1.getMonth(), broadcastTimeSnabbaCash1.getYear());

        Episode[] snabbaCashEpisodes = {
                new Episode("Snabba Cash episode 1", "begins with Leya psyching herself up and arriving in her office. Walking purposefully", broadcastTimeSnabbaCash1),
                new Episode("Snabba Cash episode 2", "Salim ringing Leya. He wants to see her again but her silence speaks volumes.", broadcastTimeSnabbaCash2),
                new Episode("Snabba Cash episode 3", "Leya taking steps toward buying out Marcus.", broadcastTimeSnabbaCash3)

        };
        Data xyz1 = new Data(26, 6, 2021);
        Data xyz2 = new Data(xyz1.getDay() + 1, xyz1.getMonth(), broadcastTimeSnabbaCash1.getYear());
        Data xyz3 = new Data(xyz1.getDay() + 1, xyz1.getMonth(), broadcastTimeSnabbaCash1.getYear());

        Episode[] xyzEpisodes = {
                new Episode("x", "no thing", xyz1),
                new Episode("y", "bla bla bla", xyz2),
                new Episode("z", "neverMind", xyz3)
        };


        Series[] series = {
                new Series("a", serpentEpisodes),
                new Series("b", snabbaCashEpisodes),
                new Series("c", xyzEpisodes)

        };
        return new NetflixSystem(series);
    }
}
