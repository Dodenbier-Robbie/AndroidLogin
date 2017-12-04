package controller;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.WishList;


@SuppressWarnings("deprecation")
public class HibernateGetWishList {
	
	private static SessionFactory factory;
    private static Transaction tx;
    private static Session session;
    
    public HibernateGetWishList () {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
      }
    }
    
    public static void closeConnection() {
        factory.close();
    }
    
    public List getWishList(Integer userId) {
    		session = factory.openSession();
    		String sql = "from WishList where userId = :userId";
    		//List results = new ArrayList<>();
    		List<WishList> results = null;
    		
  		
    		try {
    			tx = session.beginTransaction();
    			Query query = session.createQuery(sql);
    			query.setParameter("userId", userId);
    			results = query.list();
    			
    			for(WishList emp : results) {
    				emp.getItemCategory();
    				emp.getItemName();
    			}
    			session.flush();
    			tx.commit();
    		} catch (HibernateException e) {
                if (tx!=null) tx.rollback();
                e.printStackTrace();
        } finally {
                session.close();
        }
		return results;
    }

}