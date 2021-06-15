import java.util.Scanner;

public class Data {
    private int day;
    private int month;
    private int year;

    public Data(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    public Data creating() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the account creation date: ");
        System.out.println("Month:\n>");
        int userMonth = scanner.nextInt();
        boolean invalidMonth = userMonth < 1 || userMonth > Def.MONTHS;
        if (invalidMonth) {
            do {
                System.out.println("invalid✖");
                System.out.println("1 ▬ 12");
                System.out.println("Month\n>");
                userMonth = scanner.nextInt();
                invalidMonth = userMonth < 1 || userMonth > Def.MONTHS;
            } while (invalidMonth);

        }

        System.out.println("Day:\n>");
        int userDay = scanner.nextInt();
        boolean errorDay = userDay < 1 || userDay > Def.DAYS;
        if (errorDay) {
            do {
                System.out.println("invalid✖");
                System.out.println("1 ▬ 30");
                System.out.println("Day:\n>");
                userDay = scanner.nextInt();
                errorDay = userDay < 1 || userDay > Def.DAYS;
            } while (errorDay);

        }

        return new Data(userDay, userMonth, Def.CURRENT_YEAR);

    }


    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Data{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}
