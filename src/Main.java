import Commands.HelpCommand;
import Commands.InterfaceCommand;
import Commands.StartCommand;
import Commands.ViewCommand;

import java.util.Scanner;

class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        InterfaceCommand startCommand = new StartCommand();
        InterfaceCommand helpCommand = new HelpCommand();
        InterfaceCommand viewCommand = new ViewCommand();
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