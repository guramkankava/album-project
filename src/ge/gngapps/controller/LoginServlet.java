package ge.gngapps.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ge.gngapps.dao.ConnectorToDB;
import ge.gngapps.dto.Album;
import ge.gngapps.dto.Track;
import ge.gngapps.dto.User;
import ge.gngapps.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ( request.getSession().getAttribute("user") == null || request.getSession().getAttribute("album") == null ) {
			response.sendRedirect("index.jsp");
			return;
		}
			RequestDispatcher rd = request.getRequestDispatcher("/home");
			rd.forward(request, response);
	}
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession httpSession = request.getSession();
		if ( httpSession.getAttribute("user") != null ) {
			RequestDispatcher rd = request.getRequestDispatcher("/home");
			rd.forward(request, response);
			return;
		}
		String providedEmail = request.getParameter("email");
		String prividedPassword = request.getParameter("password");
		
		boolean searchStatus = LoginService.findUserByProvidedEmailAndPassword( ConnectorToDB.getConnection(), providedEmail, prividedPassword );
		
		if ( searchStatus ) {
			String correctEmail = providedEmail;
			User user = LoginService.getUser(ConnectorToDB.getConnection(), correctEmail);
			List<Album> albumContainer = LoginService.getAlbum(ConnectorToDB.getConnection(), user.getId());
			LoginService.logUserIn(httpSession, user);
			httpSession.setAttribute("album", albumContainer);
			RequestDispatcher rd = request.getRequestDispatcher("/home");
			rd.forward(request, response);
		} else {
			response.sendRedirect("index.jsp");
		}
	}
}
