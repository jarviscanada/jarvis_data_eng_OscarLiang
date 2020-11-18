package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.utils.TweetUtils;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoIntTest {

  private TwitterDao dao;

  @Before
  public void setUp() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    HttpHelper helper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    this.dao = new TwitterDao(helper);
  }

  @Test
  public void create() {

    String hashtag = "wow";
    String text = "this is a tweet #" + hashtag + " " + System.currentTimeMillis();
    Coordinates coordinates = new Coordinates();
    Double lon = 45d;
    Double lat = -45d;
    Tweet resultTweet = dao.create(TweetUtils.buildTweet(text, lon, lat));

    assertEquals(text, resultTweet.getText());
    assertNotNull(resultTweet.getCoordinates());
    List<Double> resultCoordinates = resultTweet.getCoordinates().getCoordinates();
    assertEquals(lon, resultCoordinates.get(0));
    assertEquals(lat, resultCoordinates.get(1));
    assertEquals(hashtag, resultTweet.getEntities().getHashtags().get(0).getText());
  }

  @Test
  public void findById() {
    String hashtag = "cool";
    String text = "amazing new tweet #" + hashtag + " " + System.currentTimeMillis();
    Double lon = 10d;
    Double lat = -10d;
    Tweet createdTweet = dao.create(TweetUtils.buildTweet(text, lon, lat));
    String tweetId = createdTweet.getIdStr();
    Tweet resultTweet = dao.findById(tweetId);

    assertEquals(text, resultTweet.getText());
    assertNotNull(resultTweet.getCoordinates());
    List<Double> resultCoordinates = resultTweet.getCoordinates().getCoordinates();
    assertEquals(lon, resultCoordinates.get(0));
    assertEquals(lat, resultCoordinates.get(1));
    assertEquals(hashtag, resultTweet.getEntities().getHashtags().get(0).getText());
  }

  @Test
  public void deleteById() {
    String hashtag = "sad";
    String text = "you won't see this tweet #" + hashtag + " " + System.currentTimeMillis();
    Double lon = 22d;
    Double lat = -22d;
    Tweet createdTweet = dao.create(TweetUtils.buildTweet(text, lon, lat));
    String tweetId = createdTweet.getIdStr();
    Tweet resultTweet = dao.deleteById(tweetId);

    assertEquals(text, resultTweet.getText());
    assertNotNull(resultTweet.getCoordinates());
    List<Double> resultCoordinates = resultTweet.getCoordinates().getCoordinates();
    assertEquals(lon, resultCoordinates.get(0));
    assertEquals(lat, resultCoordinates.get(1));
    assertEquals(hashtag, resultTweet.getEntities().getHashtags().get(0).getText());

    // Exception expected because tweet should not exist anymore.
    try {
      dao.findById(tweetId);
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }
  }
}