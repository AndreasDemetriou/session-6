package ru.sbt.jschool.session6;

import java.io.FileInputStream;
import java.util.Properties;

public class ApplicationProperties {
    String path;
    Properties prop;

    ApplicationProperties(String path) {
        this.path = path;
        FileInputStream fileInputStream;
        prop = new Properties();
        try {
            fileInputStream = new FileInputStream(path);
            prop.load(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return Integer.parseInt(prop.getProperty("port"));
    }

    public String getFolfder() {
        return prop.getProperty("folder");
    }
}
