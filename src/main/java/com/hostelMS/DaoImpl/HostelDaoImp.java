package com.hostelMS.DaoImpl;

import org.hibernate.Session;

import com.hostelMS.Dao.HostelMSDao;
import com.hostelMS.Exception.GlobalException;
import com.hostelMS.cfg.hibernateUtil;
import com.hostelMS.model.User;

public class HostelDaoImp implements HostelMSDao {
	
	@Override
	public int registration(User u1) throws GlobalException {
		
		try(Session ss=hibernateUtil.getSession())
		{
			
			String username=u1.getUserName();
			User u2=null;
			u2=(User)ss.createQuery("from User where UserName=:Username").setParameter("Username", username).uniqueResult();
			if(u2==null)
			{
				ss.beginTransaction();
				ss.save(u1);
				ss.getTransaction().commit();
				return 1;	
			}
			else {
				throw new GlobalException("User already existed");
			}
			
		}
		
		
	}

	@Override
	public User login(String username, String password) throws GlobalException {
		
		try(Session ss=hibernateUtil.getSession()){
			ss.beginTransaction();
			
			User u2=null;
			u2=(User)ss.createQuery("from User where UserName=:Username").setParameter("Username", username).uniqueResult();
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
