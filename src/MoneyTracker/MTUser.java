/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoneyTracker;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author slvai_000
 */
public class MTUser extends User{
    private int age;
    private int budget;
    private ArrayList<Entry> entries;
    

    public int getAge() {
        return age;
    }

    public int getBudget() {
        return budget;
    }

    public void addEntry(boolean isIncome, String nName, int nPrice) {
        if (nPrice > budget && !isIncome) {
            throw new RuntimeException("You don't have enough money in your budget for this purchase");
        } else {
            Entry newP;
            if (isIncome) {
                newP = new Income(nName, nPrice);
            } else {
                newP = new Expenditure(nName, nPrice);
            }
            budget += newP.getPrice();
            entries.add(newP);
        }
    }

    public String getMost(boolean mComORmSpent) {
        ArrayList<String> tempStr = new ArrayList<>();
        ArrayList<Integer> tempInt = new ArrayList<>();
        if (entries.isEmpty()) {
            return " ";
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
    
    public String getEntryData(int i){
        Entry e=entries.get(i);
        return e.getName() + e.getType() + e.getPrice();
    }
    
    public int getTotalEntries(){
        return entries.size();
    }
    
    MTUser(String newUsername, char[] newPassword, int newAge, int newBudget) {
        super(newUsername, newPassword);
        age = newAge;
        budget = newBudget;
        this.entries = new ArrayList<>();
    }
    
}
