/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.entry;

import logic.entry.Entry;

/**
 *
 * @author slvai_000
 */
public class Expenditure extends Entry {

    private final static String type = "Expenditure";

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getPrice() {
        return -price;
    }

    public Expenditure(String inName, int inPrice) {
        super(inName, inPrice);
        //updateDetails();
    }
}
