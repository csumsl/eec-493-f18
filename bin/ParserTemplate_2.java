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
import java.io.PrintWriter;
import java.util.HashMap;
import javax.swing.*;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.filechooser.FileSystemView;

// Gson imports
import com.google.gson.*;

// Jsoup imports
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class ParserTemplate {
    
/* ### .RIS PARSER ### */
public static HashMap<String, String> risParser (String path) throws FileNotFoundException, IOException {

    HashMap<String, String> metadataTable = new HashMap<>();
        
        BufferedReader br = new BufferedReader(new FileReader(path));
        
        // Used to read the current line in the file
        String line;
        String Key;
        String Map = "";
        int authors = 1;
        int temp = 0;
        HashMap<String, String> TypeMap = new HashMap<>();
        TypeMap.put("ABST", "abstract");
        TypeMap.put("ADVS", "audiovisual material");
        TypeMap.put("AGGR", "aggregated database");
        TypeMap.put("ANCIENT", "ancient text");
        TypeMap.put("ART", "art work");
        TypeMap.put("BILL", "bill");
        TypeMap.put("BLOG", "blog");
        TypeMap.put("BOOK", "whole book");
        TypeMap.put("CASE", "case");
        TypeMap.put("CHAP", "book chapter");
        TypeMap.put("CHART", "chart");
        TypeMap.put("CLSWK", "classical work");
        TypeMap.put("COMP", "computer program");
        TypeMap.put("CONF", "conference proceeding");
        TypeMap.put("CPAPER", "conference paper");
        TypeMap.put("CTLG", "catalog");
        TypeMap.put("DATA", "data file");
        TypeMap.put("DBASE", "online database");
        TypeMap.put("DICT", "dictionary");
        TypeMap.put("EBOOK", "electronic book");
        TypeMap.put("ECHAP", "electionic book section");
        TypeMap.put("EDBOOK", "edited book");
        TypeMap.put("EJOUR", "electronic article");
        TypeMap.put("ELEC", "web page");
        TypeMap.put("ENCYC", "encyclopedia");
        TypeMap.put("EQUA", "equation");
        TypeMap.put("FIGURE", "figure");
        TypeMap.put("GEN", "generic");
        TypeMap.put("GOVDOC", "government document");
        TypeMap.put("GRANT", "grant");
        TypeMap.put("HEAR", "hearing");
        TypeMap.put("ICOMM", "internet communication");
        TypeMap.put("INPR", "in press");
        TypeMap.put("JOUR", "journal");
        TypeMap.put("LEGAL", "legal rule or regulation");
        TypeMap.put("MANSCPT", "manuscript");
        TypeMap.put("MAP", "map");
        TypeMap.put("MGZN", "magazine article");
        TypeMap.put("MPCT", "motion picture");
        TypeMap.put("MULTI", "online multimedia");
        TypeMap.put("MUSIC", "music score");
        TypeMap.put("NEWS", "newspaper");
        TypeMap.put("PAMP", "pamphlet");
        TypeMap.put("PAT", "patent");
        TypeMap.put("PCOMM", "personal communication");
        TypeMap.put("RPRT", "report");
        TypeMap.put("SER", "serial publication");
        TypeMap.put("SLIDE", "slide");
        TypeMap.put("SOUND", "sound recording");
        TypeMap.put("STAND", "standart");
        TypeMap.put("STAT", "statute");
        TypeMap.put("THES", "thesis/dissertation");
        TypeMap.put("UNPB", "unpublished work");
        TypeMap.put("VIDEO", "video recording");
        
        // Loops continues until it reaches the end of the file
        while((line = br.readLine()) != null) {
            
            String[] data = line.split(" ");
            Key = data[0];
            if(data.length > 3){
               Map = data[3];
            }
            for(int i = 4; i < data.length;i++){
               Map = Map + " " + data[i];
            }
            if(Key.equals("TY")){
                metadataTable.put("Type", TypeMap.get(data[3]));
            }
            if(Key.equals("AU")) {
                String[] name = Map.split(" ");
                if(name.length == 1) {
                    metadataTable.put("author"+authors+"_lname", name[0]);
                    maxAuth = authors;
                    authors++;
                }
                if(name.length == 2) {
                    if((name[0].charAt(name[0].length()-1))==(',')) {
                        name[0] =  name[0].replace(",","");
                        metadataTable.put("author"+authors+"_lname", name[0]);
                        metadataTable.put("author"+authors+"_fname", name[1]);
                        maxAuth = authors;
                        authors++;
                    } else {
                        metadataTable.put("author"+authors+"_fname", name[0]);
                        metadataTable.put("author"+authors+"_lname", name[1]);
                        maxAuth = authors;
                        authors++;
                    }
                }
                if(name.length > 2) {
                    if((name[0].charAt(name[0].length()-1))==(',')) {
                        name[0] =  name[0].replace(",","");
                        metadataTable.put("author"+authors+"_lname", name[0]);
                        metadataTable.put("author"+authors+"_fname", name[1]);
                        authors++;
                    } else {
                        metadataTable.put("author"+authors+"_fname", name[0]);
                        if (name[name.length -1].equals("Jr.")||name[name.length -1].equals("Sr.")){
                        metadataTable.put("author"+authors+"_suffix", name[name.length -1]);
                        metadataTable.put("author"+authors+"_lname", name[name.length -2]);
                    } else {
                        metadataTable.put("author"+authors+"_lname", name[name.length - 1]);
                    }
                    maxAuth = authors;
                    authors++;
                    }
                }
            }
            if(Key.equals("PY")){Key = "publication_date";metadataTable.put(Key, Map);}
            if(Key.equals("Y1")){Key = "publication_date";metadataTable.put(Key, Map);}
            if(Key.equals("KW")){
                Key = "Keywords";
                if (temp == 0) {
                    metadataTable.put(Key, Map);
                    temp++;
                } else if(temp == 1) {
                    metadataTable.put(Key, metadataTable.get(Key) + ", " + Map);
                }
            }
            if(Key.equals("DA")){Key = "date";metadataTable.put(Key, Map);}
            if(Key.equals("TI")){Key = "Title";metadataTable.put(Key, Map);}
            if(Key.equals("T1")){Key = "Title";metadataTable.put(Key, Map);}
            if(Key.equals("JO")){Key = "source publication";metadataTable.put(Key, Map);}
            if(Key.equals("SP")){Key = "First page";metadataTable.put(Key, Map);}
            if(Key.equals("EP")){Key = "Last page";metadataTable.put(Key, Map);}
            if(Key.equals("VL")){Key = "volnum";metadataTable.put(Key, Map);}
            if(Key.equals("IS")){Key = "issnum";metadataTable.put(Key, Map);}
            if(Key.equals("AB")){Key = "Abstract";metadataTable.put(Key, Map);}
	    if(Key.equals("N2")){Key = "Abstract";metadataTable.put(Key, Map);}
            if(Key.equals("SN")){Key = "ISBN";metadataTable.put(Key, Map);}
            if(Key.equals("UR")){Key = "URL";metadataTable.put(Key, Map);}
            if(Key.equals("N1")){Key = "DOI";metadataTable.put(Key, Map);}
            if(Key.equals("DO")){Key = "DOI";metadataTable.put(Key, Map);}
            if(Key.equals("ID")){Key = "Reference ID";metadataTable.put(Key, Map);}
            if(Key.equals("ER")){break;}
            Map = "";   
        }
return metadataTable;
}    

/* ### .BIB PARSER ### */
public static HashMap<String, String> bibParser (String path) throws FileNotFoundException, IOException {
        
    HashMap<String, String> metadataTable = new HashMap<>();
    BufferedReader br = new BufferedReader(new FileReader(path));

    // Used to read the current line in the file
    String line;
    String Key;
    String Map = "";
    int authors = 1;

    // Skips first line (first line is irrelevant in .bib files)
    br.readLine();

    // Loops continues until it reaches the end of the file
    while((line = br.readLine()) != null) {
        
        /*
            Some .bib files separate the data with "{" "}"
            and some separate with just quotations
            This first if statement checks the first .bib format
        */
        if (line.isEmpty()) {
            br.readLine();
        }
        // Format 1
        if (line.contains("{")) {
            String[] data = line.split("[={}]");
            // Testing purposes -Elias
            //System.out.println("0 " + data[0]); // Key
            //System.out.println("1 " + data[1]); // "={" replaced with ""
            //System.out.println("2 " + data[2]); // Data
            //System.out.println("3 " + data[3]); // Comma
            Key = data[0];
            Map = data[2];
            // Fixes key's with whitespace
            if (Key.contains(" ")) {
                Key = Key.replace(" ", "");
            }
            if (Key.equals("author") || Key.equals("Author")) {
                // If there's only one author
                if (!Map.contains(" and ")) {
                    List<String> name = Arrays.asList(Map.split("[, ]"));
                    if (Map.contains(", ")) {
                        // Author has middle name/inital
                        metadataTable.put("author"+authors+"_lname", name.get(0));
                        metadataTable.put("author"+authors+"_mname", name.get(3));
                        metadataTable.put("author"+authors+"_fname", name.get(2));
                        maxAuth = authors;
                        authors++;
                        // Empties array
                        name = new ArrayList<>();
                    } else {
                        // Author only has first and last name
                        metadataTable.put("author"+authors+"_lname", name.get(0));
                        metadataTable.put("author"+authors+"_fname", name.get(2));
                        maxAuth = authors;
                        authors++;
                        // Empties array
                        name = new ArrayList<>();
                    }
                } else {
                    // Split authors here
                    // (Authors are separated like so: "John Doe and Marge Thatcher")  
                    String[] data2 = Map.split(" and ");
                    for (int i = 0; i < data2.length; i++) {
                        // Split authors once more. They are now only separated by " " or by ", "
                        // We're using a List here so we're able to empty the array completely later

                        // If they're separated by ", ", split like this
                        if (data2[i].contains(", ")) {
                            List<String> name = Arrays.asList(data2[i].split("[, ]"));
                            if (name.size() > 3) {
                                // Author has middle name/inital
                                metadataTable.put("author"+authors+"_lname", name.get(0));
                                metadataTable.put("author"+authors+"_mname", name.get(3));
                                metadataTable.put("author"+authors+"_fname", name.get(2));
                                maxAuth = authors;
                                authors++;
                                // Empties array
                                name = new ArrayList<>();
                            } else {
                                // Author only has first and last name
                                metadataTable.put("author"+authors+"_lname", name.get(0));
                                metadataTable.put("author"+authors+"_fname", name.get(2));
                                authors++;
                                maxAuth = authors;
                                // Empties array
                                name = new ArrayList<>();
                            }
                        // If they're separated by " ", split like this
                        } else {
                            List<String> name = Arrays.asList(data2[i].split(" "));
                            if (name.size() > 2) {
                                // Author has middle name/inital
                                metadataTable.put("author"+authors+"_fname", name.get(0));
                                metadataTable.put("author"+authors+"_mname", name.get(1));
                                metadataTable.put("author"+authors+"_lname", name.get(2));
                                maxAuth = authors;
                                authors++;
                                // Empties array
                                name = new ArrayList<>();
                            } else {
                                // Author only has first and last name
                                metadataTable.put("author"+authors+"_fname", name.get(0));
                                metadataTable.put("author"+authors+"_lname", name.get(1));
                                maxAuth = authors;
                                authors++;
                                // Empties array
                                name = new ArrayList<>();
                            }
                        }
                    }
                /*
                    Each data2[] value is a different author
                    They need to be split again into:
                        - first name/first inital
                        - middle name/middle initial
                        - last name/last inital
                    Then pushed to the metadataTable
                */
                }
            }
        if(Key.equals("title") || Key.equals("Title")){Key = "Title";metadataTable.put(Key, Map);}
        if(Key.equals("journal") || Key.equals("Journal") || Key.equals("booktitle") || Key.equals("Booktitle")){Key = "source publication";metadataTable.put(Key, Map);}
        if(Key.equals("year") || Key.equals("Year")){Key = "publication_date";metadataTable.put(Key, Map);}
        if(Key.equals("volume") || Key.equals("Volume")){Key = "volnum";metadataTable.put(Key, Map);}
        if (Key.equals("pages") || Key.equals("Pages")) {
            String[] data3 = Map.split("-");
            if (data3.length > 2) {
                metadataTable.put("First page", data3[0]);
                metadataTable.put("Last page", data3[2]);
            } else {
                metadataTable.put("First page", data3[0]);
                metadataTable.put("Last page", data3[1]);
            }
        }
        if(Key.equals("abstract") || Key.equals("Abstract")) {
            Key = "Abstract";
            metadataTable.put(Key,Map);
        }
        if (Key.equals("keywords") || Key.equals("Keywords")) {
            if (Map.contains(";")) {
                Map = Map.replace(";", ", ");
                metadataTable.put("Keywords", Map);
            } else {
                metadataTable.put("Keywords", Map);
            }
        }
        if(Key.equals("doi") || Key.equals("DOI") || Key.equals("Doi")){Key = "DOI";metadataTable.put(Key, Map);}
        if(Key.equals("url") || Key.equals("URL")){Key = "URL";metadataTable.put(Key, Map);}
        if(Key.equals("ISSN") || Key.equals("issn")){Key = "ISBN";metadataTable.put(Key, Map);}
        }

        // Format 2
        if (line.contains("\",")) {
            String[] data4 = line.split(" = \"");
            // Testing purposes -Elias
            //System.out.println("Key " + data4[0]); // Key
            //System.out.println("Map " + data4[1]); // Data
            Key = data4[0];
            Map = data4[1].replace("\",", "");
            if(Key.equals("title") || Key.equals("Title")){Key = "Title";metadataTable.put(Key, Map);}
            if(Key.equals("journal") || Key.equals("Journal") || Key.equals("booktitle") || Key.equals("Booktitle")){Key = "source publication";metadataTable.put(Key, Map);}
            if(Key.equals("volume") || Key.equals("Volume")){Key = "volnum";metadataTable.put(Key, Map);}
            if (Key.equals("pages") || Key.equals("Pages")) {
                String[] data6 = Map.split(" - ");
                metadataTable.put("First page", data6[0]);
                metadataTable.put("Last page", data6[1]);
            }
            if(Key.equals("year") || Key.equals("Year")){Key = "publication_date";metadataTable.put(Key, Map);}
            if(Key.equals("issn") || Key.equals("ISSN")){Key = "ISBN";metadataTable.put(Key, Map);}
            if(Key.equals("url") || Key.equals("URL")){Key = "URL";metadataTable.put(Key, Map);}
            if(Key.equals("doi") || Key.equals("DOI") || Key.equals("Doi")){Key = "DOI";metadataTable.put(Key, Map);}
            if (Key.equals("author") || Key.equals("Author")) {

                // Split authors here (Authors are separated like so:
                //                     "John Doe and Marge Thatcher")  
                String[] data7 = Map.split(" and ");

                for (int i = 0; i < data7.length; i++) {
                    // Split authors once more. They are now only separated by " "
                    // We're using a List here so we're able to empty the array completely later
                    List<String> name = Arrays.asList(data7[i].split(" "));
                    if (name.size() > 2) {
                        // Author has middle name/inital
                        metadataTable.put("author"+authors+"_fname", name.get(0));
                        metadataTable.put("author"+authors+"_mname", name.get(1));
                        metadataTable.put("author"+authors+"_lname", name.get(2));
                        maxAuth = authors;
                        authors++;
                        // Empties array
                        name = new ArrayList<>();
                    } else {
                        // Author only has first and last name
                        metadataTable.put("author"+authors+"_fname", name.get(0));
                        metadataTable.put("author"+authors+"_lname", name.get(1));
                        maxAuth = authors;
                        authors++;
                        // Empties array
                        name = new ArrayList<>();
                    }
                }
            }
            if (Key.equals("keywords") || Key.equals("Keywords")) {
                if (Map.contains(";")) {
                    Map = Map.replace(";", ", ");
                    metadataTable.put("Keywords", Map);
                } else {
                    metadataTable.put("Keywords", Map);
                }
            }
            if (Key.equals("abstract") || Key.equals("Abstract")) {
                Key = "Abstract";
                metadataTable.put(Key, Map);
            }
        }
        if (line.startsWith("abstract = \"") || line.startsWith("Abstract = \"")) {
            String[] data = line.split(" = \"");
            Key = data[0];
            Map = data[1].replace("\"", "");
            if (Key.equals("abstract") || Key.equals("Abstract")) {
                Key = "Abstract";
                metadataTable.put(Key, Map);
            }
        }
    }
    return metadataTable;
}

/* ### .BIB Multiple Articles PARSER ### */
public static HashMap<String, String> bibParser_multipleArticles (String path, File csvFile, PrintWriter writer) throws FileNotFoundException, IOException {
    
    HashMap<String, String> metadataTable = new HashMap<>();
    BufferedReader br = new BufferedReader(new FileReader(path));

    // Used to read the current line in the file
    String line;
    
    String Key;
    String Map = "";
    String temp = "";
    String last = "";

    // Skips first two lines
    br.readLine(); br.readLine();
    
    // Loops continues until it reaches the end of the file
    while((line = br.readLine()) != null) {
        if (line.startsWith("   ")) {
            while (line.startsWith("   ")) {
                if (line.contains("}},")) {
                    line = line.replace("}},", "");
                    temp = temp.concat(" " + line.replaceFirst("   ", ""));
                    metadataTable.put(last, Map + temp);
                    temp = "";
                    Map = "";
                    break;
                } else {
                    temp = temp.concat(" " + line.replaceFirst("   ", ""));
                    line = br.readLine();
                }
            }
        }
        int authors = 1;
        // Checks if the current line is at the end of an article's metadata
        // Also writes to excel
        if (line.startsWith("}")) {
            br.readLine();
            br.readLine();
            // If it reaches here then the first article in the bib file should be
            // completed and all the metadata is in the hashmap
            populateCSV(metadataTable, csvFile, writer);
            // Clears hashmap for next article
            metadataTable.clear();
        }
        
        if (line.contains("{")) {
            String[] data = line.split("[={}]");
            Key = data[0].replace(" ", "");
            // if data.length = 4 then there is more data to grab for that key
            if (data.length == 4) {
                if (Key.contains("Author") || Key.contains("author")) {
                    Map = data[2];
                    // If there's only one author
                    if (!Map.contains(" and ")) {
                        List<String> name = Arrays.asList(Map.split("[, ]"));
                        if (name.size() == 4) {
                            // Author has middle name/inital
                            metadataTable.put("author"+authors+"_lname", name.get(0));
                            metadataTable.put("author"+authors+"_mname", name.get(3));
                            metadataTable.put("author"+authors+"_fname", name.get(2));
                            maxAuth = authors;
                            authors++;
                            // Empties array
                            name = new ArrayList<>();
                        } else {
                            // Author only has first and last name
                            metadataTable.put("author"+authors+"_lname", name.get(0));
                            metadataTable.put("author"+authors+"_fname", name.get(2));
                            maxAuth = authors;
                            authors++;
                            // Empties array
                            name = new ArrayList<>();
                        }
                    } else {
                        // Split authors here
                        // (Authors are separated like so: "John Doe and Marge Thatcher")  
                        String[] data2 = Map.split(" and ");
                        for (int i = 0; i < data2.length; i++) {
                            // Split authors once more. They are now only separated by " " or by ", "
                            // We're using a List here so we're able to empty the array completely later

                            // If they're separated by ", ", split like this
                            if (data2[i].contains(", ")) {
                                List<String> name = Arrays.asList(data2[i].split("[, ]"));
                                if (name.size() > 3) {
                                    // Author has middle name/inital
                                    metadataTable.put("author"+authors+"_lname", name.get(0));
                                    metadataTable.put("author"+authors+"_mname", name.get(3));
                                    metadataTable.put("author"+authors+"_fname", name.get(2));
                                    maxAuth = authors;
                                    authors++;
                                    // Empties array
                                    name = new ArrayList<>();
                                } else {
                                    // Author only has first and last name
                                    metadataTable.put("author"+authors+"_lname", name.get(0));
                                    metadataTable.put("author"+authors+"_fname", name.get(2));
                                    maxAuth = authors;
                                    authors++;
                                    // Empties array
                                    name = new ArrayList<>();
                                }
                            // If they're separated by " ", split like this
                            } else {
                                List<String> name = Arrays.asList(data2[i].split(" "));
                                if (name.size() > 2) {
                                    // Author has middle name/inital
                                    metadataTable.put("author"+authors+"_fname", name.get(0));
                                    metadataTable.put("author"+authors+"_mname", name.get(1));
                                    metadataTable.put("author"+authors+"_lname", name.get(2));
                                    maxAuth = authors;
                                    authors++;
                                    // Empties array
                                    name = new ArrayList<>();
                                } else {
                                    // Author only has first and last name
                                    metadataTable.put("author"+authors+"_fname", name.get(0));
                                    metadataTable.put("author"+authors+"_lname", name.get(1));
                                    maxAuth = authors;
                                    authors++;
                                    // Empties array
                                    name = new ArrayList<>();
                                }
                            }
                        }
                    }
                }
                if (Key.equals("title") || Key.equals("Title")) {
                    Map = data[3];
                    metadataTable.put("Title", Map);
                    last = "Title";
                }
                if(Key.equals("journal") || Key.equals("Journal") || Key.equals("booktitle") || Key.equals("Booktitle")) {
                    Map = data[3];
                    metadataTable.put("source publication", Map);
                    last = "source publication";
                }
                if(Key.equals("year") || Key.equals("Year")) {
                    Map = data[3];
                    metadataTable.put("publication_date", Map);
                }
                if(Key.equals("volume") || Key.equals("Volume")) {
                    Map = data[3];
                    metadataTable.put("volnum", Map);
                }
                if(Key.equals("URL") || Key.equals("url")) {
                    Map = data[3];
                    metadataTable.put("URL", Map);
                }
                if (Key.equals("pages") || Key.equals("Pages")) {
                    Map = data[3];
                    String[] data3 = Map.split("-");
                    if (data3.length > 2) {
                        metadataTable.put("First page", data3[0]);
                        metadataTable.put("Last page", data3[2]);
                    } else if (data3.length == 2) {
                        metadataTable.put("First page", data3[0]);
                        metadataTable.put("Last page", data3[1]);
                    } else {
                        metadataTable.put("First page", data3[0]);
                    }
                }
                if(Key.equals("abstract") || Key.equals("Abstract")) {
                    Map = data[3];
                    metadataTable.put("Abstract", Map);
                    last = "Abstract";
                }
                if (Key.equals("keywords") || Key.equals("Keywords")) {
                    Map = data[3];
                    if (Map.contains(";")) {
                        Map = Map.replace(";", ", ");
                        metadataTable.put("Keywords", Map);
                        last = "Keywords";
                    } else {
                        metadataTable.put("Keywords", Map);
                        last = "Keywords";
                    }
                }
                if(Key.equals("doi") || Key.equals("DOI") || Key.equals("Doi")) {
                    Map = data[3];
                    metadataTable.put("DOI", Map);
                }
                if(Key.equals("ISSN") || Key.equals("issn")) {
                    Map = data[3];
                    metadataTable.put("ISSN", Map);
                }
                
                // Test print
                /*for (int i = 0; i < data.length; i++) {
                    if (i == 3) {
                        System.out.println(i + ": " + data[3].replace("}},", ""));
                    } else {
                        System.out.println(i + ": " + data[i]);
                    }
                }*/
            }
            
            // if data.length = 6 then all the data has been found
            if (data.length == 6) {
                Key = data[0].replace(" ", "");
                if (Key.contains("Author") || Key.contains("author")) {
                    Map = data[3];
                    // If there's only one author
                    if (!Map.contains(" and ")) {
                        List<String> name = Arrays.asList(Map.split("[, ]"));
                        if (name.size() == 4) {
                            // Author has middle name/inital
                            metadataTable.put("author"+authors+"_lname", name.get(0));
                            metadataTable.put("author"+authors+"_mname", name.get(3));
                            metadataTable.put("author"+authors+"_fname", name.get(2));
                            maxAuth = authors;
                            authors++;
                            // Empties array
                            name = new ArrayList<>();
                        } else {
                            // Author only has first and last name
                            metadataTable.put("author"+authors+"_lname", name.get(0));
                            metadataTable.put("author"+authors+"_fname", name.get(2));
                            maxAuth = authors;
                            authors++;
                            // Empties array
                            name = new ArrayList<>();
                        }
                    } else {
                        // Split authors here
                        // (Authors are separated like so: "John Doe and Marge Thatcher")  
                        String[] data2 = Map.split(" and ");
                        for (int i = 0; i < data2.length; i++) {
                            // Split authors once more. They are now only separated by " " or by ", "
                            // We're using a List here so we're able to empty the array completely later

                            // If they're separated by ", ", split like this
                            if (data2[i].contains(", ")) {
                                List<String> name = Arrays.asList(data2[i].split("[, ]"));
                                if (name.size() > 3) {
                                    // Author has middle name/inital
                                    metadataTable.put("author"+authors+"_lname", name.get(0));
                                    metadataTable.put("author"+authors+"_mname", name.get(3));
                                    metadataTable.put("author"+authors+"_fname", name.get(2));
                                    maxAuth = authors;
                                    authors++;
                                    // Empties array
                                    name = new ArrayList<>();
                                } else {
                                    // Author only has first and last name
                                    metadataTable.put("author"+authors+"_lname", name.get(0));
                                    metadataTable.put("author"+authors+"_fname", name.get(2));
                                    maxAuth = authors;
                                    authors++;
                                    // Empties array
                                    name = new ArrayList<>();
                                }
                            // If they're separated by " ", split like this
                            } else {
                                List<String> name = Arrays.asList(data2[i].split(" "));
                                if (name.size() > 2) {
                                    // Author has middle name/inital
                                    metadataTable.put("author"+authors+"_fname", name.get(0));
                                    metadataTable.put("author"+authors+"_mname", name.get(1));
                                    metadataTable.put("author"+authors+"_lname", name.get(2));
                                    maxAuth = authors;
                                    authors++;
                                    // Empties array
                                    name = new ArrayList<>();
                                } else {
                                    // Author only has first and last name
                                    metadataTable.put("author"+authors+"_fname", name.get(0));
                                    metadataTable.put("author"+authors+"_lname", name.get(1));
                                    maxAuth = authors;
                                    authors++;
                                    // Empties array
                                    name = new ArrayList<>();
                                }
                            }
                        }
                    }
                }
                if (Key.equals("title") || Key.equals("Title")) {
                    Map = data[3];
                    metadataTable.put("Title", Map);
                    last = "Title";
                }
                if(Key.equals("journal") || Key.equals("Journal") || Key.equals("booktitle") || Key.equals("Booktitle")) {
                    Map = data[3];
                    metadataTable.put("source publication", Map);
                    last = "source publication";
                }
                if(Key.equals("year") || Key.equals("Year")) {
                    Map = data[3];
                    metadataTable.put("publication_date", Map);
                }
                if(Key.equals("volume") || Key.equals("Volume")) {
                    Map = data[3];
                    metadataTable.put("volnum", Map);
                }
                if (Key.equals("pages") || Key.equals("Pages")) {
                    Map = data[3];
                    String[] data3 = Map.split("-");
                    if (data3.length > 2) {
                        metadataTable.put("First page", data3[0]);
                        metadataTable.put("Last page", data3[2]);
                    } else if (data3.length == 2) {
                        metadataTable.put("First page", data3[0]);
                        metadataTable.put("Last page", data3[1]);
                    } else {
                        metadataTable.put("First page", data3[0]);
                    }
                }
                // add month
                if(Key.equals("abstract") || Key.equals("Abstract")) {
                    Map = data[3];
                    metadataTable.put("Abstract", Map);
                    last = "Abstract";
                }
                if (Key.equals("keywords") || Key.equals("Keywords")) {
                    Map = data[3];
                    if (Map.contains(";")) {
                        Map = Map.replace(";", ", ");
                        metadataTable.put("Keywords", Map);
                        last = "Keywords";
                    } else {
                        metadataTable.put("Keywords", Map);
                        last = "Keywords";
                    }
                }
                if(Key.equals("URL") || Key.equals("url")) {
                    Map = data[3];
                    metadataTable.put("URL", Map);
                }
                if(Key.equals("doi") || Key.equals("DOI") || Key.equals("Doi")) {
                    Map = data[3];
                    metadataTable.put("DOI", Map);
                }
                if(Key.equals("ISSN") || Key.equals("issn")) {
                    Map = data[3];
                    metadataTable.put("ISSN", Map);
                }
                // Test print
                /*for (int i = 0; i < data.length; i++) {
                    System.out.println(i + ": " + data[i]);
                }*/
            }
        }
    }
    return null;
}

/* ### Maximum authors in an article (Used for the asme.org parser) ###*/
public static int maxAuth;

/* ### IEEExplore PARSER ### */
public static HashMap<String, String> ieeeParser (HashMap<String, String> input, ArrayList<String> html) {
    
    String fetchedMetadata = null;
    // Test print of recovered HTML source code
    // + Extracting line that contains metadata (global.document.metadata)
    for (int i = 0; i < html.size(); i++) {
        //System.out.println("Line " + i + ": " + html.get(i));
        if (html.get(i).contains("global.document.metadata"))
            fetchedMetadata = html.get(i);
    }
    if (fetchedMetadata == null) {
        return input;
    } else {
        // Fix string to make it Json and test print
        fetchedMetadata = fetchedMetadata.replace("global.document.metadata=","");
        // Removes comma at the end of Json String
        fetchedMetadata = fetchedMetadata.substring(0, fetchedMetadata.length() - 1);
        //System.out.println("Metadata content: " + fetchedMetadata);

        // Grab author portion of retrieved HTML code
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject)jsonParser.parse(fetchedMetadata);
        JsonArray jsonArr = jo.getAsJsonArray("authors");

        ArrayList<String> authors = new ArrayList<>();

        // Convert JsonArray to ArrayList<String>
        for (int k = 0; k < jsonArr.size(); k++) {
            authors.add((jsonArr.get(k)).toString());
            //System.out.println("Author " + (k+1) + ": " + authors.get(k));
        }

        int i = 0;
        int j = 1;
        while (j != authors.size() + 1) {
            if (authors.get(i).contains(input.get("author" + j + "_lname"))) {
                //System.out.println("Found: " + (input.get("author"+ j +"_lname")));
                if (authors.get(i).contains("affiliation")) {
                    String[] data = authors.get(i).split("\"affiliation\":\"");
                    String[] data2 = data[1].split("\",\"");
                    // data2[0] contains the affiliation for this particular author
                    System.out.println("Placing new institution for " + (input.get("author"+ j +"_lname")));
                    input.put("author" + j + "_institution", data2[0]);
                    i++;
                    j++;
                    if (i == authors.size())
                        i = 0;
                } else {
                    i++;
                }
            } else {
                i++;
                if (i == authors.size())
                    j++;
            }
        }
        return input;
    }
}

