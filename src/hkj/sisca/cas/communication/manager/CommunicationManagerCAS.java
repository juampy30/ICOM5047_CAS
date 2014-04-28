package hkj.sisca.cas.communication.manager;
import hkj.sisca.auxiliary.Authorization;
import hkj.sisca.auxiliary.BarrierGateManager.BarrierGateActionType;
import hkj.sisca.auxiliary.SADConfigurationManager;
import hkj.sisca.auxiliary.Tag;
import hkj.sisca.auxiliary.Tag.GivenAccessType;
import hkj.sisca.cas.communication.manager.CommunicationManagerConstants.ClientType;
import hkj.sisca.cas.communication.manager.CommunicationManagerConstants.RegistrationResult;
import hkj.sisca.utilities.AuthenticationManager;
import hkj.sisca.utilities.ClientSocket;

import java.util.LinkedList;
import java.util.List;

/**
 * CAS Communication Manager
 * @author hfranqui
 *
 */
public class CommunicationManagerCAS {

	/**
	 * Describes the types of updates that can be performed to a list of tags
	 * @author hfranqui
	 *
	 */
	public enum TagUpdateType {
		AddUpdate, RemoveUpdate;
	}

	/**
	 * Describes the names of the lists that contain tags
	 * @author hfranqui
	 *
	 */
	public enum TagUpdateListName {
		EntryTagsList, TagsToBeRenewedList, NewTagsList
	}

	/**
	 * Contains the information about an update to a tag list
	 * @author hfranqui
	 *
	 */
	public static class TagListUpdateContainer {
		private hkj.sisca.auxiliary.Tag receivedTag;
		private TagUpdateType tagUpdateType;
		private TagUpdateListName tagUpdateListName;

		public TagListUpdateContainer(hkj.sisca.auxiliary.Tag tag, TagUpdateType updateType, TagUpdateListName listName) {
			this.receivedTag = tag;
			this.tagUpdateType = updateType;
			this.tagUpdateListName = listName;
		}

		public Tag getReceivedTag() {
			return receivedTag;
		}

		public TagUpdateType getTagUpdateType() {
			return tagUpdateType;
		}

		public TagUpdateListName getTagUpdateListName() {
			return tagUpdateListName;
		}

	}


	private ClientSocket socket;
	private String deviceID;
	private ClientType type;
	private List<String> connectedSADs;
	private String arrivedMessage;
	public static final int MAX_CONNECTION_TIMEOUT = 100;
	public static final int MAX_CONNECTION_RETRY = 5;

	public CommunicationManagerCAS(ClientSocket socket, ClientType type, String deviceID) {
		this.socket = socket;
		this.type = type;
		this.deviceID = deviceID;
		this.connectedSADs = new LinkedList<String>();
	}

