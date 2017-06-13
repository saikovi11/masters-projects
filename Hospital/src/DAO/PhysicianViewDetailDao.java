package DAO;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import Model.DiagnosesBridge;
import Model.Physicians;
import Model.ProceduresBridge;
import Util.HibernateUtil;

public class PhysicianViewDetailDao {

	public Physicians getPhysicians(String id) {
		Session session = null;
		Physicians fetchList = null;
		System.out.println("the value is "+id);
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			String hql= "from Physicians where physicianId = :id ";
			Query query = session.createQuery(hql).setString("id", id);
			fetchList = (Physicians) query.list().get(0);
			System.out.println("jkciqackjq   "+fetchList.getEmail());
		} catch (HibernateException ex) {
			throw ex;
		} finally {
			session.close();
		}
		return fetchList;
	}

	public int getCountDiagnoses(Long id) {
		Session session = null;
		int a = 0;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(DiagnosesBridge.class)
					.add(Restrictions.eq("diagnosedPhysician", id));
			a = criteria.list().size();
		} catch (HibernateException ex) {
			throw ex;
		} finally {
			session.close();
		}
		return a;
	}

	public int getCountProcedures(Long id) {
		Session session = null;
		int a = 0;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(ProceduresBridge.class)
					.add(Restrictions.eq("procedurePhysician", id));
			a = criteria.list().size();
		} catch (HibernateException ex) {
			throw ex;
		} finally {
			session.close();
		}
		return a;
	}

	public String getHospitalBridge(Long id) {
		Session session = null;
		String fetchList = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			String hql = "select a.hospitalName from Hospitals a , HospitalBridge b where b.physician = :id and b.hospital = a.id ";
			Query query = session.createQuery(hql).setLong("id", id);
			fetchList = (String) query.uniqueResult();
		} catch (HibernateException ex) {
			throw ex;
		} finally {
			session.close();
		}
		return fetchList;
	}

	public String getSpecialtyBridge(Long id) {
		Session session = null;
		String fetchList = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			String hql = "select a.specialtyName from Specialties a , SpecialtyBridge b where b.physician = :id and b.specialty = a.id ";
			Query query = session.createQuery(hql).setLong("id", id);
			fetchList = (String) query.uniqueResult();

		} catch (HibernateException ex) {
			throw ex;
		} finally {
			session.close();
		}
		return fetchList;
	}

}
