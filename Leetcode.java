import java.util.*;
import java.io.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDate;
import java.sql.*;
public class Leetcode{

public static void input_data(){
	
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter problem URL");
	String url = sc.nextLine();
	System.out.println("Enter tag");
	String tag = sc.nextLine();
	System.out.println("We will get date for you, no need to input");
	//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	//LocalDateTime now = LocalDateTime.now();
//	String date = java.time.LocalDate.now();
	try
	{
		//Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/leetcode", "admin", "admin");
		PreparedStatement st = con.prepareStatement("insert into leetcode values(?,?,?)");
		st.setString(1, url);
		st.setDate(2, new java.sql.Date(System.currentTimeMillis()));
		st.setString(3, tag);
		int res  = st.executeUpdate();
		System.out.println(res + "inserted successfully");
		con.close();
	}
	catch(Exception e)
	{
		System.out.println(e);
	}

}
public static void output_data(){
	try{
		//Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/leetcode", "admin", "admin");
		Statement st = con.createStatement();
		ResultSet res = st.executeQuery("select COUNT(url), tag from leetcode GROUP BY tag");
		while(res.next())
		{	System.out.println(res.getString(1) + " " + res.getString(2));
		}
	}
	catch(Exception e)
	{
		System.out.println("SHIT EXCEPTION" + " " + e);
	}
}

public static void output_data(String aux, String d){
	try{
		//Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/leetcode", "admin", "admin");
		Statement st = con.createStatement();
		String query = "select COUNT(url), tag from leetcode GROUP BY tag HAVING submission_date >= "+ d;
		ResultSet res = st.executeQuery(query);
		while(res.next())
		{	System.out.println(res.getString(1) + " " + res.getString(2));
		}
	}
	catch(Exception e){
		System.out.println("SHIT EXCEPTION" + " " + e);
	}
}


public static void main(String args[])
{

Scanner sc = new Scanner(System.in);
System.out.println("Do you wish to store data ? Y/N");
char choice = sc.nextLine().charAt(0);
if(choice ==  'Y')input_data();
else System.out.println("OK NO DATA INPUT");
String date;
System.out.println("Do you wish to retrieve data ? Y/N");
choice = sc.nextLine().charAt(0);
if(choice ==  'Y'){
	System.out.println("By date ? 1");
	System.out.println("By tag ? 2");
	//System.out.println("By date and tag? 3");
	int n  = sc.nextInt();
	switch(n){
		case 1: System.out.println("Enter date in the format: yyyy-mm-dd");
			date = sc.next();
			output_data("date", date);
			break;
		case 2: output_data();
			break;
		case 3: System.out.println("Enter date in the format: yyyy-mm-dd");
			date = sc.next();
			//output_data("date", "tag", date);
			break;
		default: System.out.println("NOT OK");
			break;
		}
	}
else System.out.println("OK No Retrieval");
}
}