import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class Game {
    // set the RESET and the SQUARE
    public static final String RESET = "\u001B[0m";
    public static final String SQUARE = "\u25A0";
    // initalize the colors and the codes for the specific colors
    public static String[] colors = { "red", "green", "yellow", "blue", "purple", "gray" };
    // make sure that the codes are lined up with the colors
    public static String[] codes = { "\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B[35m",
            "\u001B[37m" };
    // set the length to 4 so that the colors cant exceed that
    public static int LENGTH = 4;

    public static void main(String[] args) {
        String[] answer = { "red", "green", "blue", "gray" }; // setting the answer colors and it will generate it in a
                                                              // different color
        for (int i = 0; i < answer.length; i++) {
            answer[i] = colors[(int) (Math.random() * colors.length)];
        }

        // System.out.println(Arrays.toString(answer)); // since the answer is in an
        // array we used the toString method
        Scanner scanner = new Scanner(System.in); // for the user to input the colors
        String allCorrect = ""; // originally set to am empty string then fix it in the fofr loop
        for (int i = 0; i < LENGTH; i++) {
            allCorrect += "h";
        }
        System.out.println("Do you want to enter cheat mode? (y/n)");
        while (true) {

            boolean cheatMode = false;

            String cheatInput = scanner.next().toLowerCase();

            if (cheatInput.equals("y")) {
                cheatMode = true;
                System.out.println("Cheat mode activated. The answer is: " +
                        Arrays.toString(answer));
                System.out.println("Enter your guess: ");
            } else if (cheatInput.equals("n")) {
                cheatMode = false;
                System.out.println("Enter your guess: ");
            } else {
                System.out.println("Invalid input. Please enter correct input");
                continue; // Restart the loop if the input is invalid
            }

            // System.out.println("Enter in your guess");
            String[] guess = new String[LENGTH]; // guess is an array taking in the LENGTH which is 4
            boolean[] placed = new boolean[LENGTH]; // boolean expression to find the answer
            String feedback = ""; // set it as an empty string

            for (int i = 0; i < guess.length; i++) { // going through the guess array
                guess[i] = scanner.next();
                // if (cheatMode) {
                // System.out.print(getCode(answer[i]) + SQUARE + RESET); // print the actual
                // answer
                // } else {
                // System.out.print(getCode(guess[i]) + SQUARE + RESET); // printing the guess
                // }

                System.out.print(getCode(guess[i]) + SQUARE + RESET); // printing the guess
                boolean place = false; // setting it to false
                for (int j = 0; j < answer.length; j++) { // going through the answer array
                    if (guess[i].equals(answer[j]) && i == j && place == false && placed[i] == false) {
                        // saying that if the guess is the same as the answer then it is correct
                        place = true;
                        feedback += "h";
                        placed[i] = true;

                    }

                }
                for (int j = 0; j < answer.length; j++) { // going through the answer array
                    if (guess[i].equals(answer[j]) && place == false && placed[j] == false) {
                        // saying that if the guess is the same as the answer then it is correct
                        place = true;
                        feedback += "p";
                        placed[j] = true;
                    }
                }

            }
            System.out.println();
            System.out.println(shuffle(feedback)); // printing the feedback
            if (feedback.equals(allCorrect)) { // saying that if the feedback is the same as the answer then it is
                                               // correct
                System.out.println("correct");
                break; // break out of the while loop
            }

        }
    }

    public static String getCode(String color) { // getting the code for the specific color
        for (int i = 0; i < colors.length; i++) { // going through the colors array
            if (colors[i].equals(color)) { // saying that if the color is in the colors array
                return codes[i]; // return the code for the specific color
            }
        }
        return "not found"; // if not then just return not found
    }

    public static String shuffle(String str) { // shuffling the string
        ArrayList<String> list = new ArrayList<String>(); // creating an ArrayList called list to store the string
        for (int i = 0; i < str.length(); i++) {
            list.add(str.substring(i, i + 1)); // adding the characters to the list

        }
        Collections.shuffle(list); // shuffling the list
        String result = ""; // set it
        for (int i = 0; i < list.size(); i++) { // going through the list
            result += list.get(i); // adding the characters to the result
        }
        return result; // returning the result
    }

}
