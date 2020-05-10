


/**
 * Write a description of class Faculty here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.Serializable;

public class Faculty implements Serializable
{
    private String name;
    private String department;
    private String address;
    private String mobnum;
    private String emailid;
    
    Faculty(){
        name = null;
        department = null;
        address = null;
        address = null;
        mobnum = null;
        emailid = null;
    }
    
    public void setname(String name){
        this.name = name;
    }
    public void setaddress(String address){
        this.address = address;
    }
    public void setmobnum(String mobnum){
        this.mobnum = mobnum;
    }
    public void setdepartment(String dept){
        this.department = dept;
    }
    public void setemailid(String id){
        this.emailid = id;
    }
    public String getname(){
        return this.name;
    }
    public String getaddress(){
        return this.address;
    }
    public String getmobnum(){
        return this.mobnum;
    }
    public String getdepartment(){
        return this.department;
    }
    public String getemailid(){
        return this.emailid;
    }
    
}

