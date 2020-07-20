package ca.jrvs.apps.practice;

import java.util.regex.*;

public class RegexExcImp implements RegexExc {

  private static String jpegRegex = "^\\S+\\.jpe?g$";
  private static String ipRegex = "^(\\d?\\d?\\d\\.){3}\\d?\\d?\\d$";
  private static String emptyRegex = "^\\s*$";

  @Override
  public boolean matchJpeg(String filename) {
    return matchRegex(jpegRegex, filename.toLowerCase());
  }

  @Override
  public boolean matchIp(String ip) {
    return matchRegex(ipRegex, ip);
  }

  @Override
  public boolean isEmptyLine(String line) {
    return matchRegex(emptyRegex, line);
  }

  private boolean matchRegex(String regex, String testString) {
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(testString);
    return m.matches();
  }
}
