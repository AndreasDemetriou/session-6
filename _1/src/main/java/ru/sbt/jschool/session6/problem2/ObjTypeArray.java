package ru.sbt.jschool.session6.problem2;

public class ObjTypeArray implements Outputable {

    @Override
    public String stringValue(Object obj) throws IllegalAccessException {
        String result = "[";
        Object[] mas = (Object[]) obj;
        Outputable tmp = JsonObject.classMap.get(mas.getClass().getComponentType());
        if((tmp==null)&&(mas.getClass().getComponentType().isArray())) tmp = new ObjTypeArray();
        for (int i = 0; i < mas.length - 1; i++) {
                result = result + JsonObject.toJson(mas[i]) + ",";
        }
        if(mas.length!=0) result = result + JsonObject.toJson(mas[mas.length - 1]);
        result = result + "]";
        return result;
    }
}
