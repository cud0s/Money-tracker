/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoneyTracker;

import java.util.ArrayList;

/**
 *
 * @author slvai_000
 */
public class Expenditure extends Entry{
    ArrayList <String> detailValues;

    public String getDetail(int a) {
        if ((a > -1) && (a < 4)) {
            return detailValues.get(a);
        } else {
            return null;
        }
    }

    private void updateDetails() {
        this.getName();
    }

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
        detailValues = new ArrayList<>();
    }
}
