import java.util.*;
import java.sql.*;
import java.io.IOException;
public class transaksi extends user{
    private float jumlah, saldo_tabungan;
    private String no_rek_tujuan, no_rek_tujuan_db;
    Scanner sc = new Scanner(System.in);
    
    void TransferUang(){
       try{
        System.out.println("=======================================================");
        System.out.println("Minimal transfer sebesar Rp. 10,000");
        System.out.print("Ketik Nomor Rekening Tujuan : ");
        no_rek_tujuan = sc.next();
        System.out.println("=======================================================");
        Class.forName("org.sqlite.JDBC");
    Connection k = DriverManager.getConnection("jdbc:sqlite:D:/Programming/OOP ATM/atm.db");
    Statement stat = k.createStatement();
    ResultSet set = stat.executeQuery("select username, no_rek from rekening where no_rek="+no_rek_tujuan+";");
    while (set.next()){
        System.out.println("Username bernama "+set.getString("username"));
        System.out.println("Dengan Nomor Rekening "+set.getString("no_rek"));
        no_rek_tujuan_db=set.getString("no_rek");
    }
    if (no_rek_tujuan.equals(no_rek_tujuan_db)){
        System.out.print("Jumlah uang : Rp. ");
        jumlah=sc.nextFloat();
        stat.executeQuery("select saldo_tabungan from rekening where no_rek='"+getNo_rek()+"';");
        while(set.next()){
            saldo_tabungan=set.getFloat("saldo_tabungan");
        }
        if(jumlah <= saldo_tabungan && jumlah>=10000){
        System.out.println("=======================================================");
        stat.executeUpdate("update rekening set saldo_tabungan=saldo_tabungan-"+jumlah+" where no_rek='"+getNo_rek()+"';");
        stat.executeUpdate("update rekening set saldo_tabungan=saldo_tabungan+"+jumlah+" where no_rek='"+no_rek_tujuan+"';");
        stat.executeUpdate("insert into transaksi values('"+getNo_rek()+"', 'Transfer Uang', date('now'), "+jumlah+",'"+no_rek_tujuan+"');");
        stat.executeUpdate("insert into transaksi values('"+no_rek_tujuan+"', 'Menerima Transfer', date('now'), "+jumlah+",'"+getNo_rek()+"');");
        System.out.println("Uang sebesar Rp. "+String.format("%,.2f",jumlah));
        System.out.println("Telah dikirimkan ke "+no_rek_tujuan);
        } else{System.out.println("Uang anda tidak cukup untuk melakukan transfer/Transfer kurang dari Rp. 10,000!");}
    } else { System.out.println("Nomor rekening tidak terdaftar/salah!"); }
}catch (Exception e){System.out.println("Error: "+e.getMessage());}
    }
    
    void TarikTunai(){
        try{
        System.out.println("=======================================================");
        System.out.println("Minimal pengambilan uang tunai sebesar Rp. 50,000.00");
        System.out.print("Jumlah Uang Yang Diambil : Rp. ");
        jumlah=sc.nextFloat();
        Class.forName("org.sqlite.JDBC");
    Connection k = DriverManager.getConnection("jdbc:sqlite:D:/Programming/OOP ATM/atm.db");
    Statement stat = k.createStatement();
    ResultSet set = stat.executeQuery("select saldo_tabungan from rekening where no_rek='"+getNo_rek()+"';");
    while(set.next()){
    saldo_tabungan = set.getFloat("saldo_tabungan");
    if (jumlah<=saldo_tabungan && jumlah>=50000){
    stat.executeUpdate("update rekening set saldo_tabungan=saldo_tabungan-"+jumlah+" where no_rek='"+getNo_rek()+"';");
    stat.executeUpdate("insert into transaksi values('"+getNo_rek()+"', 'Tarik Tunai', date('now'), "+jumlah+",'"+getNo_rek()+"');");
    } else {System.out.println("Saldo anda tidak mencukupi untuk penarikan tunai/Jumlah uang yang ingin diambil kurang dari 50000!");}
    }
    }catch (Exception e){System.out.println("Error: "+e.getMessage());}
    }
    
    void addDeposito(){
        try{
        System.out.println("=======================================================");
        System.out.print("Jumlah Uang Deposit Yang Ingin Dimasukkan : Rp. ");
        jumlah=sc.nextFloat();
        Class.forName("org.sqlite.JDBC");
    Connection k = DriverManager.getConnection("jdbc:sqlite:D:/Programming/OOP ATM/atm.db");
    Statement stat = k.createStatement();
        if(jumlah>=5000000){
    jumlah=jumlah+(jumlah*0.1f);
    stat.executeUpdate("update rekening set saldo_deposit=saldo_deposit+"+jumlah+" where no_rek='"+getNo_rek()+"';");
    stat.executeUpdate("insert into transaksi values('"+getNo_rek()+"', 'Setor Deposit', date('now'), "+jumlah+",'"+getNo_rek()+"');");
    System.out.println("Anda mendapatkan bunga sejumlah Rp."+String.format("%,.2f",(jumlah*0.1f)));
} else{
    stat.executeUpdate("update rekening set saldo_deposit=saldo_deposit+"+jumlah+" where no_rek='"+getNo_rek()+"';");
    stat.executeUpdate("insert into transaksi values('"+getNo_rek()+"', 'Setor Deposit', date('now'), "+jumlah+",'"+getNo_rek()+"');");
}
    System.out.println("Anda memasukkan deposit sejumlah Rp. "+String.format("%,.2f",jumlah));
} catch (Exception e){System.out.println("Error: "+e.getMessage());}
    }
    
    void addTabungan(){
        try{
        System.out.println("=======================================================");
        System.out.print("Jumlah Uang Tabungan Yang Ingin Dimasukkan : Rp. ");
        jumlah=sc.nextFloat();
        Class.forName("org.sqlite.JDBC");
    Connection k = DriverManager.getConnection("jdbc:sqlite:D:/Programming/OOP ATM/atm.db");
    Statement stat = k.createStatement();
    stat.executeUpdate("update rekening set saldo_tabungan=saldo_tabungan+"+jumlah+" where no_rek='"+getNo_rek()+"';");
    stat.executeUpdate("insert into transaksi values('"+getNo_rek()+"', 'Setor Tabungan', date('now'), "+jumlah+",'"+getNo_rek()+"');");
    System.out.println("Anda menyetor tunai sejumlah Rp. "+String.format("%,.2f",jumlah));
    }catch (Exception e){System.out.println("Error: "+e.getMessage());}
    }

}