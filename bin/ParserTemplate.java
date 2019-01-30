package parsertemplate;

/*
    This template serves as the basis for all our future parsers.
    With the use of a hashmap, we can easily organize the parsed metadata
    in a clean data structure.

    Edit the code in whatever way is feasible. If you see a more efficient
    way to gather the data, by all means, do it.
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.HashMap;

/* 
    For writing to an excel sheet in the future, we will most likely be using
    Apache POI (https://poi.apache.org/)
               (https://www.callicoder.com/java-write-excel-file-apache-poi/)
*/

public class ParserTemplate {
    
    /* 
        metadataTable contains a key and value respecfully
        The key will be the name of the metadata we need such as:
            (In this order according the the metadata sheet)
            - Title
            - Keywords
            - DOI
            - Absract
            - Publication
            - Volume Number
            - Issue Number
            - First Page (if applicable)
            - Last Page (if applicable)
            - Publication Date
    */
    
    static HashMap<String, String> metadataTable = new HashMap<>();
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("What is the filename?");
        
        // Reads the file inputed by user
        String input = in.nextLine();
        
        /*      --- Example ---
            "What is the filename?"
            ThermalFluids_Article.ris
        */
        BufferedReader br = new BufferedReader(new FileReader(input));
        
        /*
            Writing to a text file isn't important and not the final product,
            but it might be useful to see it printed out elsewhere just so
            we know the concept works.
        
            We will be writing to a spreadsheet in the future
        */
        PrintWriter writer = new PrintWriter("writerTest.txt", "UTF-8");
        
        // Used to read the current line of the inputted file
        String line;
        
        // Parsing and writing is done here
        
        // Loops continues until it reaches the end of the file
        while((line = br.readLine()) != null) {
            /*
                It's probably easier to parse the file in the order it presents
                itself in. We can always write the metadata to the spreadsheet
                in the order the library wants it in, later.
            */
            
            // TODO
            // Parsing here
        }
        
        // Closes buffered reader
        br.close();
        System.out.println(input + " has been parsed and sorted in the file writerTest.txt");
    }
}
