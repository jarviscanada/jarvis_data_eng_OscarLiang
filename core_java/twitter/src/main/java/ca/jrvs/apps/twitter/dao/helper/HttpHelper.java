package ca.jrvs.apps.twitter.dao.helper;

import java.net.URI;
import org.apache.http.HttpResponse;

public interface HttpHelper {

  /**
   * Execute an HTTP POST call
   *
   * @param uri request uri
   * @return response
   */
  HttpResponse httpPost(URI uri);

  /**
   * Execute an HTTP GET call
   *
   * @param uri request uri
   * @return response
   */
  HttpResponse httpGet(URI uri);
}
