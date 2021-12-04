package test;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Test {
}

class SimpleApp {
    public static void main(String[] args) {
        // example of how system properties override; note this
        // must be set before the config lib is used
        System.setProperty("simple-lib.whatever", "This value comes from a system property");

        // Load our own config values from the default location,
        // application.conf
        Config conf = ConfigFactory.load();
        System.out.println("The answer is: " + conf.getString("simple-app.answer"));

    }
}