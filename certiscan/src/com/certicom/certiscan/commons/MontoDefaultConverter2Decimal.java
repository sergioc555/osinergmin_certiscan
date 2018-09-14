package com.certicom.certiscan.commons;

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
@FacesConverter("montoDefaultConverter2Decimal")
public class MontoDefaultConverter2Decimal extends NumberConverter {
     @Override
       public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException { 
         
                   DecimalFormat format=(DecimalFormat) DecimalFormat.getInstance();                                     
                   DecimalFormatSymbols custom=new DecimalFormatSymbols();   
                   format.applyPattern("#,##0.00");                    
                   custom.setDecimalSeparator('.');
                   custom.setGroupingSeparator(',');
                   format.setDecimalFormatSymbols(custom);
                   
                   return  format.format(new BigDecimal(value.toString()));                 
         }
}
