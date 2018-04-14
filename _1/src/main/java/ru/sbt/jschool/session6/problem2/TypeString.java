package ru.sbt.jschool.session6.problem2;

public class TypeString implements Outputable{

    @Override
    public String stringValue(Object obj) throws IllegalAccessException {
        String result = "\"" + obj + "\"";
        return result;
    }
}
