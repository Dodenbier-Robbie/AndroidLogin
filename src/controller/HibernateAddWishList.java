package controller;

import java.util.Date;

import model.WishList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateAddWishList {
	
	private static SessionFactory factory;
    private static Transaction tx;
    private static Session session;
    
    public HibernateAddWishList () {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
      }
    }
    
    public static void closeConnection() {
        factory.close();
    }
    
    public boolean addWishList(Integer userId, String itemCategory, String itemDetail, Date createDate) {
    		session = factory.openSession();
    		String results = null;
    		try {
    			tx = session.beginTransaction();
    			WishList wishlist = new WishList(userId, itemCategory, itemDetail, createDate);
    			wishlist.setUserId(userId);
    			wishlist.setItemCategory(itemCategory);
    			wishlist.setItemName(itemDetail);
    			wishlist.setCreateDate(createDate);
    			session.save(wishlist);
    			session.flush();
    			tx.commit();
    			results = "true";
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