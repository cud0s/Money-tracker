/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.entry.gsonClasses;

import java.io.Serializable;

/**
 *
 * @author slvai_000
 */
public class GsonFoodSearch implements Serializable{

    public GsonList list;

    public static class GsonList implements Serializable{

        //private String q;
        //private String sr;
        //private int start;
        //private int end;
        public int total;
        //private String group;
        //private String sort;
        private GsonItem[] item;

        public GsonItem[] getItems() {
            return item;
        }

        public int getTotal() {
            System.out.println(Integer.toString(total));
            return total;
        }

        /* public String getNdbno(String name) {
         for (GsonItem item : item) {
         if (item.getName().startsWith(name + ",")) {
         return item.getNdbno();
         }
         }
         return null;
         }*/
        public static class GsonItem implements Serializable{

            //private int offset;
            //private String group;
            private String name;
            private String ndbno;

            public String getName() {
                return name;
            }

            public String getNdbno() {
                return ndbno;
            }

            GsonItem() {
            }
        }

        GsonList() {
        }
    }

    GsonFoodSearch() {
    }
}
