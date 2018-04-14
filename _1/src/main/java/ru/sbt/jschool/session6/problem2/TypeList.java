package ru.sbt.jschool.session6.problem2;

import java.util.List;

public class TypeList implements Outputable {
    @Override
    public String stringValue(Object obj) throws IllegalAccessException {
        List<Object> list = (List<Object>) obj;
        Object[] mas = list.toArray();
        return JsonObject.toJson(mas);
    }
}
