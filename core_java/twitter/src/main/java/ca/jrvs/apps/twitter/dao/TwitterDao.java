package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.JsonUtils;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class TwitterDao implements CrdDao<Tweet, String> {

  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy";

  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  private static final int HTTP_OK = 200;

  private HttpHelper httpHelper;

  @Autowired
  public TwitterDao(HttpHelper httpHelper) { this.httpHelper = httpHelper; }

  /**
   * Create an entity (Tweet) for the underlying storage
   *
   * @param entity entity to be created
   * @return created entity
   */
  @Override
  public Tweet create(Tweet entity) {
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    // Check if tweet has coordinates
    String coordinatesString = "";
    Coordinates coordinates = entity.getCoordinates();
    if (coordinates != null) {
      List<Double> coordinateValues = coordinates.getCoordinates();
      coordinatesString = AMPERSAND + "long" + EQUAL + coordinateValues.get(0) + AMPERSAND + "lat" +
          EQUAL + coordinateValues.get(1);
    }
    String uri = API_BASE_URI + POST_PATH + QUERY_SYM + "status" + EQUAL +
        percentEscaper.escape(entity.getText()) + coordinatesString;
    HttpResponse response = httpHelper.httpPost(URI.create(uri));

    return checkTweet(response, HTTP_OK);
  }

  /**
   * Find an entity (Tweet) by its id
   *
   * @param s entity id
   * @return Tweet entity
   */
  @Override
  public Tweet findById(String s) {
    String uri = API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL + s;
    HttpResponse response = httpHelper.httpGet(URI.create(uri));

    return checkTweet(response, HTTP_OK);
  }

  /**
   * Delete an entity (Tweet) by its ID
   *
   * @param s of the entity to be deleted
   * @return deleted entity
   */
  @Override
  public Tweet deleteById(String s) {
    String uri = API_BASE_URI + DELETE_PATH + "/" +  s + ".json";
    HttpResponse response = httpHelper.httpPost(URI.create(uri));

    return checkTweet(response, HTTP_OK);
  }

  private Tweet checkTweet (HttpResponse response, Integer expectedStatusCode) {
    int actualStatusCode = response.getStatusLine().getStatusCode();
    if (actualStatusCode != expectedStatusCode) {
      throw new RuntimeException("Unexpected status code: " + actualStatusCode);
    }
    HttpEntity entity = response.getEntity();
    if (entity == null) {
      throw new RuntimeException("Empty response body");
    }

    String jsonString;
    try {
      jsonString = EntityUtils.toString(entity);
    } catch (IOException e) {
      throw new RuntimeException("Could not convert response to string", e);
    }

    Tweet tweet = null;
    try {
      tweet = JsonUtils.toObjectFromJson(jsonString, Tweet.class);
    } catch (IOException e) {
      throw new RuntimeException("Could not convert response to JSON", e);
    }
    return tweet;
  }
}