public static HashMap<String, String> springerParser (HashMap<String, String> input, ArrayList<String> html) {
    for (int i = 0; i < html.size(); i++) {
        if (html.get(i).contains("\"citation_author\"")) {
            int slice = 0;
            String currentString = html.get(i);
            slice = currentString.indexOf("content=\"") + 9;
            String author[] = currentString.substring(slice, currentString.length() - 3).split(" ");
            Vector<String> institutions = new Vector<String>();
            String institution = "";
            while(html.get(i + 1).contains("\"citation_author_institution\"")) { //Find institutions
                i++;
                currentString = html.get(i);
                slice = currentString.indexOf("content=\"") + 9;
                institutions.addElement(currentString.substring(slice, currentString.length() - 3));
            }
            for(int k = 1; k < 10; k++) {
                System.out.println(author[0] + " is compared with " + input.get("author" + k + "_fname") + " and " + author[1] + " is compared with " + input.get("author" + k + "_lname"));
                if(author[0].equals(input.get("author" + k + "_fname")) && author[author.length - 1].equals(input.get("author" + k + "_lname"))) { //Find the author for the institution.
                    String formattedInstitutions = institutions.get(0);
                    for(int l = 1; l < institutions.size(); l++) {
                            formattedInstitutions += ", " + institutions.get(l);
                    }
                    input.put("author" + k + "_institution", formattedInstitutions);
                    break;
                }
            }
        }
    }
    return null;
}

