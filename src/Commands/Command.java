package Commands;
import model.ConsoleWriter;

public interface Command {
    String getName();

    void execute(String userId, ConsoleWriter print)
            throws Exception;

}
