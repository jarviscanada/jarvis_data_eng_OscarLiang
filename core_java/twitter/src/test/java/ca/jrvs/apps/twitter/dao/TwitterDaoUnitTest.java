package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.utils.JsonUtils;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.utils.TweetUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {

  @Mock
  HttpHelper mockHelper;

  @InjectMocks
  TwitterDao dao;

  @Test
  public void create() throws Exception {
    // test failed request
    String hashtag = "#abc";
    String text = "sometext " + hashtag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      dao.create(TweetUtils.buildTweet(text, lon, lat));
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

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
    when(mockHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonUtils.toObjectFromJson(tweetJsonStr, Tweet.class);
    doReturn(expectedTweet).when(spyDao).checkTweet(any(), anyInt());
    Tweet tweet = spyDao.create(TweetUtils.buildTweet(text, lon, lat));
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
  }

  @Test
  public void findById() throws Exception {
    when(mockHelper.httpGet(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      dao.findById("1294712407124");
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

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
    when(mockHelper.httpGet(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonUtils.toObjectFromJson(tweetJsonStr, Tweet.class);
    doReturn(expectedTweet).when(spyDao).checkTweet(any(), anyInt());
    Tweet tweet = spyDao.findById("10295712957120");
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
  }

  @Test
  public void deleteById() throws Exception {
    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      dao.deleteById("192512512957");
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

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
    when(mockHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonUtils.toObjectFromJson(tweetJsonStr, Tweet.class);
    doReturn(expectedTweet).when(spyDao).checkTweet(any(), anyInt());
    Tweet tweet = spyDao.deleteById("10295712957120");
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
  }

  @Before
  public void setUp() throws Exception {
    dao = new TwitterDao(mockHelper);
  }
}