package ie.atu.sw;

public class Menu {
    public void start() throws Exception {
        boolean running = true;

        while (running) {
            printHeader();
            printOptions();

            System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
            System.out.print("Select Option [1-5 or ?]>");
            System.out.println();

            // Коміт 2: додамо ConsoleIO і реальний ввід + switch по опціях
            // Поки не зациклюємось.
            running = false;
        }
    }

    private void printHeader() {
        System.out.println(ConsoleColour.WHITE);
        System.out.println("************************************************************");
        System.out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
        System.out.println("*                                                          *");
        System.out.println("*      Comparing Text Documents with Virtual Threads       *");
        System.out.println("*                                                          *");
        System.out.println("************************************************************");
    }

    private void printOptions() {
        System.out.println("(1) Enter Query File");
        System.out.println("(2) Enter Subject File");
        System.out.println("(3) Specify Output File (default: ./out.txt)");
        System.out.println("(4) Execute, Analyse and Report");
        System.out.println("(5) Optional Extras...");
        System.out.println("(?) Quit");
    }
}
