package ru.sbt.jschool.session6;

import ru.sbt.jschool.session6.problem2.JsonObject;

import java.io.IOException;

public class FindUser implements Action {
    @Override
    public String doAction(String s, String folder) throws IOException, ClassNotFoundException, IllegalAccessException {
        String[] mas = s.split("[/\\=\\&\\?]");
        int id = Integer.parseInt(mas[2]);
        String filename = folder + "//" + id + ".dat";
        if(Server.getUserById(id, filename) == null) return null;
        else
        return JsonObject.toJson(Server.getUserById(id, filename));
    }
}
