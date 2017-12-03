package controller;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.Login;
import model.UserID;

@SuppressWarnings("deprecation")
public class HibernateLogin {
	
	private static SessionFactory factory;
    private static Transaction tx;
    private static Session session;
    
    public HibernateLogin () {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
      }
    }
    
    public static void closeConnection() {
        factory.close();
    }
    
    public boolean validateLogin(String username, String password) {
    		session = factory.openSession();
    		String sql = "from Login where username = :username and password = :password";
    		List results = new ArrayList<>();
    		int qualifier = 0;
    		try {
    			tx = session.beginTransaction();
    			Query query = session.createQuery(sql);
    			query.setParameter("username", username);
    			query.setParameter("password", password);
    			results = query.list();
    			Login tempID = (Login) results.get(0);
    			UserID.user_id = tempID.getUserId();
    			qualifier = results.size();
    			session.flush();
    			tx.commit();
    		} catch (HibernateException e) {
                if (tx!=null) tx.rollback();
                e.printStackTrace();
        } finally {
                session.close();
        }
    		if (qualifier > 0) {
    			return true;
    		} else {
    			return false;
    		}
			
    }

}