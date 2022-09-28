package com.hostelMS.serviceImp;

import java.util.Scanner;
import java.util.regex.Pattern;

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
		log.info("welcome to registeration");
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
		//using regular expressions to check data correctness
		if(Pattern.matches("[a-zA-Z]{4,}", uname)&&Pattern.matches("[a-zA-Z0-9@#]{6,}",upwd)&&Pattern.matches("[0-9]{10}", uphone))
		{
			//saving the user details
			int status=dao.registration(u1);
			//log.info(status);
			if(status==1) {
				log.info("Registration success...!");
			}
			else {
				throw new GlobalException("Something went wrong..!\nPlease Try Again..!");
			}
		}
		else {
			throw new GlobalException("Invalid data");
		}
		
	}

	@Override
	public void login() throws GlobalException {
		// TODO Auto-generated method stub
        log.info("---------Welcome to Login----------");
		
		log.info("Enter username");
		String username=obj.next();
		log.info("Enter password");
		String password=obj.next();
		//checking login
		User u1=dao.login(username, password);
		//success message
		log.info("Hello"+u1.getUserName()+"Login Success");
		UserDashBoard udl= new UserDashBoardImp();
		AdminDashBoard adl=new AdminDashBoardImp();
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


}
