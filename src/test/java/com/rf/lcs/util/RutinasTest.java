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
	
	final String CORREO_ELECTRONICO_CORRECTO = "migarcia@recursosformacion.com";
	final String CORREO_ELECTRONICO_CORRECTO2 = "mighel.garcia@rf.com";
	final String CORREO_ELECTRONICO_CORRECTO3 = "m@r.com";
	final String CORREO_ELECTRONICO_ERRONEO_1 = "migarcia.recursosformacion.com";
	final String CORREO_ELECTRONICO_ERRONEO_2 = "migarcia@recursosformacion";
	final String CORREO_ELECTRONICO_ERRONEO_3 = "@recursosformacion.com";
	final String CORREO_ELECTRONICO_ERRONEO_4 = "migarcia@";
	final String CORREO_ELECTRONICO_ERRONEO_5 = "m@r";
	
	final String DNI_VACIO ="";
	final int DNI_LONGITUD = 12;
	final String DNI_VALIDO1 = "25.062.225-E";
	final String DNI_NOVALIDO1 = "25.062.225";
	final String DNI_NOVALIDO2 = "25062225E";
	
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
				()-> assertFalse(Rutinas.isEmailValid(CORREO_ELECTRONICO_ERRONEO_1)),
				()-> assertFalse(Rutinas.isEmailValid(CORREO_ELECTRONICO_ERRONEO_2)),
				()-> assertFalse(Rutinas.isEmailValid(CORREO_ELECTRONICO_ERRONEO_3)),
				()-> assertFalse(Rutinas.isEmailValid(CORREO_ELECTRONICO_ERRONEO_4)),
				()-> assertFalse(Rutinas.isEmailValid(CORREO_ELECTRONICO_ERRONEO_5))
				);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {CORREO_ELECTRONICO_CORRECTO, 
					CORREO_ELECTRONICO_CORRECTO2, CORREO_ELECTRONICO_CORRECTO3})
	void testEmailIsTrue(String string) {
		assertTrue(Rutinas.isEmailValid(string));
	}
	@ParameterizedTest
	@ValueSource(strings = {CORREO_ELECTRONICO_ERRONEO_1, 
			CORREO_ELECTRONICO_ERRONEO_2, CORREO_ELECTRONICO_ERRONEO_3,
			CORREO_ELECTRONICO_ERRONEO_4, CORREO_ELECTRONICO_ERRONEO_5})
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

	@Test
	void testValidDNI() {
		
		  assertAll( 
				  ()-> assertFalse(Rutinas.validDNI(DNI_VACIO)), 
				  ()-> assertTrue(Rutinas.validDNI(DNI_VALIDO1)) );
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
