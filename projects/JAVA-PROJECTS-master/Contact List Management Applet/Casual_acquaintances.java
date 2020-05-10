import java.io.Serializable;

/**
 * Write a description of class Casual_acquaintances here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
@SuppressWarnings("serial")
public class Casual_acquaintances extends Contacts 
{
    private String met_when;
    private String met_where;
    private String meeting_circumstances;
    private String other_info;
    
    Casual_acquaintances(){
        
    	super();
        met_when = null;
        met_where = null;
        meeting_circumstances = null;
        other_info = null;
    }
    
    
    
    public void setmetwhen(String when){
       // try{
            if (when.length() > 100){
                throw new IndexOutOfBoundsException();
            }
            else {
                //try{
                   // if (met_when != null && met_when.length() >= 0){
                        String appended1 = when;//met_when + when;
                        if (appended1.length() > 100){
                            throw new IndexOutOfBoundsException();
                        }
                        else met_when = appended1;
                    //}else
                      //  this.met_when = when;
                    }
                   /* catch (IndexOutOfBoundsException e){
                        System.out.println("Error: You cannot add more than 100 characters ");
                        //System.exit(0);
                    }*/
                //System.arraycopy(met.toCharArray(),0,meetingcontext,0,met.length());
                //this.meetingcontext = met;
    	//}
        /*}catch(IndexOutOfBoundsException e){
            System.out.println("Error: You cannot add more than 100 characters ");
            //System.exit(0);
        }*/
    }
    
    
    public void setmetwhere(String where){
       // try{
            if (where.length() > 100){
                throw new IndexOutOfBoundsException();
            }
            else {
               // try{
                   // if (met_where != null && met_where.length() >= 0){
                        String appended2 = where;//met_where + where;
                        if (appended2.length() > 100){
                            throw new IndexOutOfBoundsException();
                        }
                        else met_where = appended2;
                   // }else
                     //   this.met_where = where;
                    }
                  /*  catch (IndexOutOfBoundsException e){
                        System.out.println("Error: You cannot add more than 100 characters ");
                        //System.exit(0);
                    }*/
                //System.arraycopy(met.toCharArray(),0,meetingcontext,0,met.length());
                //this.meetingcontext = met;
            //}
       /* }catch(IndexOutOfBoundsException e){
            System.out.println("Error: You cannot add more than 100 characters ");
            //System.exit(0);
        }*/
    }
    
    public void setcircumstance(String circum){
      //  try{
            if (circum.length() > 100){
                throw new IndexOutOfBoundsException();
            }
            else {
             //   try{
                   // if (meeting_circumstances != null && meeting_circumstances.length() >= 0){
                        String appended3 = circum;//meeting_circumstances + circum;
                        if (appended3.length() > 100){
                            throw new IndexOutOfBoundsException();
                        }
                        else meeting_circumstances = appended3;
                   // }else
                     //   this.meeting_circumstances = circum;
                   /* }
                    catch (IndexOutOfBoundsException e){
                        System.out.println("Error: You cannot add more than 100 characters ");
                        //System.exit(0);
                    }*/
                //System.arraycopy(met.toCharArray(),0,meetingcontext,0,met.length());
                //this.meetingcontext = met;
            }
      /*  }catch(IndexOutOfBoundsException e){
            System.out.println("Error: You cannot add more than 100 characters ");
            //System.exit(0);
        }*/
    }
    
    public void setinfo(String information){
        //try{
            if (information.length() > 100){
                throw new IndexOutOfBoundsException();
            }
            else {
               // try{
                  //  if (other_info != null && other_info.length() >= 0){
                        String appended4 = information;//other_info + information;
                        if (appended4.length() > 100){
                            throw new IndexOutOfBoundsException();
                        }
                        else other_info = appended4;
                    //}else
                      //  this.other_info = information;
                   /* }
                    catch (IndexOutOfBoundsException e){
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
    }
    
   
    public String getmetwhen(){
        if (this.met_when == null){
           // System.out.println("Nothing to show! ");
            return null;
        }
        else
            return this.met_when;
    }
    
    public String getmetwhere(){
        if (this.met_where == null){
            //System.out.println("Nothing to show! ");
            return null;
        }
        else
            return this.met_where;
    }
    
    public String getcircumstance(){
        if (this.meeting_circumstances == null){
          //  System.out.println("Nothing to show! ");
            return null;
        }
        else
            return this.meeting_circumstances;
    }
    
    public  String getinfo(){
        if (this.other_info == null){
           // System.out.println("Nothing to show! ");
            return null;
        }
        else
            return this.other_info;
    }
}
