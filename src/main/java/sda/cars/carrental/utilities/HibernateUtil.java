package sda.cars.carrental.utilities;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@Slf4j
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    protected Transaction transaction;

    public static SessionFactory getSessionFactory(){
        if (sessionFactory==null){
            try{
                Configuration configuration = new Configuration();
                configuration.configure("/hibernate.cfg.xml");
                sessionFactory = configuration.buildSessionFactory();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}
