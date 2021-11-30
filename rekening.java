import java.util.*;
import java.sql.*;
import java.io.IOException;
public class rekening extends user{
    private float saldo_tabungan, saldo_deposit;
    
    public void getMutasi(){
    try{
        Class.forName("org.sqlite.JDBC");
    Connection k = DriverManager.getConnection("jdbc:sqlite:D:/Programming/OOP ATM/atm.db");
    Statement stat = k.createStatement();
    System.out.println(getNo_rek());
    ResultSet set = stat.executeQuery("select * from transaksi where no_rek='"+getNo_rek()+"';");
    System.out.println("Nomor Rekening \t Tanggal Transaksi \t Jumlah Transaksi \t Rekening Tujuan");
    while (set.next()){
        System.out.println(set.getString("no_rek")+"\t"+set.getString("tgl_transaksi")+"\t"+set.getString("jumlah")+"\t"+set.getString("no_rek_tujuan"));
    }
}catch (Exception e){System.out.println("Error: "+e.getMessage());}
}
    
    public void getSaldo(){
        try{
        Class.forName("org.sqlite.JDBC");
    Connection k = DriverManager.getConnection("jdbc:sqlite:D:/Programming/OOP ATM/atm.db");
    Statement stat = k.createStatement();
    ResultSet set = stat.executeQuery("select no_rek, saldo_tabungan, saldo_deposit from rekening where username='"+getUsername()+"';");
    System.out.println("Nomor Rekening\tSaldo Tabungan\tSaldo Deposit");
    while (set.next()){
        System.out.println(set.getString("no_rek")+"\t"+"Rp. "+set.getFloat("saldo_tabungan")+"\t Rp. "+set.getFloat("saldo_deposit"));
    }
}catch (Exception e){System.out.println("Error: "+e.getMessage());}
    }
    
    public void getSaldoTabungan(){
    try{
        Class.forName("org.sqlite.JDBC");
    Connection k = DriverManager.getConnection("jdbc:sqlite:D:/Programming/OOP ATM/atm.db");
    Statement stat = k.createStatement();
    ResultSet set = stat.executeQuery("select saldo_tabungan from rekening where username='"+getUsername()+"';");
    while (set.next()){
        saldo_tabungan=set.getFloat("saldo_tabungan");
        System.out.println("Saldo Tabungan anda berjumlah Rp. "+saldo_tabungan);
    }
}catch (Exception e){System.out.println("Error: "+e.getMessage());}
    }
    
    public float getSaldoDeposit(){
    try{
        Class.forName("org.sqlite.JDBC");
    Connection k = DriverManager.getConnection("jdbc:sqlite:D:/Programming/OOP ATM/atm.db");
    Statement stat = k.createStatement();
    ResultSet set = stat.executeQuery("select saldo_deposit from rekening where username='"+getUsername()+"';");
    while (set.next()){
        saldo_deposit=set.getFloat("saldo_deposit");
        System.out.println("Saldo Tabungan anda berjumlah Rp. "+saldo_deposit);
    }
}catch (Exception e){System.out.println("Error: "+e.getMessage());}
return saldo_deposit;
    }
    
}