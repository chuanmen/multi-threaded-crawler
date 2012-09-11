package pagecontent;

import crawler.CrawledSites;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;

import java.io.IOException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: iceman
 * Date: 9/10/12
 * Time: 4:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class FetchPage {

    public static void getContent(String url,CrawledSites crawledSites) throws IOException, BoilerpipeProcessingException {
        boolean raiseFlag=false;
        String text=null;
        URL urlToGet = new URL(url);
        System.out.println(urlToGet);
        try{
            text = ArticleExtractor.INSTANCE.getText(urlToGet);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(raiseFlag==false)
                crawledSites.addContent(url,text);


        }

    }

}
