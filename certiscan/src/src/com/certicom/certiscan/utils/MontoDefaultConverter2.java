package src.com.certicom.certiscan.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.NumberConverter;

/**
*
* @author Jhon
*/
@FacesConverter("montoDefaultConverter2")
public class MontoDefaultConverter2 extends NumberConverter {
     @Override
       public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException { 
         
                   DecimalFormat format=(DecimalFormat) DecimalFormat.getInstance();                                     
                   DecimalFormatSymbols custom=new DecimalFormatSymbols();   
                   format.applyPattern("#,##0.00000");                    
                   custom.setDecimalSeparator('.');
                   custom.setGroupingSeparator(' ');
                   format.setDecimalFormatSymbols(custom);
                   
                   return  format.format(new BigDecimal(value.toString()));                 
         }
}
