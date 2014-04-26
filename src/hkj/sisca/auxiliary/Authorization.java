package hkj.sisca.auxiliary;

public class Authorization {
	
	private int authorizationID;
	private String authorizationName;
	private boolean hasUnconditionalEntry;
	
	public Authorization(int id, String name, boolean unconditionalEntry) {
		this.authorizationID = id;
		this.authorizationName = name;
		this.hasUnconditionalEntry = unconditionalEntry;
		
	}
	
	public static Authorization getInstanceFromString(String auth) {
		auth = auth.substring(1, auth.length()-2);
		String[] authData = auth.split(",");
		return new Authorization(Integer.parseInt(authData[0]), authData[1], Boolean.getBoolean(authData[2]));
	}
	
	public int getAuthorizationID() {
		return this.authorizationID;
	}
	
	public String getAuthorizationName() {
		return this.authorizationName;
	}
	
	public boolean hasUnconditionalEntry() {
		return this.hasUnconditionalEntry;
	}
	
	@Override
	public String toString() {
	
		String authorizationString;
		authorizationString= "["+getAuthorizationID()+", "+getAuthorizationName()+", "+hasUnconditionalEntry()+"]";
		return authorizationString;
	}
	
	@Override
	public boolean equals(Object anotherObject) {
		return this.authorizationID == ((Authorization) anotherObject).getAuthorizationID();
	}
}
