package password;

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

public class GetSaltValue {
	
	private static SessionFactory factory;
    private static Transaction tx;
    private static Session session;
    
    public GetSaltValue() {
	    	try {
	            factory = new Configuration().configure().buildSessionFactory();
	        } catch (Throwable ex) { 
	            System.err.println("Failed to create sessionFactory object." + ex);
	      }
    }
    
    public static void closeConnection() {
        factory.close();
    }
    
    public String getSaltDB(String username) {
    		session = factory.openSession();
		String sql = "from Login where username = :username";
		List results = new ArrayList<>();
		int qualifier = 0;
		String salt = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("username", username);
			results = query.list();
			qualifier = results.size();
			if(qualifier > 0) {
				Login login = (Login) results.get(0);
    				salt = login.getSalt();
    				session.flush();
    				tx.commit();
			} else {
				salt = "fail";
				session.flush();
    				tx.commit();
			}
			} catch (HibernateException e) {
                if (tx!=null) tx.rollback();
                e.printStackTrace();
        } finally {
                session.close();
        }
        
        return salt;
    }
}
