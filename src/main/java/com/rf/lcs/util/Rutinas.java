package com.rf.lcs.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class Rutinas {
//En esta clase centralizamos todas las validaciones de la aplicación
// TODO https://www.youtube.com/watch?v=kovCkD7mgP0&list=PLq19OgzwgcOalrrU5s_NDviwoJOtMDRcR&index=2
	//MIN 13:04
	/**
	 * Patron para validar el email, evitando puntos finales antes de la @ y que
	 * solo contenga caracteres alfanumericos
	 */
	private final static String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private final static int DNI_LONGITUD = 12;
	/**
	 * Permite verificar que un DNI cumple con el Patron XX.XXX.XXX-L
	 */
	private final static String DNI_PATTERN ="\\d{2}\\.\\d{3}\\.\\d{3}-[a-zA-Z]"; //99.999.999-Z
	/**
	 * Letras con las cuales se comprobara la validez del DNI
	 */
	private final static String DNI_LETTER = "TRWAGMYFPDXBNJZSQVHLCKE";
	/**
	 * Patrón para password que admite letras, números y un caracter especila min=6 max=20 caracteres
	 */
	private final static String PASSWORD_PATTERN ="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
	
	/**
	 * *******************************************************************************
	 * Devuelve true si la string pasada es nula o vacia
	 * 
	 * @param email String a comprobar
	 * @return true, en caso que el formato sea valido
	 * @date: Octubre 2023
	 * @author: Miguel Garcia
	 */
	public static boolean isEmpty(String prueba) {
		return prueba == null || prueba.trim().length() == 0;
	}
	
	/**
	 * *******************************************************************************
	 * Comprueba si el email tiene un formato que pueda ser valido.
	 * 
	 * @param email String a comprobar
	 * @return true, en caso que el formato sea valido
	 * @date: Octubre 2023
	 * @author: Miguel Garcia
	 * 
	 **************************************************************************************/
	public static boolean isEmailValid(String email) {
		return !isEmpty(email) && email.matches(EMAIL_PATTERN);
	}
	
	/**
	 * *******************************************************************************
	 * Devuelve el valorNuevo si este no es nullo o vacio, si no, devuelve
	 * valorActual
	 *
	 * @param actualValue Valor que tiene el campo en la actualidad
	 * @param newValue  Valor que llega para modificar el valor actual
	 * @return true, en caso que el formato sea valido
	 * @date: Octubre 2023
	 * @author: Miguel Garcia
	 */
	public static<T> T newIsNotEmpty(T actualValue, T newValue) {
		return Objects.nonNull(newValue)? newValue : actualValue;
	}
	
	/**
	 * **********************************************************************************
	 * Devuelve True si la coleccion recibida es nula o vacia
	 * 
	 * @param collection
	 * @return
	 */
	public static boolean isEmptyOrNull(Collection<?> col) {
		return col == null || col.isEmpty();
	}
	
	/**
	 * ***********************************************************************************
	 * Esta funcion verifica que el DNI cumple el siguiente formato: xx.xxx.xxx-L y
	 * la longitud correcta
	 * 
	 * @param dni String con DNI a ser validado
	 * @return true, si el DNI cumple el estandar nacional.
	 * @date: Octubre 2023
	 * @author: Miguel Garcia
	 * 
	 **************************************************************************************/
	public static boolean validDNI(String dni) {
		if(dni == null){
			return false;
		}
		//Si es un NIE se hacen las operaciones necesarias para poder calcular
		//luego la letra correcta
		if(dni.startsWith("X")) {
			dni = dni.replaceFirst("X", "0");
		}else if(dni.startsWith("Y")) {
			dni = dni.replaceFirst("Y", "1");
		}else if(dni.startsWith("Z")) {
			dni = dni.replaceFirst("Z", "2");
		}
		
		if(dni.length() != DNI_LONGITUD) {
			return false;
		}
		if(!dni.matches(DNI_PATTERN)) {
			return false;
		}
		
		String dniNumber = dni.substring(0, dni.length() - 2).replace(".", "");
		int dniValueNumber = Integer.parseInt(dniNumber);
		
		Character letterDNI = Character.toUpperCase(dni.charAt(dni.length() - 1));
		
		if(DNI_LETTER.charAt(dniValueNumber % 23) == letterDNI) {
			return true;
		}else {
			return false;
		}
		
	}
	
	/*****************************************************************************************
	 * Fechas
	 *****************************************************************************************/

	/**
	 * *******************************************************************************
	 * Compara dos fechas
	 * 
	 * @param date1 Fecha a comprobar uno
	 * @param date2   Fecha comparacion
	 * @return -1, 0 +1
	 * @date: Octubre 2023
	 * @author: Miguel Garcia
	 */
	public static int compareDates(LocalDate date1, LocalDate date2) {
		if(date1 != null && date2 != null) {
			return date1.compareTo(date2);
		}
		return 999;
	}
	
	/**
	 * *******************************************************************************
	 * Valida fecha superior a minima
	 * 
	 * @param date Fecha a comprobar uno
	 * @param min   Fecha comparacion
	 * @return True
	 * @date: Octubre 2023
	 * @author: Miguel Garcia
	 */
	public static boolean dateIsGreater(LocalDate date, LocalDate min) {
		return Rutinas.compareDates(date, min) == 1;
	}
	
	/**
	 * *******************************************************************************
	 * Valida fecha superior o igual a minima
	 * 
	 * @param date Fecha a comprobar uno
	 * @param min   Fecha comparacion
	 * @return True
	 * @date: Octubre 2023
	 * @author: Miguel Garcia
	 */
	public static boolean dateIsGreaterOrEqual(LocalDate date, LocalDate min) {
		return Rutinas.compareDates(date, min) == 1 || Rutinas.compareDates(date, min) == 0;
	}
	
	/**
	 * ****************************************************************************
	 * Valida una fecha inferior a minima
	 * 
	 * @param date Fecha a comprobar
	 * @param min   Fecha comparacion
	 * @return True
	 * @date: Octubre 2023
	 * @author: Miguel Garcia
	 */
	public static boolean dateIsLess(LocalDate date, LocalDate min) {
		return Rutinas.compareDates(date, min) == -1;
	}
	
	/**
	 * ****************************************************************************
	 * Valida una fecha inferior o igual a minima
	 * 
	 * @param date Fecha a comprobar
	 * @param min   Fecha comparacion
	 * @return True
	 * @date: Octubre 2023
	 * @author: Miguel Garcia
	 */
	public static boolean dateIsLessOrEqual(LocalDate date, LocalDate min) {
		return Rutinas.compareDates(date, min) == -1 || Rutinas.compareDates(date, min) == 0;
	}
	
	/**
	 * *******************************************************************************
	 * esFechaValida Recibe una string con formato fecha dd/mm/aaaa y comprueba el
	 * formato
	 * 
	 * @param fecha
	 * @return
	 * @date: Octubre 2023
	 * @author: Miguel Garcia
	 */
	public static boolean dateIsValid(String fecha) {
		if(isEmpty(fecha)) {
			return false;
		}
		Optional<LocalDate> date = Optional.empty();
		try {
			date = Optional.of(LocalDate.parse(fecha, Constantes.FORMATO_FECHA_EU));
			if(date.isPresent()) {
				return true;
			}
		} catch (DateTimeParseException e) {
			// TODO: handle exception
		}
		return false;
	}
	
	public static boolean isPasswordValid(String password) {
		return isEmpty(password) && password.matches(PASSWORD_PATTERN);
	}
	
}
