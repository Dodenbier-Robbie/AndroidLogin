package controller;

import com.mysql.jdbc.Connection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Users;
import model.WishList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateAddWishList {
	
	private static SessionFactory factory;
    private static Transaction tx;
    private static Session session;
    
    @SuppressWarnings("deprecation")
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
    
    public void addWishList(Integer userId, String itemCategory, String itemDetail, Date createDate) {
    		session = factory.openSession();
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
    		} catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}