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
        metadataTable contains a Key and value respecfully
        The Key will be the name of the metadata we need such as:
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
        int temp = 0;
        
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
            if(Key.equals("TY")){
               Key = "document_type";
               if(data[3].equals("ABST")){
                  metadataTable.put(Key, "abstract");
               }
               if(data[3].equals("ADVS")){
                  metadataTable.put(Key, "audiovisual material");
               }
               if(data[3].equals("AGGR")){
                  metadataTable.put(Key, "aggregated database");
               }
               if(data[3].equals("ANCIENT")){
                  metadataTable.put(Key, "ancient text");
               }
               if(data[3].equals("ART")){
                  metadataTable.put(Key, "art work");
               }
               if(data[3].equals("BILL")){
                  metadataTable.put(Key, "bill");
               }
               if(data[3].equals("BLOG")){
                  metadataTable.put(Key, "BLOG");
               }
               if(data[3].equals("BOOK")){
                  metadataTable.put(Key, "whole book");
               }
               if(data[3].equals("CASE")){
                  metadataTable.put(Key, "case");
               }
               if(data[3].equals("CHAP")){
                  metadataTable.put(Key, "book chapter");
               }
               if(data[3].equals("CHART")){
                  metadataTable.put(Key, "chart");
               }
               if(data[3].equals("CLSWK")){
                  metadataTable.put(Key, "classical work");
               }
               if(data[3].equals("COMP")){
                  metadataTable.put(Key, "computer program");
               }
               if(data[3].equals("CONF")){
                  metadataTable.put(Key, "conference proceeding");
               }
               if(data[3].equals("CPAPER")){
                  metadataTable.put(Key, "conference paper");
               }
               if(data[3].equals("CTLG")){
                  metadataTable.put(Key, "catalog");
               }
               if(data[3].equals("DATA")){
                  metadataTable.put(Key, "data file");
               }
               if(data[3].equals("DBASE")){
                  metadataTable.put(Key, "online database");
               }
               if(data[3].equals("DICT")){
                  metadataTable.put(Key, "dictionary");
               }
               if(data[3].equals("EBOOK")){
                  metadataTable.put(Key, "electronic book");
               }
               if(data[3].equals("ECHAP")){
                  metadataTable.put(Key, "electronic book section");
               }
               if(data[3].equals("EDBOOK")){
                  metadataTable.put(Key, "edited book");
               }
               if(data[3].equals("EJOUR")){
                  metadataTable.put(Key, "electronic article");
               }
               if(data[3].equals("ELEC")){
                  metadataTable.put(Key, "web page");
               }
               if(data[3].equals("ENCYC")){
                  metadataTable.put(Key, "encyclopedia");
               }
               if(data[3].equals("EQUA")){
                  metadataTable.put(Key, "equation");
               }
               if(data[3].equals("FIGURE")){
                  metadataTable.put(Key, "figure");
               }
               if(data[3].equals("GEN")){
                  metadataTable.put(Key, "generic");
               }
               if(data[3].equals("GOVDOC")){
                  metadataTable.put(Key, "government document");
               }
               if(data[3].equals("GRANT")){
                  metadataTable.put(Key, "grant");
               }
               if(data[3].equals("HEAR")){
                  metadataTable.put(Key, "hearing");
               }
               if(data[3].equals("ICOMM")){
                  metadataTable.put(Key, "internet communication");
               }
               if(data[3].equals("INPR")){
                  metadataTable.put(Key, "in press");
               }
               if(data[3].equals("INPR")){
                  metadataTable.put(Key, "in press");
               }
               if(data[3].equals("JFULL")){
                  metadataTable.put(Key, "journal (full)");
               }
               if(data[3].equals("JOUR")){
                  metadataTable.put(Key, "journal");
               }
               if(data[3].equals("LEGAL")){
                  metadataTable.put(Key, "legal rule or regulation");
               }
               if(data[3].equals("MANSCPT")){
                  metadataTable.put(Key, "manuscript");
               }
               if(data[3].equals("MAP")){
                  metadataTable.put(Key, "map");
               }
               if(data[3].equals("MGZN")){
                  metadataTable.put(Key, "magazine article");
               }
               if(data[3].equals("MPCT")){
                  metadataTable.put(Key, "motion picture");
               }
               if(data[3].equals("MULTI")){
                  metadataTable.put(Key, "online multimedia");
               }
               if(data[3].equals("MUSIC")){
                  metadataTable.put(Key, "music score");
               }
               if(data[3].equals("NEWS")){
                  metadataTable.put(Key, "newspaperer");
               }
               if(data[3].equals("PAMP")){
                  metadataTable.put(Key, "pamphlet");
               }
               if(data[3].equals("PAT")){
                  metadataTable.put(Key, "patent");
               }
               if(data[3].equals("PCOMM")){
                  metadataTable.put(Key, "personal communication");
               }
               if(data[3].equals("RPRT")){
                  metadataTable.put(Key, "report");
               }
               if(data[3].equals("SER")){
                  metadataTable.put(Key, "serial publication");
               }
               if(data[3].equals("SLIDE")){
                  metadataTable.put(Key, "slide");
               }
               if(data[3].equals("SOUND")){
                  metadataTable.put(Key, "sound recording");
               }
               if(data[3].equals("STAND")){
                  metadataTable.put(Key, "standard");
               }
               if(data[3].equals("STAT")){
                  metadataTable.put(Key, "statute");
               }
               if(data[3].equals("THES")){
                  metadataTable.put(Key, "thesis/dissertation");
               }
               if(data[3].equals("UNPB")){
                  metadataTable.put(Key, "unpublished work");
               }
               if(data[3].equals("VIDEO")){
                  metadataTable.put(Key, "video recording");
               }
               
            }
            if(Key.equals("AU")){
               String[] name = Map.split(" ");
               if(name.length == 1){
                  metadataTable.put("author"+authors+"_lname", name[0]);
                  authors++;
               }
               if(name.length == 2){
                  if((name[0].charAt(name[0].length()-1))==(',')){
                     name[0] =  name[0].replace(",","");
                     metadataTable.put("author"+authors+"_lname", name[0]);
                     metadataTable.put("author"+authors+"_fname", name[1]);
                     authors++;
                  }
                  else{
                     metadataTable.put("author"+authors+"_fname", name[0]);
                     metadataTable.put("author"+authors+"_lname", name[1]);
                     authors++;
                  }
               }
               if(name.length > 2){
               
                  if((name[0].charAt(name[0].length()-1))==(',')){
                     name[0] =  name[0].replace(",","");
                     metadataTable.put("author"+authors+"_lname", name[0]);
                     metadataTable.put("author"+authors+"_fname", name[1]);
                     authors++;
                  }
                  else{
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
           
            }
            if(Key.equals("PY")){Key = "publication_date";metadataTable.put(Key, Map);}
            if(Key.equals("KW")){
               Key = "Keywords";
               if (temp == 0) {
                  metadataTable.put(Key, Map);
                  temp++;
               } 
               else if(temp == 1) {
            		metadataTable.put(Key, metadataTable.get(Key) + ", " + Map);
               }
            }
            if(Key.equals("DA")){Key = "date";metadataTable.put(Key, Map);}
            if(Key.equals("TI")){Key = "title";metadataTable.put(Key, Map);}
            if(Key.equals("JO")){Key = "journal";metadataTable.put(Key, Map);}
            if(Key.equals("SP")){Key = "fpage";metadataTable.put(Key, Map);}
            if(Key.equals("EP")){Key = "lpage";metadataTable.put(Key, Map);}
            if(Key.equals("VL")){Key = "volume";metadataTable.put(Key, Map);}
            if(Key.equals("IS")){Key = "issue";metadataTable.put(Key, Map);}
            if(Key.equals("AB")){Key = "abstract";metadataTable.put(Key, Map);}
            if(Key.equals("SN")){Key = "ISBN";metadataTable.put(Key, Map);}
            if(Key.equals("UR")){Key = "URL";metadataTable.put(Key, Map);}
            if(Key.equals("DO")){Key = "doi";metadataTable.put(Key, Map);}
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
            System.out.println("the Key is " + mapEntry.getKey() + " and the Value is " + mapEntry.getValue());
        }
        System.out.println(input + " has been parsed.");
    }
}
