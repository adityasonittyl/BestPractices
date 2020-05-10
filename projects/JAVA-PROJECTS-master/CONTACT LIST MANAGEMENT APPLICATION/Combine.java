



/**
 * Write a description of class Combine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class Combine
{
    
    @SuppressWarnings("unchecked")
	public static void main(String[] args){
        
        /*ArrayList<Relatives> relatives = new ArrayList<Relatives>();
        ArrayList<Personal_friends> personalfriends = new ArrayList<Personal_friends>();
        ArrayList<Professional_friends> professionalfriends = new ArrayList<Professional_friends>();
        ArrayList<Casual_acquaintances> casualacquaintances = new ArrayList<Casual_acquaintances>();
        */
    	
    	ArrayList<Contacts> relatives = new ArrayList<Contacts>();
        ArrayList<Contacts> personalfriends = new ArrayList<Contacts>();
        ArrayList<Contacts> professionalfriends = new ArrayList<Contacts>();
        ArrayList<Contacts> casualacquaintances = new ArrayList<Contacts>();
        
    	
    	
        File file = new File("save.ser");
        try{
            if (!file.exists()){//if file did not exist create new file
                file.createNewFile();
            }
            else{//else read from already existing file
                try{
                    FileInputStream fout = new FileInputStream(file);
                    ObjectInputStream oos = new ObjectInputStream(fout);
                    try{
                        //FileInputStream fout = new FileInputStream(file);
                        //ObjectInputStream oos = new ObjectInputStream(fout);
                        relatives = (ArrayList<Contacts>)oos.readObject();
                        personalfriends = (ArrayList<Contacts>)oos.readObject();
                        professionalfriends = (ArrayList<Contacts>)oos.readObject();
                        casualacquaintances = (ArrayList<Contacts>)oos.readObject();
                       // System.out.println("Finished Reading! ");
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }finally{
                        try{
                            if (oos != null){
                                oos.close();
                                fout.close();
                            }
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        
        while (true){
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("1.Create Acquaintance");
        System.out.println("2.Delete Acquaintance");
        System.out.println("3.Display Acquaintance List");
        System.out.println("4.Search Acquaintance");
        System.out.println("5.Exit");
        
        Scanner scan = new Scanner(System.in);
        String opt = scan.nextLine();
        
        switch(opt){
            case ("1"):{//create acquaintance
                System.out.println("1.Relative 2.Personal friend 3.Professional friend 4.Casual acquaintance");
                String opt1 = scan.nextLine();
                switch (opt1){
                    case ("1"):{//create relative object
                        
                        
                        Relatives newrel = new Relatives();
                        System.out.println(" Name: ");
                        newrel.setname(scan.nextLine());
                        System.out.println(" Mobile Number: ");
                        newrel.setmobnum(scan.nextLine());
                        System.out.println(" Email id: ");
                        newrel.setemailid(scan.nextLine());
                        System.out.println(" Birthday: ");
                        newrel.setbirthday(scan.nextLine());
                        System.out.println(" Date on which you met the latest time: ");
                        newrel.setdate(scan.nextLine());
                        
                        relatives.add(newrel);
                        
                        FileOutputStream fout = null;
                        ObjectOutputStream oos = null;
                        try{
                            fout = new FileOutputStream("save.ser");
                            oos = new ObjectOutputStream(fout);
                            oos.writeObject(relatives);
                            oos.writeObject(personalfriends);
                            oos.writeObject(professionalfriends);
                            oos.writeObject(casualacquaintances);
                        }catch(Exception e){
                            e.printStackTrace();
                        }finally{
                            try{
                                if (oos != null){
                                    oos.close();
                                    fout.close();
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                        
                        System.out.println("Added successfully! ");
                        
                        break;
                    }
                    case ("2"):{//create personal_friend object
                        
                        Personal_friends newperfr = new Personal_friends();
                        
                        System.out.println("Name:");
                        newperfr.setname(scan.nextLine());
                        System.out.println("Mobile number: ");
                        newperfr.setmobnum(scan.nextLine());
                        System.out.println("Email id:");
                        newperfr.setemailid(scan.nextLine());
                        System.out.println("Date of acquaintance:");
                        newperfr.setmetdate(scan.nextLine());
                        while (true){
                        	System.out.println("How you met:");
                        	try{
                        		newperfr.setmeetcontext(scan.nextLine());
                        	}catch (IndexOutOfBoundsException e){
                        		System.out.println("You cannot add more than 100 characters..Try again..");
                        		continue;
                        	}
                        	break;
                        }
                        while (true){
                        	System.out.println("Any notable events?");
                        	try{
                        		newperfr.setevents(scan.nextLine());
                        	}catch(IndexOutOfBoundsException e){
                        		System.out.println("You cannot add more than 100 characters..Try again..");
                        		continue;
                        	}
                        	break;
                        }
                        
                        personalfriends.add(newperfr);
                        
                        FileOutputStream fout = null;
                        ObjectOutputStream oos = null;
                        try{
                            fout = new FileOutputStream("save.ser");
                            oos = new ObjectOutputStream(fout);
                            oos.writeObject(relatives);
                            oos.writeObject(personalfriends);
                            oos.writeObject(professionalfriends);
                            oos.writeObject(casualacquaintances);
                        }catch(Exception e){
                            e.printStackTrace();
                        }finally{
                            try{
                                if (oos != null){
                                    oos.close();
                                    fout.close();
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                        
                        System.out.println("Added successfully! ");
                        
                        break;
                    }
                    case ("3"):{//create professional_friend object
                                                
                        Professional_friends newproffr = new Professional_friends();
                        
                         System.out.println("Name: ");
                         newproffr.setname(scan.nextLine());
                         System.out.println("Mobile number: ");
                         newproffr.setmobnum(scan.nextLine());
                         System.out.println("Email id: ");
                         newproffr.setemailid(scan.nextLine());
                         while (true){
                        	 System.out.println("Common interests: ");
                        	 try{
                        		 newproffr.setinterests(scan.nextLine());
                        	 }catch(IndexOutOfBoundsException e){
                        		 System.out.println("You cannot add more than 100 characters..Try again..");
                         		continue;
                         	}
                         	break;
                         }
                        	 
                         
                         professionalfriends.add(newproffr);                  
                         
                         FileOutputStream fout = null;
                         ObjectOutputStream oos = null;
                         try{
                             fout = new FileOutputStream("save.ser");
                             oos = new ObjectOutputStream(fout);
                             oos.writeObject(relatives);
                             oos.writeObject(personalfriends);
                             oos.writeObject(professionalfriends);
                             oos.writeObject(casualacquaintances);
                         }catch(Exception e){
                             e.printStackTrace();
                         }finally{
                             try{
                                 if (oos != null){
                                     oos.close();
                                     fout.close();
                                 }
                             }catch(Exception e){
                                 e.printStackTrace();
                             }
                         }
                         
                         System.out.println("Added succesfully!");
                        
                        break;
                    }
                    case ("4"):{//create casual_acquaintance object
                        
                        
                        Casual_acquaintances newcasacq = new Casual_acquaintances();
                        
                        System.out.println("Name: ");
                        newcasacq.setname(scan.nextLine());
                        System.out.println("Mobile number: ");
                        newcasacq.setmobnum(scan.nextLine());
                        System.out.println("Email id: ");
                        newcasacq.setemailid(scan.nextLine());
                        while (true){
                        	System.out.println("When did you meet? ");
                        	try{
                        		newcasacq.setmetwhen(scan.nextLine());
                        	}catch(IndexOutOfBoundsException e){
                       		 System.out.println("You cannot add more than 100 characters..Try again..");
                        		continue;
                        	}
                        	break;
                        }
                        while(true){
                        	System.out.println("Where did you meet? ");
                        	try{
                        		newcasacq.setmetwhere(scan.nextLine());
                        	}catch(IndexOutOfBoundsException e){
                       		 System.out.println("You cannot add more than 100 characters..Try again..");
                        		continue;
                        	}
                        	break;
                        }
                        while(true){
                        	System.out.println("Under what circumstances did you meet? ");
                        	try{
                        		newcasacq.setcircumstance(scan.nextLine());
                        	}catch(IndexOutOfBoundsException e){
                       		 System.out.println("You cannot add more than 100 characters..Try again..");
                        		continue;
                        	}
                        	break;
                        }
                        while(true){
                        	System.out.println("Any other information?");
                        	try{
                        		newcasacq.setinfo(scan.nextLine());
                        	}catch(IndexOutOfBoundsException e){
                       		 System.out.println("You cannot add more than 100 characters..Try again..");
                        		continue;
                        	}
                        	break;
                        }
                        
                        casualacquaintances.add(newcasacq);
                        
                        FileOutputStream fout = null;
                        ObjectOutputStream oos = null;
                        try{
                            fout = new FileOutputStream("save.ser");
                            oos = new ObjectOutputStream(fout);
                            oos.writeObject(relatives);
                            oos.writeObject(personalfriends);
                            oos.writeObject(professionalfriends);
                            oos.writeObject(casualacquaintances);
                        }catch(Exception e){
                            e.printStackTrace();
                        }finally{
                            try{
                                if (oos != null){
                                    oos.close() ;
                                    fout.close();
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                        
                        System.out.println("Added successfully! ");
                        
                        break;
                    }
                }
                break;
            }
            case ("2"):{//delete acquaintance
                System.out.println("1.Relative 2.Personal friend 3.Professional friend 4.Casual acquaintance");
                String opt2 = scan.nextLine();
                switch (opt2){
                    case ("1"):{//delete relative_object
                        System.out.println("Name: ");
                        String name = scan.nextLine();
                        
                        boolean delflag = false;
                        ListIterator<Contacts> it = relatives.listIterator();
                        while (it.hasNext()){
                            Relatives temprel = (Relatives) it.next();
                            if (temprel.getname().equals(name)){
                                relatives.remove(temprel);
                                System.out.println("Deleted successfully! ");
                                
                                FileOutputStream fout = null;
                                ObjectOutputStream oos = null;
                                try{
                                    fout = new FileOutputStream("save.ser");
                                    oos = new ObjectOutputStream(fout);
                                    oos.writeObject(relatives);
                                    oos.writeObject(personalfriends);
                                    oos.writeObject(professionalfriends);
                                    oos.writeObject(casualacquaintances);
                                }catch(Exception e){
                                    e.printStackTrace();
                                }finally{
                                    try{
                                        if (oos != null){
                                            oos.close();
                                            fout.close();
                                        }
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }
                                }
                                
                                delflag = true;
                                break;
                            }
                        }
                        if (!delflag){
                            System.out.println("No relative's record is available with this name.");
                        }
                        
                        break;
                    }
                    case ("2"):{//delete personal_friend object
                        System.out.println("Name:");
                        String name = scan.nextLine();
                        
                        
                        boolean delflag = false;
                        ListIterator<Contacts> it = personalfriends.listIterator();
                        while (it.hasNext()){
                            Personal_friends temp = (Personal_friends) it.next();
                            if (temp.getname().equals(name)){
                                personalfriends.remove(temp);
                                System.out.println("Deleted successfully! ");
                                
                                FileOutputStream fout = null;
                                ObjectOutputStream oos = null;
                                try{
                                    fout = new FileOutputStream("save.ser");
                                    oos = new ObjectOutputStream(fout);
                                    oos.writeObject(relatives);
                                    oos.writeObject(personalfriends);
                                    oos.writeObject(professionalfriends);
                                    oos.writeObject(casualacquaintances);
                                }catch(Exception e){
                                    e.printStackTrace();
                                }finally{
                                    try{
                                        if (oos != null){
                                            oos.close();
                                            fout.close();
                                        }
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }
                                }
                                
                                delflag = true;
                                break;
                            }
                        }
                        if (!delflag){
                            System.out.println("No personal friend's record is available with this name.");
                        }
                        
                        break;
                    }
                    case ("3"):{//delete professional_friend object
                        System.out.println("Name:");
                        String name = scan.nextLine();
                                                                       
                        boolean delflag = false;
                        ListIterator<Contacts> it = professionalfriends.listIterator();
                        while (it.hasNext()){
                            Professional_friends temp = (Professional_friends) it.next();
                            if (temp.getname().equals(name)){
                                professionalfriends.remove(temp);
                                System.out.println("Deleted successfully! ");
                                
                                FileOutputStream fout = null;
                                ObjectOutputStream oos = null;
                                try{
                                    fout = new FileOutputStream("save.ser");
                                    oos = new ObjectOutputStream(fout);
                                    oos.writeObject(relatives);
                                    oos.writeObject(personalfriends);
                                    oos.writeObject(professionalfriends);
                                    oos.writeObject(casualacquaintances);
                                }catch(Exception e){
                                    e.printStackTrace();
                                }finally{
                                    try{
                                        if (oos != null){
                                            oos.close();
                                            fout.close();
                                        }
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }
                                }
                                
                                delflag = true;
                                break;
                            }
                        }
                        if (!delflag){
                            System.out.println("No professional friend's record is available with this name.");
                        }
                    
                        break;
                    }
                    case ("4"):{// delete casual_acquaintance object
                        System.out.println("Name:");
                        String name = scan.nextLine();
                                                
                        boolean delflag = false;
                        ListIterator<Contacts> it = casualacquaintances.listIterator();
                        while (it.hasNext()){
                            Casual_acquaintances temp = (Casual_acquaintances) it.next();
                            if (temp.getname().equals(name)){
                                casualacquaintances.remove(temp);
                                System.out.println("Deleted successfully! ");
                                
                                FileOutputStream fout = null;
                                ObjectOutputStream oos = null;
                                try{
                                    fout = new FileOutputStream("save.ser");
                                    oos = new ObjectOutputStream(fout);
                                    oos.writeObject(relatives);
                                    oos.writeObject(personalfriends);
                                    oos.writeObject(professionalfriends);
                                    oos.writeObject(casualacquaintances);
                                }catch(Exception e){
                                    e.printStackTrace();
                                }finally{
                                    try{
                                        if (oos != null){
                                            oos.close();
                                            fout.close();
                                        }
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }
                                }
                                
                                delflag = true;
                                break;
                            }
                        }
                        if (!delflag){
                            System.out.println("No casual acquaintance's record is available with this name.");
                        }
                    
                        break;
                    }
                }
                
                break;
            }
            case ("3"):{//display list
                System.out.println("1.Relative 2.Personal friend 3.Professional friend 4.Casual acquaintance 5.Entire list");
                String opt3 = scan.nextLine();
                
                switch (opt3){
                    case ("1"):{//display list of relatives
                        System.out.println("\t------------------Relatives------------------------");
                        
                        ListIterator<Contacts> it1 = relatives.listIterator();
                        while (it1.hasNext()){
                            Relatives temp1 = (Relatives) it1.next();
                            System.out.println("------------------------------------------------------------");
                            System.out.println("Name: "+temp1.getname());
                            System.out.println("Date of Birth: "+temp1.getbirthday());
                            System.out.println("Mobile Number: "+temp1.getmobnum());
                            System.out.println("Email id: "+temp1.getemailid());
                            System.out.println("Last met on: "+temp1.getdate());
                            System.out.println("------------------------------------------------------------");
                        }
                                                                                             
                        break;
                    }
                    case ("2"):{//display list of personal friends
                        System.out.println("\t-------------------Personal friends------------------------");
                        
                        ListIterator<Contacts> it2 = personalfriends.listIterator();
                        while (it2.hasNext()){
                            Personal_friends temp2 = (Personal_friends) it2.next();
                            System.out.println("------------------------------------------------------------");
                            System.out.println("Name: "+temp2.getname());
                            System.out.println("Mobile number: "+temp2.getmobnum());
                            System.out.println("Email id: "+temp2.getemailid());
                            System.out.println("Meeting Date: "+temp2.getmetdate());
                            System.out.println("Meeting context: "+temp2.getmeetcontext());
                            System.out.println("Notable events: "+temp2.getevents());
                            System.out.println("------------------------------------------------------------");
                        }
                        
                        break;
                    }
                    case ("3"):{//display list of professional friends
                        System.out.println("\t-------------------Professional friends------------------------");
                        
                        ListIterator<Contacts> it3 = professionalfriends.listIterator();
                        while (it3.hasNext()){
                            Professional_friends temp3 = (Professional_friends) it3.next();
                            System.out.println("------------------------------------------------------------");    
                            System.out.println("Name: "+temp3.getname());
                            System.out.println("Mobile number: "+temp3.getmobnum());
                            System.out.println("Email id: "+temp3.getemailid());
                            System.out.println("Common interests: "+temp3.getinterests());
                            System.out.println("------------------------------------------------------------");    
                            
                        }
                                         
                        break;
                    }
                    case ("4"):{//display list of casual acquaintances
                        System.out.println("\t-------------------Casual Acquaintances------------------------");
                        
                        ListIterator<Contacts> it4 = casualacquaintances.listIterator();
                        while (it4.hasNext()){
                            Casual_acquaintances temp4 = (Casual_acquaintances) it4.next();
                            System.out.println("------------------------------------------------------------");
                            System.out.println("Name: "+temp4.getname());
                            System.out.println("Mobile number: "+temp4.getmobnum());
                            System.out.println("Email id: "+temp4.getemailid());
                            System.out.println("Met when: "+temp4.getmetwhen());
                            System.out.println("Met where: "+temp4.getmetwhere());
                            System.out.println("Meeting circumstances: "+temp4.getcircumstance());
                            System.out.println("Other information: "+temp4.getinfo());
                            System.out.println("------------------------------------------------------------");    
                           
                        }
                        
                        break;
                    }
                    case ("5"):{//display list of entire list
                        System.out.println("\t------------------Relatives------------------------");
                        
                        ListIterator<Contacts> it1 = relatives.listIterator();
                        while (it1.hasNext()){
                            Relatives temp1 = (Relatives) it1.next();
                            System.out.println("------------------------------------------------------------");
                            System.out.println("Name: "+temp1.getname());
                            System.out.println("Date of Birth: "+temp1.getbirthday());
                            System.out.println("Mobile Number: "+temp1.getmobnum());
                            System.out.println("Email id: "+temp1.getemailid());
                            System.out.println("Last met on: "+temp1.getdate());
                            System.out.println("------------------------------------------------------------");
                        }
                        
                         System.out.println("\t-------------------Personal friends------------------------");
                        
                        ListIterator<Contacts> it2 = personalfriends.listIterator();
                        while (it2.hasNext()){
                            Personal_friends temp2 = (Personal_friends) it2.next();
                            System.out.println("------------------------------------------------------------");
                            System.out.println("Name: "+temp2.getname());
                            System.out.println("Mobile number: "+temp2.getmobnum());
                            System.out.println("Email id: "+temp2.getemailid());
                            System.out.println("Meeting Date: "+temp2.getmetdate());
                            System.out.println("Meeting context: "+temp2.getmeetcontext());
                            System.out.println("Notable events: "+temp2.getevents());
                            System.out.println("------------------------------------------------------------");
                        }
                        
                        System.out.println("\t-------------------Professional friends------------------------");
                        
                        ListIterator<Contacts> it3 = professionalfriends.listIterator();
                        while (it3.hasNext()){
                            Professional_friends temp3 = (Professional_friends) it3.next();
                            System.out.println("------------------------------------------------------------");    
                            System.out.println("Name: "+temp3.getname());
                            System.out.println("Mobile number: "+temp3.getmobnum());
                            System.out.println("Email id: "+temp3.getemailid());
                            System.out.println("Common interests: "+temp3.getinterests());
                            System.out.println("------------------------------------------------------------");    
                            
                        }
                        
                        System.out.println("\t-------------------Casual Acquaintances------------------------");
                        
                        ListIterator<Contacts> it4 = casualacquaintances.listIterator();
                        while (it4.hasNext()){
                            Casual_acquaintances temp4 = (Casual_acquaintances) it4.next();
                            System.out.println("------------------------------------------------------------");
                            System.out.println("Name: "+temp4.getname());
                            System.out.println("Mobile number: "+temp4.getmobnum());
                            System.out.println("Email id: "+temp4.getemailid());
                            System.out.println("Met when: "+temp4.getmetwhen());
                            System.out.println("Met where: "+temp4.getmetwhere());
                            System.out.println("Meeting circumstances: "+temp4.getcircumstance());
                            System.out.println("Other information: "+temp4.getinfo());
                            System.out.println("------------------------------------------------------------");    
                           
                        }
                        
                        break;
                    }
                         
                }
                
                break;
            }
            case ("4"):{//search acquaintance by name
                System.out.println("Name to search for: ");
                String searchname = scan.nextLine();
                
                boolean searchflag = false;
                
                ListIterator<Contacts> it1 = relatives.listIterator();
                while (it1.hasNext()){
                    Relatives temp1 = (Relatives) it1.next();
                    if (temp1.getname().equals(searchname)){
                            System.out.println("Relative: ");
                            System.out.println("Name: "+temp1.getname());
                            System.out.println("Date of Birth: "+temp1.getbirthday());
                            System.out.println("Mobile Number: "+temp1.getmobnum());
                            System.out.println("Email id: "+temp1.getemailid());
                            System.out.println("Last met on: "+temp1.getdate());
                            System.out.println("------------------------------------------------------------");
                            searchflag = true;
                        }
                }
                
                ListIterator<Contacts> it2 = personalfriends.listIterator();
                        while (it2.hasNext()){
                            Personal_friends temp2 = (Personal_friends) it2.next();
                            if (temp2.getname().equals(searchname)){
                            System.out.println("Personal friend:");
                            System.out.println("Name: "+temp2.getname());
                            System.out.println("Mobile number: "+temp2.getmobnum());
                            System.out.println("Email id: "+temp2.getemailid());
                            System.out.println("Meeting Date: "+temp2.getmetdate());
                            System.out.println("Meeting context: "+temp2.getmeetcontext());
                            System.out.println("Notable events: "+temp2.getevents());
                            System.out.println("------------------------------------------------------------");
                            searchflag = true;
                        }
                    }
                        
                ListIterator<Contacts> it3 = professionalfriends.listIterator();
                        while (it3.hasNext()){
                            Professional_friends temp3 = (Professional_friends) it3.next();
                            if (temp3.getname().equals(searchname)){
                                                           
                            System.out.println("Professional friend:");    
                            System.out.println("Name: "+temp3.getname());
                            System.out.println("Mobile number: "+temp3.getmobnum());
                            System.out.println("Email id: "+temp3.getemailid());
                            System.out.println("Common interests: "+temp3.getinterests());
                            System.out.println("------------------------------------------------------------");    
                            searchflag = true;
                        }
                    }
                        
                 ListIterator<Contacts> it4 = casualacquaintances.listIterator();
                        while (it4.hasNext()){
                            Casual_acquaintances temp4 = (Casual_acquaintances) it4.next();
                            if(temp4.getname().equals(searchname)){
                            System.out.println("Casual acquaintance:");
                            System.out.println("Name: "+temp4.getname());
                            System.out.println("Mobile number: "+temp4.getmobnum());
                            System.out.println("Email id: "+temp4.getemailid());
                            System.out.println("Met when: "+temp4.getmetwhen());
                            System.out.println("Met where: "+temp4.getmetwhere());
                            System.out.println("Meeting circumstances: "+temp4.getcircumstance());
                            System.out.println("Other information: "+temp4.getinfo());
                            System.out.println("------------------------------------------------------------");    
                            searchflag = true;
                        }
                    }
                
                
                
                           
                if (searchflag == false)
                    System.out.println("No such record exists! ");
                break;
            }
            case("5"):{//exit
                System.out.println("Thank you! ");
                
                FileOutputStream fout = null;
                ObjectOutputStream oos = null;
                try{
                    fout = new FileOutputStream("save.ser");
                    oos = new ObjectOutputStream(fout);
                    oos.writeObject(relatives);
                    oos.writeObject(personalfriends);
                    oos.writeObject(professionalfriends);
                    oos.writeObject(casualacquaintances);
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    try{
                        if (oos != null){
                            oos.close();
                            fout.close();
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                
                System.exit(0);
            }
        }
        
    }
    
}
}
