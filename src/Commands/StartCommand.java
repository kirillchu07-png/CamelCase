package Commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.AnketaParser;
import model.ConsoleReader;
import model.JsonWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StartCommand implements InterfaceCommand {
    @Override
    public String getName() {
        return "/start";
    }

    @Override
    public void execute() {

        String separator = File.separator;
        String pathResources="src" + separator + "resources" + separator;
        String pathAnketa1 = pathResources + "anketa1.txt";

        String pathAnketa2 = pathResources + "anketa2.txt";

        String pathProfils = pathResources + "profils.json";

        ConsoleReader console = new ConsoleReader();
        JsonWriter jsonWriter = new JsonWriter();

        Map<String, String> answers = new LinkedHashMap<>();

        int questionNumber = 0;

        try {

            String[] files = {pathAnketa1, pathAnketa2};

            for (String path : files) {

                AnketaParser parser = new AnketaParser(path);

                try {

                    while (parser.hasNextLine()) {

                        String inputLine = parser.nextLine();

                        if (inputLine.equals("Давай создадим тебе профиль!!!")) {
                            continue;
                        }

                        if (inputLine.equals("Теперь я задам тебе несколько вопросов по которым я смогу(ну прям сейчас не смогу) подобрать тебе партнера!!!")) {
                            continue;
                        }

                        if (inputLine.equals("4. Потом сюда добавим возможность загружать фото.")) {
                            continue;
                        }

                        System.out.println(inputLine);

                        String answer = console.readLine();

                        if (inputLine.equals("1. Как тебя зовут?")) {

                            answers.put("name", answer);

                        } else if (inputLine.equals("2. Какого вы пола пол М/Ж?")) {

                            answers.put("age", answer);

                        } else if (inputLine.equals("3. Скажите пару слов о себе.")) {

                            answers.put("information", answer);

                        } else {

                            questionNumber++;

                            answers.put(String.valueOf(questionNumber), answer);
                        }
                    }

                } finally {
                    parser.close();
                }
            }

            jsonWriter.save(answers, pathProfils);

            System.out.println("Профиль успешно сохранён!");

        } catch (IOException e) {

            System.out.println("Ошибка при работе с файлом: "
                    + e.getMessage());
        }
    }

}
