package Commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class StartCommand implements Command {
    private final Map<String, UserSession> sessions =
            new HashMap<>();

    private final List<String> questions =
            new ArrayList<>();

    private final JsonWriter jsonWriter = new JsonWriter();

    private final String separator = File.separator;

    private final String pathResources =
            "src" + separator + "resources" + separator;

    private final String pathProfils =
            pathResources + "profils.json";

    public StartCommand() throws IOException {

        String pathAnketa1 = pathResources + "anketa1.txt";
        String pathAnketa2 = pathResources + "anketa2.txt";

        String[] files = {pathAnketa1, pathAnketa2};

        for (String path : files) {

            AnketaParser parser = new AnketaParser(path);

            try {
                while (parser.hasNextLine()) {

                    String inputLine = parser.nextLine();

                    if (inputLine.isBlank()) {
                        continue;
                    }

                    if (inputLine.equals(
                            "Давай создадим тебе профиль!!!")) {
                        continue;
                    }

                    if (inputLine.equals(
                            "Теперь я задам тебе несколько вопросов по которым я смогу(ну прям сейчас не смогу) подобрать тебе партнера!!!")) {
                        continue;
                    }

                    if (inputLine.equals(
                            "4. Потом сюда добавим возможность загружать фото.")) {
                        continue;
                    }

                    questions.add(inputLine);
                }

            } finally {
                parser.close();
            }
        }
    }

    @Override
    public String getName() {
        return "/start";
    }

    @Override
    public void execute(String userId, ConsoleWriter print)
            throws Exception {

        if (questions.isEmpty()) {
            print.output(userId, "Вопросы не найдены!");
            return;
        }

        sessions.put(userId, new UserSession());

        print.output(userId,
                "Давай создадим тебе профиль!!!");

        print.output(userId, questions.get(0));
    }

    public boolean hasSession(String userId) {
        return sessions.containsKey(userId);
    }

    public void handleAnswer(
            String userId,
            String answer,
            ConsoleWriter print) throws Exception {

        UserSession session = sessions.get(userId);

        if (session == null) {
            print.output(userId, "Сначала отправь /start");
            return;
        }

        int index = session.getQuestionIndex();

        String inputLine = questions.get(index);

        // Сохраняем ответы с твоими ключами JSON
        if (inputLine.equals("1. Как тебя зовут?")) {

            session.getAnswers().put("name", answer);

        } else if (inputLine.equals(
                "2. Какого вы пола пол М/Ж?")) {

            session.getAnswers().put("age", answer);

        } else if (inputLine.equals(
                "3. Скажите пару слов о себе.")) {

            session.getAnswers().put("information", answer);

        } else {

            int number = session.nextQuestionNumber();

            session.getAnswers().put(
                    String.valueOf(number), answer);
        }

        session.nextQuestion();

        // Все вопросы закончились
        if (session.getQuestionIndex() >= questions.size()) {

            jsonWriter.save(
                    session.getAnswers(),
                    pathProfils
            );

            sessions.remove(userId);

            print.output(userId,
                    "Профиль успешно сохранён!");

            return;
        }

        // Следующий вопрос
        print.output(
                userId,
                questions.get(session.getQuestionIndex())
        );
    }


}
