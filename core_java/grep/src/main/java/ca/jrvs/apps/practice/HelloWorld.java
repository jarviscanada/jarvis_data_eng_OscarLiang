package ca.jrvs.apps.practice;

public class HelloWorld {

    public static void main(String[] args) {

        RegexExcImp r = new RegexExcImp();
        String testString = "233.2.42.24";
        if (r.matchIp(testString)) {
            System.out.println("Success");
        }
        else {
            System.out.println("Failure");
        }
    }
}
