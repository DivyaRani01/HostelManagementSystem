/* 
 * @author Divya Rani
 * A Real time  Project on  HOSTEL MANAGMENT SYSTEM which is a automated system for manual hostel management and we added lot of methods for admin and user.
   Hostel Management System is developed with Java Hibernate and hql along with Junit This project has two users One is Admin and other one is EndUser
 * 
 */


package com.hostelMS;
//importing required packages
import java.util.Scanner;
import org.apache.log4j.Logger;
import com.hostelMS.Exception.GlobalException;
import com.hostelMS.serviceImp.loginRegisterImp;

//creating a class called App
public class App 
{
	static Logger log=Logger.getLogger(App.class);
	//creating a main method which throw global exception
    public static void main( String[] args ) throws GlobalException
    {
    	//creating scanner object to take users input
    	Scanner obj=new Scanner(System.in);
    	log.info("\t\t\t\t\t\t\t||>-----------------------WELCOME TO HOSTEL MANAGEMENT SYSTEM---------------------<||");
    	loginRegisterImp loginReg= new loginRegisterImp();
    	log.info("\nPress 1 ***For Registration\nPress 2 ***For Login");
    	//taking user's input of choice
    	int op=obj.nextInt();
    	//using switch expression 
    	switch(op) 
    	{
    	//First case -> to register new profile in the database	
    	case 1->loginReg.register();
    	//Second case -> to login the existing user in the database
    	case 2->loginReg.login();
    	}
    	//closing the scanner object
    	obj.close();
    }
}
