import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
WaitForIt - Takes an input file containing time and distance values and calculates the number of
possible win conditions for a given distance and time. For each ms a button is held, the distance
travelled per ms increases by 1.
*/
public class WaitForIt {

    public static void main(String[] args) {

        // Parse races. //
        try {
            LinkedHashMap<Integer, Integer> raceData = parseRaces();
            // Calculate number of wins.
            ArrayList<Integer> winList = new ArrayList<>();
            for (Integer key : raceData.keySet()){
                int wins = calculateWins(key, raceData.get(key));
                System.out.println(wins);
                winList.add(wins);
            }
            int margin = winList.getFirst();
            for(int n : winList.subList(1, winList.size())){
               margin = margin * n;
            }
            System.out.println("Margin:" + margin);
        } catch(FileNotFoundException e){
            System.out.println("File not found.");
        }

        // Parse single race.
        try{
            ArrayList<Long> race = parseRace();
            System.out.println("Time: " + race.get(0) + " Distance: " + race.get(1));
            long wins = calculateWins(race.get(0), race.get(1));
            System.out.println("Wins: " + wins);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

//        // Test calculating distances travelled.
//        System.out.println(calculateWins(7 , 9));

    }

    /*
    Parse the data to find the race times and goal distances. Returns a hash map.
     */
    private static LinkedHashMap<Integer, Integer> parseRaces() throws FileNotFoundException{

        LinkedHashMap<Integer, Integer> races = new LinkedHashMap<>();
        Pattern number = Pattern.compile("\\d+");
        Matcher matcher;
            // Read each line.
            Scanner reader = new Scanner(new File("input.txt"));
            String time = reader.nextLine().strip();
            String distance = reader.nextLine().strip();
            LinkedList<Integer> times = new LinkedList<>();
            LinkedList<Integer> distances = new LinkedList<>();
            // Find number matches.
            matcher = number.matcher(time);
            while (matcher.find()){
                times.add(Integer.valueOf(matcher.group()));
            }
            matcher = number.matcher(distance);
            while (matcher.find()){
                distances.add(Integer.valueOf(matcher.group()));
            }
            // Create map.
            for (int i = 0; i < times.size(); i++){
                races.put(times.get(i), distances.get(i));
            }
            // Confirm.
            for (Integer key : races.keySet()) {
                System.out.println("Time:" + key + " Distance: " + races.get(key));
            }

            return races;

    }

    /*
    Parse the data to get the time and goal distance. Returns a list with the two values.
     */
    private static ArrayList<Long>parseRace() throws FileNotFoundException{
        ArrayList<Long> race = new ArrayList<>();
        Scanner reader = new Scanner(new File("input.txt"));
        String time = reader.nextLine().strip();
        time = time.replaceAll("\\D", "");
        String distance = reader.nextLine().strip();
        distance = distance.replaceAll("\\D", "");
        race.add(Long.valueOf(time));
        race.add(Long.valueOf(distance));
        return race;
    }
    /*
    Given a maximum time, how many milliseconds could you hold the button down and still
    reach the goal distance in the remaining time.
    Returns the number of possible win conditions.
     */
    private static int calculateWins(Integer time, Integer goalDistance){
        int distanceTravelled = 0;
        int winCount = 0;
        // Where i = number of milliseconds held down.
        for(int i = 0; i <= time; i++){
            distanceTravelled = (time - i) * i;
//            System.out.println("Held: " + i + " Time remaining: "+ (time - i) + " Distance: " + distanceTravelled);
            if (distanceTravelled >= goalDistance){
                winCount ++;
            }
        }
        return winCount;
    }

    private static long calculateWins(Long time, Long goalDistance){
        long distanceTravelled;
        long winCount = 0L;
        // Where i = number of milliseconds held down.
        for(long i = 0L; i <= time; i++){
            distanceTravelled = (time - i) * i;
//            System.out.println("Held: " + i + " Time remaining: "+ (time - i) + " Distance: " + distanceTravelled);
            if (distanceTravelled >= goalDistance){
                winCount ++;
            }
        }
        return winCount;
    }

}
