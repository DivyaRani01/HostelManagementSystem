/*
 * @Divya
 *  A DATA ACCESS OBJECT interface to perform  encapsulating the details of the persistence layers and CRUD interface for single entity
 */
package com.hostelMS.DaoImpl;

import java.util.List;

import javax.persistence.Query;

import com.hostelMS.Dao.AdminDao;
import com.hostelMS.Exception.GlobalException;
import com.hostelMS.cfg.hibernateUtil;
import com.hostelMS.model.Room;
import com.hostelMS.model.User;

import org.hibernate.Session;

public class AdminDaoImp implements AdminDao {

//1st Method -> to view all available rooms
	@Override
	public List<Room> AllRooms() {
		// TODO Auto-generated method stub
		//autoclosable session object
		try(Session ss=hibernateUtil.getSession()){
			//getting rows of a room table
			Query qu=ss.createQuery("from Room");
			List<Room> roomList=qu.getResultList();
			return roomList;
		}
	}
//2ND Method -> to view all registered users
	@Override
	public List<User> AllUsers() {
		// TODO Auto-generated method stub
		//Autoclosable session
		try(Session ss=hibernateUtil.getSession()){
			//getting rows of a room table
			String student="student";
			Query qu=ss.createQuery("from User where userRole=:student").setParameter("student", student);
			List<User> userList=qu.getResultList();
			return userList;
		}
	}
//3rd Method -> to create new room
	@Override
	public int createRoom(Room r) throws GlobalException {
		// TODO Auto-generated method stub
		//Autoclosable session
		try(Session ss=hibernateUtil.getSession()){
			
			ss.beginTransaction();
			String roomName=r.getRoomName();
			Room r2=null;
			//checking for existing roomname
			r2=(Room)ss.createQuery("from Room where roomName=:roomName").setParameter("roomName", roomName).uniqueResult();
			//if room name is unique then we can save the room
			if(r2==null)
			{
				ss.save(r);
				//commit
				ss.getTransaction().commit();
				return 1;
			}
			else {
				throw new GlobalException("Room Name is already existed..!");
			}
			
		}
	}
//4th Method -> to allot room to the users
	@Override
	public int allotRoom(int uId, int rId) throws GlobalException {
		// TODO Auto-generated method stub
		//Autoclosable session
		try(Session ss=hibernateUtil.getSession())
		{
			ss.beginTransaction();
			int status=ss.createQuery("update User set userRoom_roomId=:rId where userId=:uId").setParameter("rId", rId).setParameter("uId", uId).executeUpdate();
			ss.getTransaction().commit();
			return status;
			
		}
	}
//5th Method -> to delete the user
	@Override
	public int deleteUser(int uId) throws GlobalException {
		// TODO Auto-generated method stub
		//Autoclosable session
		try(Session ss=hibernateUtil.getSession())
		{
			ss.beginTransaction();
			int status= ss.createQuery("delete from User where userId=:uId").setParameter("uId", uId).executeUpdate();
			ss.getTransaction().commit();
			return status;
		}		
		
	}
//6th Method -> to retrieve the student who are in one room
	@Override
	public List<User> userInARoom(int rId) {
		// TODO Auto-generated method stub
		//Autoclosable session
         try(Session ss=hibernateUtil.getSession()){
			
			Query qu=ss.createQuery("from User where userRoom_roomId=:rId").setParameter("rId", rId);
			List<User> userList=qu.getResultList();
			return userList;
		}
	}
//7th Method -> to add due amount to the user
	@Override
	public int addDueAmount(int uId, int amount) throws GlobalException {
		// TODO Auto-generated method stub
		//Autoclosable session
		try(Session ss=hibernateUtil.getSession()){
			ss.beginTransaction();
			int dueamount=(int)ss.createQuery("select userFee from User where userId=:uId").setParameter("uId",uId).uniqueResult();
		
			dueamount+=amount;
			int status=ss.createQuery("update User set userFee=:dueamount where userId=:uId").setParameter("dueamount", dueamount).setParameter("uId", uId).executeUpdate();
			ss.getTransaction().commit();
			return status;
		}
	}
//8th Method ->to reduce the fee amount if user paid that amount
	@Override
	public int paidFeeAmount(int uId, int amount) throws GlobalException {
		// TODO Auto-generated method stu
        //Autoclosable session
		try(Session ss=hibernateUtil.getSession()){
			ss.beginTransaction();
			int dueamount=(int)ss.createQuery("select userFee from User where userId=:uId").setParameter("uId",uId).uniqueResult();
		
			dueamount-=amount;
			int status=ss.createQuery("update User set userFee=:dueamount where userId=:uId").setParameter("dueamount", dueamount).setParameter("uId", uId).executeUpdate();
			ss.getTransaction().commit();
			return status;
		}
	}
//9th Method to view individual user's profile
	@Override
	public User viewUserProfile(int uId) throws GlobalException {
		// TODO Auto-generated method stub
		//Autoclosable session
        try(Session ss=hibernateUtil.getSession()){
			
			User u1=ss.get(User.class, uId);
			return u1;
		}
		
	}

}
