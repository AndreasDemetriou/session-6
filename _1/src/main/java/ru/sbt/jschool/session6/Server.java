package ru.sbt.jschool.session6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static User getUserById(int id, String filename) throws IOException, ClassNotFoundException {
        File f = new File(filename);
        if (!f.exists()) {
            return null;
        }
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
        User tmp1 = (User) ois.readObject();
        return tmp1;
    }

    public static void main(String[] arg) {
        String path;
        if(arg.length == 0)
            path = "C://Users//Andreas//Desktop//2.txt";
        else
            path = arg[0];
        int port = new ApplicationProperties(path).getPort();
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
                //String result = action(s, new ApplicationProperties(path).getFolfder());
                String result = new Command().doCommand(s, new ApplicationProperties(path).getFolfder());
                if (result != null) {
                    pw.println("HTTP/1.1 200 OK");
                    pw.println("Content-Type: text/html\r");
                    pw.println("\n");
                    pw.println(result.toString());
                } else {
                    pw.println("HTTP/1.1 404 Not Found");
                    pw.println("Content-Type: text/html\r");
                    pw.println("\n");
                    pw.println("404 NOT FOUND");
                }
                pw.flush();
                pw.close();
                sc.close();
                sin.close();
                sout.close();
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
