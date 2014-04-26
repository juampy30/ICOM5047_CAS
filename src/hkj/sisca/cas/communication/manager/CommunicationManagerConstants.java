package hkj.sisca.cas.communication.manager;


/**
 * 
 * @author hfranqui
 *
 */
public class CommunicationManagerConstants {

	/**
	 * Describes the type of client trying to communicate with the server
	 * @author hfranqui
	 *
	 */
	public enum ClientType {
		SAD, CAS
	}
	
	/**
	 * Describes the result of attempting to register this client to a server
	 * @author hfranqui
	 *
	 */
	public enum RegistrationResult {
		REGISTER_SUCCESSFUL, REGISTER_UNSUCCESSFUL_DUPLICATE, REGISTER_UNSUCCESSFUL_IDENTIFIER, REGISTER_UNSUCCESSFUL_SERVER_NOT_FOUND
	}
	
	/**
	 * Underlying protocol strings
	 */
	public static final String REGISTRATION_INQUIRY_MESSAGE = "clientRegister";
	public static final String REGISTRATION_SUCCESSFUL_MESSAGE = "clientRegisteredSuccessfully";
	public static final String UNREGISTER_INQUIRY_MESSAGE = "clientUnregister";
	public static final String UNREGISTER_SUCCESSFUL_MESSAGE = "clientUnregisteredSuccessfully";
	public static final String REGISTRATION_UNSUCCESSFUL_DUPLICATE_MESSAGE = "clientNotRegisteredDuplicate";
	public static final String REGISTRATION_UNSUCCESSFUL_INVALID_MESSAGE = "clientNotRegisteredInvalidID";
	public static final String SEND_TO_CAS_MESSAGE = "sendMessageToCAS";
	public static final String SEND_TO_SAD_MESSAGE = "sendMessageToSAD";
	public static final String SEPARATOR = "&";
	
	/**
	 * Communication messages strings
	 */
	public static final String MESSAGE_BEGIN_COMMUNICATION = "beginCommunication";
	public static final String MESSAGE_COMMUNICATION_ACKNOWLEDGE = "communicationAcknowledged";
	public static final String MESSAGE_BEGIN_IDENTIFICATION = "beginIdentification";
	public static final String MESSAGE_END_IDENTIFICATION = "endIdentification";
	public static final String MESSAGE_IDENTIFICATION_ACKNOWLEDGED = "identificationAcknowledged";
	public static final String MESSAGE_IDENTIFICATION_FAILED = "identificationFailed";
	public static final String MESSAGE_REQUIRE_CAS_ID = "requireCASIdentification";
	public static final String MESSAGE_REQUIRE_SAD_ID = "requireSADIdentification";
	public static final String MESSAGE_BEGIN_TAG_INFO_SEND = "beginTagInfoSend";
	public static final String MESSAGE_PREFIX_TAG_ID = "tagID";
	public static final String MESSAGE_PREFIX_AUTH_TYPE = "authorizationType";
	public static final String MESSAGE_PREFIX_EXPIRATION_DATE = "expirationDate";
	public static final String MESSAGE_PREFIX_ALLOWED_TAG_STATUS = "allowedTagStatus";
	public static final String MESSAGE_END_TAG_INFO_SEND = "endTagInfoSend";
	public static final String MESSAGE_BEGIN_TAG_LIST_UPDATE = "beginTagListUpdate";
	public static final String MESSAGE_BEGIN_ADD = "beginAdd";
	public static final String MESSAGE_PREFIX_LIST_NAME = "list";
	public static final String MESSAGE_END_ADD = "endAdd";
	public static final String MESSAGE_BEGIN_REMOVE = "beginRemove";
	public static final String MESSAGE_END_REMOVE = "endRemove";
	public static final String MESSAGE_END_TAG_LIST_UPDATE = "endTagListUpdate";
	public static final String MESSAGE_BEGIN_BARRIER_GATE_ACTION = "beginBarrierGateAction";
	public static final String MESSAGE_OPEN_BARRIER_GATE = "openBarrierGate";
	public static final String MESSAGE_CLOSE_BARRIER_GATE = "endBarrierGate";
	public static final String MESSAGE_END_BARRIER_GATE_ACTION = "endBarrierGateAction";
	public static final String MESSAGE_BEGIN_SAD_CONFIGURATION = "beginSADConfiguration";
	public static final String MESSAGE_PREFIX_UNIQUE_ID = "uniqueID";
	public static final String MESSAGE_PREFIX_DIRECTION_TYPE = "directionType";
	public static final String MESSAGE_PREFIX_AUTH_TYPE_LIST = "authorizationTypeList";
	public static final String MESSAGE_PREFIX_ACCESS_CONTROL_TIME = "accessControlTime";
	public static final String MESSAGE_PREFIX_PARKING_CAPACITY = "parkingLotCapacity";
	public static final String MESSAGE_END_SAD_CONFIGURATION = "endSADConfiguration";
	public static final String MESSAGE_BEGIN_DISPLAY_UPDATE = "beginDisplayUpdate";
	public static final String MESSAGE_PREFIX_PARKING_AVAILABLE_CAPACITY = "parkingLotAvailableCapacity";
	public static final String MESSAGE_END_DISPLAY_UPDATE = "endDisplayUpdate";
	public static final String MESSAGE_TAG_INFO_RECEIVED = "informationReceived";
	public static final String MESSAGE_TAG_INFO_FAILED = "informationFailed";
	public static final String MESSAGE_TAG_UPDATE_RECEIVED = "tagUpdateReceived";
	public static final String MESSAGE_TAG_UPDATE_FAILED = "tagUpdateFailed";
	public static final String MESSAGE_SAD_CONFIG_RECEIVED = "SADConfigurationReceived";
	public static final String MESSAGE_SAD_CONFIG_FAILED = "SADConfigurationFailed";
	public static final String MESSAGE_BARRIER_GATE_RECEIVED = "barrierGateActionReceived";
	public static final String MESSAGE_BARRIER_GATE_FAILED = "barrierGateActionFailed";
	public static final String MESSAGE_DISPLAY_UPDATE_RECEIVED = "displayUpdateReceived";
	public static final String MESSAGE_DISPLAY_UPDATE_FAILED = "displayUpdateFailed";
	public static final String MESSAGE_END_COMMUNICATION = "endCommunication";
	public static final String MESSAGE_COMMUNICATION_CLOSED = "communicationClosed";
	
	
	/**
	 * Identifiers
	 */
	public static final String CLIENT_TYPE_CAS = "cas";
	public static final String CLIENT_TYPE_SAD = "sad";
	
}
