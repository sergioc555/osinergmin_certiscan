package src.com.certicom.certiscan.utils;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.primefaces.context.RequestContext;

import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.managedBeans.LoginMB;

public class Utils {
	
	
	private static Properties getProperty(){
		Properties prop = new Properties();
		InputStream input = null;
			try {
				System.out.println("intentando abrir el archivo");
				input = Utils.class.getResourceAsStream("constante.properties");
				
				if (input == null ){
					System.out.println("el input es nulo");
				}else{
					System.out.println(input.toString());
				}
				prop.load(input);
		 
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		return prop;
	}
	
	
	
	//LECTURA DE PROPIEDADES
	
	public static String readProperty(String property){
		Properties prop = getProperty();
		String result;
		result = prop.getProperty(property);
		
		try {
			if (result==null){
				return "SYS_VALOR_NO_ENCONTRADO";
			} else{
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "SYS_ERROR";
		}
		
	}
	
	
	public static Map<String, String> getProp_Nacionalidades(){
		Map<String, String> result = new LinkedHashMap<String, String>();
		String[] nacionalidades =  readProperty("nacionalidad").split(",");
		if(nacionalidades!= null && nacionalidades.length>0){
			for(String nacionalidad: nacionalidades){
				String[] values = nacionalidad.split(":");
				
				if(values.length==1){
					result.put(values[0], values[0]);
				}else{
//					result.put(values[1], values[0]);
					result.put(values[1], values[1]);
				}
			}
		}
		return result;
	}
	
	
	

	public static boolean isNumeric(String string) {
		if (string.trim().isEmpty() || string == null)
			return false;

		int i = 0;
		if (string.charAt(0) == '-') {
			if (string.length() > 0)
				i++;
			else
				return false;
		}
		int point = 0;
		for (; i < string.length(); i++) {
			if (string.charAt(i) == '.') {
				point++;
				if (point > 1)
					return false;
			} else {
				if (!Character.isDigit(string.charAt(i)))
					return false;
			}
		}
		return true;
	}
	
	

	
	

	public static BigDecimal isNumericExcel(String string) {
		BigDecimal result = BigDecimal.ZERO;
		String cadena = "";
		if (string.trim().isEmpty() || string == null)
			return BigDecimal.ZERO;

		int i = 0;
		if (string.charAt(0) == '-') {
			if (string.length() > 0) {
				i++;
				cadena = "-";
			} else
				return BigDecimal.ZERO;
		}

		// Verificando cuantos puntos trae la celda del excel, por ejm si es:
		// 150000 excel lo trae 1.500.00
		/*
		 * int numberPoint=0; for(int k=0; k<string.length(); k++){
		 * if(string.charAt(k)=='.') numberPoint++; }
		 */

		int point = 0;
		// if(numberPoint>1){
		for (; i < string.length(); i++) {
			if (string.charAt(i) == '.') {
			} else {
				if (!Character.isDigit(string.charAt(i)))
					return BigDecimal.ZERO;
				else
					cadena = cadena + string.charAt(i);
			}
		}
		result = BigDecimal.valueOf(Double.parseDouble(cadena));
		/*
		 * }else{ for(; i<string.length(); i++){ if(string.charAt(i)=='.'){
		 * }else{ if(!Character.isDigit(string.charAt(i))) return
		 * BigDecimal.ZERO; } } result =
		 * BigDecimal.valueOf(Double.parseDouble(string)); }
		 */

		return result;
	}

	public static BigDecimal isNumericExcelPuntoDecimal(String stringDec) {
		BigDecimal result = BigDecimal.ZERO;
		String cadena = "";
		String string = "";
		// Quitando el punto decimal
		for (int k = 0; k < stringDec.length(); k++) {
			if (stringDec.charAt(k) == '.') {
				break;
			} else {
				string = string + stringDec.charAt(k);
			}
		}

		string = string.replaceAll(",", "").trim();
		if (string.trim().isEmpty() || string == null)
			return BigDecimal.ZERO;
		int i = 0;
		if (string.charAt(0) == '-') {
			if (string.length() > 0) {
				i++;
				cadena = "-";
			} else
				return BigDecimal.ZERO;
		}

		// if(numberPoint>1){
		for (; i < string.length(); i++) {
			if (!Character.isDigit(string.charAt(i))) {
				return BigDecimal.ZERO;
			} else
				cadena = cadena + string.charAt(i);
		}
		result = BigDecimal.valueOf(Double.parseDouble(cadena));

		return result;
	}
	
	public Integer getMesAnterior(int val){
		int mes = 0;
		
		switch (val) {
		case 1:
			mes = 12;
			break;

		default:
			mes =val;
		}
		
		return mes;
	}
	
	
	public static String getMonthByValueInt(Integer val) {
		String month = "";
		switch (val) {
		case 1:
			month = "ENERO";
			break;
		case 2:
			month = "FEBRERO";
			break;
		case 3:
			month = "MARZO";
			break;
		case 4:
			month = "ABRIL";
			break;
		case 5:
			month = "MAYO";
			break;
		case 6:
			month = "JUNIO";
			break;
		case 7:
			month = "JULIO";
			break;
		case 8:
			month = "AGOSTO";
			break;
		case 9:
			month = "SETIEMBRE";
			break;
		case 10:
			month = "OCTUBRE";
			break;
		case 11:
			month = "NOVIEMBRE";
			break;
		case 12:
			month = "DICIEMBRE";
			break;
		}

		return month;
	}

	public static String getMonthByValue(String val) {
		String month = "";
		switch (val) {
		case "01":
			month = "ENERO";
			break;
		case "02":
			month = "FEBRERO";
			break;
		case "03":
			month = "MARZO";
			break;
		case "04":
			month = "ABRIL";
			break;
		case "05":
			month = "MAYO";
			break;
		case "06":
			month = "JUNIO";
			break;
		case "07":
			month = "JULIO";
			break;
		case "08":
			month = "AGOSTO";
			break;
		case "09":
			month = "SETIEMBRE";
			break;
		case "10":
			month = "OCTUBRE";
			break;
		case "11":
			month = "NOVIEMBRE";
			break;
		case "12":
			month = "DICIEMBRE";
			break;
		}

		return month;
	}

	public static String addZeoLeftMonth(int month) {
		String mes = "" + month;
		if (mes.length() == 1)
			mes = "0" + mes;
		return mes;
	}

	public static Date setDateMontAndYear(Integer date, Integer month,
			Integer year) {

		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.DATE, date);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);

		return calendar.getTime(); // Devuelve el objeto Date con las nuevas
									// horas añadidas
	}
	
