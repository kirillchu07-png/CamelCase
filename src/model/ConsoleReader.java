package model;

import java.util.Scanner;

public class ConsoleReader {
    private Scanner console = new Scanner(System.in);

    public String readLine() {
        return console.nextLine();
    }

}
