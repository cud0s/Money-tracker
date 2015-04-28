/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoneyTracker;

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

    public double getmInterest() {
        return mInterest;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public double getMonthlyPay() {
        return Math.round(monthlyPay * 100.0) / 100.0d;
    }

    public double getTotalRemaining() {
        return Math.round(monthlyPay * LocalDate.now().until(returnDate).getMonths() * 100.0) / 100.0d;
    }

    private void calculateMonthlyPay() {
        monthlyPay = (this.getPrice() * 1.0) / (inputDate.until(returnDate).toTotalMonths() * 1.0)*(1.00+mInterest*0.01);
    }

    public Loan(LocalDate inReturnDate, String inName, int inPrice, double inInterest) {
        super(inName, inPrice);
        returnDate = inReturnDate;
        mInterest = inInterest;
        
        calculateMonthlyPay();
    }
}
