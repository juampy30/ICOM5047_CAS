package hkj.sisca.utilities;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Abstraction for a normal Java Socket
 * @author hfranqui
 *
 */
public class ClientSocket {

	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;
	private String receivedData;
	private static boolean isSocketOpen;
	
	/**
	 * 
	 * @param host
	 * @param port
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public ClientSocket(String host, int port) throws UnknownHostException, IOException {
			socket = new Socket(host, port);
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());	
	}
	
	/**
	 * 
	 * @param host
	 * @param port
	 * @return
	 */
	public static ClientSocket getInstance(String host, int port) {
		try {
			ClientSocket result = new ClientSocket(host, port);
			isSocketOpen = true;
			return result;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			isSocketOpen = false;
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			isSocketOpen = false;
			return null;
		}
		
		
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public boolean sendData(String data) {
		try {
			output.write(data.trim().getBytes());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isDataAvailable() {
		try {
			if (input.available() > 0) {
				byte[] receivedBytes = new byte[input.available()];
				input.read(receivedBytes);
				receivedData = new String(receivedBytes);
			}
			return (receivedData != null);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String getReceivedData() {
		// For debug purposes
		//System.out.println("Received data: " + receivedData);
		String result = receivedData.substring(0);
		receivedData = null;
		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isSocketOpen() {
		return isSocketOpen;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean closeSocket() {
		try {
			socket.close();
			input.close();
			output.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