public static HashMap<String, String> asmeParser (HashMap<String, String> input, String url) throws IOException {
    
    // citationInfo will hold all the acquired affiliations from the html
    ArrayList<String> citationInfo = new ArrayList<String>();
    Document doc;
    doc = Jsoup.connect(url).get();
    
    // Get all meta tags
    Elements meta = doc.select("meta");
    // metaTags holds all the metaTags in the website's HTML code
    String[] metaTags = new String[meta.size()];
    for (int i = 0; i < meta.size(); i++) {
        metaTags[i] = meta.get(i).toString();
    }

    int j = 0;
    for (Element metaTag : meta) {
        // For every meta tag that has the value "name", put the "name" value into citationInfo arraylist
        citationInfo.add(j, (metaTag.attr("name")));
        j++;
        // For every meta tag that has "content", put the "content" value into citationInfo arraylist
        citationInfo.add(j, (metaTag.attr("content")));
        j++;
    }

    for (int i = 0; i < citationInfo.size(); i++) {
        // Test print of new arraylist
        System.out.println("citationInfo index " + i + ": " + citationInfo.get(i));
    }

    int authors = 1;
    int i = 0;
    int maxCheck = 0;
    while (authors < maxAuth + 1) {
        if (citationInfo.get(i).contains(input.get("author" + authors + "_lname"))){
            i++;
            if (citationInfo.get(i).equals("citation_author_email")) {
                i++;
                input.put("author" + authors + "_email",citationInfo.get(i));
                // Test print to show what author was just recognized:
                System.out.println("Author: " + input.get("author" + authors + "_fname") + " " + input.get("author" + authors + "_lname"));
                System.out.println("***Entered author email in metaTable***");
                i++;
                if (citationInfo.get(i).equals("citation_author_institution")) {
                    i++;
                    input.put("author" + authors + "_institution",citationInfo.get(i));
                    System.out.println("***Entered author institution in metaTable***");
                    authors++;
                }
            } else if (citationInfo.get(i).equals("citation_author_institution")) {
                i++;
                // Test print to show what author was just recognized:
                System.out.println("Author: " + input.get("author" + authors + "_fname") + " " + input.get("author" + authors + "_lname"));
                input.put("author" + authors + "_institution",citationInfo.get(i));
                System.out.println("***Entered author institution in metaTable***");
                i++;
                if (citationInfo.get(i).equals("citation_author_email")) {
                    i++;
                    input.put("author" + authors + "_email",citationInfo.get(i));
                    System.out.println("***Entered author email in metaTable***");
                    authors++;
                }
                authors++;
            }
        }
        i++;
        /* 
           This check is incase the authors in the metaTable/citationInfo 
           are presented in a different order
        */
        if (i == citationInfo.size()) {
            // Restart search
            i = 0;
            maxCheck++;
            /* 
                This if statement is neccessary incase citationInfo 
                is missing one the authors in the metaTable
            */
            if (maxCheck == maxAuth + 1)
                break;
        }
    }
    return input;
}

