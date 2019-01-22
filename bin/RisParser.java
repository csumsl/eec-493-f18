
/*
 * Refer to the metadata samples folder for many .ris samples.
 * This parser should gather as much metadata as possible.
 * We will be missing a few fields of metadata, but that will be
 * a job for the html parser or whatever other solution we have.
 */

package risparser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class RisParser {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("What is the filename?");
        String input = in.nextLine();
        
        // Reads the file inputed by user
        BufferedReader br = new BufferedReader(new FileReader(input));
        
        // Used to write to text file
        PrintWriter writer = new PrintWriter("writerTest.txt", "UTF-8");
        
        // Used to check each line in the .ris file
        String line;
        
        while((line = br.readLine()) != null) {
            /* The if statements are checking for the metadata that we need in
             * order that they appear on the .ris files, NOT in the order that
             * the library staff wants! 
             */
            if (line.contains("TI  - ")) {
                // Seperate title indication from actual title
                // Write title to txt file with writer
                // Move cursor to next line on txt file
            }
            if (line.contains("AU  - ")) {
                /* Most likely multiple authors */
                // Seperate author indication from actual author(s)
                // Write author to txt file with writer
                // Move cursor to next line on txt file
            }
            if (line.contains("PY  - ")) {
                // Seperate published date indication from actual published date
                // Write published date to txt file with writer
                // Move cursor to next line on txt file
            }
            if (line.contains("KW  - ")) {
                // Seperate keyword indication from actual keyword(s)
                // Write keyword to txt file with writer
                // Move cursor to next line on txt file
            }
            if (line.contains("DO  - ")) {
                // Seperate DOI indication from actual DOI
                // Write DOI to txt file with writer
                // Move cursor to next line on txt file
            }
            if (line.contains("JO  - ")) {
                // Seperate journal title indication from actual journal title
                // Write journal title to txt file with writer
                // Move cursor to next line on txt file
            }
            if (line.contains("IS  - ")) {
                // Seperate issue number indication from actual issue number
                // Write issue number to txt file with writer
                // Move cursor to next line on txt file
            }
            if (line.contains("VO  - ")) {
                // Seperate volume number indication from actual volume number 
                // Write volume number to txt file with writer
                // Move cursor to next line on txt file
            }
            if (line.contains("AB  - ")) {
                // Seperate abstract indication from actual abstract
                // Write abstract to txt file with writer
                // Move cursor to next line on txt file
            }
            
            /* .ris files usually do not contain link to webpage, Fpage, Lpage,
             * season, and author affiliations. It's also somewhat common
             * for the volume and issue number sections of a .ris file to be
             * empty. We will need check for empty sections why parsing.
             */
            
        }
        // Closes buffered reader
        br.close();
        System.out.println(input + " has been parsed and sorted in the file writerTest.txt");
    }
    
}
