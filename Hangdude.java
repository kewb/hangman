import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class Hangdude {

    static char[] swap(String str, int i, int j) {
        char ch[] = str.toCharArray();
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
        return ch;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        String[] cars = { "Volvo", "Ford", "Mazda", "Maserati", "Mercedes-Benz", "Chevrolet", "Lamborghini", "Bugatti",
                "Ferrari", "Bentley", "Aston Martin", "Land Rover", "Jaguar", "Tesla" };

        int randomNmb = rand.nextInt(cars.length);
        char[] charArray = cars[randomNmb].toCharArray();
        int length = charArray.length;

        System.out.println(cars[randomNmb]);

        int tries = 0;
        int max_tries = 5;
        char[] newArr = new char[length];

        for (int i = 0; i < length; i++) {
            newArr[i] = 'X';
        }
        while (!Arrays.equals(newArr, charArray) && max_tries > 0) {

            System.out.println("Enter a letter:");
            char letter = scanner.nextLine().charAt(0);


            boolean found = false;
            for (int i = 0; i < charArray.length; i++) {
                if (charArray[i] == letter && !found) {
                    System.out.println(letter + " is at " + i);
                    found = true;
                    System.out.println(max_tries);
                }
            }

            for (int i = 0; i < length; i++) {
                if (charArray[i] == letter) {
                    newArr[i] = letter;
                }
            }

            for (char item : newArr) {
                System.out.print(item + "");
            }
            System.out.println();

            if (!found) {
                max_tries = max_tries - 1;

                System.out.println("Try again, lives left: " + max_tries);

            }
        }
        if (Arrays.equals(newArr, charArray)) {
            System.out.println("Congrats, you survived");
        } else {
            System.out.println("You died");
        }

    }
}
