package Commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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

        String pathAnketa = "src" + separator + "data" + separator + "anketa.txt";
        String pathProfils = "src" + separator + "data" + separator + "profils.json";

        File anketa = new File(pathAnketa);
        File profils = new File(pathProfils);

        try {

            Scanner inputFile = new Scanner(anketa);
            Scanner console = new Scanner(System.in);

            Map<String, String> answers = new LinkedHashMap<>();

            int questionNumber = 0;

            while (inputFile.hasNextLine()) {

                String inputLine = inputFile.nextLine();

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

                String answer = console.nextLine();

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

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            List<Map<String, String>> profilsList = new ArrayList<>();

            if (profils.exists() && profils.length() > 0) {

                Scanner jsonReader = new Scanner(profils);
                StringBuilder json = new StringBuilder();

                while (jsonReader.hasNextLine()) {
                    json.append(jsonReader.nextLine());
                }

                jsonReader.close();

                Type type = new TypeToken<List<Map<String, String>>>() {}.getType();

                List<Map<String, String>> oldProfils =
                        gson.fromJson(json.toString(), type);

                if (oldProfils != null) {
                    profilsList.addAll(oldProfils);
                }
            }

            profilsList.add(answers);

            FileWriter writer = new FileWriter(profils);

            gson.toJson(profilsList, writer);

            writer.close();
            inputFile.close();

            System.out.println("Профиль успешно сохранён!");

        } catch (FileNotFoundException e) {

            System.out.println("Файл не найден");

        } catch (IOException e) {

            System.out.println("Ошибка при записи файла");
        }
    }
}