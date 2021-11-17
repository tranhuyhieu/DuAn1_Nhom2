
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XDate {
    static final SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");

    public static Date toDate(String date,String...pattern){
        try {
            if(pattern.length>0)formater.applyPattern(pattern[0]);
            if(date==null)return XDate.now();
            return formater.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex); 
        }
    }
    
    public static String toString(Date date, String pattern){
        formater.applyPattern(pattern);
        return formater.format(date);
    }
    
    public static Date now() {
        return new Date();  
    }
}