public static void main(String[] args) throws IOException, Exception {
    
    HashMap<String, String> metaTable = new HashMap<>();
    String input;
    
    // File chooser. Allows user to select any directory
    JFileChooser chooser = new JFileChooser();
    chooser.setCurrentDirectory(new java.io.File("."));
    chooser.setDialogTitle("Choose a folder");
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
    
    chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    chooser.setDialogTitle("Save the output spreadsheet.");
    chooser.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator")+ "Documents"));
    int returnValue = chooser.showSaveDialog(null);
    String outputDirectory = "tempfile.csv";
    
    if (returnValue == JFileChooser.APPROVE_OPTION) {
    	outputDirectory = chooser.getSelectedFile().getAbsolutePath();
		System.out.println(outputDirectory);
	}
    
    File file = new File(input);
    File[] files = file.listFiles();
    PrintWriter writer = new PrintWriter(outputDirectory + ".csv", "UTF-8");
    File csvFile = CSVgen(writer);
    
    // HTML Source code variables
    String url;
    String finalURL;
    ArrayList<String> html = new ArrayList<>();
    String doiURLstart = "https://doi.org/";
    
    // Calls appropriate parsers
    for (File f: files) {
        if (f.getName().startsWith("savedrecs") && f.getName().contains(".bib")) {
            // This file is a bib file that contains multiple articles
            metaTable = bibParser_multipleArticles(f.getPath(), csvFile, writer);
            // Call specific bib parser that takes these kinds of files
        }
        if (f.getName().contains(".ris")) {
            metaTable = risParser(f.getPath());
            if (metaTable.containsKey("URL")) {
                url = metaTable.get("URL");
                System.out.println("URL print test via URL: " + url);
                // Checks which HTML parser is needed if any
                if (url.contains("doi.org")) {
                    url = getFinalURL(url);
                }
                if (url.contains("ieeexplore")) {
                    html = fetchHTML(url);
                    metaTable = ieeeParser(metaTable, html);
                } else if (url.contains("asme.org")) {
                    // Call asme parser
                    metaTable = asmeParser(metaTable, url);
                } else if (url.contains("springer.org")) {
                    // Call springer parser
                    html = fetchHTML(url);
                    springerParser(metaTable, html);
                } else {
                    System.out.println("This URL does not have a supported parser.");
                }
            } else if (metaTable.containsKey("DOI")) {
                url = doiURLstart + metaTable.get("DOI");
                System.out.println("URL print test via DOI#: " + url);
                // Checks which HTML parser is needed, if any
                finalURL = getFinalURL(url);
                if (finalURL.contains("ieeexplore")) {
                    html = fetchHTML(finalURL);
                    metaTable = ieeeParser(metaTable, html);
                } else if (finalURL.contains("asme.org")) {
                    // Call asme parser
                    metaTable = asmeParser(metaTable, url);
                } else if (finalURL.contains("springer.org")) {
                    // Call springer parser
                    html = fetchHTML(finalURL);
                    springerParser(metaTable, html);
                } else {
                    System.out.println("This URL does not have a supported parser.");
                }
            } else {
                System.out.println("No URL found for this article.");
            }
            populateCSV(metaTable, csvFile, writer);
            System.out.println(f.getName());
        }
        if (f.getName().contains(".bib") && !f.getName().startsWith("savedrecs")) {
            metaTable = bibParser(f.getPath());
            if (metaTable.containsKey("URL")) {
                url = metaTable.get("URL");
                System.out.println("URL print test via URL: " + url);
                // Checks which HTML parser is needed if any
                if (url.contains("doi.org")) {
                    url = getFinalURL(url);
                }
                if (url.contains("ieeexplore")) {
                    // Call ieee parser
                    html = fetchHTML(url);
                    metaTable = ieeeParser(metaTable, html);
                } else if (url.contains("asme")) {
                    // Call asme parser
                    metaTable = asmeParser(metaTable, url);
                } else if (url.contains("springer")) {
                    html = fetchHTML(url);
                    metaTable = springerParser(metaTable, html);
                    // Call springer parser
                } else {
                    System.out.println("This URL does not have a supported parser.");
                }
            } else if (metaTable.containsKey("DOI")) {
                url = doiURLstart + metaTable.get("DOI");
                System.out.println("URL print test via DOI#: " + url);
                finalURL = getFinalURL(url);
                // Checks which HTML parser is needed, if any
                if (finalURL.contains("ieee")) {
                    html = fetchHTML(finalURL);
                    metaTable = ieeeParser(metaTable, html);
                } else if (finalURL.contains("asme")) {
                    // Call asme parser
                    metaTable = asmeParser(metaTable, url);
                } else if (finalURL.contains("springer")) {
                    // Call springer parser
                    html = fetchHTML(finalURL);
                    metaTable = springerParser(metaTable, html);
                } else {
                    System.out.println("This URL does not have a supported parser.");
                }
            } else {
                System.out.println("No URL found for this article.");
            }
            populateCSV(metaTable, csvFile, writer);
            System.out.println(f.getName());
        }
    }
    writer.close();
}

