package com.restwithspring.restwithspring.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import com.restwithspring.restwithspring.controller.PersonController;
import com.restwithspring.restwithspring.exceptions.ExceptionResponse;
import com.restwithspring.restwithspring.exceptions.ResourceNotFoundException;
import com.restwithspring.restwithspring.mapper.DozerMapper;
import com.restwithspring.restwithspring.model.Person;
import com.restwithspring.restwithspring.repositories.PersonRepository;
import com.restwithspring.restwithspring.vo.v1.PersonVO;

@Service
public class PersonServices {
	
	//private final AtomicLong counter = new AtomicLong(); //para gerar um id mockado
	private Logger logger = Logger.getLogger(PersonServices.class.getName()); //tipo console.lo
	
	@Autowired
	PersonRepository repository;

	
	public List<PersonVO> findAll(){
		logger.info("procurando toas as pessoa");
		
		var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class); //converte a listagem para VO
		persons
			.stream()
			.forEach(p -> {
				try {
					p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		return persons;
	}
	
	
	public PersonVO findById(Long id) throws Exception {
		
		logger.info("procurando uma pessoa");
		
		//PersonVO person = new PersonVO();
		//implementando person mockada
//		person.setFirstName("Luiz Henrique");
//		person.setLastName("Marinho");
//		person.setAddress("tag city");
//		person.setGender("male");
		
		var entity = repository.findById(id) //substitui o var no java 8
				.orElseThrow(() -> new ResourceNotFoundException("id não encontrado"));
		PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public PersonVO create(PersonVO person) throws Exception{
		logger.info("Criando uma pessoa");
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public PersonVO update(PersonVO person) throws Exception{
		logger.info("editando uma pessoa");
		var entity = repository.findById(person.getKey())
		     .orElseThrow(() -> new ResourceNotFoundException("id não encontrado"));
		
		//PersonVO entity = repository.findById(person.getId()) //pega a pessoa no banco de dados e joga na entity para ser editada
		//.orElseThrow(() -> new ResourceNotFoundException("id não encontrado"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("deletando uma pessoa");
		var entity = repository.findById(id) //pega a pessoa no banco de dados e joga na entity para ser editada
				.orElseThrow(() -> new ResourceNotFoundException("id não encontrado"));
		repository.delete(entity);
	}
	
	
//	private PersonVO mockPerson(int i) {
//		PersonVO person = new PersonVO();
//		//implementando person mockada
//		person.setId(counter.incrementAndGet()); //gerando um id 
//		person.setFirstName("PersonVO name " + i);
//		person.setLastName("Last name " + i);
//		person.setAddress("Some address in Brasil " + i);
//		person.setGender("male");
//		return person;
//	}
}
