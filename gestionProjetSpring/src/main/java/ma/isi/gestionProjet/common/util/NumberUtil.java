/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.isi.gestionProjet.common.util;

import java.math.BigDecimal;


public class NumberUtil {
    
   
     private static final String CHAINE_VIDE=""; 
     private static  final Integer ZERO_INTEGER=0;
    
     
     public static BigDecimal toBigDecimal(String value){
        if(value==null || value.isEmpty()){
            return BigDecimal.ZERO;
        }else{
            return new BigDecimal(value);
        }
    }
     public static Long toLong(String value){
        if(value==null || value.isEmpty()){
            return null;
        }else{
            return new Long(value);
        }
    }
     
    public static String longToString(Long value){
        if(value==null){
            return null;
        }else{
            return String.valueOf(value);
        }
    }
    
    public static String toString(Object value){
        if(value==null){
            return CHAINE_VIDE;
        }else{
            return String.valueOf(value);
        }
    }
    
     public static Double toDouble(String value){
        if(value==null || value.isEmpty()){
            return 0D;
        }else{
            return Double.valueOf(value);
        }
    }
    public static String doubleToString(Double value){
        if(value==null){
            return CHAINE_VIDE;
        }else{
            return String.valueOf(value);
        }
    }
       public static int toInt(String value){
        if(value==null || value.isEmpty()){
            return ZERO_INTEGER;
        }else{
            return Integer.parseInt(value);
        }
    }
 public static String intToString(int value){
	 if(value==0){
         return CHAINE_VIDE;
     }else{
         return String.valueOf(value);
     }
   }
   
    
    
}
