package ca.jrvs.apps.twitter.dao.helper;

import java.net.URI;
import junit.framework.TestCase;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TwitterHttpHelperTest extends TestCase {

  @Test
  public void httpPost() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    HttpHelper helper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    HttpResponse response = helper
        .httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=testTweet"));
    System.out.println(EntityUtils.toString(response.getEntity()));
  }
}