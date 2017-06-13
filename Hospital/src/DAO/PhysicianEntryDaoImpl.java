package DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.HospitalBridge;
import Model.Physicians;
import Model.SpecialtyBridge;
import Util.HibernateUtil;

public class PhysicianEntryDaoImpl {
	
	public String enterForm(Physicians phy, HospitalBridge hospB, SpecialtyBridge spec){
		
		Session session = null;
		String status="error";
		Transaction tx = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(phy);
			spec.setPhysician(phy.getId());
			hospB.setPhysician(phy.getId());
			session.save(spec);
			session.save(hospB);
			tx.commit();
			status="success";
		} catch (HibernateException ex) {
			throw ex;
		} finally {
			session.close();
		}
		return status;
		
	}

}
