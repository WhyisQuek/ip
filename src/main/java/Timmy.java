import java.util.Scanner;

public class Timmy {
    public Timmy(){}

    public static void main(String[] args) {
        new Timmy().run();
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        boolean isExit = false;
        String welcome = """
                        ____________________________________________________________
                        Hello! I'm Timmy
                        What can I do for you?
                        ____________________________________________________________""";
        String bye = """
                        ____________________________________________________________
                        Bye. Hope to see you again soon!
                        ____________________________________________________________""";
        System.out.println(welcome);

        while (!isExit) {
            String inputString = input.nextLine();
            if (inputString.equals("bye")) {
                System.out.println(bye);
                isExit = true;
            }
            else {
                System.out.println("____________________________________________________________");
                System.out.println(inputString);
                System.out.println("____________________________________________________________");
            }
        }

    }
}
