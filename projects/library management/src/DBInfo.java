
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
  {
	  try
	  {
		  Class.forName("com.mysql.jdbc.Driver");
		  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libmanagement2","root","root");  
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
  }
  public static int insertValue(String s1,String str)
  {
	  int i=0;
		String query="insert into "+s1+" values(?,?)";
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
	  
	  String query="select * from book";
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
  
  
  
  
  public static void getValues(String value,String name)
  {
	  header=new Vector<>();//it will store the cols name of book table
	  outer=new Vector<>();
	  
	  String query="select * from book where "+value+"=?";
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
  
  
  
  
  
}
