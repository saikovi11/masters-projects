package DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Charges;
import Model.DiagnosesBridge;
import Model.Discharges;
import Model.ProceduresBridge;
import Util.HibernateUtil;

public class PatientEntryDaoImpl {
	
	
public String enterForm(Discharges phy, DiagnosesBridge hospB, ProceduresBridge spec, Charges ch){
		
		Session session = null;
		String status="error";
		Transaction tx = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(phy);
			spec.setDischarge(phy.getId());
			hospB.setDischarge(phy.getId());
			ch.setDischarge(phy.getId());
			session.save(spec);
			session.save(hospB);
			session.save(ch);
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
