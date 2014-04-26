package hkj.sisca.auxiliary;

import java.io.UnsupportedEncodingException;
import java.sql.Date;

import com.thingmagic.TagReadData;

import hkj.sisca.auxiliary.Authorization;

public class Tag {

	public enum GivenAccessType {
		AsNormal, AsNewlyWritten, AsReWritten;
	}

	private String tagID;
	private Authorization authorizationType;
	private Date expirationDate;
	private boolean isTagActive;
	private GivenAccessType tagAccessType;

	public Tag(String tagID, Authorization type, Date expDate) {
		this.tagID = tagID;
		this.authorizationType = type;
		this.expirationDate = expDate;
		this.isTagActive = true;
		this.tagAccessType = GivenAccessType.AsNormal;
	}

	public Tag(String tagID, Authorization type, Date expDate, GivenAccessType accessType) {
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

	public String getTagID() {
		return tagID;
	}

	public static Tag getInstanceFromTagData(TagReadData data) {
		String tagDataNP = data.epcString();
		try {
			if (tagDataNP.getBytes("ISO-8859-1").length > 24) {
				tagDataNP = new String(data.getTag().epcBytes());
				String[] tagData = tagDataNP.split(",");
				Date tagDate = Date.valueOf(tagData[2]);
				Authorization tagAuth = new Authorization(Integer.parseInt(tagData[1]), "", false);
				return new Tag(tagData[0], tagAuth, tagDate);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new Tag(tagDataNP, null, null);
	}

	@Override
	public String toString() {
		return "[ID: " + this.tagID + ", " + this.authorizationType + ", " + this.expirationDate + "]";
	} 
	
	@Override
	public boolean equals(Object obj) {
		return this.tagID.equals(((Tag) obj).tagID);
	}
	
	
}
