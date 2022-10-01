package com.hostelMS.cfg;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.hostelMS.model.Room;
import com.hostelMS.model.User;

public class hibernateUtil {
	
private static SessionFactory sesfac;
	
	public static SessionFactory getSessionFactory() {
		
		if(sesfac==null) {
			
		try {
				Configuration config=new Configuration();
			Properties pr=new Properties();
			
			pr.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
			pr.put(Environment.URL, "jdbc:mysql://localhost:3306/hostelMS");
			pr.put(Environment.USER,"root" );
			pr.put(Environment.PASS, "Divya123");
			pr.put(Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");
			pr.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
			pr.put(Environment.HBM2DDL_AUTO,"update");
			//pr.put(Environment.HBM2DDL_AUTO,"create");
			pr.put(Environment.SHOW_SQL,"false");
			
			config.setProperties(pr);
			config.addAnnotatedClass(User.class);
			config.addAnnotatedClass(Room.class);
			
			sesfac=config.buildSessionFactory();
			
		    }
			catch(Exception e)
		     {
			        e.printStackTrace();
			        
		     }
	     }
		return sesfac;
     }
	     
	public static Session getSession(){
		      return getSessionFactory().openSession();
	     }

 }
