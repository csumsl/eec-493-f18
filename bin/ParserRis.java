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
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

/* 
    For writing to an excel sheet in the future, we will most likely be using
    Apache POI (https://poi.apache.org/)
               (https://www.callicoder.com/java-write-excel-file-apache-poi/)
*/

public class ParserRis {
    
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
        String Key;
        String Map = "";
        int authors = 1;
        
        // Loops continues until it reaches the end of the file
        while((line = br.readLine()) != null) {
            /*
                It's probably easier to parse the file in the order it presents
                itself in. We can always write the metadata to the spreadsheet
                in the order the library wants it in, later.
            */
            
            // TODO
            // Parsing here
            Key = line;
            String[] data = line.split(" ");
            Key = data[0];
            if(data.length > 3){
               Map = data[3];
            }
            for(int i = 4; i < data.length;i++){
               Map = Map + " " + data[i];
            }
            if(Key.equals("TY")){Key = "Type";}
            if(Key.equals("AU")){
               String[] name = Map.split(" ");
               if(name.length == 1){
                  metadataTable.put("author"+authors+"_lname", name[0]);
                  authors++;
               }
               if(name.length == 2){
                  metadataTable.put("author"+authors+"_fname", name[0]);
                  metadataTable.put("author"+authors+"_lname", name[1]);
                  authors++;
               }
               if(name.length > 2){
                  metadataTable.put("author"+authors+"_fname", name[0]);
                  if (name[name.length -1].equals("Jr.")||name[name.length -1].equals("Sr.")){
                     metadataTable.put("author"+authors+"_suffix", name[name.length -1]);
                     metadataTable.put("author"+authors+"_lname", name[name.length -2]);
                  }
                  else{
                     metadataTable.put("author"+authors+"_lname", name[name.length - 1]);
                  }
                  authors++;
               }
           
            }
            if(Key.equals("PY")){
            Key = "Publication year";
            metadataTable.put(Key, Map);}
            if(Key.equals("KW")){Key = "Key words";metadataTable.put(Key, Map);}
            if(Key.equals("DA")){Key = "Date";metadataTable.put(Key, Map);}
            if(Key.equals("TI")){Key = "Title";metadataTable.put(Key, Map);}
            if(Key.equals("JO")){Key = "Journal";metadataTable.put(Key, Map);}
            if(Key.equals("SP")){Key = "First page";metadataTable.put(Key, Map);}
            if(Key.equals("EP")){Key = "Last page";metadataTable.put(Key, Map);}
            if(Key.equals("VL")){Key = "Volume";metadataTable.put(Key, Map);}
            if(Key.equals("IS")){Key = "Issue";metadataTable.put(Key, Map);}
            if(Key.equals("AB")){Key = "Abstract";metadataTable.put(Key, Map);}
            if(Key.equals("SN")){Key = "ISBN";metadataTable.put(Key, Map);}
            if(Key.equals("UR")){Key = "URL";metadataTable.put(Key, Map);}
            if(Key.equals("DO")){Key = "DOI";metadataTable.put(Key, Map);}
            if(Key.equals("ID")){Key = "Reference ID";metadataTable.put(Key, Map);}
            if(Key.equals("ER")){break;}
            Map = "";   
        }
        return metadataTable;
}
    
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        HashMap<String, String> metaTable = new HashMap<>();
        System.out.println("What is the filename?");
        
        // Reads the file inputed by user
        String input = in.nextLine();
        
        /*      --- Example ---
            "What is the filename?"
            ThermalFluids_Article.ris
        */
        
        // Calls parser function
        
        metaTable = parser_Filename(input);
        Set set = metaTable.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Map.Entry mapEntry = (Map.Entry)iterator.next();
            System.out.println("the key is " + mapEntry.getKey() + " and the Value is " + mapEntry.getValue());
        }
        System.out.println(input + " has been parsed.");
    }
}