package ca.jrvs.apps.twitter.dao.helper;

import java.io.IOException;
import java.net.URI;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TwitterHttpHelper implements HttpHelper {

  private final OAuthConsumer consumer;
  private final HttpClient httpClient;
  final Logger logger = LoggerFactory.getLogger(HttpHelper.class);

  /**
   * Constructor Setup dependencies using secrets
   *
   * @param consumerKey
   * @param consumerSecret
   * @param accessToken
   * @param tokenSecret
   */
  public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken,
      String tokenSecret) {
    consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
    consumer.setTokenWithSecret(accessToken, tokenSecret);
    /**
     * Default = single connection. Discuss source code if time permits
     */
    httpClient = new DefaultHttpClient();
  }

  /**
   * Default constructor (not used for now)
   */
  public TwitterHttpHelper() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    consumer = new CommonsHttpOAuthConsumer(consumerKey,
        consumerSecret);
    consumer.setTokenWithSecret(accessToken, tokenSecret);
    httpClient = new DefaultHttpClient();
  }

  /**
   * Execute an HTTP POST call
   *
   * @param uri request uri
   * @return response
   */
  @Override
  public HttpResponse httpPost(URI uri) {
    HttpPost request = new HttpPost(uri);
    try {
      return httpRequest(request);
    } catch (OAuthException | IOException ex) {
      throw new RuntimeException("Request failed", ex);
    }
  }

  /**
   * Execute an HTTP GET call
   *
   * @param uri request uri
   * @return response
   */
  @Override
  public HttpResponse httpGet(URI uri) {
    HttpGet request = new HttpGet(uri);
    try {
      return httpRequest(request);
    } catch (OAuthException | IOException ex) {
      throw new RuntimeException("Request failed", ex);
    }
  }

  /**
   * Execute a generic HTTP request
   *
   * @param request
   * @return response
   */
  private HttpResponse httpRequest(HttpRequestBase request) throws OAuthException, IOException {

    consumer.sign(request);
    return httpClient.execute(request);
  }
}
