/*
 * Creating a class with the name of global exception that have all the exception that might encounter in the project
 */
package com.hostelMS.Exception;

public class GlobalException extends Exception {
	
	//global exception
	public GlobalException(String msg) 
	{
		super(msg);
	}

}
