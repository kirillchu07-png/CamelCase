import Commands.HelpCommand;
import Commands.Command;
import Commands.StartCommand;
import Commands.ViewCommand;

import java.util.Scanner;

class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        Command startCommand = new StartCommand();
        Command helpCommand = new HelpCommand();
        Command viewCommand = new ViewCommand();
        if (command.equals(startCommand.getName())) {
            startCommand.execute();
        }
        else if (command.equals(helpCommand.getName())) {
            helpCommand.execute();
        }
        else if (command.equals(viewCommand.getName())) {
            viewCommand.execute();
        }
        else {
            System.out.println("Команда не найдена");
        }
        scanner.close();
    }
}