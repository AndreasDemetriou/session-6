package ru.sbt.jschool.session6;

import com.google.gson.Gson;
import ru.sbt.jschool.session6.problem2.JsonObject;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListOfUsers implements Action {
    @Override
    public String doAction(String s, String folder) throws IOException, ClassNotFoundException, IllegalAccessException {
        String[] mas = s.split("[/\\=\\&\\?]");
        FolderUtils fu = new FolderUtils(folder);
        List<User> users = new ArrayList<>();
        File folder1 = new File(folder);
        String[] files = folder1.list(new FilenameFilter() {
            @Override
            public boolean accept(File folder, String name) {
                return name.endsWith(".dat");
            }
        });
        for (String fileName : files) {
            String filename = folder + "//" + fileName;
            int id = Integer.parseInt(fileName.substring(0, fileName.length() - 4));
            users.add(Server.getUserById(id, filename));
        }
        return  JsonObject.toJson(users);
    }
}
