package htmlfetcher;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Jsoup imports
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;



/*
    This program will eventually be added to ParserTemplate, but for now I
    want to do testing here just to be cleaner. - Elias
*/

public class HTMLfetcher {
    
    public static String getFinalURL(String url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setInstanceFollowRedirects(false);
        con.connect();
        con.getInputStream();

        if (con.getResponseCode() == HttpURLConnection.HTTP_MOVED_PERM || con.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP) {
            String redirectUrl = con.getHeaderField("Location");
            return getFinalURL(redirectUrl);
        }
        return url;
    }
    
    // Not necessarily needed when using Jsoup
    public static ArrayList fetchHTML(String url) throws Exception {
        // HTML test
        System.out.println("Testing url print " + url);
        Scanner s;
        URL u;
        u = new URL(url);
        s = new Scanner(u.openStream());
        // Where the raw HTML source code will be stored
        ArrayList<String> html = new ArrayList<String>();
        int i = 0;
        while (s.hasNext()) {
            html.add(s.nextLine());
            System.out.println(html.get(i));
            i++;
        }
        return html;
    }

    public static void main(String[] args) throws Exception {
        
        /*
            The finished program will grab the URL from the .ris/.bib files.
            For testing purposes, I'm hard coding it.
        */
        String url = "https://doi.org/10.1007/s00779-016-0916-x";
        
        // Hardcoded hashmap for testing purposes
        HashMap<String, String> metaTable = new HashMap<>();
        metaTable.put("author1_fname", "Xiong");
        metaTable.put("author1_lname", "Luo");
        metaTable.put("author2_fname", "Yixuan");
        metaTable.put("author2_lname", "Lv");
        metaTable.put("author3_fname", "Mi");
        metaTable.put("author3_lname", "Zhou");
        metaTable.put("author4_fname", "Weiping");
        metaTable.put("author4_lname", "Wang");
        metaTable.put("author5_fname", "Wenbing");
        metaTable.put("author5_lname", "Zhao");
        
        /*
            fetchedURL method is used in case the URL provided is a redirect link.
            Redirect links will not give us the HTML source code we need.
        */
        String fetchedURL = getFinalURL(url);
        
        Document doc;
        doc = Jsoup.connect(fetchedURL).get();
        
        // Get all meta tags
        Elements meta = doc.select("meta");
        
        String[] metaTags = new String[meta.size()];
        for (int i = 0; i < meta.size(); i++) {
            metaTags[i] = meta.get(i).toString();
            System.out.println(metaTags[i]);
        }
        // Need authorX_institution and authorX_email <<< The only affiliations we need
        
        }
    }
