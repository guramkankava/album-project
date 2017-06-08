package ge.gngapps.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import ge.gngapps.dao.ConnectorToDB;
import ge.gngapps.dao.DBUtilityForAlbumTrackDataProcessing;
import ge.gngapps.dto.Album;
import ge.gngapps.dto.Track;

public class AlbumTrackService {

	public static void processAlbumTrackData(Connection dbConnection, Album album, Map<String, String[]> requestParams) {
		int albumId = DBUtilityForAlbumTrackDataProcessing.inserterAlbumDataAndGetId(dbConnection, album);
		if ( albumId != -1 ) {
			 DBUtilityForAlbumTrackDataProcessing.insertTrackData(ConnectorToDB.getConnection(), requestParams, albumId);
		}
	}
	
	public static void processAlbumTrackDataDeletion(Connection dbConnection, int albumId) {
		DBUtilityForAlbumTrackDataProcessing.deleteAlbumData(dbConnection, albumId);
		DBUtilityForAlbumTrackDataProcessing.deleteTrackData(ConnectorToDB.getConnection(), albumId);
	}
	
	
	public static List<Track> getTrack(Connection dbConnection, int albumId) {
		return DBUtilityForAlbumTrackDataProcessing.getTrack(dbConnection, albumId);
	}
	
}
