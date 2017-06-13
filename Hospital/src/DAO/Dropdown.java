package DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import Model.Diagnoses;
import Model.Hospitals;
import Model.MedicalItems;
import Model.Physicians;
import Model.Procedures;
import Model.Specialties;
import Util.HibernateUtil;

public class Dropdown {

	@SuppressWarnings("unchecked")
	public List<Diagnoses> getDiagnoses() {
		Session session = null;
		List<Diagnoses> fetchList = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Diagnoses.class);
			fetchList = (List<Diagnoses>) criteria.list();
		} catch (HibernateException ex) {
			throw ex;
		} finally {
			session.close();
		}
		return fetchList;
	}

	@SuppressWarnings("unchecked")
	public List<Hospitals> getHospitals() {
		Session session = null;
		List<Hospitals> fetchList = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Hospitals.class);
			fetchList = (List<Hospitals>) criteria.list();
		} catch (HibernateException ex) {
			throw ex;
		} finally {
			session.close();
		}
		return fetchList;
	}

	@SuppressWarnings("unchecked")
	public List<MedicalItems> getMedicalItems() {
		Session session = null;
		List<MedicalItems> fetchList = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(MedicalItems.class);
			fetchList = (List<MedicalItems>) criteria.list();
		} catch (HibernateException ex) {
			throw ex;
		} finally {
			session.close();
		}
		return fetchList;
	}

	@SuppressWarnings("unchecked")
	public List<Procedures> getProcedures() {
		Session session = null;
		List<Procedures> fetchList = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Procedures.class);
			fetchList = (List<Procedures>) criteria.list();
		} catch (HibernateException ex) {
			throw ex;
		} finally {
			session.close();
		}
		return fetchList;
	}

	@SuppressWarnings("unchecked")
	public List<Specialties> getSpecialties() {
		Session session = null;
		List<Specialties> fetchList = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Specialties.class);
			fetchList = (List<Specialties>) criteria.list();
		} catch (HibernateException ex) {
			throw ex;
		} finally {
			session.close();
		}
		return fetchList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Physicians> getPhysicians() {
		Session session = null;
		List<Physicians> fetchList = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Physicians.class);
			fetchList = (List<Physicians>) criteria.list();
		} catch (HibernateException ex) {
			throw ex;
		} finally {
			session.close();
		}
		return fetchList;
	}

}
