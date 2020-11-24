package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterControllerIntTest {

  private Controller controller;

  @Before
  public void setUp() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    HttpHelper helper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    CrdDao dao = new TwitterDao(helper);
    Service service = new TwitterService(dao);
    this.controller = new TwitterController(service);
  }

  @Test
  public void postTweet() {
    String hashtag = "wow";
    String text = "this is a tweet #" + hashtag + " " + System.currentTimeMillis();
    Double lon = 45d;
    Double lat = -45d;
    Tweet resultTweet = controller.postTweet(new String[]{"post", text, lat + ":" + lon});

    assertEquals(text, resultTweet.getText());
    assertNotNull(resultTweet.getCoordinates());
    List<Double> resultCoordinates = resultTweet.getCoordinates().getCoordinates();
    assertEquals(lon, resultCoordinates.get(0));
    assertEquals(lat, resultCoordinates.get(1));
    assertEquals(hashtag, resultTweet.getEntities().getHashtags().get(0).getText());
  }

  @Test
  public void showTweet() {
    String hashtag = "cool";
    String text = "amazing new tweet #" + hashtag + " " + System.currentTimeMillis();
    Double lon = 10d;
    Double lat = -10d;
    Tweet createdTweet = controller.postTweet(new String[]{"post", text, lat + ":" + lon});
    String tweetId = createdTweet.getIdStr();
    Tweet resultTweet = controller.showTweet(new String[]{"show", tweetId});

    assertEquals(text, resultTweet.getText());
    assertNotNull(resultTweet.getCoordinates());
    List<Double> resultCoordinates = resultTweet.getCoordinates().getCoordinates();
    assertEquals(lon, resultCoordinates.get(0));
    assertEquals(lat, resultCoordinates.get(1));
    assertEquals(hashtag, resultTweet.getEntities().getHashtags().get(0).getText());
  }

  @Test
  public void deleteTweet() {
    final int numTweets = 3;
    String[] tweetIds = new String[numTweets];
    StringBuilder tweetIdsString = new StringBuilder();
    String[] tweetTexts = new String[numTweets];
    String hashtag = "sad";
    Double lon = 22d;
    Double lat = -22d;
    for (int i = 0; i < numTweets; i++) {
      tweetTexts[i] = "you won't see this tweet #" + hashtag + " " + System.currentTimeMillis();
      Tweet createdTweet = controller
          .postTweet(new String[]{"post", tweetTexts[i], lat + ":" + lon});
      tweetIds[i] = createdTweet.getIdStr();
      tweetIdsString.append(createdTweet.getIdStr());
      tweetIdsString.append(",");
    }
    tweetIdsString.deleteCharAt(tweetIdsString.lastIndexOf(","));
    List<Tweet> deletedTweets = controller
        .deleteTweet(new String[]{"delete", tweetIdsString.toString()});

    for (int i = 0; i < numTweets; i++) {
      Tweet currentTweet = deletedTweets.get(i);
      assertEquals(tweetTexts[i], currentTweet.getText());
      assertNotNull(currentTweet.getCoordinates());
      List<Double> resultCoordinates = currentTweet.getCoordinates().getCoordinates();
      assertEquals(lon, resultCoordinates.get(0));
      assertEquals(lat, resultCoordinates.get(1));
      assertEquals(hashtag, currentTweet.getEntities().getHashtags().get(0).getText());

      // Exception expected because tweet should not exist anymore.
      try {
        controller.showTweet(new String[]{"show", tweetIds[i]});
        fail();
      } catch (RuntimeException e) {
        assertTrue(true);
      }
    }
  }
}