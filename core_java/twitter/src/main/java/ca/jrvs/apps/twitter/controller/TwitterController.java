package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.utils.TweetUtils;
import java.util.List;

public class TwitterController implements Controller {

  private static final String COORD_SEP = ":";
  private static final String COMMA = ",";

  private Service service;

  public TwitterController(Service service) { this.service = service; }

  /**
   * Parse user argument and post a tweet by calling service classes
   *
   * @param args user arguments
   * @return a posted tweet
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public Tweet postTweet(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("Invalid number of arguments. "
          + "USAGE: TwitterCLIApp post \"tweet text\" \"latitude:longitude\"");
    }
    String text = args[1];
    String[] coordinates = args[2].split(COORD_SEP);
    Double lat, lon;
    try {
      lat = Double.parseDouble(coordinates[0]);
      lon = Double.parseDouble(coordinates[1]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid argument: latitude/longitude is not a number. "
          + "USAGE: TwitterCLIApp post \"tweet text\" \"latitude:longitude\"");
    } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Invalid argument: latitude/longitude incorrectly "
          + "formatted. USAGE: TwitterCLIApp post \"tweet text\" \"latitude:longitude\"");
    }
    Tweet tweet = TweetUtils.buildTweet(text, lon, lat);
    return service.postTweet(tweet);
  }

  /**
   * Parse user argument and search a tweet by calling service classes
   *
   * @param args user arguments
   * @return a tweet
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public Tweet showTweet(String[] args) {
    if (args.length != 2) {
      throw new IllegalArgumentException("Invalid number of arguments. USAGE: TwitterCLIApp "
          + "show tweet_id");
    }
    String id = args[1];
    return service.showTweet(id, null);
  }

  /**
   * Parse user argument and delete tweets by calling service classes
   *
   * @param args user arguments
   * @return a tweet
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public List<Tweet> deleteTweet(String[] args) {
    if (args.length != 2) {
      throw new IllegalArgumentException("Invalid number of arguments. USAGE: TwitterCLIApp "
          + "delete id1,id2,id3,...,idn");
    }
    String[] ids = args[1].split(COMMA);
    return service.deleteTweets(ids);
  }
}
