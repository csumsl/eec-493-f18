package plainParser;





import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;



public class plainParser {
    

    
    public static HashMap<String, String> parser_Filename (String path) throws FileNotFoundException, IOException {
        
    /* 
        metadataTable contains a key and value respectfully
        The key will be the name of the metadata we need such as:
            (In this order according the the metadata sheet)
            - Title
            - Keywords
            - DOI
            - Abstract
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
        String line = "NULL";
        String authors = "NULL";
        String title = "NULL";
        String pub = "NULL";
        String vol = "NULL";
        String date = "NULL";
        String pages = "NULL";
        String issNo = "NULL";
        String doi = "NULL";
        String url = "NULL";
        String ab = "NULL";
        String keywords = "NULL";
        int i = 0;
        		
        
        
        
        // Loops continues until it reaches the end of the file
        while((line = br.readLine()) != null) {
            /*
             Read each line and place it into each category,
             the integer i is used to identify which line you are on
            */
        	
        	if (i == 1) {
        		title = line;
        		i++;
        	}
        	else if (i == 2) {
        		pub = line;
        		i++;
        	}
          	else if (i == 3) {
        		vol = line;
        		i++;
        	}
        	else if (i == 4) {
        		date = line;
        		i++;
        	}
          	else if (i == 5) {
        		pages = line;
        		i++;
        	}
          	else if (i == 6) {
        		issNo = line;
        		i++;
        	}
          	else if (i == 7) {
        		doi = line;
        		i++;
        	}
          	else if (i == 8) {
        		url = line;
        		i++;
        	}
          	else if (i == 9) {
        		ab = line;
        		i++;
        	}
          	else if (i == 10) {
        		keywords = line;
        		i++;
        	}
          	else {
        		authors = line;
        		i++;
        	
        }
        }
        
    
        
        metadataTable.put("Title", title);
        metadataTable.put("Keywords", keywords);
        metadataTable.put("DOI", doi);
        metadataTable.put("Abstract", ab);
        metadataTable.put("Publication", pub);
        metadataTable.put("Volume Number", vol);
        metadataTable.put("Issue Number", issNo);
        metadataTable.put("Pages", pages);
        metadataTable.put("Publication Date", date);
        
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
