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
    ArrayList <NutrValue> nValues;
    public Expenditure(String inName, int inPrice) {
        super(inName, inPrice);
        nValues = new ArrayList<>();
    }
    
    public void addNutritionalV(String a, int b){
        nValues.add(new NutrValue(a, b));
    }
    
    public NutrValue getNutritionalV(int a){
      if(nValues.indexOf(a)!=(-1)){
          return nValues.get(a);
      }else{
          return null;
      }
    }
    
    
    @Override
    public String getType(){
        return " Expenditure:";
    }
    
    @Override
    public int getPrice(){
        return -price;
    }
}
