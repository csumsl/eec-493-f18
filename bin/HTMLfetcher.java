package htmlfetcher;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

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
        String url = "https://www.doi.org/10.1109/EIT.2016.7535223";
        ArrayList<String> html = new ArrayList<String>();
        
        /*
            fetchedURL method is used in case the URL provided is a redirect link.
            Redirect links will not give us the HTML source code we need.
        */
        String fetchedURL = getFinalURL(url);
        
        // The ArrayList html now holds the entire source code for the website
        html = fetchHTML(fetchedURL);
        
        // TODO
        /*
            Look for missing metadata by referencing the hashmap.
            Look for authors names and see if they have an affiliation listed
            somewhere in the HTML code.
        */
        }
    }
