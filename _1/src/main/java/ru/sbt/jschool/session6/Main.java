package ru.sbt.jschool.session6;

import com.google.gson.Gson;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Hi");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("1.dat"));
        oos.writeObject(new User(1,"H",1,1));
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream( "1.dat"));
        User tmp1= (User)ois.readObject();
        System.out.println(new Gson().toJson(tmp1));
    }
}
