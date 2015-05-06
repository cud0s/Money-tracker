/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic2.entry;

import logic2.entry.Income;
import java.time.LocalDate;

public class Loan extends Income {

    private LocalDate returnDate;
    private double mInterest;
    private double monthlyPay;
    private final static String type = "Loan";

    @Override
    public String getType() {
        return type;
    }
    
    /**
     * 
     * @return double monthly interest 
     */
    public double getmInterest() {
        return mInterest;
    }

    /** 
     * 
     * @return LocalDate date until loan should be returned 
     */
    public LocalDate getReturnDate() {
        return returnDate;
    }
    
    /**
     * 
     * @return double monthly payment of the loan
     */
    public double getMonthlyPay() {
        return Math.round(monthlyPay * 100.0) / 100.0d;
    }

    /**
     * 
     * @return double total remaining value of the loan that is yet to be returned
     */
    public double getTotalRemaining() {
        return Math.round(monthlyPay * LocalDate.now().until(returnDate).getMonths() * 100.0) / 100.0d;
    }

    private void calculateMonthlyPay() {
        monthlyPay = (this.getPrice() * 1.0) / (inputDate.until(returnDate).toTotalMonths() * 1.0)*(1.00+mInterest*0.01);
    }

    /**
     * 
     * @param inReturnDate LocalDate date until loan should be returned
     * @param inName name of the loan
     * @param inPrice price of the loan
     * @param inInterest  interest in % of the loan
     */
    public Loan(LocalDate inReturnDate, String inName, int inPrice, double inInterest) {
        super(inName, inPrice);
        returnDate = inReturnDate;
        mInterest = inInterest;
        
        calculateMonthlyPay();
    }
}
