package threading;
import crawler.CrawledSites;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import urlconnection.HTMLParser;

import java.io.IOException;

public class Crawler implements Runnable {
    private int numberOfLinksToCrawl;
    private CrawledSites crawledSites;

    public CrawledSites getCrawledSites() {
        return crawledSites;
    }

    public void setCrawledSites(CrawledSites crawledSites,int numberOfLinksToCrawl) {
        this.crawledSites = crawledSites;
        this.numberOfLinksToCrawl=numberOfLinksToCrawl;
    }



    Crawler(int number){
       this.numberOfLinksToCrawl =number;

    }

    Crawler(CrawledSites crawledSites, int number){
        this.crawledSites = crawledSites;
        this.numberOfLinksToCrawl=number;
    }
    public int getNumberOfLinksToCrawl() {
        return numberOfLinksToCrawl;
    }

    public void setNumberOfLinksToCrawl(int numberOfLinksToCrawl) {
        this.numberOfLinksToCrawl = numberOfLinksToCrawl;
    }

    @Override

    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.
        try {

            while(crawledSites.getListOfSites().size()<numberOfLinksToCrawl){
            HTMLParser.getLinks(crawledSites,false);

             }
          } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (BoilerpipeProcessingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }

    public static void initializeCrawling(int numberOfThreads,CrawledSites crawledSites,int maximumLimit){
       for(int i=0;i<numberOfThreads;++i){
        new Thread(new Crawler(crawledSites,maximumLimit)).start();
        }
    }

    public static void addSeedPages(CrawledSites crawledSites, String url){
                     crawledSites.addListOfSites(url);

    }
    public static void main(String[] args){
        CrawledSites crawledSites = new CrawledSites();
        crawledSites.addListOfSites("http://digg.com");
        crawledSites.addListOfSites("http://");
        initializeCrawling(3,crawledSites,10000);

    }
}
