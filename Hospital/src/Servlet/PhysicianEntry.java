package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.PhysicianEntryDaoImpl;
import Model.HospitalBridge;
import Model.Physicians;
import Model.SpecialtyBridge;

/**
 * Servlet implementation class PhysicianEntry
 */
public class PhysicianEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
		Physicians physicians = new Physicians();
		HospitalBridge hospB= new HospitalBridge();
		SpecialtyBridge spec = new SpecialtyBridge();
		PhysicianEntryDaoImpl phyDao=new PhysicianEntryDaoImpl();
		String status="error";
		try{
			physicians.setPhysicianId(request.getParameter("physicianID"));
			physicians.setNationalProviderId(request.getParameter("nationalProviderID"));
			physicians.setPhysicianName(request.getParameter("physician"));
			physicians.setEmail(request.getParameter("email"));
			physicians.setGender(request.getParameter("gender"));
			physicians.setPrimaryState(request.getParameter("state"));
			physicians.setPrimaryCountry(request.getParameter("country"));
			physicians.setPrimaryCity(request.getParameter("city"));
			hospB.setHospital(Long.parseLong(request.getParameter("hospital")));
			spec.setIsPrimarySpecialty(request.getParameter("isPrimary").charAt(0));
			spec.setSpecialty(Long.parseLong(request.getParameter("specialtyCode")));
			
			status=phyDao.enterForm(physicians, hospB, spec);
			
			PrintWriter out= response.getWriter();
			out.print(status);
			
			if(status.equalsIgnoreCase("success")){
				response.sendRedirect("views/success.jsp");
			}
			
			
		}
		catch(Exception ex){
			throw ex;
			
		}
	
		
		
		
	}

}
