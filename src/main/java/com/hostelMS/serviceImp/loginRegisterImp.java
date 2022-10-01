package com.hostelMS.serviceImp;

import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;

import com.hostelMS.App;
import com.hostelMS.Dao.HostelMSDao;
import com.hostelMS.DaoImpl.HostelDaoImp;
import com.hostelMS.Exception.GlobalException;
import com.hostelMS.Service.AdminDashBoard;
import com.hostelMS.Service.UserDashBoard;
import com.hostelMS.Service.loginRegister;
import com.hostelMS.model.User;

public class loginRegisterImp implements loginRegister {
	
	//creating logger object 
	static Logger log=Logger.getLogger(App.class);
	//creating Scanner object to take user's input
	static Scanner obj=new Scanner(System.in);
	//creating HostelMSdao object with that of HostelDaoImp
	static HostelMSDao dao=new HostelDaoImp();
	
	
//creating registration method to register user
	@Override
	public void register() throws GlobalException {
		// TODO Auto-generated method stub
		log.info("\t\t\t\t>>-----------Welcome to Registration Portal---------------<<");
		log.info("Enter Username");
		String uname=obj.next();
		log.info("Create Password");
		String upwd=obj.next();
		log.info("Enter Phone number");
		String uphone=obj.next();
		log.info("Enter Address");
		String uaddress=obj.next();
		
		User u1=new User();
		u1.setUserName(uname);
		u1.setUserPassword(upwd);
		u1.setUserPhone(uphone);
		u1.setUserAddress(uaddress);
		u1.setUserRole("student");
		u1.setUserRoom(null);
		u1.setUserFee(0);
		
		ValidatorFactory vf= Validation.buildDefaultValidatorFactory();
		Validator valid=vf.getValidator();
		
		Set<ConstraintViolation<User>> violations=	valid.validate(u1);
		
		if(violations.size()>0)
		{
			for(ConstraintViolation<User> violate:violations)
				log.info(violate.getMessage());
		}
		else {
		
			int status = dao.registration(u1);
			log.info(status);
			if(status==1) {
				log.info("Registration success...!");
			}
			else {
				throw new GlobalException("Something went wrong..!\nPlease Try Again..!");
			}
		}
		
	}

	@Override
	public void login() throws GlobalException {
		// TODO Auto-generated method stub
        log.info("\t\t\t\t>>--------------Welcome to Login Portal--------------<<");
		
		log.info("Enter Username");
		String username=obj.next();
		log.info("Enter Password");
		String password=obj.next();
		User u1;
		//checking login
		 u1=dao.login(username, password);
		//success message
		log.info(u1.getUserName()+"..! Your Login is  Successfull");
		//creating object of user as well as admin dashboard
		UserDashBoardImp udl= new UserDashBoardImp();
		AdminDashBoardImp adl=new AdminDashBoardImp();
		//if userrole is student then userdashboard will open
		//if userrole is admin then admindashboard will open
		if(u1.getUserRole().equals("student")) {
			udl.dashboard(u1.getUserId());
		}
		else if(u1.getUserRole().equals("admin")) {
			adl.dashboard();
		}
	}
	


}
