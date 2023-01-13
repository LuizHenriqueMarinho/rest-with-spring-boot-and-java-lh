package com.restwithspring.restwithspring.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restwithspring.restwithspring.Converters.NumberConverter;
import com.restwithspring.restwithspring.exceptions.UnsupportedMathOperationException;
import com.restwithspring.restwithspring.math_operations.Operations;

@RestController //@ResponseBody + @Controller => retorna o objeto em json
public class MathController {

	private final AtomicLong counter = new AtomicLong(); //para gerar um id
	
	private Operations operations = new Operations();
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double sum(@PathVariable(value = "numberOne") String numberOne, //pegar info na url
	                  @PathVariable(value = "numberTwo") String numberTwo) //pegar info na url
	throws Exception{ //Execution of the current function will stop (the statements after throw won't be executed),
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) { //se não for numero, exceção
			throw new UnsupportedMathOperationException("insira apenas valores numéricos"); 
		}
		//return 1D; //retorna um double mockado
		return operations.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}

	@RequestMapping(value = "/sub/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double sub(@PathVariable(value = "numberOne") String numberOne, //pegar info na url
	                  @PathVariable(value = "numberTwo") String numberTwo) //pegar info na url
	throws Exception{ //Execution of the current function will stop (the statements after throw won't be executed),
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) { //se não for numero, exceção
			throw new UnsupportedMathOperationException("insira apenas valores numéricos"); 
		}
		//return 1D; //retorna um double mockado
		return operations.sub(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/mult/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double mult(@PathVariable(value = "numberOne") String numberOne, //pegar info na url
			@PathVariable(value = "numberTwo") String numberTwo) //pegar info na url
					throws Exception{ //Execution of the current function will stop (the statements after throw won't be executed),
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) { //se não for numero, exceção
			throw new UnsupportedMathOperationException("insira apenas valores numéricos"); 
		}
		//return 1D; //retorna um double mockado
		return operations.mult(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/div/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double div(@PathVariable(value = "numberOne") String numberOne, //pegar info na url
			@PathVariable(value = "numberTwo") String numberTwo) //pegar info na url
					throws Exception{ //Execution of the current function will stop (the statements after throw won't be executed),
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) { //se não for numero, exceção
			throw new UnsupportedMathOperationException("insira apenas valores numéricos"); 
		}
		//return 1D; //retorna um double mockado
		return operations.div(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/mean/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double mean(@PathVariable(value = "numberOne") String numberOne, //pegar info na url
			@PathVariable(value = "numberTwo") String numberTwo) //pegar info na url
					throws Exception{ //Execution of the current function will stop (the statements after throw won't be executed),
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) { //se não for numero, exceção
			throw new UnsupportedMathOperationException("insira apenas valores numéricos"); 
		}
		//return 1D; //retorna um double mockado
		return operations.mean(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/raiz/{numberOne}",
			method=RequestMethod.GET)
	public Double raiz(@PathVariable(value = "numberOne") String numberOne) //pegar info na url
					throws Exception{ //Execution of the current function will stop (the statements after throw won't be executed),
		if(!NumberConverter.isNumeric(numberOne)) { //se não for numero, exceção
			throw new UnsupportedMathOperationException("insira apenas valores numéricos"); 
		}
		//return 1D; //retorna um double mockado
		return operations.raiz(NumberConverter.convertToDouble(numberOne));
	}
	
	
}