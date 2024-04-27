package com.rf.lcs.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rf.lcs.exceptions.ControllerException;
import com.rf.lcs.exceptions.DaoException;
import com.rf.lcs.exceptions.DomainException;
import com.rf.lcs.persistence.entity.Cine;
import com.rf.lcs.servicio.CineService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/cine")
@Validated
public class CineController {

	@Autowired
	private CineService cineDao;

	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> readOne(@PathVariable Long id){
		Map<String, Object> map = new LinkedHashMap<>();
		Optional<Cine> cine = cineDao.findById(id);
		if(cine.isPresent()) {
			map.put("status", 1);
			map.put("data", cine.get());
		} else {
			map.put("status", 0);
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@GetMapping({"", "/"})
	public ResponseEntity<Map<String, Object>> readAll() throws ControllerException{
		Map<String, Object> map = new LinkedHashMap<>();
		List<Cine> listaCines = cineDao.listAll();
		if(!listaCines.isEmpty()) {
			map.put("status", 1);
			map.put("data", listaCines);
		} else {
			throw new ControllerException("No hay registros para mostrar");
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> insertNew(@Valid @RequestBody Cine cineNew) 
			throws DomainException, ControllerException, DaoException {
		Map<String, Object> map = new LinkedHashMap<>();
		
		cineNew.setId_cine(0L);
		try {
			cineNew = cineDao.insert(cineNew);
			map.put("status", 1);
			map.put("data", cineNew);
		} catch (Exception e) {
			throw new ControllerException("Error al insertar el registro: " +
					e.getMessage());
		}
		
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Map<String, Object>> modificacion(@RequestBody Cine cineInput) throws ControllerException, DomainException, DaoException {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (cineDao.update(cineInput)) {
			map.put("status", 1);
			map.put("message", "Error al actualizar");
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			throw new ControllerException("Error al hacer la modificacion");

		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteCine(@NotNull @PathVariable("id") Long id) 
			throws DomainException, ControllerException, DaoException {
		Map<String, Object> map = new LinkedHashMap<>();
		try {
			if(cineDao.deleteById(id)) {
				map.put("status", 1);
				map.put("data", "Registro borrado");
			}
		} catch (Exception e) {
			throw new ControllerException("Error al eliminar el registro: " +
					e.getMessage());
		}
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping("/test")
	public @ResponseBody String text() {
		return "Test";
	}
}
