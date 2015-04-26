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
public class GsonFoodReport {
    GsonReport report;

    public static class GsonReport {

        public String sr;
        public String type;
        private GsonFood food;

        public GsonFood getFood() {
            return food;
        }

        public static class GsonFood {

            private String ndbno;
            private String name;
            private GsonNutrients[] nutrients;

            public static class GsonNutrients {

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

                public static class GsonMeasures {

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
