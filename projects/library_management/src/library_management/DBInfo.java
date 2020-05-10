package library_management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

public class DBInfo
{
	static Connection con;
	static Vector<String> header;
	static Vector<Vector<String>> outer;
	static
	{	  try
		  {
			  Class.forName("com.mysql.jdbc.Driver");
			  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management","root","rat");  
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
	  }
public static int insertValueA(String str)
{
	int i=0;
	String query=("insert into author values(?,?)");
	try
	{
		PreparedStatement ps=con.prepareStatement(query);
		ps.setInt(1, 0);
		ps.setString(2, str.toUpperCase());
		i=ps.executeUpdate();
	}
	catch(Exception e)
	{
			e.printStackTrace();
	}
		return i;
	  
	}
public static int insertValueC(String str)
{
	int i=0;
	String query=("insert into category values(?,?)");
	try
	{
		PreparedStatement ps=con.prepareStatement(query);
		ps.setInt(1, 0);
		ps.setString(2, str.toUpperCase());
		i=ps.executeUpdate();
	}
	catch(Exception e)
	{
			e.printStackTrace();
	}
		return i;
	  
	}
public static int insertValueP(String str)
{
	int i=0;
	String query=("insert into publisher values(?,?)");
	try
	{
		PreparedStatement ps=con.prepareStatement(query);
		ps.setInt(1, 0);
		ps.setString(2, str.toUpperCase());
		i=ps.executeUpdate();
	}
	catch(Exception e)
	{
			e.printStackTrace();
	}
		return i;
	  
	}
public static int insertValueS(String str)
{
	int i=0;
	String query=("insert into subject values(?,?)");
	try
	{
		PreparedStatement ps=con.prepareStatement(query);
		ps.setInt(1, 0);
		ps.setString(2, str.toUpperCase());
		i=ps.executeUpdate();
	}
	catch(Exception e)
	{
			e.printStackTrace();
	}
		return i;
	  
	}
public static Vector<String> getAuthor()
{
	  Vector<String>  v=new Vector<>();
	  String query="select name from author order by name";
	  try
	  {
		  PreparedStatement ps=con.prepareStatement(query);
		  ResultSet res=ps.executeQuery();
		  while(res.next())
		  {
			 v.add(res.getString(1)); 
		  }
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  return v;
}
public static Vector<String> getPublisher()
{
	  Vector<String>  v=new Vector<>();
	  String query="select name from publisher order by name";
	  try
	  {
		  PreparedStatement ps=con.prepareStatement(query);
		  ResultSet res=ps.executeQuery();
		  while(res.next())
		  {
			 v.add(res.getString(1)); 
		  }
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  return v;
}

public static Vector<String> getCategory()
{
	  Vector<String>  v=new Vector<>();
	  String query="select name from category order by name";
	  try
	  {
		  PreparedStatement ps=con.prepareStatement(query);
		  ResultSet res=ps.executeQuery();
		  while(res.next())
		  {
			 v.add(res.getString(1)); 
		  }
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  return v;
}

public static Vector<String> getSubject()
{
	  Vector<String>  v=new Vector<>();
	  String query="select name from subject order by name";
	  try
	  {
		  PreparedStatement ps=con.prepareStatement(query);
		  ResultSet res=ps.executeQuery();
		  while(res.next())
		  {
			 v.add(res.getString(1)); 
		  }
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  return v; 
}

public static void getAllBooks()
{
	  header=new Vector<>();//it will store the cols name of book table
	  outer=new Vector<>();
	  
	  String query="select * from book_table";
	  try
	  {
	PreparedStatement ps=con.prepareStatement(query);
	ResultSet res=ps.executeQuery();
	
	//getting MetaData
	ResultSetMetaData rsmd=res.getMetaData();
	int colcount=rsmd.getColumnCount();
	for(int i=1;i<=colcount;i++)
	{
		String colName=rsmd.getColumnName(i);
	   header.add(colName);
	}
	
	//getting table records
	while(res.next())
	{
		Vector<String> records=new Vector<>();
		for(int i=1;i<=colcount;i++)
		{
			String value=res.getString(i);
			records.add(value);
		}
		outer.add(records);
	}
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
}

public static void getValuesP(String name)
{
	  header=new Vector<>();//it will store the cols name of book table
	  outer=new Vector<>();
	  
	  String query="select * from book_table where publisher =?";
	  try
	  {
	PreparedStatement ps=con.prepareStatement(query);
	ps.setString(1, name);
	ResultSet res=ps.executeQuery();
	
	//getting MetaData
	ResultSetMetaData rsmd=res.getMetaData();
	int colcount=rsmd.getColumnCount();
	for(int i=1;i<=colcount;i++)
	{
		String colName=rsmd.getColumnName(i);
	   header.add(colName);
	}
	
	//getting table records
	while(res.next())
	{
		Vector<String> records=new Vector<>();
		for(int i=1;i<=colcount;i++)
		{
			String v=res.getString(i);
			records.add(v);
		}
		outer.add(records);
	}
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
}	

public static void getValuesA(String name)
{
	  header=new Vector<>();//it will store the cols name of book table
	  outer=new Vector<>();
	  
	  String query="select * from book_table where author =?";
	  try
	  {
	PreparedStatement ps=con.prepareStatement(query);
	ps.setString(1, name);
	ResultSet res=ps.executeQuery();
	
	//getting MetaData
	ResultSetMetaData rsmd=res.getMetaData();
	int colcount=rsmd.getColumnCount();
	for(int i=1;i<=colcount;i++)
	{
		String colName=rsmd.getColumnName(i);
	   header.add(colName);
	}
	
	//getting table records
	while(res.next())
	{
		Vector<String> records=new Vector<>();
		for(int i=1;i<=colcount;i++)
		{
			String v=res.getString(i);
			records.add(v);
		}
		outer.add(records);
	}
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
}

public static void getValuesC(String name)
{
	  header=new Vector<>();//it will store the cols name of book table
	  outer=new Vector<>();
	  
	  String query="select * from book_table where category =?";
	  try
	  {
	PreparedStatement ps=con.prepareStatement(query);
	ps.setString(1, name);
	ResultSet res=ps.executeQuery();
	
	//getting MetaData
	ResultSetMetaData rsmd=res.getMetaData();
	int colcount=rsmd.getColumnCount();
	for(int i=1;i<=colcount;i++)
	{
		String colName=rsmd.getColumnName(i);
	   header.add(colName);
	}
	
	//getting table records
	while(res.next())
	{
		Vector<String> records=new Vector<>();
		for(int i=1;i<=colcount;i++)
		{
			String v=res.getString(i);
			records.add(v);
		}
		outer.add(records);
	}
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
}
public static void getValuesS(String name)
{
	  header=new Vector<>();//it will store the cols name of book table
	  outer=new Vector<>();
	  
	  String query="select * from book_table where subject =?";
	  try
	  {
	PreparedStatement ps=con.prepareStatement(query);
	ps.setString(1, name);
	ResultSet res=ps.executeQuery();
	
	//getting MetaData
	ResultSetMetaData rsmd=res.getMetaData();
	int colcount=rsmd.getColumnCount();
	for(int i=1;i<=colcount;i++)
	{
		String colName=rsmd.getColumnName(i);
	   header.add(colName);
	}
	
	//getting table records
	while(res.next())
	{
		Vector<String> records=new Vector<>();
		for(int i=1;i<=colcount;i++)
		{
			String v=res.getString(i);
			records.add(v);
		}
		outer.add(records);
	}
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
}
public static void getAllUsers() {
	// TODO Auto-generated method stub

	  header=new Vector<>();//it will store the cols name of book table
	  outer=new Vector<>();
	  
	  String query="select * from registeration";
	  try
	  {
	PreparedStatement ps=con.prepareStatement(query);
	ResultSet res=ps.executeQuery();
	
	//getting MetaData
	ResultSetMetaData rsmd=res.getMetaData();
	int colcount=rsmd.getColumnCount();
	for(int i=1;i<=colcount;i++)
	{
		String colName=rsmd.getColumnName(i);
	   header.add(colName);
	}
	
	//getting table records
	while(res.next())
	{
		Vector<String> records=new Vector<>();
		for(int i=1;i<=colcount;i++)
		{
			String value=res.getString(i);
			records.add(value);
		}
		outer.add(records);
	}
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	
}

public static void  Books(String nm)
{  
	  header=new Vector<>();//it will store the cols name of book table
	  outer=new Vector<>();
	  
	  String query="select * from issue where username=?";
	  try
	  {
	PreparedStatement ps=con.prepareStatement(query);
	ps.setString(1,nm);
	ResultSet res=ps.executeQuery();
	
	//getting MetaData
	ResultSetMetaData rsmd=res.getMetaData();
	int colcount=rsmd.getColumnCount();
	for(int i=1;i<=colcount;i++)
	{ 
		String colName=rsmd.getColumnName(i);
	   header.add(colName);
	}
	
	//getting table records
	while(res.next())
	{
		Vector<String> records=new Vector<>();
		for(int i=1;i<=colcount;i++)
		{
			String value=res.getString(i);
			records.add(value);
		}
		outer.add(records);
	}
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	 
}
}