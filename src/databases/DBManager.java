package databases;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

	//Fields 
	DBConnector dbConnect; 
	Connection con;
	ResultSet rs;
	Statement stmt;
	Tag examTag;

	private ArrayList<Object> rows;
	private ArrayList<Object> arrayOfRows;
	private int numberOfColumns;

	int tagnumber = 999999;
	int authorizationID  = 0;
	String authorizationname = null;
	Authorization atzType = null;
	Date date = null;
	Object[] keyValue = null;
	String sadName;
	private String sadDirection;
	private String authorizationTypeName;

	public DBManager() throws ClassNotFoundException, SQLException{
		Class.forName ("org.postgresql.Driver");
		dbConnect = new DBConnector("jdbc:postgresql://localhost:5001/SisCA");
		con = dbConnect.connect();
		System.out.println("Main Connect");	
	}	

	//Buscar en la Tabla de Datos
	public ArrayList<Object> getFromDB(String getQuery) throws SQLException, ParseException{
		stmt = con.createStatement();
		rs = stmt.executeQuery(getQuery);
		rows = new ArrayList<Object>(); 
		arrayOfRows = new ArrayList<Object>(); 

		ResultSetMetaData rsmd = rs.getMetaData();
		numberOfColumns = rsmd.getColumnCount();
		int i=1;

		// Leyendo de la Base de datos
		while(i < numberOfColumns && rs.next()){
			for(int k=1; k<=numberOfColumns; k++){
				//Guardado de la forma: {columnName:Value}
				rows.add( rsmd.getColumnName(k) + ":" + rs.getString(k));
				System.out.println(" ======" + rs.getString(k));
			}
		}

		//Procesar la info de la tabla de datos
		//De la forma: [{row1},{row2},{row3}]
		subListas(rows);
		//Debugginf
		//System.out.println("--------------------------------------COLUMNAS & FILAS-------------------------------------- \n" + rows);
		System.out.println("--------------------------------------     FILAS     --------------------------------------- \n" + arrayOfRows);
		
		//ProcessTag ... Devuelve el Tag buscado (query)
		/*examTag = processTag(arrayOfRows);
		System.out.println("Tag ID: "+ examTag.getTagID());
		System.out.println("Tag Authorization Type: " + examTag.getAuthorizationType());
		System.out.println("Tag Expiration Date: "+ examTag.getExpirationDate());*/

		/*ArrayList<Tag> examList;
		examList = getTagList(arrayOfRows);
		System.out.println("Lista de Tag que cumples con el query: ");
		for(int k=0; k<examList.size(); k++){

			System.out.println("Tag "+ k);
			System.out.println("\t" + examList.get(k).getTagID());
			System.out.println("\t" + examList.get(k).getAuthorizationType());
			System.out.println("\t" + examList.get(k).getExpirationDate());
		}*/


		stmt.close();
		return arrayOfRows;
	}


	//Sublista [{Row1},{Row2},{Row3}]
	private void subListas(ArrayList<Object> rows2) {

		int fromIndex = 0; //columna 1
		int toIndex = numberOfColumns; //columna n (donde n es el numero de columnas en la tabla)

		//resultado guardara la lista de la forma [{1:A},{2:B},{3:C}]
		for (int i=0; (i+1)<rows2.size(); i=i+numberOfColumns){
			java.util.List<Object> resultado = rows2.subList(fromIndex, toIndex);
			arrayOfRows.add(resultado);
			fromIndex = fromIndex + numberOfColumns;
			toIndex = toIndex + numberOfColumns;
		}
	//	System.out.println(rows);
	}

	//Insertar en la Base de Datos
	public void insertDB(String updateQuery) throws SQLException{
		System.out.println(updateQuery + "returning sisca_account_id");

		
		//PreparedStatement updateemp = con.prepareStatement(updateQuery);
		//updateemp.setString(1,"regular");
		//updateemp.executeUpdate();

		//System.out.println(updateQuery);

		//stmt.close();
	}

	//Processar un Solo Tag(int tagID, Authorization type, Date expDate)
	@SuppressWarnings("unchecked")
	public Tag getTag(ArrayList<Object> listToProcess) throws ParseException{
		//[{1:A},{2:B},{3:C}]
		for(int i=0; i<listToProcess.size(); i++){
			//obtener el elemento i del elemento 1 (el array del array) 
			for(int k=0 ; k<((List<Object>) listToProcess.get(i)).size(); k++){
				//result = 1:A
				Object result = ((List<Object>) listToProcess.get(i)).get(k);
				//keyValue -> {1,A}
				keyValue = result.toString().split(":");
				String strCondition = null;
				boolean condition = false;

				if(keyValue[0].equals("sisca_permission_tagnumber")){
					tagnumber = Integer.valueOf((String) keyValue[1]);
				}
				if(keyValue[0].equals("sisca_authorization_type_id")){
					authorizationID = Integer.valueOf((String) keyValue[1]);
				}
				if(keyValue[0].equals("sisca_authorization_type_name")){
					authorizationname = (String) keyValue[1];
				}
				if(keyValue[0].equals("sisca_authorization_type_unconditionalentry")) {
					strCondition = (String) keyValue[1];
					if(strCondition == "TRUE"){
						condition = true;
					}
					atzType = new Authorization(authorizationID, authorizationname, condition);
				}
				if(keyValue[0].equals("sisca_permission_expirationdate")){
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //"mm.dd.yy"
					String myString = (String) keyValue[1];
					myString = myString.substring(0,10);
					date = (Date) dateFormat.parse(myString);
				}
			}
		}
		return new Tag(tagnumber, atzType, date);
	}
	
	public ArrayList<Object> getAvailableSAD(ArrayList<Object> listToProcess){
		ArrayList<Object> availableSAD = new ArrayList<Object>();
		//[{1:A},{2:B},{3:C}]
				for(int i=0; i<listToProcess.size(); i++){
					//obtener el elemento i del elemento 1 (el array del array) 
					for(int k=0 ; k<((List<Object>) listToProcess.get(i)).size(); k++){
						//result = 1:A
						Object result = ((List<Object>) listToProcess.get(i)).get(k);
						//keyValue -> {1,A}
						keyValue = result.toString().split(":");
						if(keyValue[0].equals("sisca_sad_name")){
							sadName = (String) keyValue[1];
						}
						if(keyValue[0].equals("sisca_sad_direction")){
							sadDirection = (String) keyValue[1];
						}
						
					}
					availableSAD.add(sadName +" >> "+  sadDirection);
				}
				System.out.println(availableSAD);
		
		return availableSAD;
		
	}

	public ArrayList<Object> getAvailableAuthorizationTypes(ArrayList<Object> listToProcess){
		ArrayList<Object> availableAtzType = new ArrayList<Object>();
		//[{1:A},{2:B},{3:C}]
				for(int i=0; i<listToProcess.size(); i++){
					//obtener el elemento i del elemento 1 (el array del array) 
					for(int k=0 ; k<((List<Object>) listToProcess.get(i)).size(); k++){
						//result = 1:A
						Object result = ((List<Object>) listToProcess.get(i)).get(k);
						//keyValue -> {1,A}
						keyValue = result.toString().split(":");
						System.out.println(" KeyVale: " + keyValue[1]);
						if(keyValue[0].equals("sisca_authorization_type_name")){
							authorizationTypeName = (String) keyValue[1];
						}						
					}
					availableAtzType.add(authorizationTypeName);
				}
				System.out.println(availableAtzType);
		
		return availableAtzType;
		
	}
	
	public ArrayList<Object>[] getParkingTable(ArrayList<Object> listToProcess){
		ArrayList<Object> availableSAD = new ArrayList<Object>();
		//[{1:A},{2:B},{3:C}]
				for(int i=0; i<listToProcess.size(); i++){
					//obtener el elemento i del elemento 1 (el array del array) 
					for(int k=0 ; k<((List<Object>) listToProcess.get(i)).size(); k++){
						//result = 1:A
						Object result = ((List<Object>) listToProcess.get(i)).get(k);
						//keyValue -> {1,A}
						keyValue = result.toString().split(":");
						if(keyValue[0].equals("sisca_sad_name")){
							sadName = (String) keyValue[1];
						}
						if(keyValue[0].equals("sisca_sad_direction")){
							sadDirection = (String) keyValue[1];
						}
						
					}
					availableSAD.add(sadName +" >> "+  sadDirection);
				}
				System.out.println(availableSAD);
		
		return null;
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Tag> getTagList(ArrayList<Object> rows2) throws ParseException{
		ArrayList<Tag> tagList = new ArrayList<Tag>();
		boolean flag = false;
		Tag examTag = null;

		//rows2 -> [{1:A},{2:B},{3:C}]
		for(int i=0; i<rows2.size(); i++){
			flag=false;
			//obtener el elemento i del elemento 1 (el array del array) 
			for(int k=0 ; k<((List<Object>) rows2.get(i)).size(); k++){
				//result = 1:A
				Object result = ((List<Object>) rows2.get(i)).get(k);
				//keyValue -> {1,A}
				keyValue = result.toString().split(":");
				String strCondition = null;
				boolean condition = false;

				if(keyValue[0].equals("sisca_permission_tagnumber")){
					tagnumber = Integer.valueOf((String) keyValue[1]);
					flag=true;
				}
				if(keyValue[0].equals("sisca_authorization_type_id")){
					authorizationID = Integer.valueOf((String) keyValue[1]);
					flag=true;
				}
				if(keyValue[0].equals("sisca_authorization_type_name")){
					authorizationname = (String) keyValue[1];
					flag=true;
				}
				if(keyValue[0].equals("sisca_authorization_type_unconditionalentry")) {
					strCondition = (String) keyValue[1];
					if(strCondition == "TRUE"){
						condition = true;
					}
					atzType = new Authorization(authorizationID, authorizationname, condition);
					flag=true;
				}
				if(keyValue[0].equals("sisca_permission_expirationdate")){
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //"mm.dd.yy"
					String myString = (String) keyValue[1];
					myString = myString.substring(0,10);
					date = (Date) dateFormat.parse(myString);
					flag=true;
				}
			}

			examTag= new Tag(tagnumber,atzType,date);
			if(flag){
				tagList.add(examTag);
			}
		}
		//	System.out.println(tagList.get(0).getExpirationDate());
		return tagList;	
	}


	/*///>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> MAIN <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {
		System.out.println("Comenzo el MAIN");
		DBManager dbman = new DBManager();

		/////////DEBUG DEBUG DEBUG DEBUG 
		//System.out.println("Query: Select * from Sisca_permission");
		//dbman.getFromDB("Select * from sisca_permission");
		//System.out.println("Query: Select * from Sisca_vehicle");
		//dbman.getFromDB("Select * from sisca_vehicle");
		//System.out.println("Query: Inner Join");
		//dbman.getFromDB("select sisca_permission_tagnumber, sisca_authorization_type_id, sisca_authorization_type_name, sisca_authorization_type_unconditionalentry, sisca_permission_expirationdate from sisca_permission inner join sisca_authorization_type on sisca_permission.sisca_permission_authorization_type_id=sisca_authorization_type.sisca_authorization_type_id"); 
		//System.out.println("Query: Insert new Account ");
		//dbman.insertDB("insert into sisca_account (sisca_account_type) values ('prueba')");
		System.out.println("QUERY: Llenar SAD parking Manager");
		dbman.getFromDB("select * from sisca_sad where sisca_sad_active='false'");
	}*/
}

