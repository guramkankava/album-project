package ge.gngapps.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ge.gngapps.dao.ConnectorToDB;
import ge.gngapps.dao.DBUtilityForRegAndLog;
import ge.gngapps.service.RegistrationService;


/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/register")
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Map<String,String> userData = new HashMap<>(); 
		userData.put("email", req.getParameter("email"));
		userData.put("password", req.getParameter("password"));
		userData.put("firstname", req.getParameter("firstname"));
		userData.put("lastname", req.getParameter("lastname"));
		
		boolean dataStatus = RegistrationService.checkProvidedData(userData);
		
		Connection dbConnection;
		boolean registrationStatus = false;
		
		if ( !dataStatus ) {
			// stay on index.jsp
			return;
		}
		
		if ( dataStatus ) {
			dbConnection = ConnectorToDB.getConnection();
			registrationStatus = RegistrationService.registerUser(dbConnection, userData);
		}
		
		if ( registrationStatus ) {
			// send to home page
		}
		
	}
	
	
}
