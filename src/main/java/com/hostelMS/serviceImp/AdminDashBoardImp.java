/*
 * creating a Admin dashbaord that have all the implementation of different abstract method 
 */
package com.hostelMS.serviceImp;

//importing required packages
import java.util.List;
import java.util.Scanner;

import com.hostelMS.Dao.AdminDao;
import com.hostelMS.DaoImpl.AdminDaoImp;
import com.hostelMS.Exception.GlobalException;
import com.hostelMS.Service.AdminDashBoard;
import com.hostelMS.model.Room;
import com.hostelMS.model.User;

import org.apache.log4j.Logger;

public class AdminDashBoardImp  implements AdminDashBoard{
	
	static Logger log=Logger.getLogger(AdminDashBoardImp.class);
	static Scanner obj=new Scanner(System.in);
	static AdminDashBoardImp adl=new AdminDashBoardImp();
	static AdminDao dao=new AdminDaoImp();
//IniTIAL METHOD -> To implement admin dashboard
	@Override
	public void dashboard() {
		// TODO Auto-generated method stub
		log.info("\n\t\t\t>>------------Welcome to Admin Dashboard-------------------<<");
		
		int op=0;
		while(op<10)
		{
			
		log.info("\n\tPress 1 ***TO View all Rooms Data\t\tPress 2 ***To View all Users Data\n\tPress 3 ***To Create Rooms\t\t\tPress 4 ***To Allot room to user\n\tPress 5 ***To view user in a room\t\tPress 6 ***To view user profile\n\tPress 7 ***To Add Due Amount\t\t\tPress 8 ***To Pay Due Amount\n\tPress 9 ***To Delete user");
		
		op=obj.nextInt();
		switch(op) {
		
		case 1->adl.fetchAllRooms();
		case 2->adl.fetchAllUsers();
		case 3->adl.createRoom();
		case 4->adl.allotRoom();
		case 5->adl.userInARoom();
		case 6->adl.viewUserProfile();
		case 7->adl.addDueAmount();
		case 8->adl.depositDueAmount();
		case 9->adl.deleteUser();
		default->System.exit(0);
		}
		
	  }	
		
		
	}
//ist Method -> to fetch all room details
	@Override
	public void fetchAllRooms() {
		// TODO Auto-generated method stub
		List<Room> roomList = dao.AllRooms();
		log.info("\n\t\t\t----------List of ALL Rooms--------");
		for(Room r : roomList)
			//printing details
		
			log.info("Room Id : "+r.getRoomId()+"\t\t Room Name : "+r.getRoomName()+"\t\tRoom Type : "+r.getRoomType());
		
	}
//2nd Method -> tp fetch the details of all the users 
	@Override
	public void fetchAllUsers() {
		// TODO Auto-generated method stub
       List<User> userList = dao.AllUsers();
		log.info("\n\t\t\t----------List of ALL Users--------");
		for(User u1 : userList)
			//printing details
			log.info("User Id : "+u1.getUserId()+"\t\tName : "+u1.getUserName()+"\t\tContact No : "+u1.getUserPhone()+"\t\tRoom : "+u1.getUserRoom().getRoomId());
		   
	}
	
//3rd Method -> to create a room for individual user
	@Override
	public void createRoom() {
		// TODO Auto-generated method stub
		Room r=new Room();
		log.info("Enter Room Id");
		int rId = obj.nextInt();
		r.setRoomId(rId);
		log.info("Enter Room Name");
		String rName = obj.next();
		r.setRoomName(rName);
		log.info("Enter Room Type");
		String rType = obj.next();
		r.setRoomType(rType);
		try {
			// Calling create method via admin dao
			int st= dao.createRoom(r); 
			if(st==1) {
				log.info("Room added successfully...!");
			}
		}
		catch(GlobalException e) {
			log.info(e.getMessage());
		}
		
	}
//4th Method -> to  allot room to the user
	@Override
	public void allotRoom() {
		// TODO Auto-generated method stub
		log.info("Enter user Id");
		int uId =obj.nextInt();
		log.info("Enter room Id");
		int rId =obj.nextInt();
		int status;
		try {
			
			//calling allot method via admin dao
			status = dao.allotRoom(uId, rId);
			if(status==1)
				log.info("Room alloted successfully...!");
		} 
		catch (GlobalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//5TH mETHOD -> TO delete the user details 
	@Override
	public void deleteUser() {
		// TODO Auto-generated method stub
		log.info("Enter user Id to delete");
		int uId = obj.nextInt();
		int status;
		try {
			
			//calling delete method via admin dao
			status = dao.deleteUser(uId);
			if(status == 1)
				log.info("Successfully deleted...!");
		} catch (GlobalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//6th Method -> to know user in a room
	@Override
	public void userInARoom() {
		// TODO Auto-generated method stub
		log.info("Enter Room Id : ");
		int rId = obj.nextInt();
		
		//calling user room via admin dao
		List<User> userList = dao.userInARoom(rId);
		for(User u1:userList)
			//printing user details and information
			log.info("\n\t\t---------Data----------------\nUser Id : "+u1.getUserId()+"\tName : "+u1.getUserName()+"\tContact : "+u1.getUserPhone());
		
	}
//7th Method -> to add due amount
	@Override
	public void addDueAmount() {
		// TODO Auto-generated method stub
		log.info("Enter Amount to Add");
		int amount = obj.nextInt();
		log.info("Enter user Id");
		int uId = obj.nextInt();
		int status;
		try {
			
			//calling add due amount method via admin dao
			status = dao.addDueAmount(uId, amount);
			if(status==1)
				log.info("Amount Added Successfully..!");
		} catch (GlobalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
//8th Method -> to deposit due amount
	@Override
	public void depositDueAmount() {
		// TODO Auto-generated method stub
		log.info("Enter Amount to Pay Fees");
		int amount = obj.nextInt();
		log.info("Enter user Id");
		int uId = obj.nextInt();
		int reviseAmount;
		try {
			
			reviseAmount = dao.addDueAmount(uId, amount);
			if(reviseAmount >= 0)
				log.info("\nPAYMENT SUCCESSFULL \nFees Paid "+amount+".Rs \nYour Revised Due Fees Amount : "+reviseAmount+".Rs");
		} 
		catch (GlobalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
//9th Method -> to view user profile
	@Override
	public void viewUserProfile() {
		// TODO Auto-generated method stub
		log.info("Enter User Id : ");
		int uId = obj.nextInt();
		User u;
		try {
			
			// calling view user profile via admin dao
			u = dao.viewUserProfile(uId);
			log.info(u);
		} 
		catch (GlobalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
