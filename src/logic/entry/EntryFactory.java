/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.entry;

import gui.MainJFrame;
import java.time.LocalDate;

/**
 *
 * @author slvai_000
 */
public class EntryFactory {

    /**
     * Creates new purchase and executes asynchronous search in food database  returns new purchase before search is finished
     * 
     * @param isIncome true if it's income, false if it's not income(expenditure)
     * @param name name of entry
     * @param price price of entry
     * @return Entry new purchase
     */
    public Entry getEntry(boolean isIncome, String name, int price) {
        Entry newP;
        if (isIncome) {
            newP = new Income(name, price);
        } else {
            newP = new Expenditure(name, price);
        }
        MainJFrame.executor.execute(newP);
        return newP;
    }

     /**
     * Creates new purchase and executes asynchronous search in food database, returns new purchase before search is finished
     * 
     * @param name name of entry
     * @param price price of entry
     * @param interest interest
     * @param returnDate year (for example 2010)
     * @return Entry new purchase
     */
    public Entry getEntry(LocalDate returnDate, String name, int price, double interest) {
        Entry newP;
        newP = new Loan(returnDate, name, price, interest);
        MainJFrame.executor.execute(newP);
        return newP;
    }
}