public static File CSVgen(PrintWriter writer) throws IOException { //This method sets the names for all the value types (published date, issue, journal, etc.)
    File tempfile = new File("tempfile.csv");
	/*if(tempfile.createNewFile()) {
            System.out.println("FILE CREATED");
	} else {
            System.out.println("FILE ALREADY EXISTS");
            throw new IOException("FILE ALREADY EXISTS");
	}*/
    writer.write("title,filename,source_fulltext_url,fulltext_url,version,copyright_statement,distribution_license,embargo_date,keywords,Abstract,author1_fname,author1_mname,author1_lname,author1_suffix,author1_email,author1_institution,author1_is_corporate,author2_fname,author2_mname,author2_lname,author2_suffix,author2_email,author2_institution,author2_is_corporate,author3_fname,author3_mname,author3_lname,author3_suffix,author3_email,author3_institution,author3_is_corporate,author4_fname,author4_mname,author4_lname,author4_suffix,author4_email,author4_institution,author4_is_corporate,author5_fname,author5_mname,author5_lname,author5_suffix,author5_email,author5_institution,author5_is_corporate,author6_fname,author6_mname,author6_lname,author6_suffix,author6_email,author6_institution,author6_is_corporate,article_desc,disciplines,comments,create_openurl,custom_citation,document_type,doi,honors_pub,issn,volnum,issnum,source_publication,fpage,lpage,peer_reviewed,mcnair,orcid_id,publication_date,season\n");
    return tempfile;
}
	
