/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.entry;

import gsonClasses.GsonFoodSearch;
import gsonClasses.GsonFoodReport;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author slvai_000
 */
public abstract class Entry implements Runnable, Serializable{

    protected final int price;
    protected LocalDate inputDate;

    private HashMap nutrientData;
    private String ndbno = null;
    private final static String KEY = "ujiILYb2jj66QxKZRBFpekHnBgitQCbyGZXRadda";
    private final String[] textField = new String[11];
    private final String name;

    /**
     * 
     * @return price 
     */
    public abstract int getPrice();

    /**
     * 
     * @return single string with name of type, for example "Loan" or "Expenditure" 
     */
    public abstract String getType();

    /**
     * 
     * @return Name + "  " + type + ": " + price 
     */
    @Override
    public String toString() {
        String type = "Loan";
        return this.getName()+ "  " + this.getType() + ": " + this.getPrice();
    }

    /**
     * updates details
     */
    @Override
    public void run() {
        updateDetails();
    }
    
    /**
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return string ndbno (usda database food number)
     */
    public String getNdbno() {
        return ndbno;
    }

    /**
     * 
     * @return LocalDate date, when entry was added
     */
    public LocalDate getInputDate() {
        return inputDate;
    }

    /**
     * 
     * @return String[11] each even number indicates text should be displayed on
     * left side and subsequent value should be displayed side by side on the 
     * right
     */
    public String[] getDetails() {
        System.out.println("get details");
        GsonFoodReport.GsonReport.GsonFood.GsonNutrients nutrient = null;
        String nutrientId;

        for (int a = 0; a < 4; a++) {
            switch (a) {
                case 0:
                    nutrientId = "208"; //energy (kcals)
                    break;
                case 1:
                    nutrientId = "205"; //carbs
                    break;
                case 2:
                    nutrientId = "203"; //protein
                    break;
                case 3:
                    nutrientId = "204"; //total fat
                    break;
                default:
                    nutrientId = "Error";
                    break;

            }
            if (nutrientData.containsKey(nutrientId)) {
                nutrient = (GsonFoodReport.GsonReport.GsonFood.GsonNutrients) nutrientData.get(nutrientId);

                textField[a * 2] = nutrient.getName() + " (100g):";
                textField[a * 2 + 1] = nutrient.getValue() + " " + nutrient.getUnit();
            } else {
                switch (a) {
                    case 0:
                        textField[a] = "Price:";
                        break;
                    case 1:
                        textField[a] = Integer.toString(this.price);
                        break;
                    case 2:
                        textField[a] = "Type:";
                        break;
                    case 3:
                        textField[a] = this.getType();
                        break;
                    case 4:
                        textField[a] = "";
                        break;
                    case 5:
                        textField[a] = "";
                        break;
                    case 6:
                        textField[a] = "";
                        break;
                    case 7:
                        textField[a] = "";
                        break;
                    default:
                        break;
                }
                /*textField[a * 2] = "Standart data" + (a * 2) + " :";
                 textField[a * 2] = "Standart data" + (a * 2 + 1);*/
            }
        }

        /*  paklaust, ar geriau užkomentuotas variantas, ar dvi eilutės žemiau
         if(ndbno == null){
         textField[9]="Date added:";
         textField[8]=inputDate.toString();
         }else{
         textField[9]="AutoSearch name:";
         //textField[8] was already set to the name of selected food by updateDetails()
         }*/
        textField[9] = (ndbno == null) ? "Date added:" : "AutoSearch name:";
        textField[8] = (ndbno == null) ? inputDate.toString() : textField[8];

        System.out.println(Arrays.toString(textField));
        return textField;
    }

    private void updateDetails() {
        try {
            // Get Gson object
            Gson gson = new Gson();
            String fileData = new String();
            // read JSON file data as String
            URL foodSearch = new URL("http://api.nal.usda.gov/usda/ndb/search/?format=json&q=" + name + "&sort=r&max=25&offset=0&api_key=" + KEY);
            fileData = new Scanner(foodSearch.openStream(), "UTF-8").useDelimiter("\\A").next();
            foodSearch.openStream().close();

            // parse json string to object
            GsonFoodSearch gsonFoodSearch = gson.fromJson(fileData, GsonFoodSearch.class);
            for (GsonFoodSearch.GsonList.GsonItem item : gsonFoodSearch.list.getItems()) {
                //checks if starts with, ends with, or contains the name+"," symbol
                if (item.getName().toLowerCase().contains(", " + name.toLowerCase() + ",") || item.getName().toLowerCase().startsWith(name.toLowerCase() + ",") || item.getName().toLowerCase().endsWith(", " + name.toLowerCase())) {
                    ndbno = item.getNdbno();
                    System.out.println("Found");
                    textField[8] = item.getName();
                    updateNutritionalData();
                    break;
                }
            }
            System.out.println("Not found");

        } catch (MalformedURLException ex) {
            //Logger.getLogger(Expenditure.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(Expenditure.class.getName()).log(Level.SEVERE, null, ex);
        }

        // create JSON String from Object
        //String jsonEmp = gson.toJson(det);
        //System.out.print(jsonEmp);
        /*try(Reader reader = new InputStreamReader(JsonToJava.class.getResourceAsStream("/Server2.json"), "UTF-8")){
         Gson gson = new GsonBuilder().create();
         Person p = gson.fromJson(reader, Person.class);
         System.out.println(p);
         }*/
    }

    private void updateNutritionalData() {
        Gson gson = new Gson();
        String fileData = new String();

        try {
            // read JSON file data as String
            URL foodSearch = new URL("http://api.nal.usda.gov/usda/ndb/reports/?ndbno=" + ndbno + "&type=b&format=json&api_key=" + KEY);
            fileData = new Scanner(foodSearch.openStream(), "UTF-8").useDelimiter("\\A").next();
            foodSearch.openStream().close();

        } catch (MalformedURLException ex) {
            //Logger.getLogger(Expenditure.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(Expenditure.class.getName()).log(Level.SEVERE, null, ex);
        }
        GsonFoodReport gsonFoodReport = gson.fromJson(fileData, GsonFoodReport.class);
      //  System.out.println(gsonFoodReport.report.type);
        //System.out.println(gsonFoodReport);
        for (GsonFoodReport.GsonReport.GsonFood.GsonNutrients nutrient : gsonFoodReport.report.getFood().getNutrients()) {
            nutrientData.put(nutrient.getNutrientId(), nutrient);
        }
    }
    
    /**
     * 
     * @param inName name
     * @param inPrice price
     */
    public Entry(String inName, int inPrice) {
        name = inName;
        price = inPrice;
        nutrientData = new HashMap();
        for (String a : textField) {
            a = "";
        }
        inputDate = LocalDate.now();
    }
}
