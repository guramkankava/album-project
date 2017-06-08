package ge.gngapps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ge.gngapps.dto.Album;
import ge.gngapps.dto.Track;
import ge.gngapps.dto.User;

public class DBUtilityForRegAndLog {
	
	private static PreparedStatement ps;
	private static ResultSet rs;
	
	private static final String INSERT_USER_STATEMANT = "INSERT INTO user (email, password, firstname, lastname) VALUES (?, ?, ?, ?)";
	private static final String CHECK_IF_USER_HAS_REGISTERD_QUER = "SELECT COUNT(*) FROM user where email = ? and password = ?";
	private static final String GET_USER_DATA_QUERY = "SELECT id, email, firstname, lastname from user WHERE email = ?";
	private static final String GET_ALBUMS_QUERY ="SELECT id, title, artist, lable, releaseDate FROM album where userId = ?";
	
	public static boolean insertRegistrationDataToDB ( Connection connection, Map<String, String> userData ) {
		try {
			ps = connection.prepareStatement(INSERT_USER_STATEMANT);
			ps.setString(1, userData.get("email"));
			ps.setString(2, userData.get("password"));
			ps.setString(3, userData.get("firstname"));
			ps.setString(4, userData.get("lastname"));
			int insertStatus = ps.executeUpdate();
			if ( insertStatus > 0 ) { 
				return true;
			}
		} catch ( SQLException e ) {	
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static boolean findUserByProvidedEmailAndPassword ( Connection dbConnection, String providedEmail, String providedPassword ) {
		try {
			ps = dbConnection.prepareStatement(CHECK_IF_USER_HAS_REGISTERD_QUER);
			ps.setString(1, providedEmail);
			ps.setString(2, providedPassword);
			rs = ps.executeQuery();
			rs.next();
			if ( rs.getInt(1) == 1 ) {
				return true;
			} 
		} catch (SQLException sql ) {
			sql.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static User getUser( Connection dbConnection, String email ) {
		try {
			ps = dbConnection.prepareStatement(GET_USER_DATA_QUERY);
			ps.setString(1, email);
			rs = ps.executeQuery();
			rs.next();
			User user = new User();
			user.setId(rs.getInt(1));
			user.setEmail(rs.getString(2));
			user.setfName(rs.getString(3));
			user.setlName(rs.getString(4));
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static List<Album> getAlbum(Connection dbConnection, int userId) {
		try {
			ps = dbConnection.prepareStatement(GET_ALBUMS_QUERY);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			List<Album> albumContainer = new ArrayList<>();
			while ( rs.next() ) {
				Album album = new Album();
				album.setId(rs.getInt(1));
				album.setTitle(rs.getString(2));
				album.setArtist(rs.getString(3));
				album.setLable(rs.getString(4));
				album.setDate(rs.getString(5));
				album.setAlbumOwnerId(userId);
				albumContainer.add(album);
			}
			return albumContainer;
		} catch (SQLException sql) {
			sql.printStackTrace(); 
		} 
		finally {
			try {
				rs.close();
				ps.close();
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Collections.emptyList();
	}

}
