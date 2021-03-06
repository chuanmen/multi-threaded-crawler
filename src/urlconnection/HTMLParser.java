package urlconnection;

import crawler.CrawledSites;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import de.l3s.boilerpipe.extractors.DefaultExtractor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pagecontent.FetchPage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLParser {

    private final static Pattern Filters= Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g"
            + "|png|tiff?|mid|mp2|mp3|mp4"
            + "|wav|avi|mov|mpeg|ram|m4v|pdf"
            + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

    public static void getLinks(CrawledSites crawledSites,Boolean getContent) throws IOException, BoilerpipeProcessingException {
        String url = crawledSites.getSeedUrl();
        Elements links = null;
        try{
        Document doc= Jsoup.connect(url).get();
        crawledSites.addCrawledSites(url);
        links = doc.select("a[href]");
        }catch(Exception e){
        links=null;
        }
        finally{
               if(links!=null){
        for (Element link : links){
            if(shouldVisit(link.attr("abs:href").toString())){
              crawledSites.addListOfSites(link.attr("abs:href").toString());
              if(getContent==true)
                FetchPage.getContent(link.attr("abs:href").toString(),crawledSites);

            }


            }
        }
        }
    }

    public static String getDomain(String url) throws MalformedURLException {
              String cleanUrl = url.toLowerCase().trim();
              URL link = new URL(cleanUrl);
              String domain = link.getHost();
              System.out.println(domain);
             return domain;



    }

   public static boolean shouldVisit(String url){
      String cleanUrl = url.toLowerCase().trim();
      return !((Filters.matcher(cleanUrl).matches()));

   }


}
