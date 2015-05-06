/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsonClasses;

import java.io.Serializable;

/**
 *
 * @author slvai_000
 */
public class GsonFoodReport implements Serializable{

    public GsonReport report;

    public static class GsonReport implements Serializable{

        public String sr;
        public String type;
        private GsonFood food;

        public GsonFood getFood() {
            return food;
        }

        public static class GsonFood implements Serializable{

            private String ndbno;
            private String name;
            private GsonNutrients[] nutrients;

            public static class GsonNutrients implements Serializable{

                private String nutrient_id;
                private String name;
                private String group;
                private String unit;
                private String value;
                private GsonMeasures[] measures;

                public String getNutrientId() {
                    return nutrient_id;
                }

                public String getName() {
                    return name;
                }

                public String getUnit() {
                    return unit;
                }

                public String getValue() {
                    return value;
                }

                public static class GsonMeasures implements Serializable{

                    private String label;
                    private double eqv;
                    private double qty;
                    private String value;
                }

                public GsonNutrients() {
                }
            }

            public GsonNutrients[] getNutrients() {
                return nutrients;
            }

            public GsonFood() {
            }
        }

        public GsonReport() {
        }
    }

    public GsonFoodReport() {
    }
}
