package ge.gngapps.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ge.gngapps.dao.ConnectorToDB;
import ge.gngapps.dto.Track;
import ge.gngapps.service.AlbumTrackService;

/**
 * Servlet implementation class GetTrackServlet
 */
@WebServlet("/GetTrack")
public class GetTrackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int albumId = Integer.parseInt(request.getParameter("albumId"));
		List<Track> trackContainer = AlbumTrackService.getTrack(ConnectorToDB.getConnection(), albumId);
		String json = new Gson().toJson(trackContainer);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

}
