package ca.jrvs.apps.twitter.utils;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.Arrays;

public class TweetUtils {

  public static Tweet buildTweet(String text, Double lon, Double lat) {
    Tweet tweet = new Tweet();
    Coordinates coordinates = new Coordinates();

    tweet.setText(text);
    coordinates.setCoordinates(Arrays.asList(lon, lat));
    tweet.setText(text);
    tweet.setCoordinates(coordinates);

    return tweet;
  }

}
