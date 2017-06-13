package DAO;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import Model.Charges;
import Model.Discharges;
import Util.HibernateUtil;

public class PatientViewDetailDao {
	
	public Discharges getDischarges(Long id) {
		Session session = null;
		Discharges fetchList = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Discharges.class).add(Restrictions.eq("encounterNumber", id));
			fetchList = (Discharges) criteria.list().get(0);
		} catch (HibernateException ex) {
			throw ex;
		} finally {
			session.close();
		}
		return fetchList;
	}

	

	public String getDiagnosisDetail(Long id) {
		Session session = null;
		String fetchList = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			String hql = "select a.diagnosesDescription from Diagnoses a , DiagnosesBridge b where b.discharge = :id and b.diagnoses = a.id ";
			Query query = session.createQuery(hql).setLong("id", id);
			fetchList = (String) query.uniqueResult();
		} catch (HibernateException ex) {
			throw ex;
		} finally {
			session.close();
		}
		return fetchList;
	}

	public String getProcedureDetail(Long id) {
		Session session = null;
		String fetchList = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			String hql = "select a.proceduresDescription from Procedures a , ProceduresBridge b where b.discharge = :id and b.procedure = a.id ";
			Query query = session.createQuery(hql).setLong("id", id);
			fetchList = (String) query.uniqueResult();

		} catch (HibernateException ex) {
			throw ex;
		} finally {
			session.close();
		}
		return fetchList;
	}
	
	public Charges getMedicalItems(Long id) {
		Session session = null;
		Charges fetchList = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Charges.class).add(Restrictions.eq("discharge", id));
			fetchList = (Charges) criteria.list().get(0);


		} catch (HibernateException ex) {
			throw ex;
		} finally {
			session.close();
		}
		return fetchList;
	}
	
	

}
