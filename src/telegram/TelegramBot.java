package telegram;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
public class TelegramBot {
    private final String token;
    private final HttpClient client = HttpClient.newHttpClient();

    public TelegramBot(String token) {
        this.token = token;
    }

    public void sendMessage(long chatId, String text)
            throws Exception {

        String body = "chat_id=" + chatId
                + "&text=" + URLEncoder.encode(
                text, StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.telegram.org/bot"
                        + token + "/sendMessage"))
                .header("Content-Type",
                        "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(
                request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException(response.body());
        }
    }

    public String getUpdates(long offset) throws Exception {

        String url = "https://api.telegram.org/bot" + token
                + "/getUpdates?offset=" + offset
                + "&timeout=25";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException(response.body());
        }

        return response.body();
    }


}
