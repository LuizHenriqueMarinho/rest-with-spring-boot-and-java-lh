package com.restwithspring.restwithspring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restwithspring.restwithspring.exceptions.ResourceNotFoundException;
import com.restwithspring.restwithspring.model.Person;
import com.restwithspring.restwithspring.repositories.PersonRepository;

@Service
public class PersonServices {
	
	//private final AtomicLong counter = new AtomicLong(); //para gerar um id mockado
	private Logger logger = Logger.getLogger(PersonServices.class.getName()); //tipo console.lo
	
	@Autowired
	PersonRepository repository;
	
	public List<Person> findAll() {
		logger.info("procurando toas as pessoa");
		
		return repository.findAll();
	}
	
	
	public Person findById(Long id) {
		
		logger.info("procurando uma pessoa");
		
		//Person person = new Person();
		//implementando person mockada
//		person.setFirstName("Luiz Henrique");
//		person.setLastName("Marinho");
//		person.setAddress("tag city");
//		person.setGender("male");
		
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("id não encontrado"));
	}
	
	public Person create(Person person) {
		logger.info("Criando uma pessoa");
		return repository.save(person);
	}
	
	public Person update(Person person, Long id) {
		logger.info("editando uma pessoa");
		Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id não encontrado"));
		
		//Person entity = repository.findById(person.getId()) //pega a pessoa no banco de dados e joga na entity para ser editada
		//.orElseThrow(() -> new ResourceNotFoundException("id não encontrado"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		logger.info("deletando uma pessoa");
		Person entity = repository.findById(id) //pega a pessoa no banco de dados e joga na entity para ser editada
				.orElseThrow(() -> new ResourceNotFoundException("id não encontrado"));
		repository.delete(entity);
	}
	
	
//	private Person mockPerson(int i) {
//		Person person = new Person();
//		//implementando person mockada
//		person.setId(counter.incrementAndGet()); //gerando um id 
//		person.setFirstName("Person name " + i);
//		person.setLastName("Last name " + i);
//		person.setAddress("Some address in Brasil " + i);
//		person.setGender("male");
//		return person;
//	}
}
