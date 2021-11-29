import java.util.*;
import java.sql.*;
import java.io.IOException;
public class transaksi extends user{
    float jumlah, saldo_tabungan;
    String no_rek_tujuan, no_rek_tujuan_db;
    Scanner sc = new Scanner(System.in);
    
    @Override
    public String getNo_rek() {
        return super.no_rek;
    }
    
    void TransferUang(){
       try{ 
        System.out.print("Ketik Nomor Rekening Tujuan : ");
        no_rek_tujuan = sc.next();
        Class.forName("org.sqlite.JDBC");
    Connection k = DriverManager.getConnection("jdbc:sqlite:D:/Programming/OOP ATM/atm.db");
    Statement stat = k.createStatement();
    ResultSet set = stat.executeQuery("select username, no_rek from rekening where no_rek="+no_rek_tujuan+";");
    while (set.next()){
        System.out.println(set.getString("username")+"\t"+set.getString("no_rek"));
        no_rek_tujuan_db=set.getString("no_rek");
    if (no_rek_tujuan.equals(no_rek_tujuan_db)){
        System.out.println("Jumlah uang : Rp. ");
        jumlah=sc.nextFloat();
        stat.executeQuery("select saldo_tabungan from rekening where no_rek='"+getUsername()+"';");
        while(set.next()){
            saldo_tabungan=set.getFloat("saldo_tabungan");
        if(jumlah>=saldo_tabungan){
        stat.executeUpdate("update rekening set saldo_tabungan=saldo_tabungan-"+jumlah+" where no_rek="+getNo_rek()+";");
        stat.executeUpdate("update rekening set saldo_tabungan=saldo_tabungan+"+jumlah+" where no_rek="+no_rek_tujuan+";");
        stat.executeUpdate("insert into transaksi values("+getNo_rek()+", 'Transfer Uang', curdate(), "+jumlah+","+no_rek_tujuan+");");
        System.out.println("Uang sebesar Rp. "+jumlah);
        System.out.println("Telah dikirimkan ke "+no_rek_tujuan);
    }
    else{System.out.println("Uang anda tidak cukup untuk melakukan transfer!");}
    }
    } else{ System.out.println("Nomor rekening tidak terdaftar/salah!");
}
}
}catch (Exception e){}
    }
    
    void TarikTunai(){
        try{ 
        System.out.print("Jumlah Uang Yang Diambil : Rp. ");
        jumlah=sc.nextFloat();
        Class.forName("org.sqlite.JDBC");
    Connection k = DriverManager.getConnection("jdbc:sqlite:D:/Programming/OOP ATM/atm.db");
    Statement stat = k.createStatement();
    stat.executeUpdate("update rekening set saldo_tabungan=saldo_tabungan-"+jumlah+" where no_rek="+getNo_rek()+";");
    System.out.println("");
    }catch (Exception e){}
    }
    
    void addDeposito(){
        try{
        System.out.print("Jumlah Uang Deposit Yang Ingin Dimasukkan : Rp. ");
        jumlah=sc.nextFloat();
        Class.forName("org.sqlite.JDBC");
    Connection k = DriverManager.getConnection("jdbc:sqlite:D:/Programming/OOP ATM/atm.db");
    Statement stat = k.createStatement();
    stat.executeUpdate("update rekening set saldo_deposit=saldo_deposit+"+jumlah+" where no_rek='"+getNo_rek()+"';");
    }catch (Exception e){}
    }
    
    void addTabungan(){
        try{
        System.out.print("Jumlah Uang Tabungan Yang Ingin Dimasukkan : Rp. ");
        jumlah=sc.nextFloat();
        Class.forName("org.sqlite.JDBC");
    Connection k = DriverManager.getConnection("jdbc:sqlite:D:/Programming/OOP ATM/atm.db");
    Statement stat = k.createStatement();
    stat.executeUpdate("update rekening set saldo_tabungan=saldo_tabungan+"+jumlah+" where no_rek='"+getNo_rek()+"';");
    }catch (Exception e){}
    }
}