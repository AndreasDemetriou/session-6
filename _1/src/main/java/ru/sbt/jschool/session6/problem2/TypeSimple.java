package ru.sbt.jschool.session6.problem2;

public class TypeSimple implements Outputable {
    @Override
    public String stringValue(Object obj) throws IllegalAccessException {
        return  obj.toString();
    }
}
