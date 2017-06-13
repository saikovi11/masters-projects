package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.PatientViewDetailDao;
import DAO.PhysicianViewDetailDao;

/**
 * Servlet implementation class PatientDetail
 */
public class PatientDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
	
	@SuppressWarnings("unused")
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Long patid;

		PatientViewDetailDao dao = new PatientViewDetailDao();

		RequestDispatcher rd = request.getRequestDispatcher("views/patientViewDetails.jsp");

		try {
			

			if (request.getParameter("term") != null) {
				
				patid = Long.parseLong(request.getParameter("term"));
				request.setAttribute("discharges", dao.getDischarges(patid));
				patid = dao.getDischarges(patid).getId();
				
				if(patid != null && patid >=0){
				request.setAttribute("diagnosesDetail", dao.getDiagnosisDetail(patid));
				request.setAttribute("medicalItem", dao.getMedicalItems(patid));
				request.setAttribute("proDetail", dao.getProcedureDetail(patid));

					rd.forward(request, response);

				}else {
					request.setAttribute("flag", true);
				}
			}

		} catch (Exception ex) {

			throw ex;
			
			
		}

		
	}

}
