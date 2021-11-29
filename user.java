import java.sql.*;
import java.io.*;
import java.util.*;
public class user{
    public static String namadepan, namabelakang, id_ktp, tgl_lahir, username,no_rek, pin;
   
   public user(String namadepan, String namabelakang, String id_ktp, String tgl_lahir, String username, String pin){
    namadepan=this.namadepan;
    namabelakang=this.namabelakang;
    id_ktp=this.id_ktp;
    tgl_lahir=this.tgl_lahir;
    username=this.username;
    pin=this.pin;
   }
   
   user(){
       
   }
   
   public void adduser(){
              try{
       Class.forName("org.sqlite.JDBC");
    Connection k = DriverManager.getConnection("jdbc:sqlite:D:/Programming/OOP ATM/atm.db");
    Statement stat = k.createStatement();
    stat.executeUpdate("insert into user values('"+namadepan+"','"+namabelakang+"','"+id_ktp+"','"+tgl_lahir+"','"+username+"','"+pin+"')");
    stat.executeUpdate("insert into rekening values('"+username+"',"+no_rek+",0,0)");
} catch (Exception e){}    
   }
   
   public void setUsername(String un){
       username=un;
   }
   
   public String getUsername(){
       return username;
   }
   
   public void setNo_rek(String norek){
        no_rek=norek;
    }
   
   public String getNo_rek(){
       return no_rek;
   }
}