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
public class Expenditure extends Entry{

    @Override
    public String getType() {
        return " Expenditure:";
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
