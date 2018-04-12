package ru.sbt.jschool.session6;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {
    public static User getUserById(int id,String filename) throws IOException, ClassNotFoundException {
        File f = new File(filename);
        if(!f.exists()) {return null;}
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
        User tmp1= (User)ois.readObject();
        return tmp1;
    }

    public static String action(String s, String folder) throws IOException, ClassNotFoundException {
        String result = null;
        s = s.substring(4);
        s = s.substring(0,s.indexOf(' '));
        String[] mas = s.split("[/\\=\\&\\?]");
        FolderUtils fu = new FolderUtils(folder);
        switch (mas[2]) {
            case "create":
                Integer id = fu.getId();
                User tmp = new User(id,mas[4],Integer.parseInt(mas[6]),Integer.parseInt(mas[8]));
                String filename = folder + "//"  + id + ".dat";
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
                oos.writeObject(tmp);
                result = id.toString();
                break;
            case"delete":
                result = fu.del(Integer.parseInt(mas[3])).toString();
                break;
            case "list":
                List<User> users = new ArrayList<>();
                File folder1 = new File(folder);
                String[] files = folder1.list(new FilenameFilter() {
                    @Override public boolean accept(File folder, String name) {
                        return name.endsWith(".dat");
                    }
                });
                for ( String fileName : files ) {
                    filename = folder + "//" + fileName;
                    id = Integer.parseInt(fileName.substring(0, fileName.length() - 4));
                    users.add(getUserById(id,filename));
                }
                result = new Gson().toJson(users);
                break;
            default:
                id = Integer.parseInt(mas[2]);
                filename = folder + "//"  + id + ".dat";
                result =  new Gson().toJson(getUserById(id,filename));
                break;
        }
        return result;
    }
    public static void main(String[] arg){
        String path;
        if(arg[0] == null) path = "C://Users//Andreas//Desktop//2.txt";
        else path = arg[0];
        int port = new GetProp(path).getPort();
        try {
            ServerSocket ssocket = new ServerSocket(port);
            System.out.println("Waiting for a client...");
            while (true) {
                Socket socket = ssocket.accept();
                InputStream sin = socket.getInputStream();
                OutputStream sout = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(sout);
                Scanner sc = new Scanner(sin);

                String s = sc.nextLine();
                String result = action(s,new GetProp(path).getFolfder());
                if(result!=null) {
                    pw.println("HTTP/1.1 200 OK");
                    pw.println("Content-Type: text/html\r");
                    pw.println("\n");
                    pw.println(result.toString());
                    pw.flush();
                }else{
                    pw.println("HTTP/1.1 404 Not Found");
                    pw.println("Content-Type: text/html\r");
                    pw.println("\n");
                    pw.println("404 NOT FOUND");
                    pw.flush();
                }
            }
        } catch(Exception x) { x.printStackTrace(); }
    }
}
