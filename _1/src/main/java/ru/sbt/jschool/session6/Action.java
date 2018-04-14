package ru.sbt.jschool.session6;

import java.io.IOException;

public interface Action {
    public String doAction(String s, String folder) throws IOException, ClassNotFoundException, IllegalAccessException;
}
