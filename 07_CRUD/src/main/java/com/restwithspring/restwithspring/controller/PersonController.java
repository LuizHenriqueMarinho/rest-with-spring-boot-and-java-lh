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

import com.restwithspring.restwithspring.exceptions.ResourceNotFoundException;
import com.restwithspring.restwithspring.model.Person;
import com.restwithspring.restwithspring.services.PersonServices;

@RestController //@ResponseBody + @Controller => retorna o objeto em json
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonServices service;
	//private PersonServices service = new Person Services();
	
	//@GetMapping
	@RequestMapping(method=RequestMethod.GET,
			        produces = MediaType.APPLICATION_JSON_VALUE)//produz o JSON, n precisa dessa notação com o GetMapping
	public List<Person> findAll() { //Execution of the current function will stop (the statements after throw won't be executed),
		
		return service.findAll();
	}
	
	//@GetMapping
	@RequestMapping(value = "/{id}",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)//produz o JSON
	public Person findById(@PathVariable(value = "id") Long id) throws Exception{ //Execution of the current function will stop (the statements after throw won't be executed),

		return service.findById(id);
	}
	
	//@PostMapping
	@RequestMapping(method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)//produz o JSON
	public Person create(@RequestBody Person person) { //o @RequestBody pega as informações no body, assim como no postman		
		return service.create(person);
	}
	
	//@PutMapping
	@RequestMapping(value = "/{id}",
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)//produz o JSON
	public Person update(@RequestBody Person person, @PathVariable Long id) { //o @RequestBody pega as informações no body, assim como no postman		
		return service.update(person, id);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity delete(@PathVariable(value = "id") Long id) { 	
		service.delete(id);
		return ResponseEntity.noContent().build(); //Para retonar o 204 ok
	}
}