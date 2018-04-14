package ru.sbt.jschool.session6;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CreateUser implements Action {
    @Override
    public String doAction(String s, String folder) throws IOException {
        String[] mas = s.split("[/\\=\\&\\?]");
        FolderUtils fu = new FolderUtils(folder);
        Integer id = fu.getId();
        User tmp = new User(id, mas[4], Integer.parseInt(mas[6]), Integer.parseInt(mas[8]));
        String filename = folder + "//" + id + ".dat";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
        oos.writeObject(tmp);
        return id.toString();
    }
}
