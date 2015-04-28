/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoneyTracker;

import java.time.LocalDate;

/**
 *
 * @author slvai_000
 */
public class EntryFactory {

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

    public Entry getEntry(LocalDate returnDate, String name, int price, double interest) {
        Entry newP;
        newP = new Loan(returnDate, name, price, interest);
        MainJFrame.executor.execute(newP);
        return newP;
    }
}
