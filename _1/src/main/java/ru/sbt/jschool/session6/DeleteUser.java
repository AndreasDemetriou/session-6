package ru.sbt.jschool.session6;

public class DeleteUser implements Action {
    @Override
    public String doAction(String s, String folder) {
        String[] mas = s.split("[/\\=\\&\\?]");
        FolderUtils fu = new FolderUtils(folder);
        if (fu.del(Integer.parseInt(mas[3]))==null) return null;
        else
        return  fu.del(Integer.parseInt(mas[3])).toString();
    }
}
