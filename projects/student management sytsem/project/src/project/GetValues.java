package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

public class GetValues 
{ 
	 public static Vector<Vector<String>>  records;
	 public static Vector<String> header;
	  public static Vector<String> getCourse()
	  {
		  Vector<String> v=new Vector<>();
		  try
		  {
			  String query="select * from course order by name";
			  PreparedStatement ps=DBInfo.con.prepareStatement(query);
			  ResultSet res=ps.executeQuery();
			  while(res.next())
			  {
				  String course=res.getString(2);
				  v.add(course);
			  }
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		  System.out.println(v);
		  return v;
	  }
	  public static Vector<String> getBranch()
	  {
		  Vector<String> v=new Vector<>();
		  try
		  {
			  String query="select * from branch order by name";
			  PreparedStatement ps=DBInfo.con.prepareStatement(query);
			  ResultSet res=ps.executeQuery();
			  while(res.next())
			  {
				  String branch=res.getString(2);
				  v.add(branch);
			  }
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		  System.out.println(v);
		  return v;
	  }

	  public static void getStudents()
	  {
		  records=new Vector<>();
		  header=new Vector<>();
		  String query="select * from student_registration";
		  Connection con=DBInfo.con;
		  try
		  {
			 PreparedStatement ps=con.prepareStatement(query);
			 ResultSet res=ps.executeQuery();//metadata and records
			 
			 //only meta data
			 ResultSetMetaData rsmd=res.getMetaData();
			 int colcount=rsmd.getColumnCount();
			 for(int i=1;i<=colcount;i++)
			 {
				 header.add(rsmd.getColumnName(i));
			 }
			 
			 
			 //only records
			 while(res.next())
			 {
				 Vector<String> v=new Vector<>();  //inner vector
			     for(int i=1;i<=colcount;i++)
			     {
			    	v.add(res.getString(i)); 
			     }
			     records.add(v);
			 }
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
	  }
	  public static void getNotices()
	  {
		  records=new Vector<>();
		  header=new Vector<>();
		  String query="select * from notice";
		  Connection con=DBInfo.con;
		  try
		  {
			 PreparedStatement ps=con.prepareStatement(query);
			 ResultSet res=ps.executeQuery();//metadata and records
			 
			 //only meta data
			 ResultSetMetaData rsmd=res.getMetaData();
			 int colcount=rsmd.getColumnCount();
			 for(int i=1;i<=colcount;i++)
			 {
				 header.add(rsmd.getColumnName(i));
			 }
			 
			 
			 //only records
			 while(res.next())
			 {
				 Vector<String> v=new Vector<>();  //inner vector
			     for(int i=1;i<=colcount;i++)
			     {
			    	v.add(res.getString(i)); 
			     }
			     records.add(v);
			 }
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
	  }
}
