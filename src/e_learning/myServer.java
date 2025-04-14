package e_learning;

import com.vmm.JHTTPServer;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;


public class myServer extends JHTTPServer 
{
    Response res=null;
    public myServer(int port) throws IOException
    {
        super(port);
    }
    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files) 
     {
        if(uri.equals("/"))
        {
            String ans=Math.random()+"";
            res=new Response(HTTP_OK,"text/plain",ans);
            return res;
        }
        else if(uri.equals("/one"))
        {
          
             res=new Response(HTTP_OK,"text/plain","successfull");
            return res;
        }
        else if(uri.equals("/Login"))
        {
            String email=parms.getProperty("email");
            String pass=parms.getProperty("pass");
            try
            {
            ResultSet rs=DBLoader.executeQuery("select * from users where email='"+email+"' and password='"+pass+"'");
            if(rs.next())
            {
                String ans="success";
                 res=new Response(HTTP_OK,"text/plain",ans);
                 return res;
            }
            else{
                String ans="not match";
                 res=new Response(HTTP_OK,"text/plain",ans);
                 return res;
            }
            }
            catch(Exception ex)
            {
                
                ex.printStackTrace();
                
            }
        }
        else if(uri.equals("/SignUp"))
        {
            String email=parms.getProperty("email");
            String pass=parms.getProperty("pass");
            String mobile=parms.getProperty("mobile");
            String address=parms.getProperty("address");         
            // save file
            String photoname=saveFileOnServerWithRandomName(files,parms,"Ph","src/uploads/");
            try{
                 ResultSet rs=DBLoader.executeQuery("select * from users where email='"+email+"'");
                  if(rs.next())
                   {
                       String ans="exist";
                       res=new Response(HTTP_OK,"text/plain",ans);
                       return res;
                    }
                 else{
                      rs.moveToInsertRow();
                      rs.updateString("email", email);
                      
                      rs.updateString("password", pass);
                     
                      rs.updateString("mobile", mobile);
                      
                      rs.updateString("address", address);
                      
                      rs.updateString("photo", "src/uploads/"+photoname);
                      rs.insertRow();
                      
                      
                       res=new Response(HTTP_OK,"text/plain","success");
                       return res;
                     }   
            }
            catch(Exception ex)
            {
                
                ex.printStackTrace();
                
            }
        }
        else if(uri.equals("/fetchcat"))
        {String ans="";
            try 
            {
                ResultSet rs=DBLoader.executeQuery("select * from category");
                while(rs.next())
                {
                    String name=rs.getString("name");
                    String photo=rs.getString("photo");
                    String row=name+"$"+photo;
                    ans=ans+row+";;";
                    
                }
                System.out.println(ans);
               res= new Response(HTTP_OK,"text/plain",ans);
               return res;
            } 
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(uri.equals("/courses"))
        {
            String ans="";
            String category=parms.getProperty("category");
            try {
                ResultSet rs=DBLoader.executeQuery("select * from courses where category='"+category+"'");
                while(rs.next())
                {
                    int id=rs.getInt("id");
                    String name=rs.getString("name");
                    String photo=rs.getString("photo");
                    String row=id+"$"+name+"$"+photo;
                    ans=ans+row+";;";
                    
                }
                return new Response(HTTP_OK,"text/plain",ans);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(uri.equals("/fetchlectures"))
        {
            int course_id=Integer.parseInt(parms.getProperty("course_id"));
            String ans="";
            try {
                 ResultSet rs=DBLoader.executeQuery("select * from lectures where course_id="+course_id);
                 while(rs.next())
                {
                    int id=rs.getInt("id");
                    String name=rs.getString("name");
                    String photo=rs.getString("photo");
                    String row=id+"$"+name+"$"+photo;
                    ans=ans+row+";;";
                    //                     System.out.println(ans+"anmaolanmolanmol---anmolanmol-----anmol----anmol-----anmol-----anmol--");
 /* for(int i=0;i<10;i++)
  {
      System.out.println("*********");
  }*/
                }
                 //System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
                 return new Response(HTTP_OK,"text/plain",ans);
            } 
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        /*else if(uri.equals("/fetchdetail"))
         {
             String ans = "";
             
             int id = Integer.parseInt(parms.getProperty("id"));
             
             try
             {
                 ResultSet rs = DBLoader.executeQuery("select * from lectures where id="+id);
                 
                 if(rs.next())
                 {
                     int id1 = rs.getInt("id");
                     String name = rs.getString("name");
                     String description = rs.getString("description");
                     String duration = rs.getString("duration");
                     String photo = rs.getString("photo");
                     int course_id = rs.getInt("course_id");
                     String trailer = rs.getString("trailer");
                     String video = rs.getString("video");
                     
                     ans = id1+"$"+name+"$"+description+"$"+duration+"$"+photo+"$"+course_id+"$"+trailer+"$"+video;
                     System.out.println(ans+"anmaolanmolanmol---anmolanmol-----anmol----anmol-----anmol-----anmol--");
                 }
                 
                 res= new Response(HTTP_OK, "text/plain", ans);
                 return res;
             }
             catch(Exception ex)
             {
                 ex.printStackTrace();
             }
         }
        
        */
        
        
        
        
        
       else if(uri.equals("/fetchdetail"))
        {
            int id=Integer.parseInt(parms.getProperty("id"));
            String row="";
            try {
                 ResultSet rs=DBLoader.executeQuery("select * from lectures where id="+id);
                if(rs.next())
                {
                    int id1=rs.getInt("id");
                    String name=rs.getString("name");
                    String description=rs.getString("desciption");
                    String duration=rs.getString("duration");
                    String photo=rs.getString("photo");
                    int course_id=rs.getInt("course_id");
                    String trailer=rs.getString("trailer");
                    String video=rs.getString("video");
                    row=id1+"$"+name+"$"+description+"$"+duration+"$"+photo+"$"+course_id+"$"+trailer+"$"+video;
                    
                }
                 return new Response(HTTP_OK,"text/plain",row);
            } 
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
       else if(uri.equals("/adminLogin"))
       {
           String username=parms.getProperty("username");
           String password=parms.getProperty("password");
           try {
               ResultSet rs=DBLoader.executeQuery("select * from admin where username='"+username+"'and password='"+password+"'");
               if(rs.next())
               {
                   res= new Response(HTTP_OK,"text/plain","success");
                    return res;
               }
               else{
                    return new Response(HTTP_OK,"text/plain","server fail");
                }
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       else if(uri.equals("/addCategories"))
       {
            String category=parms.getProperty("category");
          String photoname=saveFileOnServerWithRandomName(files,parms,"f1","src/upload_admin/");
          
           try {
               ResultSet rs=DBLoader.executeQuery("select * from category where name='"+category+"'");
               if(rs.next())
               {
                   res= new Response(HTTP_OK,"text/plain","exist");
                    return res;
               }
               else{
                   /*
                   movetocurrentRow mean update the current row
                   moveToInsertRow mean insert in the new row int he sql table
                   
                   */
                   
                   rs.moveToInsertRow(); //go to new row
                   rs.updateString("name", category);
                   rs.updateString("photo","src/upload_admin"+photoname);
                   rs.insertRow();
                    return new Response(HTTP_OK,"text/plain","success");
                }
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       else if(uri.equals("/fetchCat"))
       {
           try {
                ResultSet rs=DBLoader.executeQuery("select * from category");
                String ans="";
                while(rs.next())
                {
                    String name=rs.getString("name");
                    String photo=rs.getString("photo");
                    String row=name+"$"+photo;
                    ans=ans+row+";;";
                }
                return new Response(HTTP_OK,"text/plain",ans);
                
           } catch (Exception e) {
                e.printStackTrace();
           }
       }
       else if(uri.equals("/deletecat"))
       {
           String name=parms.getProperty("name");
           try {
               ResultSet rs=DBLoader.executeQuery("select * from category where name='"+name+"'");
               if(rs.next())
               {
                   rs.deleteRow();
                   return new Response(HTTP_OK,"text/plain","Success");

               }
           } catch (Exception e) {
                 e.printStackTrace();
           }
       }
       else if(uri.equals("/fetchCatgories"))
       {
            try {
                ResultSet rs=DBLoader.executeQuery("select * from category");
                String ans="";
                while(rs.next())
                {
                    String name=rs.getString("name");
                    ans=ans+name+";;";
                }
                return new Response(HTTP_OK,"text/plain",ans);
                
           } catch (Exception e) {
                e.printStackTrace();
           }
       }
        else{
            res=new Response(HTTP_OK,"text/plain","INVALID URL");
            return res;

        }
        return null;
    }
    
//    start our server by the GUI without directly call the main method
   /* public static void main(String[] args) 
    {
        try 
        {
            myServer obj=new myServer(9000);
            Thread.sleep(1000000000);// bez we want are server to be active for a specfic time
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
        
    }*/
}
