package com.hostelMS.DaoImpl;

import com.hostelMS.Dao.UserDao;
import com.hostelMS.Exception.GlobalException;
import com.hostelMS.cfg.hibernateUtil;
import com.hostelMS.model.User;

import org.hibernate.Session;

public class UserDaoImp implements UserDao {
//Getting user's profile -> to print details
	@Override
	public User viewRoom(int uId) {
		// TODO Auto-generated method stub
          try(Session ss=hibernateUtil.getSession()){
			
			User u2= ss.get(User.class,uId);
			return u2;
		}
		
	}
//Getting dueAmount     
	@Override
	public int viewDueAmount(int uId) {
		// TODO Auto-generated method stub
		//autoclosable session
		try(Session ss=hibernateUtil.getSession()){
		
			int amount=(int)ss.createQuery("select UserFee from User where UserId=:uId").setParameter("uId", uId).uniqueResult();
				return amount;
		}
	}
//Getting Profile details of the user
	@Override
	public User viewProfile(int uId) {
		// TODO Auto-generated method stub
       try(Session ss=hibernateUtil.getSession()){
			
			User u2=ss.get(User.class,uId);
			return u2;
	     }
	}
//Getting update of the user's phone number
	@Override
	public int changePhone(int uId, String phone) {
		// TODO Auto-generated method stub
		try(Session ss=hibernateUtil.getSession()){
			ss.beginTransaction();
		int status=ss.createQuery("update User set UserPhone=:phone where UserId=:uId").setParameter("phone", phone).setParameter("uId",uId).executeUpdate();
			ss.getTransaction().commit();
			return status;	
		}
	}

	@Override
	public int changePassword(int uId, String oldPwd, String newPwd) throws GlobalException {
		// TODO Auto-generated method stub
		  try(Session ss=hibernateUtil.getSession()){
			ss.beginTransaction();
			User u1=ss.get(User.class, uId);
			if(u1.getUserPassword().equals(oldPwd)) {
				int status =ss.createQuery("update User set UserPassword=:newPwd where uId=:uId").setParameter("newPwd", newPwd).setParameter("uId", uId).executeUpdate();
				ss.getTransaction().commit();
				return status;
			}
			else {
				throw new GlobalException("To update password you have to enter current password");
			}
			
			
			
		}
	}
	

}
