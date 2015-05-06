/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programIO;

import gui.MainJFrame;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author slvai_000
 */
public class FileIO implements Runnable {

    private static int total = 1;
    private final int id;
    private final String name;
    private boolean ioActive = false;
    private Serializable data;

    @Override
    public void run() {
        ioActive = true;
        try {
            FileOutputStream fileOut = new FileOutputStream(name);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            throw new RuntimeException(i);//RuntimeException("Sorry an error has occured, data not saved");
        }
        ioActive = false;
    }

    public void updateFile(Serializable newData) {
        if (!ioActive) {
            data = newData;
            MainJFrame.executor.execute(this);
        }
    }

    public Serializable getObjects() {
        Serializable objects;
        try {
            FileInputStream fileIn = new FileInputStream(name);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            objects = (Serializable) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println(i);
            objects = null;
        } catch (ClassNotFoundException c) {
            System.out.println("classnotfoundexeption");
            objects = null;
        }
        return objects;
    }

    public FileIO() {
        this.id = FileIO.total;
        name = "mtData" + ((total == 0) ? "" : Integer.toString(id)) + ".ser";
        FileIO.total++;
    }
}