	/**
	 * Allows this software to register in the server in order to be able to communicate
	 * with the rest of the software applications that make up SisCA
	 * @return A RegistrationResult that describes the result of a registration attempt
	 */
	public RegistrationResult performASRegistration() {
		if (socket.isSocketOpen()) {
			int tries = 0;
			do {
				socket.sendData(CommunicationManagerConstants.REGISTRATION_INQUIRY_MESSAGE + ":" + this.type + ":" + this.deviceID);

				while(!socket.isDataAvailable());
				String response = socket.getReceivedData();
				if (response != null && response.contains(CommunicationManagerConstants.REGISTRATION_SUCCESSFUL_MESSAGE)) {
					System.out.println("Registration was successful.");
					return RegistrationResult.REGISTER_SUCCESSFUL;
				}
				else if (response != null && response.contains(CommunicationManagerConstants.REGISTRATION_UNSUCCESSFUL_DUPLICATE_MESSAGE)) {
					System.out.println("Registration not performed: This client already existed.");
					return RegistrationResult.REGISTER_UNSUCCESSFUL_DUPLICATE;
				}
				else if (response != null && response.contains(CommunicationManagerConstants.REGISTRATION_UNSUCCESSFUL_INVALID_MESSAGE)) {
					System.out.println("Registration unsuccessful: This client ID is invalid.");
					return RegistrationResult.REGISTER_UNSUCCESSFUL_IDENTIFIER;
				}
				else {
					try {
						tries++;
						Thread.sleep(MAX_CONNECTION_TIMEOUT);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			while (tries < MAX_CONNECTION_RETRY);
			return RegistrationResult.REGISTER_UNSUCCESSFUL_SERVER_NOT_FOUND;
		}
		return RegistrationResult.REGISTER_UNSUCCESSFUL_SERVER_NOT_FOUND;
	}

	/**
	 * Method used to know if there's any incoming communication to this CAS. The method received all and stores
	 * the message to a private variable. It then can be managed by calling the corresponding managing method.
	 * @return True if a communication has arrived, false otherwise.
	 */
	public boolean hasCommunicationArrived() {
		String communicatingSAD = this.waitForServerResponse(new String[] {CommunicationManagerConstants.MESSAGE_BEGIN_COMMUNICATION});
		String incomingSADID = communicatingSAD.split(":")[1];
		
		int tries = 0;
		do {
			if (performAuthentication(false, incomingSADID)) {
				this.connectedSADs.add(incomingSADID);
				this.arrivedMessage = this.waitForServerResponse(new String[] {CommunicationManagerConstants.MESSAGE_BEGIN_TAG_INFO_SEND});
				System.out.println(this.arrivedMessage);
				return true;
			}
			else {
				tries++;
				try {
					Thread.sleep(MAX_CONNECTION_TIMEOUT);
				}
				catch (Exception e) {
					System.out.println("Error on authentication or invalid communication received.");
					e.printStackTrace();
				}
			}
		}
		while (tries < MAX_CONNECTION_RETRY);
		
		return false;
	}

	/**
	 * Manages the receiving of tag information from a SAD.
	 * @return A Tag object that contains the information sent by the SAD.
	 */
	public Tag manageReceivedTagInfo() {
		String[] messageData = this.arrivedMessage.split(CommunicationManagerConstants.SEPARATOR);
		if (messageData[0].contains(CommunicationManagerConstants.MESSAGE_BEGIN_TAG_INFO_SEND) &&
				messageData[5].contains(CommunicationManagerConstants.MESSAGE_END_TAG_INFO_SEND)) {

			GivenAccessType type = null;
			if (messageData[4].contains("normal")) {
				type = GivenAccessType.AsNormal;
			}
			else if (messageData[4].contains("newlyWritten")) {
				type = GivenAccessType.AsNewlyWritten;
			}
			else if (messageData[4].contains("renewed")) {
				type = GivenAccessType.AsReWritten;
			}
			
			this.endCommunication(this.getLastConnectedSADID(), true, false);
			return new Tag(messageData[1], Authorization.getInstanceFromString(messageData[2]), messageData[3], type);
		}
		
		this.endCommunication(this.getLastConnectedSADID(), true, true);
		return null;
	}
	
	/**
	 * Sends display update data to an specific SAD
	 * @param destID The ID of the SAD to which the message is to be sent
	 * @param displayUpdateCapacity The display data to be applied
	 * @return True if the message was received successfully by the SAD, false otherwise
	 */
	public boolean sendDisplayUpdate(String destID, int displayUpdateCapacity) {
		String toSendDisplayUpdate = Integer.toString(displayUpdateCapacity);

		if (this.performAuthentication(true, destID)) {
			this.sendMessage(ClientType.SAD, destID, CommunicationManagerConstants.SEND_TO_SAD_MESSAGE, CommunicationManagerConstants.MESSAGE_BEGIN_DISPLAY_UPDATE
					+ CommunicationManagerConstants.SEPARATOR
					+ CommunicationManagerConstants.MESSAGE_PREFIX_PARKING_AVAILABLE_CAPACITY
					+ CommunicationManagerConstants.SEPARATOR
					+ toSendDisplayUpdate
					+ CommunicationManagerConstants.SEPARATOR
					+ CommunicationManagerConstants.MESSAGE_END_DISPLAY_UPDATE);
			String answerFromSAD = this.waitForServerResponse(new String[] {CommunicationManagerConstants.MESSAGE_DISPLAY_UPDATE_RECEIVED, CommunicationManagerConstants.MESSAGE_DISPLAY_UPDATE_FAILED});
			if (answerFromSAD.contains(CommunicationManagerConstants.MESSAGE_DISPLAY_UPDATE_RECEIVED)) {
				this.endCommunication(destID, false, false);
				return true;
			}
			else {
				this.endCommunication(destID, false, true);
				return false;
			}
		} 
		return false;
	}

	/**
	 * Sends a message to an specific SAD to add or remove a tag from its internal lists
	 * @param destID The ID of the SAD to which the message is to be sent
	 * @param container An object that contains the information needed to perform an update
	 * to a list of tags
	 * @return True if the message was received successfully by the SAD, false otherwise
	 */
	public boolean sendTagListUpdate(String destID, TagListUpdateContainer container) {

		// Get name of list to update
		String listName = "";
		switch (container.tagUpdateListName) {
		case NewTagsList: listName = "tagsToBeWritten";
		break;
		case TagsToBeRenewedList: listName = "tagsToBeRenewedList";
		break;
		case EntryTagsList: listName = "tagsOnEntryList";
		break;
		}

		if (this.performAuthentication(true, destID)) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {}
			this.sendMessage(ClientType.SAD, destID, CommunicationManagerConstants.SEND_TO_SAD_MESSAGE, CommunicationManagerConstants.MESSAGE_BEGIN_TAG_LIST_UPDATE
					+ CommunicationManagerConstants.SEPARATOR
					+ (container.tagUpdateType == TagUpdateType.AddUpdate ? CommunicationManagerConstants.MESSAGE_BEGIN_ADD : CommunicationManagerConstants.MESSAGE_BEGIN_REMOVE)
					+ CommunicationManagerConstants.SEPARATOR
					// Name of list to be updated
					+ CommunicationManagerConstants.MESSAGE_PREFIX_LIST_NAME + CommunicationManagerConstants.SEPARATOR + listName
					+ CommunicationManagerConstants.SEPARATOR
					// Tag ID
					+ CommunicationManagerConstants.MESSAGE_PREFIX_TAG_ID + CommunicationManagerConstants.SEPARATOR + container.getReceivedTag().getTagID()
					+ CommunicationManagerConstants.SEPARATOR
					// Authorization type
					+ CommunicationManagerConstants.MESSAGE_PREFIX_AUTH_TYPE + CommunicationManagerConstants.SEPARATOR + container.getReceivedTag().getAuthorizationType()
					+ CommunicationManagerConstants.SEPARATOR
					// Expiration date
					+ CommunicationManagerConstants.MESSAGE_PREFIX_EXPIRATION_DATE + CommunicationManagerConstants.SEPARATOR + container.getReceivedTag().getExpirationDate().toString()
					+ CommunicationManagerConstants.SEPARATOR
					// Allowed tag status
					+ CommunicationManagerConstants.MESSAGE_PREFIX_ALLOWED_TAG_STATUS + CommunicationManagerConstants.SEPARATOR + container.getReceivedTag().getTagAccessType()
					+ CommunicationManagerConstants.SEPARATOR
					+ (container.tagUpdateType == TagUpdateType.AddUpdate ? CommunicationManagerConstants.MESSAGE_END_ADD : CommunicationManagerConstants.MESSAGE_END_REMOVE)
					+ CommunicationManagerConstants.SEPARATOR
					+ CommunicationManagerConstants.MESSAGE_END_TAG_LIST_UPDATE);

			String answerFromSAD = this.waitForServerResponse(new String[] {CommunicationManagerConstants.MESSAGE_TAG_UPDATE_RECEIVED, CommunicationManagerConstants.MESSAGE_TAG_UPDATE_FAILED});
			if (answerFromSAD.contains(CommunicationManagerConstants.MESSAGE_TAG_UPDATE_RECEIVED)) {
				this.endCommunication(destID, false, false);
				return true;
			}
			else {
				this.endCommunication(destID, false, true);
				return false;
			}
		}
		return false;
	}

	/**
	 * Sends configuration data to an specific SAD
	 * @param destID The ID of the SAD to which the message is to be sent
	 * @param manager The object that contains the information about SAD configuration
	 * @return True if the message was received successfully by the SAD, false otherwise
	 */
	public boolean sendSADConfiguration(String destID, SADConfigurationManager manager) {

		// Get authorization type list concatenated on a String
		String authTypesList = "";
		for (int i = 0; i < manager.getAuthorizationType().length; i++) {
			authTypesList = authTypesList + "," + manager.getAuthorizationType()[i];
		}
		authTypesList = authTypesList.substring(1);

		// Get authorization type list concatenated on a String
		String accessTimesList = "";
		for (int i = 0; i < manager.getAccessControlTime().length; i++) {
			accessTimesList = accessTimesList + "," + manager.getAccessControlTime()[i];
		}
		accessTimesList = accessTimesList.substring(1);

		if (this.performAuthentication(true, destID)) {
			this.sendMessage(ClientType.SAD, destID, CommunicationManagerConstants.SEND_TO_SAD_MESSAGE, CommunicationManagerConstants.MESSAGE_BEGIN_SAD_CONFIGURATION
					+ CommunicationManagerConstants.SEPARATOR
					// Unique ID
					+ CommunicationManagerConstants.MESSAGE_PREFIX_UNIQUE_ID + CommunicationManagerConstants.SEPARATOR + manager.getSadConfigurationID()
					+ CommunicationManagerConstants.SEPARATOR
					// Direction type
					+ CommunicationManagerConstants.MESSAGE_PREFIX_DIRECTION_TYPE + CommunicationManagerConstants.SEPARATOR + manager.getDirection()
					+ CommunicationManagerConstants.SEPARATOR
					// Authorization Types List
					+ CommunicationManagerConstants.MESSAGE_PREFIX_AUTH_TYPE_LIST + CommunicationManagerConstants.SEPARATOR + authTypesList
					+ CommunicationManagerConstants.SEPARATOR
					// Access Control Times
					+ CommunicationManagerConstants.MESSAGE_PREFIX_ACCESS_CONTROL_TIME + CommunicationManagerConstants.SEPARATOR + accessTimesList
					+ CommunicationManagerConstants.SEPARATOR
					// Parking lot capacity
					+ CommunicationManagerConstants.MESSAGE_PREFIX_PARKING_CAPACITY + CommunicationManagerConstants.SEPARATOR + Integer.toString(manager.getParkingCapacity())					
					+ CommunicationManagerConstants.SEPARATOR
					+ CommunicationManagerConstants.MESSAGE_END_SAD_CONFIGURATION);
			String answerFromSAD = this.waitForServerResponse(new String[] {CommunicationManagerConstants.MESSAGE_SAD_CONFIG_RECEIVED, CommunicationManagerConstants.MESSAGE_SAD_CONFIG_FAILED});
			if (answerFromSAD.contains(CommunicationManagerConstants.MESSAGE_SAD_CONFIG_RECEIVED)) {
				this.endCommunication(destID, false, false);
				return true;
			}
			else {
				this.endCommunication(destID, false, true);
				return false;
			}
		}
		return false;
	}

	/**
	 * Sends a message to an specific SAD ordering it to open or close its barrier gate
	 * @param destID The ID of the SAD to which the message is to be sent
	 * @param type Describes whether the barrier gate is to be opened or closed
	 * @return True if the message was received successfully by the SAD, false otherwise
	 */
	public boolean sendBarrierGateAction(String destID, BarrierGateActionType type) {
		
		if (this.performAuthentication(true, destID)) {
			this.sendMessage(ClientType.SAD, destID, CommunicationManagerConstants.SEND_TO_SAD_MESSAGE, CommunicationManagerConstants.MESSAGE_BEGIN_BARRIER_GATE_ACTION
					+ CommunicationManagerConstants.SEPARATOR
					+ (type == BarrierGateActionType.OpenBarrierGate ? CommunicationManagerConstants.MESSAGE_OPEN_BARRIER_GATE : CommunicationManagerConstants.MESSAGE_CLOSE_BARRIER_GATE)
					+ CommunicationManagerConstants.SEPARATOR
					+ CommunicationManagerConstants.MESSAGE_END_BARRIER_GATE_ACTION);
					
					String answerFromSAD = this.waitForServerResponse(new String[] {CommunicationManagerConstants.MESSAGE_TAG_UPDATE_RECEIVED, CommunicationManagerConstants.MESSAGE_TAG_UPDATE_FAILED});
			if (answerFromSAD.contains(CommunicationManagerConstants.MESSAGE_TAG_UPDATE_RECEIVED)) {
				this.endCommunication(destID, false, false);
				return true;
			}
			else {
				this.endCommunication(destID, false, true);
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Returns a list ordered from least recent to most recently connected SAD
	 * @return A List of String that represents the ID of each connected SAD
	 */
	public List<String> getAllConnectedSAD() {
		return this.connectedSADs;
	}
	
	/**
	 * Returns the ID of the last connected SAD
	 * @return A String that contains the ID of the last connected SAD
	 */
	public String getLastConnectedSADID() {
		return this.connectedSADs.get(this.connectedSADs.size()-1);
	}

	/**
	 * CAS Authentication Procedure
	 * @param isOutgoing Must be true if the authentication is being initiated by the CAS, otherwise should be false
	 * @return True if authentication is successful, false otherwise
	 */
	private boolean performAuthentication(boolean isOutgoing, String destID) {

		if (socket.isSocketOpen()) {

			if (isOutgoing) {
				// Send begin communication to SAD
				//System.out.println("CAS: [method performAuthentication]: Sending 'beginCommunication'...");
				this.sendMessage(ClientType.SAD, destID, CommunicationManagerConstants.SEND_TO_SAD_MESSAGE, CommunicationManagerConstants.MESSAGE_BEGIN_COMMUNICATION);
				//System.out.println("CAS: [method performAuthentication]: Sent 'beginCommunication'");

				// Wait for communication acknowledge from SAD
				//System.out.println("CAS: [method performAuthentication]: Waiting for 'communicationAcknowledge'...");
				waitForServerResponse(new String[] {CommunicationManagerConstants.MESSAGE_COMMUNICATION_ACKNOWLEDGE});
				//System.out.println("CAS: [method performAuthentication]: Received 'communicationAcknowledge'");

				int tries = 0;
				do {
					// Send this CAS ID
					this.sendMessage(ClientType.SAD, destID, CommunicationManagerConstants.SEND_TO_SAD_MESSAGE, 
							CommunicationManagerConstants.MESSAGE_BEGIN_IDENTIFICATION 
							+ CommunicationManagerConstants.SEPARATOR 
							+ this.deviceID 
							+ CommunicationManagerConstants.SEPARATOR 
							+ CommunicationManagerConstants.MESSAGE_END_IDENTIFICATION);

					// Wait for response regarding this CAS ID acknowledge
					String idAcknowledgeMessage = waitForServerResponse(new String[]{CommunicationManagerConstants.MESSAGE_IDENTIFICATION_ACKNOWLEDGED, CommunicationManagerConstants.MESSAGE_IDENTIFICATION_FAILED});

					// Manage authentication success
					if (idAcknowledgeMessage.contains(CommunicationManagerConstants.MESSAGE_IDENTIFICATION_ACKNOWLEDGED)) {
						// ID Acknowledge, now request the SAD ID
						this.sendMessage(ClientType.SAD, destID, CommunicationManagerConstants.SEND_TO_SAD_MESSAGE, CommunicationManagerConstants.MESSAGE_REQUIRE_SAD_ID);

						// Wait for response regarding SAD ID
						String incomingSADID = waitForServerResponse(new String[]{CommunicationManagerConstants.MESSAGE_BEGIN_IDENTIFICATION});

						// Verify SAD authenticity
						if ( (new AuthenticationManager().checkAuthenticationIDValidity(Long.parseLong(incomingSADID.split(CommunicationManagerConstants.SEPARATOR)[1]))) ) {
							// Authentication successful
							this.sendMessage(ClientType.SAD, destID, CommunicationManagerConstants.SEND_TO_SAD_MESSAGE, CommunicationManagerConstants.MESSAGE_IDENTIFICATION_ACKNOWLEDGED);
							return true;
						}
						else {
							// Authentication failed
							this.sendMessage(ClientType.SAD, destID, CommunicationManagerConstants.SEND_TO_CAS_MESSAGE, CommunicationManagerConstants.MESSAGE_IDENTIFICATION_FAILED);
							return false;
						}
					}
					// Manage authentication failure
					else if (idAcknowledgeMessage.contains(CommunicationManagerConstants.MESSAGE_IDENTIFICATION_FAILED)){
						tries++;
						try {
							Thread.sleep(MAX_CONNECTION_TIMEOUT);
						}
						catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				while (tries < MAX_CONNECTION_RETRY);

			}
			// Manage authentication when communication is incoming
			else {
				// Send communication acknowledge message to SAD
				this.sendMessage(ClientType.SAD, destID, CommunicationManagerConstants.SEND_TO_SAD_MESSAGE, CommunicationManagerConstants.MESSAGE_COMMUNICATION_ACKNOWLEDGE);

				int tries = 0;
				do {
					// Wait for response regarding SAD ID
					String sadID = this.waitForServerResponse(new String[] {CommunicationManagerConstants.MESSAGE_BEGIN_IDENTIFICATION});

					// Verify SAD authenticity
					if ( (new AuthenticationManager().checkAuthenticationIDValidity(Long.parseLong(sadID.split(CommunicationManagerConstants.SEPARATOR)[1]))) ) {
						this.sendMessage(ClientType.SAD, destID, CommunicationManagerConstants.SEND_TO_SAD_MESSAGE, CommunicationManagerConstants.MESSAGE_IDENTIFICATION_ACKNOWLEDGED);

						// Wait for authentication request
						this.waitForServerResponse(new String[] {CommunicationManagerConstants.MESSAGE_REQUIRE_CAS_ID});

						// Send this CAS ID
						this.sendMessage(ClientType.SAD, destID, CommunicationManagerConstants.SEND_TO_SAD_MESSAGE, 
								CommunicationManagerConstants.MESSAGE_BEGIN_IDENTIFICATION 
								+ CommunicationManagerConstants.SEPARATOR 
								+ this.deviceID 
								+ CommunicationManagerConstants.SEPARATOR 
								+ CommunicationManagerConstants.MESSAGE_END_IDENTIFICATION);

						// Wait for response
						String thisAuthResult = this.waitForServerResponse(new String[] {CommunicationManagerConstants.MESSAGE_IDENTIFICATION_ACKNOWLEDGED, CommunicationManagerConstants.MESSAGE_IDENTIFICATION_FAILED});

						// Check response
						if (thisAuthResult.contains(CommunicationManagerConstants.MESSAGE_IDENTIFICATION_ACKNOWLEDGED)) {
							return true;
						}
						else if (thisAuthResult.contains(CommunicationManagerConstants.MESSAGE_IDENTIFICATION_FAILED)) {
							return false;
						}

					}
					// SAD Authentication failed
					else {
						this.sendMessage(ClientType.SAD, destID, CommunicationManagerConstants.SEND_TO_SAD_MESSAGE, CommunicationManagerConstants.MESSAGE_IDENTIFICATION_FAILED);
						tries++;
						try {
							Thread.sleep(MAX_CONNECTION_TIMEOUT);
						}
						catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				while (tries < MAX_CONNECTION_RETRY);
			}
		}

		return false;
	}

	/**
	 * Handles the closing of communication with the SAD, whether it is the SAD that requests
	 * the end of communication or the CAS
	 * @param destID The ID of the SAD to stop communication
	 * @param isOutgoing  Whether the closing is started by the CAS (outgoing) or not
	 * @param isError Whether if the communication closed by an error in the data received or not
	 */
	private void endCommunication(String destID, boolean isOutgoing, boolean isError) {
		if (isOutgoing) {
			if (isError)
				this.sendMessage(ClientType.SAD, destID, CommunicationManagerConstants.SEND_TO_SAD_MESSAGE, CommunicationManagerConstants.MESSAGE_TAG_INFO_FAILED);
			else
				this.sendMessage(ClientType.SAD, destID, CommunicationManagerConstants.SEND_TO_SAD_MESSAGE, CommunicationManagerConstants.MESSAGE_TAG_INFO_RECEIVED);

			this.waitForServerResponse(new String[] {CommunicationManagerConstants.MESSAGE_END_COMMUNICATION});
			this.sendMessage(ClientType.SAD, destID, CommunicationManagerConstants.SEND_TO_SAD_MESSAGE, CommunicationManagerConstants.MESSAGE_COMMUNICATION_CLOSED);
			return;
		}
		else {
			this.sendMessage(ClientType.SAD, destID, CommunicationManagerConstants.SEND_TO_SAD_MESSAGE, CommunicationManagerConstants.MESSAGE_END_COMMUNICATION);
			this.waitForServerResponse(new String[] {CommunicationManagerConstants.MESSAGE_COMMUNICATION_CLOSED});
			return;
		}
	}

	/**
	 * Sends a message to a device as specified by the parameters
	 * @param dest The destination device type
	 * @param destID The ID of the destination device. Leave blank if sending to CAS
	 * @param messageType The type of message, whether it's a message to a SAD or to a CAS
	 * @param message The message to be sent
	 * @return True if the message was successfully sent, false otherwise.
	 */
	private boolean sendMessage(ClientType dest, String destID, String messageType, String message) {
		String toSend = "";
		if (dest == ClientType.CAS) {
			//client = CommunicationManagerConstants.CLIENT_TYPE_CAS;
			toSend = this.type + "::" + this.deviceID + "::" + dest + "::" + messageType + "::" + message;
		}
		else {
			toSend = this.type + "::" + this.deviceID + "::" + dest + "::" + destID + "::" + messageType + "::" + message;	
		}

		return socket.sendData(toSend);
	}

	/**
	 * IMPORTANT: This method blocks the current running thread by means of an infinite loop
	 * waiting for the server response. Once it obtains a response similar to the expected response, 
	 * @param expectedResponseText The message expected to be received from the SAD, as a String
	 * @return The actual response message received
	 */
	private String waitForServerResponse(String[] expectedResponseText) {
		String response = "";
		boolean expectedResponseFound = false;

		while (!expectedResponseFound) {
			while(!socket.isDataAvailable());
			response = socket.getReceivedData();
			for (String s: expectedResponseText) {
				if (response.contains(s))
					expectedResponseFound = true;
			}
		}
		return response;
	}

}
