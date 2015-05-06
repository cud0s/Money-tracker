/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.user;

import logic.entry.Loan;
import logic.entry.Income;
import logic.entry.Expenditure;
import logic.entry.EntryFactory;
import logic.entry.Entry;
import java.time.LocalDate;
import java.time.DateTimeException;
import java.util.ArrayList;

/**
 *
 * @author slvai_000
 */
public class MTUser extends User {

    private int age;
    private int budget;
    private final ArrayList<Entry> entries;
    private int totalFoodEntries = 0;

    /**
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     *
     * @return budget
     */
    public int getBudget() {
        return budget;
    }

    /**
     * @param i index of Entry to get from user
     * @return Entry with index i,
     * @throws IndexOutOfBoundsException if no such index
     */
    public Entry getEntry(int i) {
        return entries.get(i);
    }

    /**
     * @param i index of Loan to get from user
     * @return Loan with index i, null if not found
     */
    public Loan getLoan(int i) {
        int count = 0;
        for (Entry entry : entries) {
            if (entry instanceof Loan) {
                if (count == i) {
                    return (Loan) entry;
                }
                count++;
            }
        }
        return null;
    }

    /**
     *
     * @param i - index of entry from which to take data string
     * @return data string - name + type + price
     * @throws IndexOutOfBoundsException if index out of bounds
     */
    public String getEntryDataString(int i) {
        Entry e = entries.get(i);
        return e.toString();
    }

    /**
     * 
     * @return number of entries, 0 if there's none
     */
    public int getTotalEntries() {
        return entries.size();
    }
    
    /**
     * 
     * @return number of loans, 0 if there's none 
     */
    public int countLoans() {
        int count = 0;
        for (Entry entry : entries) {
            if (entry instanceof Loan) {
                count++;
            }
        }
        return count;
    }

    /**
     * 
     * @return number of food entries, 0 if there's none 
     */
    public int countFoodEntries() {
        //not a very efficient solution
        reCountFoodEntries();
        return totalFoodEntries;
    }

    /**
     * 
     * @param isIncome true if entry's type is income
     * @param nName name of entry
     * @param nPrice price of entry
     * @throws NumberFormatException if invalid price is entered
     * @throws RuntimeException if user doesn't have enough money for the purchase
     */
    public void addEntry(boolean isIncome, String nName, int nPrice) {
        EntryFactory entryFactory = new EntryFactory();
        Entry newP = entryFactory.getEntry(isIncome, nName, nPrice);
        addEntry(newP);
    }
    
    /**
     * 
     * @param nName name of entry
     * @param nPrice price of entry
     * @param interest interest
     * @param dateNotPeriod true if loan should be returned until concrete date
     * false if it is to be returned in specified number of time, from creation
     * 
     * @param year year (for example 2010)
     * @param month month of year (for example 6)
     * @param day day of month (for example 20)
     * @throws NumberFormatException if invalid price is entered
     * @throws RuntimeException if user doesn't have enough money for the purchase
     * @throws DateTimeException if the value of any field is out of range, or if the day-of-month is invalid for the month-year
     */
    public void addEntry(String nName, int nPrice, double interest, boolean dateNotPeriod, int year, int month, int day) {       //for adding loans  
        EntryFactory entryFactory = new EntryFactory();
        LocalDate returnDate;
        if (dateNotPeriod) {
            returnDate = LocalDate.of(year, month, day);
        } else {
            returnDate = LocalDate.now();
            returnDate.plusYears(year);
            returnDate.plusMonths(month);
            returnDate.plusDays(day);
        }

        Entry newP = entryFactory.getEntry(returnDate, nName, nPrice, interest);
        addEntry(newP);
    }
    
    /**
     * 
     * @param mComORmSpent when true calculate most common purchase, when false - most expensivve
     * @return "No purchases yet" if no valid purchase is found, name of most common or most expensive purchase if found
     */
    public String getMost(boolean mComORmSpent) {
        ArrayList<String> tempStr = new ArrayList<>();
        ArrayList<Integer> tempInt = new ArrayList<>();
        if (countExpenditures() == 0) {
            return "No purchases yet";
        }
        for (Entry p : entries) {
            if (p instanceof Expenditure) {
                int a = 0;
                for (String t : tempStr) {
                    if (t.equals(p.getName())) {
                        if (mComORmSpent) {
                            tempInt.set(a, tempInt.get(a) + 1);
                        } else {
                            tempInt.set(a, tempInt.get(a) - p.getPrice());
                        }

                        break;
                    }
                    a++;
                }
                if (a == tempStr.size()) {
                    tempStr.add(p.getName());
                    if (mComORmSpent) {
                        tempInt.add(0);
                    } else {
                        tempInt.add(-p.getPrice());
                    }

                }
            }
        }
        int a = 0;
        int b = 0;
        for (Integer tempI : tempInt) {
            if (tempI > tempInt.get(a)) {
                a = b;
            }
            b++;
        }
        return tempStr.get(a);
    }

    private void addEntry(Entry newP) {
        if (((budget + newP.getPrice()) < 0) && !(newP instanceof Income)) {
            throw new RuntimeException("You don't have enough money in your budget for this purchase");
        }
        if ((newP.getPrice() < 0) && !(newP instanceof Expenditure)) {
            throw new NumberFormatException();
        }
        if ((newP.getPrice() > 0) && (newP instanceof Expenditure)) {
            throw new NumberFormatException();
        }
        budget += newP.getPrice();
        entries.add(newP);
        if (newP.getNdbno() != null) {
            totalFoodEntries++;
        }
    }

    private void reCountFoodEntries() {
        totalFoodEntries = 0;
        for (Entry entry : entries) {
            if (entry.getNdbno() != null) {
                totalFoodEntries++;
            }
        }
    }

    private int countExpenditures() {
        if (entries.isEmpty()) {
            return 0;
        } else {
            int count = 0;
            for (Entry entry : entries) {
                if (entry instanceof Expenditure) {
                    count++;
                }
            }
            return count;
        }
    }

    /**
     * 
     * @param newUsername username
     * @param newPassword password
     * @param newAge age
     * @param newBudget budget
     */
    MTUser(String newUsername, char[] newPassword, int newAge, int newBudget) {
        super(newUsername, newPassword);
        age = newAge;
        budget = newBudget;
        this.entries = new ArrayList<>();
    }

}
