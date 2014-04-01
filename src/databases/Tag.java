package databases;

import java.util.Date;

public class Tag {

	public enum GivenAccessType {
		AsNormal, AsNewlyWritten, AsReWritten;
	}

	private int tagID;
	private Authorization authorizationType;
	private Date expirationDate;
	private boolean isTagActive;
	private GivenAccessType tagAccessType;

	public Tag(int tagID, Authorization type, Date expDate) {
		this.tagID = tagID;
		this.authorizationType = type;
		this.expirationDate = expDate;
		this.isTagActive = true;
		this.tagAccessType = GivenAccessType.AsNormal;
	}

	public Tag(int tagID, Authorization type, Date expDate, GivenAccessType accessType) {
		this.tagID = tagID;
		this.authorizationType = type;
		this.expirationDate = expDate;
		this.isTagActive = true;
		this.tagAccessType = accessType;
	}

	public Authorization getAuthorizationType() {
		return authorizationType;
	}

	public void setAuthorizationType(Authorization authorizationType) {
		this.authorizationType = authorizationType;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public boolean isTagActive() {
		return isTagActive;
	}

	public void setTagActive(boolean isTagActive) {
		this.isTagActive = isTagActive;
	}

	public GivenAccessType getTagAccessType() {
		return tagAccessType;
	}

	public void setTagAccessType(GivenAccessType tagAccessType) {
		this.tagAccessType = tagAccessType;
	}

	public int getTagID() {
		return tagID;
	}

}
