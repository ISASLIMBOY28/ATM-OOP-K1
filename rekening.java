import java.util.*;
import java.sql.*;
import java.io.IOException;
public class rekening extends user{
    private float saldo_tabungan, saldo_deposit;
    private String tgl_awal, tgl_akhir;
    
    public void getMutasi(){
    try{
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------------------------------------------");
        System.out.println("Tanggal dengan ketikan YYYY-MM-DD");
        System.out.print("Tanggal Transaksi Dari   : ");
        tgl_awal=sc.next();
        System.out.print("Tanggal Transaksi Sampai : ");
        tgl_akhir=sc.next();
        System.out.println("------------------------------------------------------");
        Class.forName("org.sqlite.JDBC");
    Connection k = DriverManager.getConnection("jdbc:sqlite:D:/OOP ATM/atm.db");
    Statement stat = k.createStatement();
    ResultSet set = stat.executeQuery("select * from transaksi where no_rek='"+getNo_rek()+"' and tanggal between '"+tgl_awal+"' and '"+tgl_akhir+"';");
    while (set.next()){
        System.out.println("Tanggal       : "+set.getString("tanggal"));
        System.out.println("Jenis         : "+set.getString("jenis"));
        System.out.println("Jumlah        : Rp. "+String.format("%,.2f",set.getFloat("jumlah")));
        System.out.println("No. Rekening  : "+set.getString("no_rek_tujuan"));
        System.out.println("------------------------------------------------------");    
    }
}catch (Exception e){System.out.println("Error: "+e.getMessage());}
}
    
    public void getSaldo(){
        try{
        Class.forName("org.sqlite.JDBC");
    Connection k = DriverManager.getConnection("jdbc:sqlite:D:/OOP ATM/atm.db");
    Statement stat = k.createStatement();
    ResultSet set = stat.executeQuery("select no_rek, saldo_tabungan, saldo_deposit from rekening where username='"+getUsername()+"';");
    System.out.println("------------------------------------------------------");
    while (set.next()){
        System.out.println(set.getString("no_rek"));
        System.out.println("Jumlah Saldo Tabungan  : Rp. "+String.format("%,.2f",set.getFloat("saldo_tabungan")));
        System.out.println("Jumlah Saldo Deposit   : Rp. "+String.format("%,.2f",set.getFloat("saldo_deposit")));
        System.out.println("------------------------------------------------------");
    }
}catch (Exception e){System.out.println("Error: "+e.getMessage());}
    }
    
    public void getSaldoTabungan(){
    try{
        Class.forName("org.sqlite.JDBC");
    Connection k = DriverManager.getConnection("jdbc:sqlite:D:/OOP ATM/atm.db");
    Statement stat = k.createStatement();
    ResultSet set = stat.executeQuery("select saldo_tabungan from rekening where username='"+getUsername()+"';");
    while (set.next()){
        saldo_tabungan=set.getFloat("saldo_tabungan");
        System.out.println("Saldo Tabungan anda berjumlah Rp. "+String.format("%,.2f",saldo_tabungan));
    }
}catch (Exception e){System.out.println("Error: "+e.getMessage());}
    }
    
    public float getSaldoDeposit(){
    try{
        Class.forName("org.sqlite.JDBC");
    Connection k = DriverManager.getConnection("jdbc:sqlite:D:/OOP ATM/atm.db");
    Statement stat = k.createStatement();
    ResultSet set = stat.executeQuery("select saldo_deposit from rekening where username='"+getUsername()+"';");
    while (set.next()){
        saldo_deposit=set.getFloat("saldo_deposit");
        System.out.println("Saldo Deposito anda berjumlah Rp. "+String.format("%,.2f",saldo_deposit));
    }
}catch (Exception e){System.out.println("Error: "+e.getMessage());}
return saldo_deposit;
    }
    
}