import java.util.*;
import java.sql.*;
import java.io.*;
public class ATM{
    static Random r = new Random();
    static rekening rek = new rekening();
    static transaksi tr = new transaksi();
    static user u = new user();
    static Scanner sc = new Scanner(System.in);
    static int pilih, acak;
    
    public static void main(String[]args){
       do{
        System.out.println("*****Bank OOP Memoria*****");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Pilih menu : ");
        pilih = sc.nextInt();        
        if (pilih == 1){
            login();
        } else if (pilih == 2){
            register();
        } else{System.exit(0);}
    } while (pilih !=3);
    }
    
    static void login(){
        try{
    System.out.println("=======================================================");
    System.out.print("Username : ");
    String username=sc.next();
    System.out.print("Pin : ");
    String pin=sc.next();
    Class.forName("org.sqlite.JDBC");
    Connection k = DriverManager.getConnection("jdbc:sqlite:D:/Programming/OOP ATM/atm.db");
    Statement stat = k.createStatement();
    ResultSet set = stat.executeQuery("select * from user where username='"+username+"' and pin='"+pin+"';");
     while (set.next()){
        String usernamedb=set.getString("username");
        String pindb=set.getString("pin");
        String namad=set.getString("namadepan");
        String namab=set.getString("namabelakang");
    if (username.equals(usernamedb) && pin.equals(pindb)){
    do {
        System.out.println("=======================================================");
        u.setUsername(username);
        stat.executeQuery("select no_rek from rekening where username='"+username+"';");
        while(set.next()){
            String no_rek=set.getString("no_rek");
            u.setNo_rek(no_rek);
        }
        System.out.println("Selamat datang "+namad+" "+namab+"!");
            System.out.println("Pilihan menu Bank OOP Memoria");
            System.out.println("  1) Cek Saldo Deposit dan Rekening");
            System.out.println("  2) Mutasi rekening");
            System.out.println("  3) Tarik Tunai");
            System.out.println("  4) Setor Tunai");
            System.out.println("  5) Transfer");
            System.out.println("  6) Deposito");
            System.out.println("  7) Log Out");
            System.out.print("Pilihan Anda: ");
            pilih = sc.nextInt();
             switch (pilih) {
        case 1:
            rek.getSaldo();
            break;
        case 2:
            rek.getMutasi();
            break;
        case 3:
            tr.TarikTunai();
            rek.getSaldoTabungan();
            break;
        case 4:
            tr.addTabungan();
            rek.getSaldoTabungan();
            break;
        case 5:
            tr.TransferUang();
            rek.getSaldoTabungan();
            break;
        case 6:
            tr.addDeposito();
            rek.getSaldoDeposit();
            break;
        case 7:
            System.out.println("Terima Kasih sudah menggunakan Bank OOP Memoria");
            System.out.println("Melakukan Log Out.....");
            break;
                            }
        } while (pilih!=7); 
} else if (!username.equals(usernamedb) && !pin.equals(pindb)) {System.out.println("Username/pin salah!!!");}
}
}catch (Exception e){ System.out.println("Error: "+e.getMessage());}  
}
    
    static void register(){
    System.out.println("Anda ingin melakukan registerasi");
    System.out.println("Harap mempersiapkan berkas yang akan diinput nomornya");
    System.out.println("=======================================================");
    System.out.print("Nama Depan : ");
    String a = sc.next();
    u.setNamadepan(a);
    System.out.print("Nama Belakang : ");
    String b = sc.next();
    u.setNamabelakang(b);
    System.out.print("No. KTP : ");
    String c = sc.next();
    u.setId_ktp(c);
    System.out.print("Tanggal Lahir (YYYY-MM-DD) : ");
    String d = sc.next();
    u.setTgl_lahir(d);
    System.out.print("Username : ");
    String e = sc.next();
    u.setUsername(e);
    System.out.print("Pin : "); 
    String f = sc.next();
    u.setPin(f);
    acak = r.nextInt(99999999);
    u.setNo_rek(Integer.toString(acak));
    u.adduser();
    System.out.println(u.getNamadepan()+", anda sudah terdaftar di Bank OOP Memoria");
    System.out.println("Akun anda bernomor rekening "+u.getNo_rek());
    }
    
}