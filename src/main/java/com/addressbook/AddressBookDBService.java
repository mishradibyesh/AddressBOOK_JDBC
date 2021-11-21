package com.addressbook;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class AddressBookDBService {

	public static Connection getConnection() throws Exception  {

		String url = "jdbc:mysql://localhost:3306/addressbookservice?useSSL=false";
		String uname = "root";
		String pwd ="Dibyesh@3";

		try {
			//mysql driver 
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver is loaded........");		
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//getting all drivers in driver manager
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while(drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			System.out.println("Driver  Name is :" + driver);
		}

		//making connection
		java.sql.Connection connection = null ;
		try {
			connection =DriverManager.getConnection(url, uname, pwd);
			System.out.println("Connection to the DB succsessful............! " + connection);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return connection; 

	}

	public List<PersonInformation> getDataFromDB() throws Exception {

		Connection connection = this.getConnection();
		Statement st = connection.createStatement();
		ResultSet rs  = st.executeQuery("select * from AddressBook ");
		List <PersonInformation> list = new ArrayList();
		while(rs.next()) {

			String first_name =rs.getString("first_name");
			String last_name = rs.getString("last_name");
			String address = rs.getString("address");
			String city = rs.getString("city");
			String state = rs.getString("state");
			int zip = rs.getInt("zip");
			long phoneNumber = rs.getLong("phoneNumber");
			String email = rs.getString("email");
			PersonInformation info = new PersonInformation(first_name, last_name , address, city,state, zip,phoneNumber, email);
			list.add(info);
		}
		connection.close();
		return list;
	}	

	// method to update the table
	public void updateExistingTable() throws Exception {
		Connection connection = this.getConnection();
		String sql ="update addressbook set relation=? where first_name=?";
		PreparedStatement pmst =connection.prepareStatement(sql);
		pmst.setString(1, "brother");
		pmst.setString(2, "Abhay");
		int i = pmst.executeUpdate();

		connection.close();

	}
	
	//method to retrieve the 
	public List<PersonInformation> retrieveData_inBetween_Range() throws Exception
	{
		String sql = "select * from Addressbook where start between cast('0000-00-00' as Date ) AND DATE(NOW())";
		List< PersonInformation> list=new ArrayList();
		try {
			Connection connection =getConnection();
			Statement statement=connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while( rs.next())
			{
				String first_name =rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String address = rs.getString("address");
				String city = rs.getString("city");
				String state = rs.getString("state");
				int zip = rs.getInt("zip");
				long phoneNumber = rs.getLong("phoneNumber");
				String email = rs.getString("email");
				PersonInformation info = new PersonInformation(first_name, last_name , address, city,state, zip,phoneNumber, email);
				list.add(info);
			}
			System.out.println("\n Retrieved Data In Range Is:");
			System.out.println(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

}
