import java.io.Serializable;

/**
 * Write a description of class Professional_friends here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
@SuppressWarnings("serial")
public class Professional_friends extends Contacts 
{
    
    private String common_interests;
    
    Professional_friends(){
        
    	super();
        common_interests = new String();
    }
    
   
    public void setinterests(String interests){
        //try{
            if (interests.length() > 100){
                throw new IndexOutOfBoundsException();
            }
            else {
          //      try{
                   // if (common_interests != null && common_interests.length() > 0){
                        String appendedinterests = interests;//common_interests + interests;
                        if (appendedinterests.length() > 100){
                            throw new IndexOutOfBoundsException();
                        }
                        else common_interests = appendedinterests;
                   // }else
                     //   this.common_interests = interests;
            /*        }
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
    
   
    public String getinterests(){
         if (this.common_interests == null){
            System.out.println("Nothing to show! ");
            return null;
        }
        else
            return this.common_interests;
    }
}
