package ge.gngapps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import ge.gngapps.dto.Album;
import ge.gngapps.dto.Track;

public class DBUtilityForAlbumTrackDataProcessing {
	
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static final String INSERT_ALBUM_DATA_STATEMANT = "INSERT INTO album ( title, artist, lable, releaseDate, userId ) "
			+ "values ( ?, ?, ?, ?, ?)";
	private static final String DELETE_ALBUM_DATA_STATEMENT = "DELETE FROM ALBUM WHERE id = ?";
	private static final String INSERT_TRACK_DATA_STATEMANT = "INSERT INTO track ( trackN, title, duration, albumId ) "
			+ "values ( ?, ?, ?, ?)";
	private static final String DELETE_TRACK_DATA_STATEMENT = "DELETE FROM TRACK WHERE albumId = ?";
	private static final String GET_TRACK_QUERY = "SELECT trackN, title, duration FROM track WHERE albumId = ?";
	
	
	public static int inserterAlbumDataAndGetId(Connection dbConnection, Album album) {
		try {
			ps = dbConnection.prepareStatement(INSERT_ALBUM_DATA_STATEMANT);
			ps.setString(1, album.getTitle());
			ps.setString(2, album.getArtist());
			ps.setString(3, album.getLable());
			ps.setString(4, album.getDate());
			ps.setInt(5, album.getAlbumOwnerId());
			int rowCount = ps.executeUpdate();
			if ( rowCount > 0 ) {
				rs = ps.getGeneratedKeys();
				if ( rs.next() ) {
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				 rs.close();
				 ps.close();
				 dbConnection.close();
			} catch ( SQLException sql) {
				sql.printStackTrace();
			}
		}
		return -1;
	}
	public static void deleteAlbumData(Connection dbConnection, int albumId) {
		try {
			ps = dbConnection.prepareStatement(DELETE_ALBUM_DATA_STATEMENT);
			ps.setInt(1, albumId);
			ps.executeUpdate();
		} catch ( SQLException sql) {
			sql.printStackTrace();
		} finally {
			try {
				ps.close();
				dbConnection.close();
			} catch (SQLException sql) {
				sql.printStackTrace();
			}
		}
	}
	
	public static void insertTrackData(Connection dbConnection, Map<String, String[]> requestParams, int albumId) {
		try {
			ps = dbConnection.prepareStatement(INSERT_TRACK_DATA_STATEMANT);
			int trackAmount = requestParams.size() / 3;
			for ( int i = 1; i <= trackAmount; i++ ) {
				String key = "track" + i + "info" + 1;
				ps.setInt(1, Integer.parseInt(requestParams.get(key)[0]));
				key = "track" + i + "info" + 2;
				ps.setString(2, requestParams.get(key)[0]);
				key = "track" + i + "info" + 3;
				ps.setString(3, requestParams.get(key)[0]);
				ps.setInt(4, albumId);
				ps.executeUpdate();
			}
		} catch ( SQLException sqle ) {
			sqle.printStackTrace();
		}
		finally {
			try {
				ps.close();
				dbConnection.close();
			} catch ( SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}
	public static List<Track> getTrack(Connection dbConnection, int albumId) {
		try {
			ps = dbConnection.prepareStatement(GET_TRACK_QUERY);
			ps.setInt(1, albumId);
			rs = ps.executeQuery();
			List<Track> trackContainer = new ArrayList<>();
			while ( rs.next() ) {
				Track track = new Track();
				track.setAlbumId(albumId);
				track.setPositInAlbum(rs.getInt(1));
				track.setTitle(rs.getString(2));
				track.setDuration(rs.getString(3));
				trackContainer.add(track);
			}
			
		 return trackContainer;
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
		finally {
			try {
				rs.close();
				ps.close();
				dbConnection.close();
			} catch ( SQLException sql ) {
				sql.printStackTrace();
			}
		}
		return Collections.emptyList();
	}
	
	public static void deleteTrackData(Connection dbConnection, int albumId) {
		try {
			ps = dbConnection.prepareStatement(DELETE_TRACK_DATA_STATEMENT);
			ps.setInt(1, albumId);
			ps.executeUpdate();
		} catch ( SQLException sql) {
			sql.printStackTrace();
		} finally {
			try {
				ps.close();
				dbConnection.close();
			} catch (SQLException sql) {
				sql.printStackTrace();
			}
		}
	}
	
}
