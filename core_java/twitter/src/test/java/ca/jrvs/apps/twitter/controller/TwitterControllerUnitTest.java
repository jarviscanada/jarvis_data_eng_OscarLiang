package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.utils.JsonUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {

  @Mock
  Service service;

  @InjectMocks
  TwitterController controller;

  Tweet expectedTweet;

  @Before
  public void setUp() {
    controller = new TwitterController(service);
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
    when(service.postTweet(notNull())).thenReturn(expectedTweet);
    // Test bad args
    try {
      controller.postTweet(new String[]{"post", "text"});
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try {
      controller.postTweet(new String[]{"post", "text", "23:-5", "rsitensr"});
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try {
      controller.postTweet(new String[]{"post", "text", "2k5:-3"});
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try {
      controller.postTweet(new String[]{"post", "text", "24:"});
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    Tweet result = controller.postTweet(new String[]{"post", "text", "-19.5:74.3"});
    assertNotNull(result);
    assertNotNull(result.getText());
  }

  @Test
  public void showTweet() {
    when(service.showTweet(notNull(), any())).thenReturn(expectedTweet);
    try {
      controller.showTweet(new String[]{"show"});
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try {
      controller.showTweet(new String[]{"show", "01925125917231", "show"});
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    Tweet resultTweet = controller.showTweet(new String[]{"show", "01925712571212"});
    assertNotNull(resultTweet);
    assertNotNull(resultTweet.getText());
  }

  @Test
  public void deleteTweet() {
    List<Tweet> tweets = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      tweets.add(expectedTweet);
    }
    when(service.deleteTweets(notNull())).thenReturn(tweets);
    try {
      controller.deleteTweet(new String[]{"delete"});
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try {
      controller.deleteTweet(new String[]{"delete", "01925125917231,125120957125", "delete"});
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    controller.deleteTweet(new String[]{"delete", "01925712571212,125901251729"}).forEach(tweet -> {
      assertNotNull(tweet);
      assertNotNull(tweet.getText());
    });
  }
}