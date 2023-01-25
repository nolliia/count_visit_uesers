import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserCounter {
    public static void main(String[] args) throws IOException {
        // Parse the file name from the command line arguments
        if (args.length == 0) {
            System.err.println("Error: No file name provided");
            return;
        }
        String fileName = args[0];

        // Create a map to store the counts for each source
        Map<String, Integer> sourceCounts = new HashMap<>();

        // Create a map to store the sources for each email and phone combination
        Map<String, Set<String>> userSources = new HashMap<>();

        // Open the file and read it line by line
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Skip the header
            String header = reader.readLine();
            if(header == null) {
                System.out.println("File is empty");
                return;
            }
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into fields
                String[] fields = line.split(",");

                // Skip entries with nullable fields
                if (fields[0].isEmpty() || fields[1].isEmpty() || fields[2].isEmpty()) {
                    continue;
                }

                // Get the email, phone, and source for this entry
                String email = fields[0];
                String phone = fields[1];
                String source = fields[2];

                // Get the set of sources for this user
                Set<String> sources = userSources.get(email + phone);
                if (sources == null) {
                    sources = new HashSet<>();
                    userSources.put(email + phone, sources);
                }

                // Check if this user has been counted for this source before
                if (!sources.contains(source)) {
                    // This is a new source for this user, so increment the count for this source
                    sourceCounts.put(source, sourceCounts.getOrDefault(source, 0) + 1);

                    // Add this source to the set for this user
                    sources.add(source);
                }
            }
        }

        // Print the results
        for (Map.Entry<String, Integer> entry : sourceCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
