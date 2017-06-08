package ge.gngapps.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import ge.gngapps.dao.DBUtilityForRegAndLog;
import ge.gngapps.dto.Album;
import ge.gngapps.dto.Track;
import ge.gngapps.dto.User;

public class LoginService {
	
	
	public static boolean findUserByProvidedEmailAndPassword( Connection dbConnection, String providedEmail, String providedPassword ) {
		if( providedEmail.trim().isEmpty() || providedPassword.trim().isEmpty() ) {
			return false;
		} else {
			 return DBUtilityForRegAndLog.findUserByProvidedEmailAndPassword( dbConnection, providedEmail, providedPassword );
		}
	}
	
	public static User getUser(Connection dbConnection, String email) {
		return DBUtilityForRegAndLog.getUser(dbConnection, email);
	}
	
	public static List<Album> getAlbum (Connection dbConnection, int userId) {
		return DBUtilityForRegAndLog.getAlbum(dbConnection, userId);
	}
	
	public static void logUserIn ( HttpSession session, User user ) {
			session.setAttribute("user", user);
	}
}
