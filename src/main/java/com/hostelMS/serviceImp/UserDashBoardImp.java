package com.hostelMS.serviceImp;
//importing required packagesto be used further
import java.util.Scanner;

import com.hostelMS.App;
import com.hostelMS.Dao.UserDao;
import com.hostelMS.DaoImpl.HostelDaoImp;
import com.hostelMS.DaoImpl.UserDaoImp;
import com.hostelMS.Exception.GlobalException;
import com.hostelMS.Service.AdminDashBoard;
import com.hostelMS.Service.UserDashBoard;
import com.hostelMS.Service.loginRegister;
import com.hostelMS.model.User;

import org.apache.log4j.Logger;

public class UserDashBoardImp implements UserDashBoard{
	//declaring static objects to use in entire class
	//creating logger object 
	static Logger log=Logger.getLogger(App.class);
	//creating Scanner object to take user's input
	static Scanner obj=new Scanner(System.in);
    //creating object of UserDashboardimp
	static UserDashBoardImp udl=new UserDashBoardImp();
	//Creating object of userdao as dao
	static UserDao dao=new UserDaoImp();
	static int userId;
	
//creating dashboard method	
	@Override
	public void dashboard(int uId) throws GlobalException {
		// TODO Auto-generated method stub
			log.info("\t\t\t---------------------Welcome to userdashboard----------------------");
			int op=0;
			userId=uId;
			while(op<6) {
				//user can select operation
				log.info("\nPress 1 for viewRoom\nPress 2 for view dueAmount \nPress 3 for view profile\nPress 4 for Update Phone number \nPress 5 for Change password");
				
				op=obj.nextInt();
				
				switch(op) {
			
			case 1->udl.viewRoom();
			
			case 2->udl.viewDueAmount();
			
			case 3->udl.viewProfile();
			
			case 4->udl.changePhonenumber();
			
			case 5->udl.changePassword();
			}
			
		}

		
	}
//creating method to view details of the room	
	@Override
	public void viewRoom() {
		// TODO Auto-generated method stub
		//Calling dao layer
		User u1=dao.viewRoom(userId);
		log.info("Hello "+u1.getUserName()+" your room number is"+u1.getUserRoom().getRoomId()+" room name is "+u1.getUserRoom().getRoomName()+" and it is "+u1.getUserRoom().getRoomType()+" room");
		
	}
//Creating method to view dueamount of the user
	@Override
	public void viewDueAmount() {
		// TODO Auto-generated method stub
		//calling dao layer
		int amount=dao.viewDueAmount(userId);
		log.info("your fee due upto this month is :"+amount);
		
	}
//creating method to view profile of the user	
	@Override
	public void viewProfile() {
		// TODO Auto-generated method stub
		User u1=dao.viewProfile(userId);
		log.info(u1);
		
	}
//creating method to chnage phone number	
	@Override
	public void changePhonenumber() {
		// TODO Auto-generated method stub
		log.info("Enter New Phone number");
		String phone=obj.next();
		int st=dao.changePhone(userId, phone);
		if(st==1) {
			log.info("Phone number updated");
		}
		
	}
//creating method to update passwoed	
	@Override
	public void changePassword() throws GlobalException {
		// TODO Auto-generated method stub
		log.info("Enter Current Password");
		String oldpwd=obj.next();
		log.info("Enter New Password");
		String newpwd=obj.next();
		int st=dao.changePassword(userId, oldpwd, newpwd);
		if(st==1) {
			log.info("password changed");
		}
		
	}
	
	

	

}
