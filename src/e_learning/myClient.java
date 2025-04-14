
package e_learning;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import java.io.File;

public class myClient {
    
    public static String fetch_index()
    {
        String url="http://localhost:9000/";
        try{
        HttpResponse<String> res=Unirest.get(url).asString();
            if(res.getStatus()==200)
            {
                return res.getBody();
            }
            else{
                return "server error";
            }
        }
        catch(Exception ex)
        {
            return ex.toString();
        }
    }
    public static String Login(String email,String pass)
    {
        String url="http://localhost:9000/Login";
        try{
        HttpResponse<String> res=Unirest.get(url)
                .queryString("email", email)
                .queryString("pass", pass).asString();
            if(res.getStatus()==200)
            {
                return res.getBody();
            }
            else{
                return "server error";
            }
        }
        catch(Exception ex)
        {
            return ex.toString();
        }
    }
    public static String SignUp(String email,String pass,String address,String mobile,File ph)
    {
        String url="http://localhost:9000/SignUp";
        try{
        HttpResponse<String> res=Unirest.post(url)
                .queryString("email", email)
                .queryString("pass", pass)
                .queryString("mobile",mobile)
                .queryString("address", address)
                .field("Ph", ph).asString();
            if(res.getStatus()==200)
            {
                return res.getBody();
            }
            else{
                return "server error";
            }
        }
        catch(Exception ex)
        {
            return ex.toString();
        }
        
    }

    /**
     *
     * @return
     */
    public static String fetchcat()
    {
        String url="http://localhost:9000/fetchcat";
        try{
        HttpResponse<String> res=Unirest.get(url)
                .asString();
            if(res.getStatus()==200)
            {
                return res.getBody();
            }
            else{
                return "server error";
            }
        }
        catch(Exception ex)
        {
            return ex.toString();
        }
    }
    public static String courses(String category)
    {
        try {
            HttpResponse<String> res=Unirest.get("http://localhost:9000/courses")
                    .queryString("category", category)
                    .asString();
            if(res.getStatus()==200)
            {
                return res.getBody();
            }
            else{
                return "server error";
            }
            
        } catch (Exception e) 
        {
            return e.toString();
        }
    }
    public static String fetchlectures(int course_id)
    {
        try {
            HttpResponse<String> res=Unirest.get("http://localhost:9000/fetchlectures")
                    .queryString("course_id", course_id)
                    .asString();
            if(res.getStatus()==200)
            {
                return res.getBody();
            }
            else{
                return "server error";
            }
        } catch (Exception e) {
            return e.toString();
        }
    }
  public static String fetchdetail(int id)
 {
     try
     {
        HttpResponse<String> res = Unirest.get("http://localhost:9000/fetchdetail")
               .queryString("id", id)
                .asString();
        
        if(res.getStatus() == 200)
        {
            return res.getBody();
        }
        else
        {
            return "Server Error";
        }
     }
     catch(Exception ex)
     {
         return ex.toString();
     }
 }
  public static String adminLogin(String username,String password)
  {
       try
     {
        HttpResponse<String> res = Unirest.get("http://localhost:9000/adminLogin")
               .queryString("username", username)
                .queryString("password",password)
                .asString();
        
        if(res.getStatus() == 200)
        {
            return res.getBody();
        }
        else
        {
            return "Server Error";
        }
     }
     catch(Exception ex)
     {
         return ex.toString();
     }
  }
  public static String addCategories(String category,File ph)
  {
      try
     {
        HttpResponse<String> res = Unirest.post("http://localhost:9000/addCategories")
               .queryString("category", category)
                .field("f1",ph)
                .asString();
        
        if(res.getStatus() == 200)
        {
            return res.getBody();
        }
        else
        {
            return "Server Error";
        }
     }
     catch(Exception ex)
     {
         return ex.toString();
     }
  }
  public static String fetchCat()
  {
      try
      {
        HttpResponse<String> res = Unirest.get("http://localhost:9000/fetchCat")
             .asString();
        
        if(res.getStatus() == 200)
        {
            return res.getBody();
        }
        else
        {
            return "Server Error";
        }
     }
     catch(Exception ex)
     {
         return ex.toString();
     }
  }
  public static String deletecat(String name)
  {
      try {
          HttpResponse<String> res=Unirest.get("http://localhost:9000/deletecat")
                  .queryString("name", name)
                  .asString();
          if(res.getStatus()==200)
          {
              return res.getBody();
          }
          else
          {
              return "Server Error";
          }
      } catch (Exception e) {
          return e.toString();
      }
  }
  public static String fetchCatgories()
  {
      try
      {
        HttpResponse<String> res = Unirest.get("http://localhost:9000/fetchCatgories")
             .asString();
        
        if(res.getStatus() == 200)
        {
            return res.getBody();
        }
        else
        {
            return "Server Error";
        }
     }
     catch(Exception ex)
     {
         return ex.toString();
     }
  }
}
