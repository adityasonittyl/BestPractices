import java.io.Serializable;

//import java.util.*;

@SuppressWarnings("serial")
public class Personal_friends extends Contacts 
{
    private String meetingcontext;
    private String date_of_acquaintance;
    private String events;
    
    Personal_friends(){
       
    	super();
        meetingcontext = new String();
        date_of_acquaintance = new String();
        events = new String();
    }
    
   
    
    public void setmetdate(String date){
        this.date_of_acquaintance = date;
    }
    
    public void setmeetcontext(String met){
        
       // try{
            if (met.length() > 100){
                throw new IndexOutOfBoundsException();
            }
            else {
                //try{
                    if (meetingcontext != null && meetingcontext.length() >= 0){
                        String appended = meetingcontext + met;
                        if (appended.length() > 100){
                            throw new IndexOutOfBoundsException();
                        }
                        else meetingcontext = appended;
                    }else
                        this.meetingcontext = met;
                    }
                    /*catch (IndexOutOfBoundsException e){
                        System.out.println("Error: You cannot add more than 100 characters ");
                        //System.exit(0);
                    }*/
                //System.arraycopy(met.toCharArray(),0,meetingcontext,0,met.length());
                //this.meetingcontext = met;
            }
       /* }catch(IndexOutOfBoundsException e){
            System.out.println("Error: You cannot add more than 100 characters ");
            //System.exit(0);
        }*/
               
    
    
    public void setevents(String event){
        //try{
            if (event.length() > 100){
                throw new IndexOutOfBoundsException();
            }
            else{
                //try{
                    if (events != null && events.length() >= 0){
                        String eventappend = events + event;
                        if (eventappend.length() > 100){
                            throw new IndexOutOfBoundsException();
                        }
                        else events = eventappend;
                    }else
                        this.events = event;
                /*}catch(IndexOutOfBoundsException e){
                     System.out.println("Error: You cannot add more than 100 characters ");
                     System.exit(0);
                    }*/
            }
       /* }catch(IndexOutOfBoundsException e){
            System.out.println("Error: You cannot add more than 100 characters ");
            //System.exit(0);
        }*/
    }
    
  
    
    public String getmetdate(){
        return this.date_of_acquaintance;
    }
    
    public String getmeetcontext(){
        if (this.meetingcontext == null){
            //System.out.println("Nothing to show! ");
            return null;
        }
        else
            return this.meetingcontext;//.toString();
    }
    
    public String getevents(){
        if (this.events == null){
            //System.out.println("Nothing to show! ");
            return null;
        }
        else
            return this.events;//.toString();
    }
    
}

