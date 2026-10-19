package core;
import Commands.Command;
import Commands.StartCommand;
import model.ConsoleWriter;
import model.IncomingMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class CommandDispatcher {
    private final Map<String, Command> commands =
            new HashMap<>();

    private final StartCommand startCommand;
    private final ConsoleWriter print;

    public CommandDispatcher(
            List<Command> commandList,
            StartCommand startCommand,
            ConsoleWriter print) {

        this.startCommand = startCommand;
        this.print = print;

        for (Command command : commandList) {
            commands.put(command.getName(), command);
        }
    }

    public void dispatch(IncomingMessage message)
            throws Exception {

        String userId = message.userId();
        String text = message.text();

        if (commands.containsKey(text)) {

            commands.get(text).execute(userId, print);

        } else if (text.startsWith("/")) {

            print.output(userId, "Команда не найдена");

        } else if (startCommand.hasSession(userId)) {

            startCommand.handleAnswer(userId, text, print);

        } else {

            print.output(userId,
                    "Отправь /start для заполнения анкеты");
        }
    }
}