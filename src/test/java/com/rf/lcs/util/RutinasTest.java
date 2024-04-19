package com.rf.lcs.util;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class RutinasTest {
	
	//*************** VALIDACIONES DE STRING **********************************
	String STRING_NULA;
	final String STRING_VACIA = "";
	final String STRING_CON_DATOS = "Hola";
	final String STRING_CON_DATOS1 = "H";
	
	//*************** VALIDACIONES DE DNI **************************************
	final String NUMERO_DNI_OK = "25.062.225-E";
	final String NUMERO_DNI_ERROR_LETRA = "12.345.678-Ñ";
	final String NUMERO_DNI_ERROR_FORM_CORTO = "12.2.678-A";
	final String NUMERO_DNI_ERROR_FORM_CORTO2 = "122.678-A";
	final String NUMERO_DNI_ERROR_FORM_LARGO = "123.456.678-A";
	final String NUMERO_DNI_ERROR_FORM_ERR = "12345678A";
	final String NUMERO_DNI_ERROR_FORM_ERR2 = "12.345.678.A";
	
	//**************** VALIDACIONES DE EMAIL ************************************
	final String CORREO_ELECTRONICO_CORRECTO = "migarcia@recursosformacion.com";
	final String CORREO_ELECTRONICO_CORRECTO2 = "mighel.garcia@rf.com";
	final String CORREO_ELECTRONICO_CORRECTO3 = "m@r.com";
	final String CORREO_ELECTRONICO_SIN_A = "migarcia.recursosformacion.com";
	final String CORREO_ELECTRONICO_SIN_TLD = "migarcia@recursosformacion";
	final String CORREO_ELECTRONICO_SIN_NOMBRE = "@recursosformacion.com";
	final String CORREO_ELECTRONICO_SIN_DOMINIO = "migarcia@";
	final String CORREO_ELECTRONICO_CON_NUMERO = "123@123.12";
	
	//************** VALIDACIONES DE FECHAS ********************************
		final static LocalDate AHORA = LocalDate.now();
		final static LocalDate MANIANA = LocalDate.now().plusDays(1);;
		final static LocalDate AYER = LocalDate.now().minusDays(2);

		final String FECHA_OK = "01/07/2022";
		final String FECHA_OK_1 = "26/12/9999";
		final String FECHA_OK_2 = "08/01/2022";
		final String FECHA_OK_3 = "09/05/2021";

		final String FECHA_ERROR = "2/30/2022";
		final String FECHA_ERROR_1 = "2022/2/22";
		final String FECHA_ERROR_2 = "31/2/2022";
		final String FECHA_ERROR_3 = "2/9993/9";
	
		//***********Validaciones de contraseñas************
		final String CONTRASENIA_OK = "qasLOASD#~@1!!!";
		final String CONTRASENIA_ERROR = "12345";
		final String CONTRASENIA_ERROR_1 = "123456789123456789123456789";
		final String CONTRASENIA_ERROR_2 = "}}¬¬¬||SD";

	
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"\t", "\n", "\r"})
	void testIsNullOrEmpty(String string) {
		
		/*
		 * Código normal sin ParameterizedTest
		 * assertAll( ()-> assertTrue(Rutinas.isEmpty(STRING_NULA)), ()->
		 * assertTrue(Rutinas.isEmpty(STRING_VACIA)), ()->
		 * assertFalse(Rutinas.isEmpty(STRING_CON_DATOS)) );
		 */
		//Mejora del código con @ParameterizedTest
		if(string == null) {
			assertTrue(Rutinas.isEmpty(string));
		} else {
			assertTrue(Rutinas.isEmpty(string.trim()));
		}
	}
	
	@ParameterizedTest
	@ValueSource(strings = {STRING_CON_DATOS, STRING_CON_DATOS1})
	void testIsEmptyWithData(String string) {
		assertFalse(Rutinas.isEmpty(string.trim()));
	}
	

	@Test
	void testIsEmailValid() {
		assertAll(
				()-> assertTrue(Rutinas.isEmailValid(CORREO_ELECTRONICO_CORRECTO)),
				()-> assertTrue(Rutinas.isEmailValid(CORREO_ELECTRONICO_CORRECTO2)),
				()-> assertTrue(Rutinas.isEmailValid(CORREO_ELECTRONICO_CORRECTO3)),
				()-> assertFalse(Rutinas.isEmailValid(CORREO_ELECTRONICO_SIN_A)),
				()-> assertFalse(Rutinas.isEmailValid(CORREO_ELECTRONICO_SIN_TLD)),
				()-> assertFalse(Rutinas.isEmailValid(CORREO_ELECTRONICO_SIN_NOMBRE)),
				()-> assertFalse(Rutinas.isEmailValid(CORREO_ELECTRONICO_SIN_DOMINIO)),
				()-> assertFalse(Rutinas.isEmailValid(CORREO_ELECTRONICO_CON_NUMERO))
				);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {CORREO_ELECTRONICO_CORRECTO, 
					CORREO_ELECTRONICO_CORRECTO2, CORREO_ELECTRONICO_CORRECTO3})
	void testEmailIsTrue(String string) {
		assertTrue(Rutinas.isEmailValid(string));
	}
	@ParameterizedTest
	@ValueSource(strings = {CORREO_ELECTRONICO_SIN_A, 
			CORREO_ELECTRONICO_SIN_TLD, CORREO_ELECTRONICO_SIN_NOMBRE,
			CORREO_ELECTRONICO_SIN_DOMINIO, CORREO_ELECTRONICO_CON_NUMERO})
	void testEmailIsFalse(String string) {
		assertFalse(Rutinas.isEmailValid(string));
		
	}

	@Test
	void testNewIsNotEmptyStringString() {
		String nulo = null;
		String vacio = "";
		String hola = "Hola";
		String original = "original";
		
		assertAll(
				() -> assertEquals(Rutinas.newIsNotEmptyStringString(original, nulo), "original"),
				() -> assertEquals(Rutinas.newIsNotEmptyStringString(original, vacio), "original"),
				() -> assertEquals(Rutinas.newIsNotEmptyStringString(original, hola), "Hola")
				);
	}
	@ParameterizedTest
	@CsvSource(value = {
			"10,,10",
			"7,25,25"
	})
	void testNewIsEmptyLongLong(Long tengo, Long llega, Long resultado) {
		assertEquals(Rutinas.newIsNotEmptyLongLong(tengo, llega), resultado);
	}
	@Test
	void testIsEmptyOrNull() {
		fail("Not yet implemented");
	}

	@ParameterizedTest
	@ValueSource(strings = {NUMERO_DNI_OK})
	void testValidDNI(String string) {
		assertTrue(Rutinas.validDNI(string));
		  
	}
	@ParameterizedTest
	@ValueSource(strings = {NUMERO_DNI_ERROR_LETRA,
			NUMERO_DNI_ERROR_FORM_CORTO,
			NUMERO_DNI_ERROR_FORM_CORTO2,
			NUMERO_DNI_ERROR_FORM_LARGO,
			NUMERO_DNI_ERROR_FORM_ERR,
			NUMERO_DNI_ERROR_FORM_ERR2})
	void testDNIIsFalse(String string) {
		assertFalse(Rutinas.validDNI(string));
		
	}
	
	//INICIO TEST DE FECHAS
	private static Stream<? extends Arguments> testDates(){
		return Stream.of(
					Arguments.of(List.of(AHORA, AHORA, 0)),
					Arguments.of(List.of(AHORA, MANIANA, -1)),
					Arguments.of(List.of(MANIANA, AHORA, 1)));
	}
	
	@ParameterizedTest
	@MethodSource("testDates")
	void testCompareDates(List<LocalDate> variables) {
		LocalDate fecha = (LocalDate) variables.get(0);
		LocalDate referencia = (LocalDate) variables.get(1);
		assertEquals(Rutinas.compareDates(fecha, referencia), variables.get(2));
	}

	@Test
	void testDateIsGreater() {
		fail("Not yet implemented");
	}

	@Test
	void testDateIsGreaterOrEqual() {
		fail("Not yet implemented");
	}

	@Test
	void testDateIsLess() {
		fail("Not yet implemented");
	}

	@Test
	void testDateIsLessOrEqual() {
		fail("Not yet implemented");
	}

	@Test
	void testDateIsValid() {
		fail("Not yet implemented");
	}

	@ParameterizedTest
	@CsvSource(value = {
			"true, qasLOASD#~@1!!!",
			"false, adfgddsfasgE",
			"false, adfgddsfasg$",
			"false, adf"
	})
	void testIsPasswordValid(Boolean resultado, String cadena) {
		assertEquals(Rutinas.isPasswordValid(cadena), resultado);
	}
	

}
