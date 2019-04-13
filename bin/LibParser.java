package LibParser;

/*
    This template serves as the basis for all our future parsers.
    With the use of a hashmap, we can easily organize the parsed metadata
    in a clean data structure.
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import javax.swing.*;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.filechooser.*;
import javax.swing.JTextArea;
import java.io.IOException;
import java.io.OutputStream;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/* 
    For writing to an excel sheet in the future, we will most likely be using
    Apache POI (https://poi.apache.org/)
               (https://www.callicoder.com/java-write-excel-file-apache-poi/)
*/


class LibParser extends JFrame implements ActionListener{

   static JFrame ParserWin = new JFrame("Choose files to be parsered");
   static JPanel pan = new JPanel();
   static JPanel pan2 = new JPanel();
   static JLabel l;
   LibParser(){}
   
   public static void main(String args[]){
      //JFrame ParserWin = new JFrame("Choose files to be parsered");
      ParserWin.setSize(400, 600);
      ParserWin.setVisible(true);
      ParserWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JButton buttonS = new JButton("Select File"); 
      JButton buttonH = new JButton("Help");
      LibParser Pwin = new LibParser();
      buttonS.addActionListener(Pwin); 
      buttonH.addActionListener(Pwin);
      //JPanel pan = new JPanel();
      //JPanel pan2 = new JPanel();
      JTextArea console = new JTextArea(25, 30);
      PrintStream printStream = new PrintStream(new CustomOutputStream(console));
      System.setOut(printStream);
      System.setErr(printStream);      
      console.setEditable(false);
      pan.add(buttonS);
      pan.add(buttonH);
      pan2.add(console);
      pan2.add(new JScrollPane(console));
      l = new JLabel("no file selected");
      pan.add(l);
      ParserWin.add(pan);
      ParserWin.show();
   }
   public void actionPerformed(ActionEvent BEvent){
      String choice = BEvent.getActionCommand();
      
      if(choice.equals("Select File")){
         JFileChooser FChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
         int n = FChooser.showOpenDialog(null);
         if(n == JFileChooser.APPROVE_OPTION) {
            ParserWin.remove(pan);
            ParserWin.add(pan2);
            ParserWin.show();
            l.setText(FChooser.getSelectedFile().getAbsolutePath());
            HashMap<String, String> metaTable = new HashMap<>();
            try{
            metaTable = risParser(FChooser.getSelectedFile().getAbsolutePath());
            }catch(FileNotFoundException fnfe){
               System.out.println(fnfe.getMessage());
            }catch(IOException ioe){
               System.out.println(ioe.getMessage());
            }
            Set set = metaTable.entrySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext()){
               Map.Entry mapEntry = (Map.Entry)iterator.next();
               System.out.println("the Key is " + mapEntry.getKey() + " and the Value is " + mapEntry.getValue() + "\n");
            }
        System.out.println(FChooser.getSelectedFile().getAbsolutePath() + " has been parsed.");
        }
         else
            l.setText("the user cancelled the operation");
      }
      else {
         l.setText("the user cancelled the operation");
         /*
         try {
            URL myURL = new URL("https://github.com/csumsl/eec-493-f18/blob/master/README.md");
            URLConnection myURLConnection = myURL.openConnection();
            myURLConnection.connect();
         } 
         catch (MalformedURLException mfue) { 
            System.out.println(mfue.getMessage());
         } 
         catch (IOException ioe) {   
            System.out.println(ioe.getMessage());
         }
         */
      }
   }

/*
public class ParserRis extends Frame
{     public static void main(String[] args) throws IOException {new ParserRis();}
         ParserRis(){new ParserWD(this);}
 }   
      class ParserWD extends Dialog
      { TextField parserInstrc = new TextField(5);
        Button Fbutton = new Button("Choose File");
        Button Hbutton = new Button("Help");
        
        ParserWD(Frame fr)
        {  super(fr, "Library File Parser", true);
      addWindowListener(new WindowAdapter()
      {  public void windowClosing(WindowEvent e)
         {  dispose();
            System.exit(0);
         }
      });
      
      Panel p1 = new Panel(), p2 = new Panel(), p3 = new Panel();
      Label lbtest = new Label("Select files:");
      p1.add(lbtest);
      p1.add(parserInstrc);
      p2.add(Fbutton);
      p3.add(Hbutton);
      setLayout(new BorderLayout());
      add("North", p1);
      add("West", p2);
      add("East", p3);
      
      Fbutton.addActionListener(new ActionListener()
      { public void actionPerformed(ActionEvent ae)
         {lbtest.setText("file parsed");}
         
      });
      Dimension dim = getToolkit().getScreenSize();
      setSize(500, 250);
      setLocation(dim.width/8, dim.height/8);
      show();
      }
 
     */   
     /*

        
        metaTable = risParser(input);
        Set set = metaTable.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Map.Entry mapEntry = (Map.Entry)iterator.next();
            System.out.println("the Key is " + mapEntry.getKey() + " and the Value is " + mapEntry.getValue());
        }
        System.out.println(input + " has been parsed.");
    }
    */
    
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
        - First Page
        - Last Page
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
                    authors++;
                }
                if(name.length == 2) {
                    if((name[0].charAt(name[0].length()-1))==(',')) {
                        name[0] =  name[0].replace(",","");
                        metadataTable.put("author"+authors+"_lname", name[0]);
                        metadataTable.put("author"+authors+"_fname", name[1]);
                        authors++;
                    } else {
                        metadataTable.put("author"+authors+"_fname", name[0]);
                        metadataTable.put("author"+authors+"_lname", name[1]);
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
                } else if(temp == 1) {
                    metadataTable.put(Key, metadataTable.get(Key) + ", " + Map);
                }
            }
            if(Key.equals("DA")){Key = "date";metadataTable.put(Key, Map);}
            if(Key.equals("TI")){Key = "Title";metadataTable.put(Key, Map);}
            if(Key.equals("JO")){Key = "source publication";metadataTable.put(Key, Map);}
            if(Key.equals("SP")){Key = "First page";metadataTable.put(Key, Map);}
            if(Key.equals("EP")){Key = "Last page";metadataTable.put(Key, Map);}
            if(Key.equals("VL")){Key = "volnum";metadataTable.put(Key, Map);}
            if(Key.equals("IS")){Key = "issnum";metadataTable.put(Key, Map);}
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


}
