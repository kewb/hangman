import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordGenerator {
    public static String getRandomWord() throws IOException {
        String apiUrl = "https://random-word-api.herokuapp.com/word";
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = reader.readLine();
            reader.close();

            String regex = "\\[\"([^\"]*)\"\\]";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(response);

            if (matcher.find()) {
                return matcher.group(1);
            } else {
                throw new IOException("Failed to extract the word from the response.");
            }
        } else {
            throw new IOException("Failed to fetch random word. Response Code: " + responseCode);
        }
    }
}
