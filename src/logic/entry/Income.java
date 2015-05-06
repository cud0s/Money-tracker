/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.entry;

/**
 *
 * @author slvai_000
 */
public class Income extends Entry {

    private final static String type = "Income";

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public Income(String inName, int inPrice) {
        super(inName, inPrice);
    }
}
