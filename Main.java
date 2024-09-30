import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> commonWords = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        try (Scanner common = new Scanner(new File("/Users/rgl/Downloads/commonWords.txt"))) {
            while (common.hasNext()) {
                String commonWord = common.next().toLowerCase();
                commonWords.add(commonWord);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try (Scanner scanner = new Scanner(new File("/Users/rgl/Downloads/Animal+Farm.txt"))) {
            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase().replaceAll("[^a-z]", "");
                if (!commonWords.contains(word) && !word.isEmpty()) {
                    map.put(word, map.getOrDefault(word, 0) + 1); // getOrDefault returns the value 0 if the word is not present therefore incrementing it to 1
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(map.entrySet());
        wordList.sort((a, b) -> b.getValue().compareTo(a.getValue())); // converts map entry into list before sorting

        System.out.println("Top 5 words with the most occurrences:");
        for (int i = 0; i < Math.min(5, wordList.size()); i++) {
            System.out.println(wordList.get(i).getKey() + ": " + wordList.get(i).getValue());
        }
    }
}