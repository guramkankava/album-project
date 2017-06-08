package ge.gngapps.service;

import java.sql.Connection;
import java.util.Map;

import ge.gngapps.dao.DBUtilityForRegAndLog;

public class RegistrationService {

	public static boolean checkProvidedData(Map<String, String> userData) {
		for ( Map.Entry<String, String> entry : userData.entrySet() ) {
			if ( entry.getValue().trim().length() == 0 ) {
				return false;
			}
		} 
		return true;
	}
	public static boolean registerUser (Connection dbConnection, Map<String, String> userData) {
		boolean registraionStatus = DBUtilityForRegAndLog.insertRegistrationDataToDB(dbConnection, userData);
		return registraionStatus;
	}

}
