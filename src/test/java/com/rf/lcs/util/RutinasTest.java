package com.rf.lcs.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RutinasTest {
	
	String STRING_NULA;
	final String STRING_VACIA = "";
	final String STRING_CON_DATOS = "Hola";
	
	final String CORREO_ELECTRONICO_CORRECTO = "migarcia@recursosformacion.com";
	final String CORREO_ELECTRONICO_CORRECTO2 = "mighel.garcia@rf.com";
	final String CORREO_ELECTRONICO_CORRECTO3 = "m@r.com";
	final String CORREO_ELECTRONICO_ERRONEO_1 = "migarcia.recursosformacion.com";
	final String CORREO_ELECTRONICO_ERRONEO_2 = "migarcia@recursosformacion";
	final String CORREO_ELECTRONICO_ERRONEO_3 = "@recursosformacion.com";
	final String CORREO_ELECTRONICO_ERRONEO_4 = "migarcia@";
	final String CORREO_ELECTRONICO_ERRONEO_5 = "m@r";
	
	@Test
	void testIsEmpty() {
		assertAll(
				()-> assertTrue(Rutinas.isEmpty(STRING_NULA)),
				()-> assertTrue(Rutinas.isEmpty(STRING_VACIA)),
				()-> assertFalse(Rutinas.isEmpty(STRING_CON_DATOS))
				);
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
		fail("Not yet implemented");
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
