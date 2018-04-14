package ru.sbt.jschool.session6;

import java.io.File;

public class FolderUtils {

    public String folder;

    public FolderUtils(String folder) {
        this.folder = folder;
    }

    public int getId() {
        int id = 0;
        File f;
        do {
            f = new File(folder + "//" + id + ".dat");
            id++;
        } while (f.exists());
        id--;
        return id;
    }

    public Boolean del(int id) {
        File f = new File(folder + "//" + id + ".dat");
        if (!f.exists()) return null;
        else {
            f.delete();
            return true;
        }
    }
}
