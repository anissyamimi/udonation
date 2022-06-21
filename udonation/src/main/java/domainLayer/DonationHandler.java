package domainLayer;

import jakarta.servlet.RequestDispatcher;     
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import dataLayer.DonationDao;


/**
 * Servlet implementation class DonationHandler
 */
@WebServlet("/")
public class DonationHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DonationDao donationDao;
  
	public void init() throws ServletException{
		donationDao = new DonationDao();
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		switch(action)
		{
		case "/new":
			showNewForm(request, response);
			break;
			
		case "/insert":
			try {
				insertDonation(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/select":
			try {
				showUpdateDonation(request,response);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        break;
			
		case "/update":
			try {
				updateDonation(request,response);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
			
		case "/delete":
			try {
				deleteDonation(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		default:
			try {
				listDonation(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("donationForm.jsp");
		dispatcher.forward(request, response);
	}
	//Insert Donation
	private void insertDonation(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
	{	
		String donationName = request.getParameter("donationName");
		String donationDescription = request.getParameter("donationDescription");
		String donationOpenDate = request.getParameter("donationOpenDate");
		String donationCloseDate = request.getParameter("donationCloseDate");
		

		Donation newDonation = new Donation(donationName,donationDescription,Date.valueOf(donationOpenDate),Date.valueOf(donationCloseDate));
		
		donationDao.insertDonation(newDonation);
		response.sendRedirect("donationList.jsp");
	}
	
	//select Donation
	private void showUpdateDonation(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
	{
		int id = Integer.parseInt(request.getParameter("DonationID"));
		
		Donation existingDonation;
		try {
			existingDonation = donationDao.selectDonation(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("donationDetails.jsp");
			request.setAttribute("donation", existingDonation);
			dispatcher.forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}	
	//update donation
	private void updateDonation(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
	{
		int donationID = Integer.parseInt(request.getParameter("donationID"));
		String donationName = request.getParameter("donationName");
		String donationDescription = request.getParameter("donationDescription");
		String donationOpenDate = request.getParameter("donationOpenDate");
		String donationCloseDate = request.getParameter("donationCloseDate");
		
		Donation donation = new Donation(donationID,donationName,donationDescription,Date.valueOf(donationOpenDate),Date.valueOf(donationCloseDate));
		donationDao.updateDonation(donation);
		response.sendRedirect("donationList.jsp");
	}
	
	
	//delete Donation
	private void deleteDonation(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
	{
		int id = Integer.parseInt(request.getParameter("donationID"));
		try {
			donationDao.deleteDonation(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("donationList.jsp");
	}
	
	//default session
	private void listDonation(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
	{
		try {
			List<Donation> listDonation = donationDao.selectAllDonation();
			request.setAttribute("listDonation", listDonation);
			RequestDispatcher dispatcher = request.getRequestDispatcher("donationList.jsp");
			dispatcher.forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}

