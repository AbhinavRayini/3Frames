import java.io.*;
import java.util.*;

public class Word_Count_Program2 {
    public static void main(String[] args) {
        try {
            
            Map<String, Integer> wordCount = new HashMap<>();
            List<String> sortedWords = new ArrayList<>();

            
            BufferedReader reader = new BufferedReader(new FileReader("large_file.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                
                String[] words = line.split("\\s+");

                
                for (String word : words) {
                    word = word.toLowerCase(); 
                    word = word.replaceAll("[^a-zA-Z]", ""); 
                    if (!word.isEmpty()) {
                        wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                    }
                }
            }
            reader.close();

            
            List<Map.Entry<String, Integer>> entries = new ArrayList<>(wordCount.entrySet());
            entries.sort(Map.Entry.comparingByKey());

            
            for (Map.Entry<String, Integer> entry : entries) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
                sortedWords.add(entry.getKey());
            }

            
            String searchQuery = "search"; 
            int threshold = 2; 
            for (String word : sortedWords) {
                if (getLevenshteinDistance(word, searchQuery) <= threshold) {
                    System.out.println("Fuzzy Match: " + word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    private static int getLevenshteinDistance(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
