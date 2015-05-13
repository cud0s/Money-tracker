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
 * Opens or creates, if doesn't find one, new file where Serializable data can be saved and retrieved
 * Can save data to file asynchronously using one thread from MainJFrame.executor
 * @author slvai_000
 */
public class FileIO implements Runnable {

    private static int total = 1;
    private final int id;
    private final String name;
    private boolean ioActive = false;
    private Serializable data;

    /**
     * Runs file output on single thread - if ioActive is true does not run,
     * else sets ioActive true until file output is complete
     * @throws RuntimeException "Sorry an error has occured, data not saved" if unable to save data
     */
    @Override
    public void run() {
        if (!ioActive) {
            ioActive = true;
            try {
                try (FileOutputStream fileOut = new FileOutputStream(name);
                        ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                    out.writeObject(data);
                }
            } catch (IOException i) {
                throw new RuntimeException("Sorry an error has occured, data not saved");
            }
        }
        ioActive = false;
    }
    
    

    public boolean isIoActive() {
        return ioActive;
    }
    
    /**
     * Writes to file using executor in MainJFrame
     * Uses only one thread
     * @param newData Serializable data to write to file
     */
    public void updateFile(Serializable newData) {
        if (!ioActive) {
            data = newData;
            MainJFrame.executor.execute(this);
        }
    }

    /**
     * 
     * @return Returns Serializable objects from file, null if file is not found
     */
    public Serializable getObjects() {
        Serializable objects;
        try {
            try (FileInputStream fileIn = new FileInputStream(name);
                    ObjectInputStream in = new ObjectInputStream(fileIn)) {
                objects = (Serializable) in.readObject();
            }
        } catch (IOException | ClassNotFoundException i) {
            System.out.println(i);
            objects = null;
        }
        return objects;
    }

    /**
     * Opens file with name: "mtData"+FileIO.total+".ser"
     * FileIO.total is total number FileIO class has been initialised
     */
    public FileIO() {
        this.id = FileIO.total;
        name = "mtData" + ((total == 0) ? "" : Integer.toString(id)) + ".ser";
        FileIO.total++;
    }
}
