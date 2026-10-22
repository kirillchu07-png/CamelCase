import Commands.Command;
import Commands.StartCommand;
import Commands.HelpCommand;
import Commands.ViewCommand;
import core.CommandDispatcher;
import model.ConsoleReader;
import model.ConsoleWriter;
import model.IncomingMessage;
import telegram.TelegramApiClient;
import telegram.TelegramReader;
import telegram.TelegramWriter;
import java.util.List;
class Main {
    public static void main(String[] args)
            throws Exception {

        String token = System.getenv("BOT_TOKEN");

        if (token == null || token.isBlank()) {
            throw new IllegalStateException(
                    "Не указан BOT_TOKEN"
            );
        }

        TelegramApiClient api =
                new TelegramApiClient(token);

        ConsoleReader console =
                new TelegramReader(api);

        ConsoleWriter print =
                new TelegramWriter(api);

        StartCommand startCommand =
                new StartCommand();
        ViewCommand viewCommand =
                new ViewCommand();
        HelpCommand helpCommand =
                new HelpCommand();

        List<Command> commands = List.of(
                startCommand,
                helpCommand,
                viewCommand
        );

        CommandDispatcher dispatcher =
                new CommandDispatcher(
                        commands,
                        print
                );

        System.out.println("Telegram-бот запущен!");

        while (true) {

            try {

                IncomingMessage message =
                        console.readLine();

                dispatcher.dispatch(message);

            } catch (Exception e) {

                e.printStackTrace();

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
}