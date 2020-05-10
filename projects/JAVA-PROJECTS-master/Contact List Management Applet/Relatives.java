import java.io.Serializable;

@SuppressWarnings("serial")
public class Relatives extends Contacts {
    private String birthday;
    private String lastmetdate;
    
    Relatives(){
    	super();
        birthday = null;
        lastmetdate = null;
    }
    
    
    public void setbirthday(String bday){
        this.birthday = bday;
    }
    public void setdate(String date){
        this.lastmetdate = date;
    }
    
    
    public String getbirthday(){
        return this.birthday;
    }
    
    public String getdate(){
        return this.lastmetdate;
    }
}
