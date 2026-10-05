package Commands;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
public class StartCommand implements InterfaceCommand {
    @Override
    public String getName(){
        return "/start";
    }
    @Override
    public void execute() {
        String separator = File.separator;
        String pathAnketa = "src"+separator+"data"+separator+"anketa.txt";
        String pathProfils = "src"+separator+"data"+separator+"profils.txt";
        File anketa = new File(pathAnketa);
        File profils = new File(pathProfils);
        try {
            PrintWriter outputFile=new PrintWriter(profils);
            Scanner inputFile = new Scanner(anketa);
            Scanner console =new Scanner(System.in);
            while (inputFile.hasNextLine()){
                String inputLine = inputFile.nextLine();
                if((inputLine.equals("Теперь я задам тебе несколько вопросов по которым я смогу(ну прям сейчас не смогу) подобрать тебе партнера!!!"))||(inputLine.equals("Давай создадим тебе профиль!!!"))||(inputLine.equals("4. Потом сюда добавим возможность загружать фото."))){
                    continue;
                }
                System.out.println(inputLine);
                String answer=console.nextLine();
                outputFile.println(answer);


            }
            outputFile.close();
            inputFile.close();
        }catch (FileNotFoundException e){
            System.out.println("Файл-ы не найден-ы");
        }
    }
}
