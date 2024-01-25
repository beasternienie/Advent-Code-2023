import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Mai {
    public static void main(String[] args) {

        File myFile = new File("input-001.txt"); // Input the file.
        System.out.println("The calibration number is: " + readFile(myFile)); // Return the puzzle answer.


    } // End of main().

    /*
    Method to read a file and return the sum of the first and last digits
    of each line concatenated.
     */
    public static int readFile(File inputFile) {

        int sum = 0; // Initialize sum value.

        // Get file and read the line //

        try {

            // Read the input file. //

            Scanner fileReader; // Scanner Object.
            fileReader = new Scanner(inputFile);

            // While there are lines in the file... //

            do {

                // Get the next line from the file. //

                String currentLine = fileReader.nextLine(); // Get line.
                System.out.println("Current line: " + currentLine);

                // Get the characters from the line and convert to a character array. //

                char[] characters = currentLine.toCharArray(); // Create an array of characters.
                System.out.println("Characters: " + (Arrays.toString(characters))); // Test.

                // Create a string builder to hold the two-digit number. //

                StringBuilder digit = new StringBuilder();

                // For characters in the string, if the character is a digit append to new string. //

                for (char c : characters) {
                    if (Character.isDigit(c)) {
                        digit = new StringBuilder(digit.append(c));
                    }
                }

                // If the length of the digits is one, then double the digit. //

                if (digit.length() == 1){
                    digit.append(digit);
                }

                // If the length of the digits is greater than two then remove the middle digits. //

                if (digit.length() > 2) {
                    digit.delete(1, digit.length() - 1);
                }

                System.out.println("Final digit: " + digit); // Test.

                // Convert the two-digit number into an int. //

                String number = String.valueOf(digit); // Convert the string builder to string.
                int calibrationNum = Integer.parseInt(number); // Convert the string to an int.

                // Add the number to the sum. //

                sum = sum + calibrationNum;
                System.out.println("Current sum: " + sum); // Test.

            } while (fileReader.hasNextLine());

        } catch(FileNotFoundException e) {
            // If file does not exist. //
            System.out.println("Something went wrong.");
            System.exit(1);
        }

        return sum; // Return the final sum of all the digits.

    }

} // End of file.