public static void populateCSV(HashMap<String, String> input, File csvFile, PrintWriter writer) throws IOException { //This method populates a single row representing an article.
    String bigString = "";
    bigString = csvHelper(input, "Title", bigString);
    bigString += "!!!put FileName Here!!!,";
    bigString = csvHelper(input, "URL", bigString);
    bigString = csvHelper(input, "fulltext_url", bigString);
    bigString = csvHelper(input, "Version", bigString);
    bigString = csvHelper(input, "copyright_statement", bigString);
    bigString = csvHelper(input, "Distribution Liscence", bigString);
    bigString = csvHelper(input, "Embargo Date", bigString);
    bigString = csvHelper(input, "Keywords", bigString);
    bigString = csvHelper(input, "Abstract", bigString);
    for(int i = 1; i <= 6; i++) {
        bigString = csvHelper(input, "author" + i + "_fname", bigString);
        bigString = csvHelper(input, "author" + i + "_mname", bigString);
        bigString = csvHelper(input, "author" + i + "_lname", bigString);
        bigString = csvHelper(input, "author" + i + "_suffix", bigString);
        bigString = csvHelper(input, "author" + i + "_email", bigString);
        bigString = csvHelper(input, "author" + i + "_institution", bigString);
        bigString = csvHelper(input, "author" + i + "_is_corporate", bigString);
    }
    bigString = csvHelper(input, "article_desc", bigString);
    bigString = csvHelper(input, "Disciplines", bigString);
    bigString = csvHelper(input, "comments", bigString);
    bigString = csvHelper(input, "create_openurl", bigString);
    bigString = csvHelper(input, "custom_citation", bigString);
    bigString = csvHelper(input, "Type", bigString);
    bigString = csvHelper(input, "DOI", bigString);
    bigString = csvHelper(input, "honors_pub", bigString);
    bigString = csvHelper(input, "ISSN", bigString);
    bigString = csvHelper(input, "volnum", bigString);
    bigString = csvHelper(input, "issnum", bigString);
    bigString = csvHelper(input, "source publication", bigString);
    bigString = csvHelper(input, "First page", bigString);
    bigString = csvHelper(input, "Last page", bigString);
    bigString = csvHelper(input, "Peer Reviewed", bigString);
    bigString = csvHelper(input, "mcnair", bigString);
    bigString = csvHelper(input, "orcid id", bigString);
    bigString = csvHelper(input, "publication_date", bigString);
    bigString = csvHelper(input, "Season", bigString);
    bigString += "\n";
    writer.write(bigString);
}

