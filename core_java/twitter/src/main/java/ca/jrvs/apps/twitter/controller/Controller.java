package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import java.util.List;

public interface Controller {

  /**
   * Parse user argument and post a tweet by calling service classes
   *
   * @param args user arguments
   * @return a posted tweet
   * @throws IllegalArgumentException if args are invalid
   */
  Tweet postTweet(String[] args);

  /**
   * Parse user argument and search a tweet by calling service classes
   *
   * @param args user arguments
   * @return a tweet
   * @throws IllegalArgumentException if args are invalid
   */
  Tweet showTweet(String[] args);

  /**
   * Parse user argument and delete tweets by calling service classes
   *
   * @param args user arguments
   * @return a tweet
   * @throws IllegalArgumentException if args are invalid
   */
  List<Tweet> deleteTweet(String[] args);
}