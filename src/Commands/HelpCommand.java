package Commands;
import model.ConsoleWriter;
public class HelpCommand implements Command {
    @Override
    public String getName(){
        return "/help";
    }
    @Override
    public void execute(){
        ConsoleWriter print = new ConsoleWriter();
        print.output("Доступные команды:");
        print.output("/start-заполнение вашего профиля");
        print.output("/view-посмотреть анкеты всех пользователей");
    }
}
