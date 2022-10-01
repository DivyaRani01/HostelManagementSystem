/*
 *  @Divya
 *  A DATA ACCESS OBJECT interface to perform  encapsulating the details of the persistence layers and CRUD interface for single entity
 */

package com.hostelMS.DaoImpl;
//importing required packages
import com.hostelMS.Dao.UserDao;
import com.hostelMS.Exception.GlobalException;
import com.hostelMS.cfg.hibernateUtil;
import com.hostelMS.model.User;

import org.hibernate.Session;

public class UserDaoImp implements UserDao {
//Ist method -> Getting user's Room Details
	@Override
	public User viewRoom(int uId) {
		// TODO Auto-generated method stub
          try(Session ss=hibernateUtil.getSession()){
			//Fetching user's object
			User u2= ss.get(User.class,uId);
			return u2;
		}
		
	}
//2nd Method -> Getting dueAmount of user
	@Override
	public int viewDueAmount(int uId) {
		// TODO Auto-generated method stub
		//autoclosable session
		try(Session ss=hibernateUtil.getSession()){
		
			int amount=(int)ss.createQuery("select userFee from User where UserId=:uId").setParameter("uId", uId).uniqueResult();
				return amount;
		}
	}
//3rd Method ->Getting Profile details of the user via uId
	@Override
	public User viewProfile(int uId) {
		// TODO Auto-generated method stub
       try(Session ss=hibernateUtil.getSession()){
			//fetching 
			User u2=ss.get(User.class,uId);
			return u2;
	     }
	}
//4th Method -> Getting update of the user's phone number
	@Override
	public int changePhone(int uId, String phone) {
		// TODO Auto-generated method stub
		try(Session ss=hibernateUtil.getSession()){
			ss.beginTransaction();
		int status=ss.createQuery("update User set userPhone=:phone where UserId=:uId").setParameter("phone", phone).setParameter("uId",uId).executeUpdate();
			ss.getTransaction().commit();
			return status;	
		}
	}
//5th Method -> to change password of user's database
	@Override
	public int changePassword(int uId, String oldPwd, String newPwd) throws GlobalException {
		// TODO Auto-generated method stub
		  try(Session ss=hibernateUtil.getSession()){
			ss.beginTransaction();
			User u1=ss.get(User.class, uId);
			//verifying the user's password
			if(u1.getUserPassword().equals(oldPwd)) {
				int status =ss.createQuery("update User set userPassword=:newPwd where userId=:uId").setParameter("newPwd", newPwd).setParameter("uId", uId).executeUpdate();
				ss.getTransaction().commit();
				return status;
			}
			else {
				throw new GlobalException("To update password you have to enter current password");
			}
			
			
			
		}
	}
	

}
