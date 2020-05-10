import java.io.Serializable;

//base class for inheritance

@SuppressWarnings("serial")
public class Contacts implements Serializable{
	protected String name;
    protected String mobile_number;
    protected String email_id;
    
    Contacts(){
    	name = new String();
    	mobile_number = new String();
    	email_id = new String();
    	
    }
    
    
    public void setname(String name){
        this.name = name;
     }
    public void setmobnum(String mobnum){
         this.mobile_number = mobnum;
     }
    public void setemailid(String id){
         this.email_id = id;
     }
     
    public String getname(){
         return this.name;
      }
    public String getmobnum(){
         return this.mobile_number;
      }
    public String getemailid(){
         return this.email_id;
      }
    
}
