package ge.gngapps.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import ge.gngapps.dao.ConnectorToDB;
import ge.gngapps.dto.Album;
import ge.gngapps.dto.User;
import ge.gngapps.service.AlbumTrackService;
import ge.gngapps.service.LoginService;

/**
 * Servlet implementation class AlbumPersisterServlet
 */
@WebServlet("/persistAlbumData")
public class AlbumPersisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userId = ((User) request.getSession().getAttribute("user")).getId();
		Album album = new Album();
		album.setAlbumOwnerId(userId);
		album.setArtist(request.getParameter("artistName"));			 
		album.setTitle(request.getParameter("albumTitle"));
		album.setLable(request.getParameter("albumLabel"));
		album.setDate(request.getParameter("releaseDate"));
		
		Map<String, String[]> requestParams = new TreeMap<>(request.getParameterMap());
		requestParams.remove("artistName");
		requestParams.remove("albumTitle");
		requestParams.remove("albumLabel");
		requestParams.remove("releaseDate");
		
		AlbumTrackService.processAlbumTrackData(ConnectorToDB.getConnection(), album, requestParams);
		List<Album> albumContainer = LoginService.getAlbum(ConnectorToDB.getConnection(), userId);
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("album", albumContainer);
		
		String json = new Gson().toJson(albumContainer);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		
	}
}
