package com.restwithspring.restwithspring.math_operations;


public class Operations {
	
	public Double sum(Double numberOne, Double numberTwo) 
	{ 
		return (numberOne) + (numberTwo);
	}


	public Double sub(Double numberOne, Double numberTwo) 
	{
		return (numberOne) - (numberTwo);
	}
	

	public Double mult(Double numberOne, Double numberTwo) 
	{
		return (numberOne) * (numberTwo);
	}
	

	public Double div(Double numberOne, Double numberTwo) 
	{ 	
		return (numberOne) / (numberTwo);
	}
	
	
	public Double mean(Double numberOne, Double numberTwo) 
	{ 
		return ((numberOne) + (numberTwo))/2;
	}
	

	public Double raiz(Double numberOne)
	{
		return Math.sqrt((numberOne));
	}
}
