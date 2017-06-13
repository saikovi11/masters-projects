package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Dropdown;

/**
 * Servlet implementation class Diagnoses
 */
public class Diagnoses extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Dropdown dropdown= new Dropdown();
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		request.setAttribute("diagnosesList", dropdown.getDiagnoses());
		request.setAttribute("hospitalList", dropdown.getHospitals());
		request.setAttribute("medicalItemList", dropdown.getMedicalItems());
		request.setAttribute("procedureList", dropdown.getProcedures());
		request.setAttribute("specialtiesList", dropdown.getSpecialties());
		request.setAttribute("physiciansList", dropdown.getPhysicians());
	
		
		
		
	}

}
