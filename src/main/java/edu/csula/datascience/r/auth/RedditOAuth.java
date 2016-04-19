package edu.csula.datascience.r.auth;

import java.util.HashMap;
import java.util.Map;

import net.dean.jraw.http.UserAgent;
import net.dean.jraw.RedditClient;
import net.dean.jraw.http.oauth.Credentials;
import net.dean.jraw.http.oauth.OAuthData;
import net.dean.jraw.http.oauth.OAuthException;

public class RedditOAuth {

  private final String USERNAME = "REDDIT_USERNAME";
  private final String PASSWORD = "REDDIT_PASSWORD";
  private final String APP_ID = "REDDIT_APP_ID";
  private final String APP_SECRET = "REDDIT_APP_SECRET";

  private Map getVariables(){
    Map<String, String> envMap = new HashMap<>(4);
    envMap.put(USERNAME, System.getenv(USERNAME));
    envMap.put(PASSWORD, System.getenv(PASSWORD));
    envMap.put(APP_ID, System.getenv(APP_ID));
    envMap.put(APP_SECRET, System.getenv(APP_SECRET));
    return envMap;
  }

  public RedditClient authenticate(){
    Map<String, String> envMap = getVariables();
    UserAgent myUserAgent = UserAgent.of("desktop", "awesomescript", "v0.1", "victorious-secret");
    RedditClient redditClient = new RedditClient(myUserAgent);
    Credentials credentials = Credentials.script(envMap.get(USERNAME), envMap.get(PASSWORD),
        envMap.get(APP_ID), envMap.get(APP_SECRET));
    try{
      OAuthData authData= redditClient.getOAuthHelper().easyAuth(credentials);
      redditClient.authenticate(authData);
    }
    catch(OAuthException ex){
      ex.printStackTrace();
    }
    return redditClient;
  }

  public static void main(String[] args) throws Exception{
    RedditOAuth auth = new RedditOAuth();
    RedditClient redditClient = auth.authenticate();
    System.out.println(redditClient.me());
    System.out.println(redditClient.getOAuthData());
  }
}
