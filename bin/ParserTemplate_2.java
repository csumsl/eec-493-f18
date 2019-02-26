/*
    This template serves as the basis for all our future parsers.
    With the use of a hashmap, we can easily organize the parsed metadata
    in a clean data structure.
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import javax.swing.*;
import java.io.File;

/* 
    For writing to an excel sheet in the future, we will most likely be using
    Apache POI (https://poi.apache.org/)
               (https://www.callicoder.com/java-write-excel-file-apache-poi/)
*/

public class ParserTemplate_2 {
    
    /* 
        This function will be used to parse a specific filetype.
        Please change the method's name to whatever filetype you're
        working on.   Example: parserRis or parserBibtext
    */
    
    /* ### .RIS PARSER ### */
    public static HashMap<String, String> risParser (String path) throws FileNotFoundException, IOException {
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
    
    /*
    ### .TXT PARSER ###
    public static HashMap<String, String> txtParser (String path) throws FileNotFoundException, IOException {
        // Code here when finished
    }
    
    ### .BIB PARSER ###
    public static HashMap<String, String> bibParser (String path) throws FileNotFoundException, IOException {
        // Code here when finished
    }
    */
    
    public static void main(String[] args) throws IOException {
        
        HashMap<String, String> metaTable = new HashMap<>();
        String input;
        
        // File chooser. Allows user to select any directory
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("choosertitle");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
          System.out.println("Current directory: " + chooser.getCurrentDirectory());
          System.out.println("Selected file: " + chooser.getSelectedFile());
        } else {
          System.out.println("No Selection was made");
        }
        
        // Gets the name of the selected file and converts to string
        input = chooser.getSelectedFile().toString();
        
        // Opens selected file and calls respective parser
        File file = new File(input);
        File[] files = file.listFiles();
        for (File f: files) {
            if (f.getName().contains(".ris")) {
                /*  metaTable = risParser(f.getName())
                 *  Export metaTable to excel spreadsheet
                 */ 
                System.out.println(f.getName());
            }
            if (f.getName().contains(".txt")) {
                /*  metaTable = txtParser(f.getName())
                 *  Export metaTable to excel spreadsheet  
                 */ 
                System.out.println(f.getName());
            }
            if (f.getName().contains(".bib")) {
                /*  metaTable = bibParser(f.getName())
                 *  Export metaTable to excel spreadsheet 
                 */  
                System.out.println(f.getName());
            }
        }
        
        // metaTable = risParser(input);
        Set set = metaTable.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Map.Entry mapEntry = (Map.Entry)iterator.next();
            System.out.println("The key is " + mapEntry.getKey() + " and the Value is " + mapEntry.getValue());
        }
    }
}
