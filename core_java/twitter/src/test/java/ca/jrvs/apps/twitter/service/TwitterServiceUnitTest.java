package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.utils.JsonUtils;
import ca.jrvs.apps.twitter.utils.TweetUtils;
import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {

  @Mock
  CrdDao<Tweet, String> dao;

  @InjectMocks
  TwitterService service;

  Tweet expectedTweet;

  @Before
  public void setUp() {
    service = new TwitterService(dao);
    // Have the mock DAO return a valid tweet
    String tweetJsonStr = "{\n"
        + "    \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
        + "    \"id\":1097607853932564480,\n"
        + "    \"id_str\":\"1097607853932564480\",\n"
        + "    \"text\":\"test with loc223\",\n"
        + "    \"entities\":{\n"
        + "        \"hashtags\":[],"
        + "        \"user_mentions\":[]"
        + "    },\n"
        + "    \"coordinates\":null,\n"
        + "    \"retweet_count\":0,\n"
        + "    \"favorite_count\":0,\n"
        + "    \"favorited\":false,\n"
        + "    \"retweeted\":false\n"
        + "}";
    try {
      this.expectedTweet = JsonUtils.toObjectFromJson(tweetJsonStr, Tweet.class);
    } catch (IOException e) {
      throw new RuntimeException("Failed to convert sample tweet to JSON", e);
    }
  }

  @Test
  public void postTweet() {
    // Failed request
    String text = "text goes here #twitter";
    Double lat = 57d;
    Double lon = -23d;
    when(dao.create(notNull())).thenReturn(this.expectedTweet);

    // Invalid arguments
    String badText = "This text is way too long. Twitter will never accept this text because it is "
        + "over 140 characters long. Because of this, the function is going to return an "
        + "IllegalArgumentException and pass the test.";
    Double badLonPos = 190d;
    Double badLonNeg = -181d;
    Double badLatPos = 95d;
    Double badLatNeg = -91d;
    try {
      service.postTweet(TweetUtils.buildTweet(badText, lon, lat));
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try {
      service.postTweet(TweetUtils.buildTweet(text, badLonPos, lat));
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try {
      service.postTweet(TweetUtils.buildTweet(text, badLonNeg, lat));
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try {
      service.postTweet(TweetUtils.buildTweet(text, lon, badLatPos));
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try {
      service.postTweet(TweetUtils.buildTweet(text, lon, badLatNeg));
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    // Happy path
    Tweet resultTweet = service.postTweet(TweetUtils.buildTweet(text, lon, lat));
    assertNotNull(resultTweet);
    assertNotNull(resultTweet.getText());
  }

  @Test
  public void showTweet() {
    when(dao.findById(notNull())).thenReturn(this.expectedTweet);
    try {
      service.showTweet("2948294812n1293", null);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try {
      service.showTweet("12.52515156", null);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try {
      service.showTweet("abcdef", null);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    Tweet resultTweet = service.showTweet("1097607853932564480", null);
    assertNotNull(resultTweet);
    assertNotNull(resultTweet.getText());
  }

  @Test
  public void deleteTweets() {
    when(dao.deleteById(notNull())).thenReturn(this.expectedTweet);
    try {
      service.deleteTweets(new String[]{"123123123", "1252957219", "24215k123123", "12091752"});
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    List<Tweet> resultTweets = service.deleteTweets(new String[]{"1097607853932564480",
        "109284124124124"});
    assertEquals(2, resultTweets.size());
    assertNotNull(resultTweets.get(1));
    assertNotNull(resultTweets.get(1).getText());
  }
}