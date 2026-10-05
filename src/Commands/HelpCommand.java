package Commands;

public class HelpCommand implements InterfaceCommand {
    @Override
    public String getName(){
        return "/help";
    }
    @Override
    public void execute(){
        System.out.println("Доступные комманды:");
        System.out.println("/start-заполнение вашего профиля");
        System.out.println("/view-посмотреть анкеты всех пользователей");
    }
}
