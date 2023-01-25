package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

func main() {
	// Parse the file name from the command line arguments
	if len(os.Args) < 2 {
		fmt.Fprintln(os.Stderr, "Error: No file name provided")
		return
	}
	fileName := os.Args[1]

	// Create a map to store the counts for each source
	sourceCounts := make(map[string]int)

	// Create a map to store the sources for each email and phone combination
	userSources := make(map[string]map[string]bool)

	// Open the file and read it line by line
	file, err := os.Open(fileName)
	if err != nil {
		fmt.Fprintln(os.Stderr, "Error: ", err)
		return
	}
	defer file.Close()

	// Create a scanner to read the file
	scanner := bufio.NewScanner(file)

	// Skip the header
	scanner.Scan()

	for scanner.Scan() {
		// Split the line into fields
		fields := strings.Split(scanner.Text(), ",")

		// Skip entries with nullable fields
		if fields[0] == "" || fields[1] == "" || fields[2] == "" {
			continue
		}

		// Get the email, phone, and source for this entry
		email := fields[0]
		phone := fields[1]
		source := fields[2]

		// Get the set of sources for this user
		sources, ok := userSources[email+phone]
		if !ok {
			sources = make(map[string]bool)
			userSources[email+phone] = sources
		}

		// Check if this user has been counted for this source before
		if _, ok := sources[source]; !ok {
			// This is a new source for this user, so increment the count for this source
			sourceCounts[source]++

			// Add this source to the set for this user
			sources[source] = true
		}
	}

	// Print the results
	for source, count := range sourceCounts {
		fmt.Printf("%s: %d\n", source, count)
	}
}
