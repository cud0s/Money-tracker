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
public class Income extends Entry{
    public Income(String inName, int inPrice) {
        super(inName, inPrice);
    }
    
    @Override
    public String getType(){
        return " Income:";
    }
    
    @Override
    public int getPrice(){
        return price;
    }
}
