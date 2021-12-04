import java.sql.*;
import java.io.*;
import java.util.*;
public class user implements bank{
    private static String namadepan, namabelakang, id_ktp, tgl_lahir, username,no_rek, pin, ktpdb, usernamedb;
   
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
    ResultSet set = stat.executeQuery("select username from user where username='"+getUsername()+"';");
    while (set.next()){
    usernamedb=set.getString("username");
    }
    stat.executeQuery("select id_ktp from user where id_ktp='"+getId_ktp()+"';");
    while (set.next()){
    ktpdb=set.getString("id_ktp");
    }
    if(getUsername().equals(usernamedb) || getId_ktp().equals(ktpdb)){
    System.out.println("Username/ID KTP Sudah terpakai!");
    } else  {
    stat.executeUpdate("insert into user values('"+getNamadepan()+"','"+getNamabelakang()+"','"+getTgl_lahir()+"','"+getId_ktp()+"','"+getUsername()+"','"+getPin()+"')");
    stat.executeUpdate("insert into rekening values('"+getUsername()+"',"+getNo_rek()+",0,0)");
    System.out.println(getNamadepan()+", anda sudah terdaftar di Bank OOP Memoria");
    System.out.println("Akun anda bernomor rekening "+getNo_rek());
    }
} catch (Exception e){}    
   }
   
   public void setNamadepan(String namad){
       namadepan=namad;
   }
   
   public String getNamadepan(){
       return namadepan;
   }
   
   public void setNamabelakang(String namab){
       namabelakang=namab;
   }
   
   public String getNamabelakang(){
       return namabelakang;
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
   
   public void setId_ktp(String ktp){
        id_ktp=ktp;
    }
   
   public String getId_ktp(){
       return id_ktp;
   }
   
   public void setTgl_lahir(String tgl){
        tgl_lahir=tgl;
    }
   
   public String getTgl_lahir(){
       return tgl_lahir;
   }
   
   public void setPin(String p){
        pin=p;
    }
   
   public String getPin(){
       return pin;
   }
}