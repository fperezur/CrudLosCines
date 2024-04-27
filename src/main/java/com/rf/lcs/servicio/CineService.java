package com.rf.lcs.servicio;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rf.lcs.exceptions.DaoException;
import com.rf.lcs.exceptions.DomainException;
import com.rf.lcs.persistence.entity.Cine;
import com.rf.lcs.repository.ICine;
import com.rf.lcs.service.interfaces.IService;
import com.rf.lcs.util.Rutinas;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@Service
@Validated
public class CineService implements IService<Cine, Long> {
	
	@Autowired
	private ICine cineRepository;
	
	//Validamos el registro a insertar
	public Cine validateInput(@Valid Cine cine) throws ConstraintViolationException, DomainException {
		if(cine == null) {
			throw new DomainException("El registro no es válido");
		}
		return cine;
	}

	@Override
	public Cine insert(Cine t) throws DomainException, DaoException {
		Cine cine = validateInput(t);
		List<Long> lista_entradas = cine.getCi_lista_entradas();
		if(Rutinas.isEmptyOrNull(lista_entradas)) {
			lista_entradas = List.of(0L);
		}
		cine.setCi_lista_entradas(lista_entradas);
		if(cine.isValidInsert()) {
			cineRepository.save(t);
		} else {
			throw new DaoException("El registro no es válido");
		}
		
		return cine;
	}

	@Override
	public boolean update(Cine cineInput) throws DomainException, DaoException {
		Optional<Cine> cineToUpdate = cineRepository.findById(cineInput.getId_cine());
		if(!cineToUpdate.isPresent()) {
			throw new DaoException("El registro no existe");
		}
		
		Cine cineValidate = validateInput(cineInput);
		List<Long> lista_entradas = cineValidate.getCi_lista_entradas();
		if(Rutinas.isEmptyOrNull(lista_entradas)) {
			lista_entradas = List.of(0L);
		}
		cineValidate.setCi_lista_entradas(lista_entradas);
		
		Cine cineInsertToDatabase = cineToUpdate.get();
		if(Objects.nonNull(cineInput.getCi_nombre()) && !"".equalsIgnoreCase(cineInput.getCi_nombre()))  {
			cineInsertToDatabase.setCi_nombre(cineInput.getCi_nombre());
		}
		if(Objects.nonNull(cineInput.getCi_calle()) && !"".equalsIgnoreCase(cineInput.getCi_calle()))  {
			cineInsertToDatabase.setCi_calle(cineInput.getCi_calle());
		}
		if(Objects.nonNull(cineInput.getCi_barrio()) && !"".equalsIgnoreCase(cineInput.getCi_barrio()))  {
			cineInsertToDatabase.setCi_barrio(cineInput.getCi_barrio());
		}
		if(Objects.nonNull(cineInput.getCi_capacidad()))  {
			cineInsertToDatabase.setCi_capacidad(cineInput.getCi_capacidad());
		}
		if(cineInsertToDatabase.isValidUpdate()) {
			cineRepository.save(cineInsertToDatabase);
		} else {
			throw new DaoException("El registro no es válido");
		}
		return true;
	}

	@Override
	public boolean deleteById(Long id) throws DaoException {
		Optional<Cine> cineDB = cineRepository.findById(id);
		if(!cineDB.isPresent()) {
			throw new DaoException("El registro no existe");
		}
		cineRepository.deleteById(id);
		return true;
	}

	@Override
	public List<Cine> listAll() {
		
		return cineRepository.findAll();
	}

	@Override
	public Optional<Cine> findById(Long id) {
		return cineRepository.findById(id);
	}

	@Override
	public boolean existById(Long id) {
		return cineRepository.existsById(id);
	}

}
