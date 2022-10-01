/*
 *  @Divya
 *  A DATA ACCESS OBJECT interface to perform  encapsulating the details of the persistence layers and CRUD interface for single entity
 */
package com.hostelMS.DaoImpl;


//importing required packages
import com.hostelMS.Dao.HostelMSDao;
import com.hostelMS.Exception.GlobalException;
import com.hostelMS.cfg.hibernateUtil;
import com.hostelMS.model.User;

import org.hibernate.Session;
//method that implements interface
public class HostelDaoImp implements HostelMSDao {
	//method for registration
	@Override
	public int registration(User u1) throws GlobalException {
		//creating a autoclosable session
		try(Session ss  = hibernateUtil.getSession())
		{
			
			String username=u1.getUserName();
			User u2= null;
			u2=(User)ss.createQuery("from User where userName=:username").setParameter("username", username).uniqueResult();
			if(u2==null)
			{
				ss.beginTransaction();
				ss.save(u1);
				ss.getTransaction().commit();
				return 1;	
			}
			else {
				throw new GlobalException("User already Exist\nTry Again with other Name");
			}
			
		}
		
		
	}
    //Method to Login
	@Override
	public User login(String userName, String password) throws GlobalException {
		//creating a utoclosable session
		try(Session ss=hibernateUtil.getSession()){
			ss.beginTransaction();
			//creating query to login
			User u2=null;
			u2=(User)ss.createQuery("from User where userName=:username").setParameter("username", userName).uniqueResult();
			if(u2!=null)
			{
			if(u2.getUserPassword().equals(password)) {
				return u2;
			}
			else {
				throw new GlobalException("Wrong Username or Password");
			}
			}
			else {
				throw new GlobalException("Wrong Username or Password");
			}
			
		}
		
	}
}
