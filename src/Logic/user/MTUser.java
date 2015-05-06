/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoneyTracker;

import java.time.LocalDate;
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

    public int getAge() {
        return age;
    }

    public int getBudget() {
        return budget;
    }

    public Entry getEntry(int i) {
        return entries.get(i);
    }

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

    public String getEntryDataString(int i) {
        Entry e = entries.get(i);
        return e.toString();
    }

    public int getTotalEntries() {
        return entries.size();
    }

    public int countLoans() {
        int count = 0;
        for (Entry entry : entries) {
            if (entry instanceof Loan) {
                count++;
            }
        }
        return count;
    }

    public int countFoodEntries() {
        return totalFoodEntries;
    }

    public void addEntry(boolean isIncome, String nName, int nPrice) {
        EntryFactory entryFactory = new EntryFactory();
        Entry newP = entryFactory.getEntry(isIncome, nName, nPrice);
        addEntry(newP);
    }

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

    MTUser(String newUsername, char[] newPassword, int newAge, int newBudget) {
        super(newUsername, newPassword);
        age = newAge;
        budget = newBudget;
        this.entries = new ArrayList<>();
    }

}
