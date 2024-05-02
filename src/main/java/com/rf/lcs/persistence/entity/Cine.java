package com.rf.lcs.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import com.rf.lcs.persistence.interfaces.Modelo;
import com.rf.lcs.util.Rutinas;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cine")
public class Cine implements Modelo{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id_cine;
	
	@Column
	@NotNull
	@Size(min = 5, max = 30, message = "El nombre de la calle debe tener entre 5 y 30")
	@NotEmpty
	private String ci_nombre;
	
	@Column
	@NotNull
	@NotEmpty
	@Size(min = 5, max = 100, message = "El nombre de la calle debe tener entre 5 y 100")
	private String ci_calle;
	
	@Column
	@NotNull
	@NotEmpty
	@Size(min = 5, max = 100, message = "El nombre de barrio debe tenr entre 5 y 100")
	private String ci_barrio;
	
	@Column
	@NotNull
	@Min(value = 1, message = "La cpacidad ha de ser mayor a 1")
	@Max(value = 100, message = "La capacidad ha de ser menor que 100")
	private int ci_capacidad;
	
	@ElementCollection
	private List<Long> ci_lista_entradas;
	
	

	public Cine() {
		super();
	}
	
	
	//Constructor para testear el servicio.
	public Cine(Long id_cine,
			@NotNull @Size(min = 5, max = 30, message = "El nombre de la calle debe tener entre 5 y 30") @NotEmpty String ci_nombre,
			@NotNull @NotEmpty @Size(min = 5, max = 100, message = "El nombre de la calle debe tener entre 5 y 100") String ci_calle,
			@NotNull @NotEmpty @Size(min = 5, max = 100, message = "El nombre de barrio debe tenr entre 5 y 100") String ci_barrio,
			@NotNull @Min(value = 1, message = "La cpacidad ha de ser mayor a 1") @Max(value = 100, message = "La capacidad ha de ser menor que 100") int ci_capacidad) {
		super();
		this.id_cine = id_cine;
		this.ci_nombre = ci_nombre;
		this.ci_calle = ci_calle;
		this.ci_barrio = ci_barrio;
		this.ci_capacidad = ci_capacidad;
	}



	public Cine(long id_cine, String ci_nombre, String ci_calle, String ci_barrio,
			int ci_capacidad, List<Long> ci_lista_entradas) {
		super();
		this.id_cine = id_cine;
		this.ci_nombre = ci_nombre;
		this.ci_calle = ci_calle;
		this.ci_barrio = ci_barrio;
		setCi_lista_entradas(ci_lista_entradas);
	}

	public Long getId_cine() {
		return id_cine;
	}

	public void setId_cine(Long id_cine) {
		this.id_cine = id_cine;
	}

	public String getCi_nombre() {
		return ci_nombre;
	}

	public void setCi_nombre(String ci_nombre) {
		this.ci_nombre = ci_nombre;
	}

	public String getCi_calle() {
		return ci_calle;
	}

	public void setCi_calle(String ci_calle) {
		this.ci_calle = ci_calle;
	}

	public String getCi_barrio() {
		return ci_barrio;
	}

	public void setCi_barrio(String ci_barrio) {
		this.ci_barrio = ci_barrio;
	}

	public int getCi_capacidad() {
		return ci_capacidad;
	}



	public void setCi_capacidad(int ci_capacidad) {
		this.ci_capacidad = ci_capacidad;
	}



	public List<Long> getCi_lista_entradas() {
		return ci_lista_entradas;
	}



	public void setCi_lista_entradas(List<Long> ci_lista_entradas) {
		if(Rutinas.isEmptyOrNull(ci_lista_entradas)) {
			ci_lista_entradas = new ArrayList<Long>();
		}
		this.ci_lista_entradas = ci_lista_entradas;
	}
	
	@Override
	public String toString() {
		return "Cine [id_cine=" + id_cine + ", ci_nombre=" + ci_nombre + ", ci_calle=" + ci_calle + ", ci_barrio="
				+ ci_barrio + ", ci_capacidad=" + ci_capacidad + "]";
	}



	@Override
	public boolean isValidInsert() {
		return true;
	}

	@Override
	public boolean isValidUpdate() {
		// TODO Auto-generated method stub
		return true;
	}

}
