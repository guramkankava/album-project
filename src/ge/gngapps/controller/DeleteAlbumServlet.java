package ge.gngapps.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import ge.gngapps.dao.ConnectorToDB;
import ge.gngapps.dao.DBUtilityForAlbumTrackDataProcessing;
import ge.gngapps.dto.Album;
import ge.gngapps.dto.User;
import ge.gngapps.service.AlbumTrackService;
import ge.gngapps.service.LoginService;

/**
 * Servlet implementation class DeleteAlbumServlet
 */
@WebServlet("/deleteAlbum")
public class DeleteAlbumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		AlbumTrackService.processAlbumTrackDataDeletion(ConnectorToDB.getConnection(), 
				Integer.parseInt(request.getParameter("albumId")));
		
		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("user");
		
		List<Album> albumContainer = LoginService.getAlbum( ConnectorToDB.getConnection(), user.getId() );
		
		httpSession.setAttribute("album", albumContainer);
		
		String json = new Gson().toJson(albumContainer);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
												 	
	}

}
