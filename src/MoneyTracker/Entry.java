/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoneyTracker;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author slvai_000
 */
public abstract class Entry implements Runnable {

    HashMap nutrientData;

    private final static String KEY = "rcxyzNl40xB4Q0MfFkM1GZmFuV4HUkW8uMSkOCIM";
    private String ndbno;

    private final String[] textField = new String[9];

    private final String name;
    protected final int price;

    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public abstract int getPrice();

    public abstract String getType();
    
    
    
    @Override
    public void run(){
        updateDetails();
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
        System.out.println("point3");
        GsonFoodReport gsonFoodReport = gson.fromJson(fileData, GsonFoodReport.class);
        System.out.println("point4");
        System.out.println(gsonFoodReport.report.type);
        System.out.println(gsonFoodReport);
        System.out.println("point5");
        for (GsonFoodReport.GsonReport.GsonFood.GsonNutrients nutrient : gsonFoodReport.report.getFood().getNutrients()) {
            nutrientData.put(nutrient.getNutrientId(), nutrient);
        }
        System.out.println("point1");
    }

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
                textField[a * 2] = "Standart data" + (a * 2) + " :";
                textField[a * 2] = "Standart data" + (a * 2 + 1);
            }
        }
        System.out.println(Arrays.toString(textField));
        return textField;
    }

    public Entry(String inName, int inPrice) {
        name = inName;
        price = inPrice;
        nutrientData = new HashMap();
        for (String a : textField) {
            a = "";
        }
        textField[8]=name;
        //updateDetails();
    }
}
