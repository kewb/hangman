import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hangdude {

    static char[] swap(String str, int i, int j) {
        char ch[] = str.toCharArray();
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
        return ch;
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            Random rand = new Random();

            String apiUrl = "https://random-word-api.herokuapp.com/word";

            // Create a URL object
            URL url = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response from the API
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String response = reader.readLine();

                // Use a regular expression to extract the word from the response
                String regex = "\\[\"([^\"]*)\"\\]";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(response);

                if (matcher.find()) {
                    String randomWord = matcher.group(1);
                    System.out.println("Random Word: " + randomWord);

                    int length = randomWord.length();
                    char[] charArray = randomWord.toCharArray();

                    int tries = 0;
                    int maxTries = 7;
                    char[] newArr = new char[length];

                    for (int i = 0; i < length; i++) {
                        newArr[i] = '-';
                    }

                    while (!Arrays.equals(newArr, charArray) && maxTries > 0) {
                        System.out.println("Enter a letter:");
                        char letter = scanner.nextLine().charAt(0);

                        boolean found = false;
                        for (int i = 0; i < charArray.length; i++) {
                            if (charArray[i] == letter && !found) {
                                System.out.println(letter + " is at " + i);
                                found = true;
                                System.out.println(maxTries);
                            }
                        }

                        for (int i = 0; i < length; i++) {
                            if (charArray[i] == letter) {
                                newArr[i] = letter;
                            }
                        }

                        for (char item : newArr) {
                            System.out.print(item + " ");
                        }
                        System.out.println();

                        if (!found) {
                            maxTries--;
                            System.out.println("Try again, lives left: " + maxTries);
                        }
                    }

                    if (Arrays.equals(newArr, charArray)) {
                        System.out.println("Congrats, you survived");
                    } else {
                        System.out.println("You died");
                    }
                } else {
                    System.out.println("Failed to extract the word from the response.");
                }
            } else {
                System.out.println("Failed to fetch random word. Response Code: " + responseCode);
            }

            // Close the connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
