package ca.jrvs.apps.practice;

import java.util.function.Function;
import java.util.regex.*;

public class RegexExcImp implements RegexExc {

  private static String jpegRegex = "^\\S+\\.jpe?g$";
  private static String ipRegex = "^(\\d?\\d?\\d\\.){3}\\d?\\d?\\d$";
  private static String emptyRegex = "^\\s*$";

<<<<<<< HEAD
  @Override
  public boolean matchJpeg(String filename) {
    return matchRegex(jpegRegex, filename.toLowerCase());
  }
=======
    public static void main(String[] args) {
        String jpegString = "picture.jpeg";
        String badJpegString = ".jpg";
        String ipString = "127.0.0.1";
        String badIpString = "1234.0.2.1";
        String emptyString = "";
        String badEmptyString = ".";
        RegexExcImp regex = new RegexExcImp();
        System.out.print(jpegString + ": ");
        if (regex.matchJpeg(jpegString)) {
            System.out.println("Match");
        } else {
            System.out.println("No match");
        }
        System.out.print(badJpegString + ":");
        if (regex.matchJpeg(badJpegString)) {
            System.out.println("Match");
        } else {
            System.out.println("No match");
        }
        System.out.print(ipString + ":");
        if (regex.matchIp(ipString)) {
            System.out.println("Match");
        } else {
            System.out.println("No match");
        }
        System.out.print(badIpString + ":");
        if (regex.matchIp(badIpString)) {
            System.out.println("Match");
        } else {
            System.out.println("No match");
        }
        System.out.print(emptyString + ":");
        if (regex.isEmptyLine(emptyString)) {
            System.out.println("Match");
        } else {
            System.out.println("No match");
        }
        System.out.print(badEmptyString + ":");
        if (regex.isEmptyLine(badEmptyString)) {
            System.out.println("Match");
        } else {
            System.out.println("No match");
        }
    }

    @Override
    public boolean matchJpeg(String filename) {
        return matchRegex(jpegRegex, filename.toLowerCase());
    }
>>>>>>> feature/regex

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
