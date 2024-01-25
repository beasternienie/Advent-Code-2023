import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AdventCode {

    public static ArrayList<String> linesArray = new ArrayList<>();

    public static void main(String[] args) {

        // Create list of file lines. //

        getLinesFromFile(new File ("input-001.txt"));   // Create list.
        System.out.println("Total entries in list array: " + linesArray.size());    // Confirm data.
        System.out.println("|--------------------|");
        System.out.println(getCumulativeSum(linesArray)); // Get the cumulative sum of all first and last digits on each line.

    }

    // Get the line of text from a file. Puts lines in a List.//

    public static void getLinesFromFile(File inputFile){
        String line = "";
        try{
            Scanner reader = new Scanner(inputFile);    // Get the file.
            while(reader.hasNext()) {
                line = reader.nextLine();   // Read the line if possible.
                linesArray.add(line);       // Add line to the array of lines.
            }
        } catch (FileNotFoundException e){
            System.out.println("File was not found. Check the file name.");
            return;
        }
        System.out.println("File reading completed.");
    }

    // Check for numbers as words or numbers as digits. Returns list of matching digits //

    public static List<String> getNumbersFromString(String line) {

        String[] digits = {"one", "two", "three", "four", "five", "six", "seven",
                "eight", "nine", "1", "2", "3", "4", "5", "6", "7", "8", "9"}; // Numbers to check for match.
        List<String> digitsInString = new LinkedList<>();

        // Place all the matching digits in the list in order they occur. //

        for (int currentIndex = 0; currentIndex < line.length(); currentIndex++){
            // Iterate until the entire line has been checked. //
            for (String digit : digits) {
                // For digits to find... //
                // If the digit is found at this index... //
                if (line.indexOf(digit, currentIndex) == currentIndex) {
                    digitsInString.add(digit);  // Add digit to list.
                    break; // If a digit is found break out of loop early.
                }
            }
        }

        // Confirm check. //
        System.out.println("Line has been checked for matches.");
        System.out.println("Current line: " + line);
        System.out.println("Current digits in line: " + digitsInString);
        return digitsInString;

    } // End of method().

    // Sum the first and last digits from a list of digits. //

    public static int sumFirstAndLast(List<String> lineDigits){
        int firstDigit = 0;
        int lastDigit = 0;
        try {
            firstDigit = Integer.parseInt(lineDigits.getFirst());   // Try to parse the value.
        } catch (NumberFormatException e){
            // If the digit is a word... convert to int. //
            switch (lineDigits.getFirst()){
                case "one" -> firstDigit = 1;
                case "two" -> firstDigit = 2;
                case "three" -> firstDigit = 3;
                case "four" -> firstDigit = 4;
                case "five" -> firstDigit = 5;
                case "six" -> firstDigit = 6;
                case "seven" -> firstDigit = 7;
                case "eight" -> firstDigit = 8;
                case "nine" -> firstDigit = 9;
            }
        }
        try {
            lastDigit = Integer.parseInt(lineDigits.getLast()); // Try to parse the value.
        } catch (NumberFormatException e){
            // If the digit is a word... convert to int. //
            switch (lineDigits.getLast()){
                case "one" -> lastDigit = 1;
                case "two" -> lastDigit = 2;
                case "three" -> lastDigit = 3;
                case "four" -> lastDigit = 4;
                case "five" -> lastDigit = 5;
                case "six" -> lastDigit = 6;
                case "seven" -> lastDigit = 7;
                case "eight" -> lastDigit = 8;
                case "nine" -> lastDigit = 9;
            }
        }
        System.out.println(firstDigit + " + " + lastDigit + " ...");
        String first = String.valueOf(firstDigit);  // Convert first digit to string.
        String last = String.valueOf(lastDigit);    // Convert last digit to string.
        String concat = first + last;   // Concatenate the first and last digits.
        return Integer.parseInt(concat);    // Return the concatenated value parsed as int.
    }

    // Read the lines of a list and then get the sum of the first and last digits. //

    public static int getCumulativeSum(ArrayList<String> lines){

        int sum = 0;

        // Read each line and get its first and last sum. //
        for (String line : lines){
            List<String> numbers = getNumbersFromString(line); // Get the numbers.
            sum += sumFirstAndLast(numbers); // Sum the first and last digits and add to overall sum.
        }
        System.out.println("File completed.");
        return sum; // Return the sum of all values.
    }


}
