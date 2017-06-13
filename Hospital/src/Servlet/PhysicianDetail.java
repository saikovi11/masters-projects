package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.PhysicianViewDetailDao;

/**
 * Servlet implementation class PhysicianDetail
 */
public class PhysicianDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@SuppressWarnings("unused")
	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Long phyid;

		PhysicianViewDetailDao dao = new PhysicianViewDetailDao();

		RequestDispatcher rd = request.getRequestDispatcher("views/physicianViewDetails.jsp");

		try {
			

			if (request.getParameter("term") != null) {
				request.setAttribute("physicians", dao.getPhysicians(request.getParameter("term")));
				phyid = dao.getPhysicians(request.getParameter("term")).getId();
				request.setAttribute("hospital", dao.getHospitalBridge(phyid));
				request.setAttribute("special", dao.getSpecialtyBridge(phyid));
				request.setAttribute("diagCount", dao.getCountDiagnoses(phyid));
				request.setAttribute("proCount", dao.getCountProcedures(phyid));

				if (phyid != null) {
					rd.forward(request, response);

				} else {
					request.setAttribute("flag", true);
				}
			}

		} catch (Exception ex) {
			throw ex;
		}

	}

}
