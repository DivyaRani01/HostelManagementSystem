package com.hostelMS;
//importing required packages
import com.hostelMS.Dao.AdminDao;
import com.hostelMS.Dao.HostelMSDao;
import com.hostelMS.Dao.UserDao;
import com.hostelMS.DaoImpl.AdminDaoImp;
import com.hostelMS.DaoImpl.HostelDaoImp;
import com.hostelMS.DaoImpl.UserDaoImp;
import com.hostelMS.Exception.GlobalException;
import com.hostelMS.cfg.hibernateUtil;
import com.hostelMS.model.Room;
import com.hostelMS.model.User;

import org.hibernate.Session;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Unit test for simple App.
 */
public class AppTest {
    
	
		@Test
		@DisplayName("----Registration Testing-----")
		void registrationTest() {
			
			//creating dao object
			HostelMSDao dao = new HostelDaoImp();
			//creating user object that already existed in the database
			User t = new User();
		    t.setUserName("Divya");
	        t.setUserPassword("Divu123");
			t.setUserPhone("8800372300");
			t.setUserAddress("dELHI");
			
			// Creating a new user object
			User t1 = new User();
		    t1.setUserName("Richa");
	        t1.setUserPassword("ric123");
			t1.setUserPhone("9876543219");
			t1.setUserAddress("Mumbai");
			
			assertAll(
					
					//positive test case to add user
					()->assertEquals(1,dao.registration(t)),
					
					//Negative Test Case to add user that already existed
					()->assertThrows(GlobalException.class,()->dao.registration(t1))
			);
		}
		
		// test 2
		@Test
		@DisplayName("-----Login Testing---------")
		void loginTest() throws GlobalException {
			
			//creating dao object og hostelDao
			HostelMSDao dao = new HostelDaoImp();
			
			//creating session object
			Session ses = hibernateUtil.getSession();
			
			//fetching the user
			User u1 = ses.get(User.class, 1);
			
			// fetching same database
			User t = dao.login("Divya", "Divu123");
			
			assertAll(
					//positive case -> by giving correct details
					()->assertEquals(u1.toString(),t.toString()),
					//negative case->by giving wrong details
					()->assertThrows(GlobalException.class,()->dao.login("Divyyaa", "di56789"))
			);
		}

		
		@Test
		@DisplayName("Test case to check creatie room")
		void addRoomTest(){
			
			//creating Admin object
			AdminDao dao = new AdminDaoImp();
			
			//creating room object that have alerady existing user
			Room r = new Room();
			r.setRoomId(101);
			r.setRoomName("Abc");
			r.setRoomType("Ac");
			
			//creating room object
			Room t = new Room();
			t.setRoomId(111);
			t.setRoomName("ElevRoom");
			t.setRoomType("NonAC");
			
			assertAll(
					
					//Positive test case
					()->assertEquals(1,dao.createRoom(t)),
					//negative test case
					()->assertThrows(GlobalException.class,()->dao.createRoom(r))
			);
		}

		@Test
		@DisplayName("---Allot Room Testing---")
		void allotRoomTest() {
			
			
			AdminDao dao = new AdminDaoImp();
		
			assertAll(
					//positive case by giving true details
					()->assertEquals(1,dao.allotRoom(1, 101)),
					
					//negative test case by giving wrong details
					()->assertThrows(GlobalException.class,()->dao.allotRoom(400, 800))
			);
			
		}

		
		@Test
		@DisplayName("--Delete User Testing----")
		void deleteUserTest() {
			
			// creating admin dao object
			AdminDao dao = new AdminDaoImp();
			
			assertAll(
					
					//positive case
					()->assertEquals(1,dao.deleteUser(8)),
					
					//negative case
					()->assertThrows(GlobalException.class,()->dao.deleteUser(010))
					);
		}
		

	
		@Test
		@DisplayName("----View user testing----")
		void viewUserTest() throws GlobalException {
			
			// creating admin dao object
			AdminDao dao = new AdminDaoImp();
			//creating session object
			Session ses = hibernateUtil.getSession();
			// fetching user detail
			User u1 = ses.get(User.class, 1);
			// fetching same method via profile method
			User u2 = dao.viewUserProfile(1);
			assertAll(
					
		            //positive case
					()->assertEquals(u1.toString(),u2.toString()),
					//negative case
					()->assertThrows(GlobalException.class,()->dao.viewUserProfile(900))
			);
		}
		
		
		@Test
		@DisplayName("---Due fee Testing---")
		void dueAmountTest() {
			
			// creating user dao method
			UserDao dao = new UserDaoImp();
			
			assertAll(
					
					()->assertEquals(10000,dao.viewDueAmount(2)),
					
					()->assertThrows(GlobalException.class,()->dao.viewDueAmount(500))
			);
		}
		
		
		@Test
		@DisplayName("--Change phone number testing--")
		void changePhoneTest() {
			
		
			UserDao dao = new UserDaoImp();
			
			assertAll(
					
					()->assertEquals(1,dao.changePhone(10, "9650362040")),
					
					()->assertThrows(GlobalException.class,()->dao.changePhone(90, "9999999999"))
					);
		}
		
		
		@Test
		@DisplayName("-----Change password test-----")
		void changePasswordTest() {
			
			UserDao dao = new UserDaoImp();
			
			assertAll(
					()->assertEquals(1,dao.changePassword(6,"char44","char444")),
					()->assertThrows(GlobalException.class,()->dao.changePassword(4,"char444","char44"))
					);
		}
		
	
}