public static String csvHelper(HashMap<String, String> input, String check, String bigString) { //This method is just here to make the populate method not look like garbage.
    if(input.containsKey(check)) {
	bigString += "\"" + input.get(check) + "\"";
    }
    bigString += ",";
    return bigString;
}

/* ### HTML Source code methods ### */
public static String getFinalURL(String url) throws IOException {
    if (url.contains("asme.org")) {
        return url;
    }
    HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
    con.setInstanceFollowRedirects(false);
    con.connect();
    if (con.getResponseCode() == 403) {
        System.out.println("HTTP response code: 403 - Forbidden");
        return url;
    }
    if (con.getResponseCode() == 503) {
        System.out.println("HTTP response code: 503 - Service Unavailable");
        return url;
    }
    con.getInputStream();

    if (con.getResponseCode() == HttpURLConnection.HTTP_MOVED_PERM || con.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP) {
        String redirectUrl = con.getHeaderField("Location");
        return getFinalURL(redirectUrl);
    }
    System.out.println("URL TEST 7: " + url);
    return url;
}
    
// Alternative to Jsoup -- Puts HTML source code into ArrayList
public static ArrayList fetchHTML(String url) throws Exception {
    
    System.out.println("Grabbing HTML source code from: " + url);
    Scanner s;
    URL u;
    u = new URL(url);
    s = new Scanner(u.openStream());
    // Where the raw HTML source code will be stored
    ArrayList<String> html = new ArrayList<String>();
    int i = 0;
    while (s.hasNext()) {
        html.add(s.nextLine());
        //System.out.println(html.get(i));
        i++;
    }
    return html;
}

}
