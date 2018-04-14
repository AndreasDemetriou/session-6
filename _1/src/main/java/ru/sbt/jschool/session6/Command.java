package ru.sbt.jschool.session6;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Command {
    static Map<String, Action> map = new HashMap<>();
    {
        map.put("create", new CreateUser());
        map.put("delete", new DeleteUser());
        map.put("list", new ListOfUsers());
        map.put("findId", new FindUser());
    }

    public String doCommand (String s, String folder) throws IOException, ClassNotFoundException, IllegalAccessException {
        s = s.substring(4);
        s = s.substring(0, s.indexOf(' '));
        System.out.println(s);
        String[] mas = s.split("[/\\=\\&\\?]");
        Action tmp;
        if(Command.map.containsKey(mas[2])) tmp = Command.map.get(mas[2]);
        else tmp = Command.map.get("findId");
        return tmp.doAction(s, folder);
    }
}
