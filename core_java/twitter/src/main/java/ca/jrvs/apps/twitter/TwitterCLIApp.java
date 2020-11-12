package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

public class TwitterCLIApp {

  private Controller controller;

  public TwitterCLIApp(Controller controller) { this.controller = controller; }

  public static void main(String[] args) {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    HttpHelper helper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    CrdDao<Tweet, String> dao = new TwitterDao(helper);
    Service service = new TwitterService(dao);
    Controller controller = new TwitterController(service);
    TwitterCLIApp app = new TwitterCLIApp(controller);

    app.run(args);
  }

  public void run(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException("USAGE: TwitterCLIApp post|show|delete [arguments]");
    }
    String command = args[0];
    switch (command) {
      case "post":
        printTweet(this.controller.postTweet(args));
        break;
      case "show":
        printTweet(this.controller.showTweet(args));
        break;
      case "delete":
        this.controller.deleteTweet(args).forEach(this::printTweet);
        break;
      default:
        throw new IllegalArgumentException("USAGE: TwitterCLIApp post|show|delete [arguments]");
    }
  }

  private void printTweet(Tweet tweet) {
    try {
      System.out.println(JsonUtils.toJson(tweet, true, false));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Could not print tweet in JSON format.", e);
    }
  }
}
