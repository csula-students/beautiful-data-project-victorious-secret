package edu.csula.datascience.r;

import edu.csula.datascience.r.acquisition.RedditSource;
import edu.csula.datascience.r.auth.RedditOAuth;
import net.dean.jraw.RedditClient;

/**
 * Created by tj on 4/21/16.
 */
public class CollectDriver {
  public static void main(String[] args) throws Exception{
    RedditSource source = new RedditSource();
    source.downloadSubmissions();
  }
}
