multi-threaded-crawler
======================

Download the following dependencies:
1. Jsoup for Java
2. Boilerpipe 

Usage :

Currently the crawler is configured to fetch urls and content from the webpages.

To use the crawler,

1. Create an instance of Crawled Sites.
CrawledSites crawledSites = new CrawledSites();

2. Add seed pages.
 crawledSites.addListOfSites("http://digg.com");

3.  Call the initialize crawling method with the following attributes.
initializeCrawling(int numberOfSites,CrawledSites crawledSites,int urlsToFetch);

4. To view, list of urls fetched.
crawledSites.getListOfSites();

5. To view , list of crawled sites.
crawledSites.getCrawledSites();












