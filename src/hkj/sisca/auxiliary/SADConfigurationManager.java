package hkj.sisca.auxiliary;

import hkj.sisca.auxiliary.Authorization;


/** SAD Configuration Manager Class
 * @author Juan Pablo Berm�dez Reyes
 * Date: March 11, 2014
 * Last Modified: March 11, 2014
 *****************************************/

public class SADConfigurationManager {

	// SADConfigurationManager Enum
	public enum Direction{
		entry,exit
	}

	// SADConfigurationManager Fields
	String sadConfigurationID;
	Integer parkingCapacity;
	Authorization[] authorizationType;
	String[] accessControlTime;
	Direction direction;

	// SADConfigurationManager Methods
	public SADConfigurationManager(String sID, int pCapacity, Authorization[] aType, String[]aControlTime, Direction direction){
		this.sadConfigurationID=sID;
		this.parkingCapacity=pCapacity;
		this.authorizationType=aType;
		this.accessControlTime=aControlTime;
		this.direction=direction;

	}
	public String getSadConfigurationID() {
		return sadConfigurationID;
	}
	public void setSadConfigurationID(String sadConfigurationID) {
		this.sadConfigurationID = sadConfigurationID;
	}
	public Integer getParkingCapacity() {
		return parkingCapacity;
	}
	public void setParkingCapacity(Integer parkingCapacity) {
		this.parkingCapacity = parkingCapacity;
	}
	public Authorization[] getAuthorizationType() {
		return authorizationType;
	}
	public void setAuthorizationType(Authorization[] authorizationType) {
		this.authorizationType = authorizationType;
	}
	public String[] getAccessControlTime() {
		return accessControlTime;
	}
	public void setAccessControlTime(String[] accessControlTime) {
		this.accessControlTime = accessControlTime;
	}

	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}


}
