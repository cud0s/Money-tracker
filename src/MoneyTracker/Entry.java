/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoneyTracker;

/**
 *
 * @author slvai_000
 */
public abstract class Entry {
    private final String name;
    protected final int price;
    
    public String getName(){
        return name;
    }
    
    /**
     *
     * @return
     */
    public abstract int getPrice();
    
    public abstract String getType();
    
    public Entry(String inName, int inPrice){
        name=inName;
        price=inPrice;
    }
}
