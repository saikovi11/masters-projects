package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.PatientEntryDaoImpl;
import Model.Charges;
import Model.DiagnosesBridge;
import Model.Discharges;
import Model.ProceduresBridge;

/**
 * Servlet implementation class PatientEntry
 */
public class PatientEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			process(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Discharges dis = new Discharges();
		DiagnosesBridge db = new DiagnosesBridge();
	
		ProceduresBridge pb = new ProceduresBridge();
		Charges che = new Charges();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		PatientEntryDaoImpl pat = new PatientEntryDaoImpl();
		
		String status="error";
		
		try{
			dis.setEncounterNumber(Long.parseLong(request.getParameter("encounterNumber")));
			dis.setMedicalRecordNumber(Long.parseLong(request.getParameter("medicalRecordNumber")));
			
			dis.setAdmissionDate(df.parse(request.getParameter("admissionDate")));
			dis.setDischargeDate(df.parse(request.getParameter("dischargeDate")));

			dis.setGender(request.getParameter("gender"));
			dis.setPatientName(request.getParameter("patientName"));
			dis.setPatientPhoneNumber(request.getParameter("contactNumber"));
			dis.setPatientAddress(request.getParameter("patientAddress"));
			
			pb.setProcedure(Long.parseLong(request.getParameter("procedureCode")));
			pb.setProcedureDate(new Date());
			pb.setProcedurePhysician(Long.parseLong(request.getParameter("proPhysician")));
			pb.setSequence(Long.parseLong(request.getParameter("seqDiag")));
			
			db.setDiagnosedPhysician(Long.parseLong(request.getParameter("diagPhysician")));
			db.setDiagnoses(Long.parseLong(request.getParameter("diagnosisCode")));
			db.setPresentOnAdmission('Y');
			db.setSequence(Long.parseLong(request.getParameter("seqDiag")));
			
			che.setCharge(Long.parseLong(request.getParameter("totalCharge")));
			che.setMedicalItem(Long.parseLong(request.getParameter("medicalCode")));
			che.setQuantity(Long.parseLong(request.getParameter("quantity")));
			
			
			status=pat.enterForm(dis,db, pb, che);
			
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