	public static Date addNextDay(Date _date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_date);
		
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)+1);
		return calendar.getTime();
	}

	public static Date setDateToActualMonthYear(Integer date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		Calendar newDate = Calendar.getInstance();

		newDate.set(Calendar.DATE, date);
		newDate.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		newDate.set(Calendar.YEAR, calendar.get(Calendar.YEAR));

		return newDate.getTime();

	}
	
	public static Integer getHourFromDate(Date _date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	public static Integer getMinuteFromDate(Date _date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_date);
		return calendar.get(Calendar.MINUTE);
	}
	
	
	public static Date removeHourMinuteSecond(Date _date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_date);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();

	}
	
	
	public static Date setHourFromDateToDate(Date _dateTo,Date _dateFrom){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_dateTo);
		calendar.set(Calendar.HOUR,getHourFromDate(_dateFrom));
		calendar.set(Calendar.MINUTE,getMinuteFromDate(_dateFrom));
		calendar.set(Calendar.SECOND,0);
	
		return calendar.getTime();
	}
	
	public static Date setHourMinuteToDate(Date _dateTo,int hour,int minute){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_dateTo);
		calendar.set(Calendar.HOUR_OF_DAY,hour);
		calendar.set(Calendar.MINUTE,minute);
		calendar.set(Calendar.SECOND,0);
	
		return calendar.getTime();
	}
	
	
	
	public static String calcularDiferenciaFechas(DateTime fechaAnterior, DateTime fechaActual) {
        Integer days = Days.daysBetween(fechaAnterior, fechaActual).getDays();
        Integer hours = Hours.hoursBetween(fechaAnterior, fechaActual).getHours();
        Integer minutes = Minutes.minutesBetween(fechaAnterior, fechaActual).getMinutes();
        Integer seconds = Seconds.secondsBetween(fechaAnterior, fechaActual).getSeconds();

        if (fechaActual.getMillisOfSecond() < fechaAnterior.getMillisOfSecond()) {
            seconds += 1;
        }
        if (hours > 23) {
            hours = hours % 24;
        }
        if (minutes > 59) {
            minutes = minutes % 60;
        }
        if (seconds > 59) {
            seconds = seconds % 60;
        }

        String ret = "";
        if (days > 0) ret += (days == 1 ? days + " dia ": days + " dias ");
        if (hours > 0) ret += hours + " horas ";
        if (minutes > 0) ret += minutes + " minutos ";
        if (seconds > 0) ret += seconds + " segundos ";

        return ret;
}
	
	
	public static boolean calcularDiferenciaHoras(DateTime fechaAnterior, DateTime fechaActual, int horas) {
        boolean mayor = false;
		Integer days = Days.daysBetween(fechaAnterior, fechaActual).getDays();
        Integer hours = Hours.hoursBetween(fechaAnterior, fechaActual).getHours();
        Integer minutes = Minutes.minutesBetween(fechaAnterior, fechaActual).getMinutes();
        Integer seconds = Seconds.secondsBetween(fechaAnterior, fechaActual).getSeconds();

        if (fechaActual.getMillisOfSecond() < fechaAnterior.getMillisOfSecond()) {
            seconds += 1;
        }
        if (hours > 23) {
            hours = hours % 24;
        }
        if (minutes > 59) {
            minutes = minutes % 60;
        }
        if (seconds > 59) {
            seconds = seconds % 60;
        }
        
        if(hours>=1){
        	mayor = true;
        } else{
        	mayor = false;
        }


        return mayor;
}
	
	
	
	public static String calcularTiempoTranscurrido(Integer _days,Integer _hours, Integer _minutes, Integer _seconds ) {
        Integer days = _days;
        Integer hours = _hours;
        Integer minutes = _minutes;
        Integer seconds = _seconds;

//        if (fechaActual.getMillisOfSecond() < fechaAnterior.getMillisOfSecond()) {
//            seconds += 1;
//        }
        if (hours > 23) {
            hours = hours % 24;
        }
        if (minutes > 59) {
            minutes = minutes % 60;
        }
        if (seconds > 59) {
            seconds = seconds % 60;
        }

        String ret = "";
        if (days > 0) ret += (days == 1 ? days + " dia ": days + " dias ");
        if (hours > 0) ret += hours + " horas ";
        if (minutes > 0) ret += minutes + " minutos ";
        if (seconds > 0) ret += seconds + " segundos ";

        return ret;
}
	
	
	
	
	
	
	

	public static String dateFormatToString(Date fecha, int opcion) {
		String fechaString = "";
		String FORMATO = "";
		switch (opcion) {
		case 1:
			FORMATO = "dd-MM-yyyy";
			break;
		case 2:
			FORMATO = "HH:mm:ss";
			break;
		case 3:
			FORMATO = "MMMM YYYY";
			break;	
		case 4:
			FORMATO = "dd/MM/yyyy";
			break;
		case 5:
			FORMATO = "dd/MM/yyyy HH:mm:ss";
			break;	
		}
		DateFormat dformat = new SimpleDateFormat(FORMATO);
		fechaString = dformat.format(fecha);
		return fechaString;
	}

	public static Date stringFormatToDate(String fecha, int opcion)
			throws ParseException {
		Date fechaDate = null;
		String FORMATO = "";
		switch (opcion) {
		case 1:
			FORMATO = "dd-MM-yyyy HH:mm:ss";
			break;
		case 2:
			FORMATO = "dd-MM-yyyy";
			break;
		// agregar
		}
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO);

		fechaDate = sdf.parse(fecha);
		return fechaDate;
	}
	
	public static Date restarMes(Date fecha, int mes){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-mes);
		
		return calendar.getTime();
	}
	
	public static Integer getNroMes(Date fecha){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		
		return calendar.get(Calendar.MONTH);
	}
 
