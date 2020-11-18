package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class TwitterService implements Service {

  private final CrdDao<Tweet, String> dao;

  @Autowired
  public TwitterService(CrdDao<Tweet, String> dao) {
    this.dao = dao;
  }

  /**
   * Validate and post a user input Tweet
   *
   * @param tweet tweet to be created
   * @return created tweet
   * @throws IllegalArgumentException if text exceeds max number of allowed characters or lat/long
   *                                  out of range
   */
  @Override
  public Tweet postTweet(Tweet tweet) {

    if (tweet.getText().length() > 140) {
      throw new IllegalArgumentException("Text length exceeded 140 characters");
    }
    List<Double> coordinates = tweet.getCoordinates().getCoordinates();
    if (coordinates.get(0) < -180d || coordinates.get(0) > 180d) {
      throw new IllegalArgumentException("Invalid value for longitude (must be between -180 and "
          + "180");
    }
    if (coordinates.get(1) < -90d || coordinates.get(1) > 90d) {
      throw new IllegalArgumentException("Invalid value for latitude (must be between -90 and 90)");
    }
    return dao.create(tweet);
  }

  /**
   * Search a tweet by ID
   *
   * @param id     tweet id
   * @param fields set fields not in the list to null
   * @return Tweet object which is returned by the Twitter API
   * @throws IllegalArgumentException if id or fields param is invalid
   */
  @Override
  public Tweet showTweet(String id, String[] fields) {
    if (!validateId(id)) {
      throw new IllegalArgumentException("Invalid Tweet ID: " + id);
    }
    return dao.findById(id);
  }

  /**
   * Delete Tweet(s) by id(s).
   *
   * @param ids tweet IDs which  will be deleted
   * @return A list of Tweets
   * @throws IllegalArgumentException if one of the IDs is invalid.
   */
  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    for (String id : ids) {
      if (!validateId(id)) {
        throw new IllegalArgumentException("Invalid Tweet ID: " + id);
      }
    }
    List<Tweet> deletedTweets = new ArrayList<>();
    for (String id : ids) {
      deletedTweets.add(dao.deleteById(id));
    }
    return deletedTweets;
  }

  private boolean validateId(String id) {
    // Simple validation to see if ID is a number. May be able to improve this with a more rigorous
    // check.
    String regex = "^\\d+$";
    return Pattern.compile(regex).matcher(id).matches();
  }
}
