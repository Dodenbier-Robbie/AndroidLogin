package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Users;
import model.Login;
import model.UserID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public class HibernateAddUser {
	
	private static SessionFactory factory;
    private static Transaction tx;
    private static Session session;
    
    public HibernateAddUser () {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
      }
    }
    
    public static void closeConnection() {
        factory.close();
    }
    
    public void addUser(String firstName, String lastName, String email, int age, Date createDate) {
        session = factory.openSession();
        try {
            tx = session.beginTransaction();
            Users user = new Users(firstName, lastName, email, age, createDate);
            user.setFirstname(firstName);
            user.setLastname(lastName);
            user.setEmail(email);
            user.setAge(age);
            user.setCreateDate(createDate);   
            session.save(user);
            session.flush();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    
    public int getUserId(String email) {
    		session = factory.openSession();
    		String sql = "from Users where email = :email";
    		List results = new ArrayList<>();
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("email", email);
			results = query.list();
			Users tempID = (Users) results.get(0);
			UserID.user_id = tempID.getUserId();
			session.flush();
			tx.commit();
		} catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
	    } finally {
	            session.close();
	    }
		
		return UserID.user_id ;
    }
    
    public boolean addLogin(Integer userId, String userName, String password, Date createDate, String salt) {
    		session = factory.openSession();
    		String results = null;
    		try {
    			tx = session.beginTransaction();
    			Login login = new Login(userId, userName, password, createDate, salt);
    			login.setUserId(userId);
    			login.setUsername(userName);
    			login.setPassword(password);
    			login.setCreateDate(createDate);
    			login.setSalt(salt);
    			results = "true";
    			session.save(login);
    			session.flush();
    			tx.commit();
    		} catch (HibernateException e) {
    			if (tx!=null) tx.rollback();
    			e.printStackTrace();
    			results = "false";
    		} finally {
    			session.close();
    		}
    		
    		if(results == "true") {
    			return true;
    		} else {
    			return false;
    		}

    }
}