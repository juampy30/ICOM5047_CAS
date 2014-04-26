import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.mail.MessagingException;

import databases.DBManager;


public class NotificationManager {
	
	DBManager dbman;
	EmailServer emailServer;
	ArrayList<Object> notificationsList;
	
	public NotificationManager(){
		
		String username = "hkjsisca2014";
		String password = "sisca2014";
		String incomingHost = "pop.gmail.com";
		String outgoingHost = "smtp.gmail.com";

		emailServer = new EmailServer(username, password,incomingHost,outgoingHost);
		
	}
	
	public void setNotifications(){
		
		try {
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Calendar cal = Calendar.getInstance();
			String currentDate= "'"+dateFormat.format(cal.getTime())+"'";
			
			
			notificationsList= new ArrayList<Object>();
			String query= "Select sisca_permission_tag_number, sisca_permission_expiration_date, sisca_applicant_first_name, sisca_applicant_last_name, "
					+ "sisca_applicant_email, sisca_notification_date from ((sisca_permission natural join sisca_notification) natural join sisca_applicant) "
					+ "where sisca_applicant_id= sisca_permission_applicant_id "
					+ "and sisca_notification_id= sisca_permission_notification_id  "
					+ "and sisca_permission_status='true' "
					+ "and sisca_notification_date="+ currentDate;
			dbman= new DBManager();
			notificationsList= dbman.getNotificationsInformation(query);
			
			System.out.println("Notifications: "+ notificationsList);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void sendNotifications() throws MessagingException{
		System.out.println("SendNotifications: "+ notificationsList);
		for(int i=0; i<notificationsList.size();i++){
			
			String tagNumber= ""+((List<Object>) notificationsList.get(i)).get(0);
			String expirtationDate=  ""+((List<Object>) notificationsList.get(i)).get(1);
			String applicantName= ""+((List<Object>) notificationsList.get(i)).get(2) +" "+((List<Object>) notificationsList.get(i)).get(3);;
			String email=  ""+((List<Object>) notificationsList.get(i)).get(4);
			String notificationDate=  ""+((List<Object>) notificationsList.get(i)).get(5);
			
			
			//System.out.println("email: "+ email);
			
			String messageContents= "Saludos "+applicantName+ ": " +
					"\n\nEl permiso de estacionamiento, con la numeracion "+tagNumber+", ha expirado el dia "+expirtationDate+"."+
					"\n\nFavor de pasar por la Oficina de Transito para la renovacion de su permiso.";
			
			emailServer.sendMessage(email,"Notificacion de Permiso para Estacionamiento", messageContents);
			
		}
		
	}
	
	public static void main (String[] args) throws MessagingException {
		
		NotificationManager notificationsManager= new NotificationManager();
		notificationsManager.setNotifications();
		notificationsManager.sendNotifications();
	}

}
