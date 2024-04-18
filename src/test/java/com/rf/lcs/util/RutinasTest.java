package com.rf.lcs.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class RutinasTest {
	
	String STRING_NULA;
	final String STRING_VACIA = "";
	final String STRING_CON_DATOS = "Hola";
	final String STRING_CON_DATOS1 = "H";
	
	final String NUMERO_DNI_OK = "25.062.225-E";
	final String NUMERO_DNI_ERROR_LETRA = "12.345.678-Ñ";
	final String NUMERO_DNI_ERROR_FORM_CORTO = "12.2.678-A";
	final String NUMERO_DNI_ERROR_FORM_CORTO2 = "122.678-A";
	final String NUMERO_DNI_ERROR_FORM_LARGO = "123.456.678-A";
	final String NUMERO_DNI_ERROR_FORM_ERR = "12345678A";
	final String NUMERO_DNI_ERROR_FORM_ERR2 = "12.345.678.A";
	
	final String CORREO_ELECTRONICO_CORRECTO = "migarcia@recursosformacion.com";
	final String CORREO_ELECTRONICO_CORRECTO2 = "mighel.garcia@rf.com";
	final String CORREO_ELECTRONICO_CORRECTO3 = "m@r.com";
	final String CORREO_ELECTRONICO_SIN_A = "migarcia.recursosformacion.com";
	final String CORREO_ELECTRONICO_SIN_TLD = "migarcia@recursosformacion";
	final String CORREO_ELECTRONICO_SIN_NOMBRE = "@recursosformacion.com";
	final String CORREO_ELECTRONICO_SIN_DOMINIO = "migarcia@";
	final String CORREO_ELECTRONICO_CON_NUMERO = "123@123.12";
	
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
	void testNewIsNotEmpty() {
		fail("Not yet implemented");
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
	

	@Test
	void testCompareDates() {
		fail("Not yet implemented");
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

	@Test
	void testIsPasswordValid() {
		fail("Not yet implemented");
	}

}
