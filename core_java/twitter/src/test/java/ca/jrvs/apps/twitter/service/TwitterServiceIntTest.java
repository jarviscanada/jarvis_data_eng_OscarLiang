package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.utils.TweetUtils;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceIntTest {

  Service service;

  @Before
  public void setUp() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    HttpHelper helper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    CrdDao dao = new TwitterDao(helper);
    this.service = new TwitterService(dao);
  }

  @Test
  public void postTweet() {
    String hashtag = "wow";
    String text = "this is a tweet #" + hashtag + " " + System.currentTimeMillis();
    Coordinates coordinates = new Coordinates();
    Double lon = 45d;
    Double lat = -45d;
    Tweet resultTweet = service.postTweet(TweetUtils.buildTweet(text, lon, lat));

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
    Tweet createdTweet = service.postTweet(TweetUtils.buildTweet(text, lon, lat));
    String tweetId = createdTweet.getIdStr();
    Tweet resultTweet = service.showTweet(tweetId, null);

    assertEquals(text, resultTweet.getText());
    assertNotNull(resultTweet.getCoordinates());
    List<Double> resultCoordinates = resultTweet.getCoordinates().getCoordinates();
    assertEquals(lon, resultCoordinates.get(0));
    assertEquals(lat, resultCoordinates.get(1));
    assertEquals(hashtag, resultTweet.getEntities().getHashtags().get(0).getText());
  }

  @Test
  public void deleteTweets() {
    final int numTweets = 3;
    String[] tweetIds = new String[numTweets];
    String[] tweetTexts = new String[numTweets];
    String hashtag = "sad";
    Double lon = 22d;
    Double lat = -22d;
    for (int i = 0; i < numTweets; i++) {
      tweetTexts[i] = "you won't see this tweet #" + hashtag + " " + System.currentTimeMillis();
      Tweet createdTweet = service.postTweet(TweetUtils.buildTweet(tweetTexts[i], lon, lat));
      tweetIds[i] = createdTweet.getIdStr();
    }
    List<Tweet> deletedTweets = service.deleteTweets(tweetIds);

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
        service.showTweet(tweetIds[i], null);
        fail();
      } catch (RuntimeException e) {
        assertTrue(true);
      }
    }
  }
}