//	lee objetos de tipo [{"contacto":"monkar","telefono":"3751544"}{"contacto":"veronica","telefono":"4515451"}]
	public static List<VGeneric> leerListaPatronArreglo(String cadena){
		//String parameter
		List<VGeneric> result = new ArrayList<VGeneric>();
		if (cadena == null) {
			return result;
		}

		if (cadena.isEmpty()) {
			return result;
		} else {
			
			try{
				Class contenedor = Class.forName("src.com.certicom.certiscan.utils.VGeneric");
				Class[] paramString = new Class[1];	
				paramString[0] = String.class;
				cadena = cadena.replace("[", "").replace("]", "");
				cadena = cadena.replace("},{", ",,");
				String objetos[] = cadena.split(",,");
				
	//			objetos
				for(String objeto: objetos){
					System.out.println("objeto -> "+objeto);
	//				VGeneric contenedor = new VGeneric(); //crea el objeto contenedor
					Object instancia = contenedor.newInstance();
					
					objeto = objeto.replace("{", "").replace("}", "");
	//				elimino las comillas
					objeto = objeto.replace("\"", "");
					
	//				atributos
					String atributos[] = objeto.split(",");
					int index = 1;
					for(String atributo:atributos){
						System.out.println("atributo -> "+atributo);
						Method metodo = contenedor.getDeclaredMethod("setVal0"+index, paramString);
	//					String valor a agregar :
						String valores[] = atributo.split(":");
						metodo.invoke(instancia, new String(valores[1])); //ponemos 1 para ignorar el primer valor (la descripcion);
						index ++;
					}
					result.add((VGeneric) instancia);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}
	}
	
//	escribe objetos de tipo [{"contacto":"monkar","telefono":"3751544"},{"contacto":"veronica","telefono":"4515451"}]
	public static String escribirListaPatronArreglo(List<VGeneric> lista,String[] headers) {
		String result = "";
		if (lista == null || lista.size() == 0) {
			return result;
		} else {
			try{
				//String parameter
				Class contenedor = Class.forName("src.com.certicom.certiscan.utils.VGeneric");
				
				for (VGeneric objeto : lista) {
					String objetoEscrito="";
					
					int index=1;
					for(String head: headers){
						Method metodo = contenedor.getDeclaredMethod("getVal0"+index);
						
						objetoEscrito+="\""+head+"\""+":";
						objetoEscrito+="\""+ metodo.invoke(objeto)+"\""+",";
						
						index++;
					}
					objetoEscrito = objetoEscrito.substring(0, objetoEscrito.length() - 1);
					objetoEscrito="{"+objetoEscrito+"},";
					
					result += objetoEscrito;
				}
				
				
				
				result = result.substring(0, result.length() - 1);
				result = "["+result+"]";
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}
	}
	
	
	
//	agrega un elemento de tipo {"contacto":"monkar","telefono","37252544"}
	public static String agregarPatronArreglo(String source, VGeneric item,String[] headers) {
		String result = "";
		if (item == null){
			return "NULO"; 
		}
		else if(item.getVal01() == null || item.getVal01().isEmpty()){
			return "EMPTY";
		}else{
			try { 
				
				List<VGeneric> lista = leerListaPatronArreglo(source);
				
				
				for(VGeneric objeto: lista){
					if(objeto.getVal01().equals(item.getVal01())){
						return "EXIST";
					}
				}
				
				lista.add(item);
				return escribirListaPatronArreglo(lista, headers);
//				Class contenedor = Class.forName("src.com.certicom.certiscan.utils.VGeneric");
//				List<VGeneric> lista = leerListaPatronArreglo(source);
//				
//				int index = 1;
//				String objetoEscrito ="";
//				for(String head: headers){
//					Method metodo = contenedor.getDeclaredMethod("getVal0"+index);
//					objetoEscrito+="\""+head+"\""+":";
//					objetoEscrito+="\""+ metodo.invoke(item)+"\""+",";
//					
//					index++;
//				}
//				objetoEscrito = objetoEscrito.substring(0, objetoEscrito.length() - 1);
//				objetoEscrito="{"+objetoEscrito+"}";
//				if(lista.size()==0){
//					result = "["+objetoEscrito+"]";
//				}else{
//					result = source.replace("]", "");
//					result += ","+objetoEscrito+"]";
//				}
//				return result;
			} catch (Exception e) {
				e.printStackTrace();
				return "ERROR";
			}
		}
	}

	public static String removerPatronArreglo(String source, VGeneric item, String[] headers) {
		if (item == null){
			return "NULO";
		}else{
			List<VGeneric> lista = leerListaPatronArreglo(source);
			
			
//			int indexKey = 0;
//			
//			int index = 0;
//			for(String x: headers){
//				if(x.equalsIgnoreCase(key)){
//					indexKey = index;
//					break;
//				}
//				index++;
//			}
			List<VGeneric> nuevaLista = new ArrayList<VGeneric>();
			for(VGeneric objeto: lista){
				if(!(objeto.getVal01().equals(item.getVal01())))  //siempre el key estará en el valor 01
					nuevaLista.add(objeto);
			}
			return escribirListaPatronArreglo(nuevaLista, headers);
		}
	}
	
	
	
//	lee objetods de tipo {rojo,azul,verdo,negro}
	public static List<String> leerListaPatronSimple(String cadena) {
		List<String> result = new ArrayList<String>();

		if (cadena == null) {
			return result;
		}

		if (cadena.isEmpty()) {
			return result;
		} else {
			cadena = cadena.replace("{", "").replace("}", "");
			result.addAll(Arrays.asList(cadena.split(",")));
			return result;
		}
	}

	public static String escribirListaPatronSimple(List<String> lista) {
		String result = "";
		if (lista == null || lista.size() == 0) {
			return result;
		} else {
			result += "{";
			for (String i : lista) {
				result += i + ",";
			}
			result = result.substring(0, result.length() - 1);
			result += "}";
			return result;
		}
	}

	public static String agregarPatronSimple(String source, String item) {

		if (item == null)
			return "NULO";

		item = item.replaceAll("\\s+", "");
		if (item.isEmpty())
			return "EMPTY";

		List<String> lista = leerListaPatronSimple(source);

		if (lista.size() == 0)
			return "{" + item + "}";

		for (String i : lista) {
			if (i.trim().equalsIgnoreCase(item.trim())) {
				return "EXIST";
			}
		}

		source = source.replace("}", "");
		source += "," + item + "}";
		return source;
	}

	public static String removerPatronSimple(String source, String item) {

		if (item == null)
			return "NULO";

		item = item.replaceAll("\\s+", "");
		if (item.isEmpty())
			return "EMPTY";

		List<String> lista = leerListaPatronSimple(source);

		if (lista.size() == 0)
			return source;

		List<String> result = new ArrayList<String>();
		for (String i : lista) {
			if (!i.trim().equalsIgnoreCase(item.trim())) {
				result.add(i);
			}
		}

		return escribirListaPatronSimple(result);
	}

	public static void showSuccess(String value) {
		FacesUtils.showFacesMessage(value, 3);
		RequestContext.getCurrentInstance().update("gnGrowl");
	}

	public static void showError(String value) {
		FacesUtils.showFacesMessage(value, 1);
		RequestContext.getCurrentInstance().update("gnGrowl");
	}

	public static void hideDialog(String dialogo) {
		RequestContext.getCurrentInstance().execute(
				"PF('" + dialogo + "').hide()");
	}
	
	public static List<Date> gePeriodosByTrimestre(Integer nro,Integer anio){
		
		List<Date> listDates = new ArrayList<>();
		Integer mes_ini = 0,mes_fin =0; 
		switch (nro) {
		case 1:
			mes_ini =1;
			mes_fin=3;
			break;
		case 2:
			mes_ini =4;
			mes_fin=6;
			break;
		case 3:
			mes_ini =7;
			mes_fin=9;
			break;
		case 4:
			mes_ini =10;
			mes_fin=12;
			break;

		}
		
		for (int i = mes_ini; i <= mes_fin; i++) {
			listDates.add(stringToDate("01", i+"", anio.toString()));
		}
		
		return listDates;
	}
	
	public static Date stringToDate(String dia, String mes, String anio)
	{
        Date fecha = null;
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        String strFecha = anio+"-"+mes+"-"+dia;
        try {

            fecha = formatoDelTexto.parse(strFecha);

        } catch (ParseException ex) {

            ex.printStackTrace();

        }
        
        return fecha;
		
	}
	
	
	public static List<DateBean> generatesPeriodosByYear(Integer anio){
		List<DateBean> listDates = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {
			DateBean db = new DateBean();
			db.setMonth(getMonthByValueInt(i));
			db.setPeriodo(stringToDate("01", i+"", anio.toString()));
			listDates.add(db);
		}
		
		return listDates;
	}
	
	public static String formatGmapCenter(String lat,String longt){
		String separator = ",";
		String format  ;
		format = lat +" " + separator+ " " + longt;
		return format;
	}
	
	public static List<Date> getDaysBetweenDates(Date startdate, Date enddate)
	{
	    List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(startdate);

	    while (calendar.getTime().before(enddate))
	    {
	        Date result = calendar.getTime();
	        dates.add(result);
	        calendar.add(Calendar.DATE, 1);
	    }
	    dates.add(enddate);
	    return dates;
	}
	
	public static List<BaseBean> getCriteriosGPS(){
		List<BaseBean> lista = new ArrayList<>();
		BaseBean bd1 = new BaseBean();
		bd1.setIdvalor(1);
		bd1.setDescripcion("Hora Salida Casa");
		
		BaseBean bd2 = new BaseBean();
		bd2.setIdvalor(2);
		bd2.setDescripcion("Hora LLegada Casa");
		
		BaseBean bd3 = new BaseBean();
		bd3.setIdvalor(3);
		bd3.setDescripcion("Nro Citas Programadas");
		
		BaseBean bd5 = new BaseBean();
		bd5.setIdvalor(5);
		bd5.setDescripcion("Visitas Actualizadas GPS");
		
		BaseBean bd4 = new BaseBean();
		bd4.setIdvalor(4);
		bd4.setDescripcion("Tiempo en Casa");
		
		
		lista.add(bd1);
		lista.add(bd2);
		lista.add(bd3);
		lista.add(bd5);
		lista.add(bd4);

		return lista;
		
	}
	
	public static String convertirLongBytes(long bytes, boolean si) {
	    int unit = si ? 1000 : 1024;
	    if (bytes < unit) return bytes + " B";
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
	    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}
	
	
}
