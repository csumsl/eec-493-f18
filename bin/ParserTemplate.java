package parsertemplate;

/*
    This template serves as the basis for all our future parsers.
    With the use of a hashmap, we can easily organize the parsed metadata
    in a clean data structure.
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

/* 
    For writing to an excel sheet in the future, we will most likely be using
    Apache POI (https://poi.apache.org/)
               (https://www.callicoder.com/java-write-excel-file-apache-poi/)
*/

public class ParserTemplate {
    
    /* 
        This function will be used to parse a specific filetype.
        Please change the method's name to whatever filetype you're
        working on.   Example: parserRis or parserBibtext
    */
    
    public static HashMap<String, String> parser_Filename (String path) throws FileNotFoundException, IOException {
        
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
        
        The value is the data we're parsing
    */
        HashMap<String, String> metadataTable = new HashMap<>();
        
        BufferedReader br = new BufferedReader(new FileReader(path));
        
        // Used to read the current line in the file
        String line;
        
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
        
        return metadataTable;
}
    
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("What is the filename?");
        
        // Reads the file inputed by user
        String input = in.nextLine();
        
        /*      --- Example ---
            "What is the filename?"
            ThermalFluids_Article.ris
        */
        
        // Calls parser function
        parser_Filename(input);
        
        System.out.println(input + " has been parsed.");
    }
}
