package com.restwithspring.restwithspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restwithspring.restwithspring.services.PersonServices;
import com.restwithspring.restwithspring.vo.v1.PersonVO;

@RestController //@ResponseBody + @Controller => retorna o objeto em json
@RequestMapping("/api/person/v1") //versão v1 serve de indicador para o migration
public class PersonController {
	
	@Autowired
	private PersonServices service;
	//private PersonServices service = new PersonVO Services();
	
	//@GetMapping
	@RequestMapping(method=RequestMethod.GET,
			        produces = MediaType.APPLICATION_JSON_VALUE)//produz o JSON, n precisa dessa notação com o GetMapping
	public List<PersonVO> findAll() { //Execution of the current function will stop (the statements after throw won't be executed),
		
		return service.findAll();
	}
	
	//@GetMapping
	@RequestMapping(value = "/{id}",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)//produz o JSON
	public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception{ //Execution of the current function will stop (the statements after throw won't be executed),

		return service.findById(id);
	}
	
	//@PostMapping
	@RequestMapping(method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)//produz o JSON
	public PersonVO create(@RequestBody PersonVO person) { //o @RequestBody pega as informações no body, assim como no postman		
		return service.create(person);
	}
	
	//@PutMapping
	@RequestMapping(value = "/{id}",
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)//produz o JSON
	public PersonVO update(@RequestBody PersonVO person, @PathVariable Long id) { //o @RequestBody pega as informações no body, assim como no postman		
		return service.update(person);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity delete(@PathVariable(value = "id") Long id) { 	
		service.delete(id);
		return ResponseEntity.noContent().build(); //Para retonar o 204 ok
	}
}