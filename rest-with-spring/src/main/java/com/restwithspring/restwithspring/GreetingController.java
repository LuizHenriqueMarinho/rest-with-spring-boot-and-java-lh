package com.restwithspring.restwithspring;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //@ResponseBody + @Controller => retorna o objeto em json
public class GreetingController {

	private static final String template = "Hello, %s!"; //equivalente ao `texto ${variavel}` do javascript
	private final AtomicLong counter = new AtomicLong(); //para gerar um id
	
	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) 
	{
		return new Greeting(counter.incrementAndGet(), String.format(template, name)); //equivalente ao `texto ${variavel}` do javascript
	}
}