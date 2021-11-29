import java.util.*;
import java.sql.*;
import java.io.IOException;
public class rekening extends user{
    
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
}catch (Exception e){}
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
}catch (Exception e){}
    }
    
}