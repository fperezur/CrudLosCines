package com.rf.lcs.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rf.lcs.exceptions.ControllerException;
import com.rf.lcs.exceptions.DaoException;
import com.rf.lcs.exceptions.DomainException;
import com.rf.lcs.persistence.entity.Cine;
import com.rf.lcs.servicio.CineService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@CrossOrigin(originPatterns = {"*"}, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping("/api/cine")
@Validated
public class CineController {

	@Autowired
	private CineService cineDao;

	@GetMapping("/test")//Comprobado en postman y funciona correctamente
	public Cine getCineTest() {
		Cine cine = null;
		
		System.out.println("LLamando a getCineTest");
		cine = new Cine(1L,"Albéniz", "Calle del centro, 8", "Centro", 50 );
		
		return cine;
	}
	//Testeado en Postman y funciona, se obtiene los datos de un registro según su Id
	@GetMapping("/{id}")
	@Operation(description = "Servicio que consulta un cine por ID")
	public ResponseEntity<Map<String, Object>> readOne(@PathVariable("id") Long ids) throws ControllerException{
		String mensaje = "";
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if(ids != null) {
			try {
				Optional<Cine> cineDB = this.cineDao.findById(ids);
				
				if(cineDB.isPresent()) {
					map.put("status", 1);
					map.put("data", cineDB.get());
					return new ResponseEntity<>(map, HttpStatus.OK);
				} else {
					mensaje = "NO existen datos";
				}
			} catch (Exception e) {
				mensaje = "Formato erróneo";
			}
		}else {
			mensaje = "Formato erróneo";
		}
		throw new ControllerException(mensaje);
	}
	//Testeado en Postman y funciona, se obtine listado de todos los registros
	@GetMapping({"", "/"})
	@Operation(description = "Recurso que lista todos los cines en la BBDD")
	public ResponseEntity<Map<String, Object>> readAll() throws ControllerException{
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Cine> listaCines = this.cineDao.listAll();
		if(!listaCines.isEmpty()) {
			System.out.println(listaCines);
			map.put("status", 1);
			map.put("data", listaCines);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			throw new ControllerException("No hay registros para mostrar");
		}
	}
	//Testeado en Postman y funciona
	@PostMapping
	@Operation(description = "Recurso que inserta un nuevo cine en la BBDD")
	public ResponseEntity<Map<String, Object>> insertNew(@Valid @RequestBody Cine cineNew, BindingResult bindingResult) 
			throws DomainException, ControllerException, DaoException {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if(!bindingResult.hasErrors()) {
			cineNew.setId_cine(0L);
			cineNew = this.cineDao.insert(cineNew);
			if(cineNew != null) {
				System.out.println("En alta: " + cineNew.toString());
				map.put("status", 1);
				map.put("data", cineNew);
				return new ResponseEntity<>(map, HttpStatus.OK);
			}else {
				throw new ControllerException("Error al hacer la inserción");
			}
		}
		throw new ControllerException("Error de datos al hacer la inserción");
	}
	//Testeado en Postman y funciona, modifica el registro existente
	@PutMapping
	@Operation(description = "Recurso que modifica un cine existente en la BBDD")
	public ResponseEntity<Map<String, Object>> modificacion(@RequestBody Cine cineInput) throws ControllerException, DomainException, DaoException {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (cineDao.update(cineInput)) {
			map.put("status", 1);
			map.put("message", "Actualización correcta");
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			throw new ControllerException("Error al hacer la modificacion");

		}
	}
	//Testeado en Postman y funciona, elimina registro existente según su Id
	@DeleteMapping("/{id}")
	@Operation(description = "Recurso que borra un cine en la BBDD")
	public ResponseEntity<Map<String, Object>> deleteCine(@PathVariable("id") String ids) 
			throws ControllerException {
		Map<String, Object> map = new LinkedHashMap<>();
		if(ids != null) {
			try {
					long id = Long.parseLong(ids);
					Optional<Cine> cineDB = cineDao.findById(id);
					cineDao.deleteById(cineDB.get().getId_cine());
					map.put("status", 1);
					map.put("data", "Registro borrado");
					return new ResponseEntity<>(map, HttpStatus.OK);
			} catch (Exception e) {
				throw new ControllerException("Error al eliminar el registro: " +
						e.getMessage());
			}
		}
		throw new ControllerException("No existe el registro a borrar");
	}
}
