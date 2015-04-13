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
public class NutrValue {
    private String name = new String();
    private int value = 0;
    
    public String getName(){
        return name;
    }
    
    public int getValue(){
        return value;
    }
    
    NutrValue(String a, int b){
        name = a;
        value = b;
    }
}
