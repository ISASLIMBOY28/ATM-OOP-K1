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
    u.username=sc.next();
    System.out.print("Pin : ");
    u.pin=sc.next();
    Class.forName("org.sqlite.JDBC");
    Connection k = DriverManager.getConnection("jdbc:sqlite:D:/Programming/OOP ATM - Kelompok 1/atm.db");
    Statement stat = k.createStatement();
    ResultSet set = stat.executeQuery("select * from user where username='"+u.username+"' and pin='"+u.pin+"';");
     while (set.next()){
        String usernamedb=set.getString("username");
        String pindb=set.getString("pin");
        u.namadepan=set.getString("namadepan");
        u.namabelakang=set.getString("namabelakang");
    if (u.username.equals(usernamedb) && u.pin.equals(pindb)){
    do {
        System.out.println("=======================================================");
        u.setUsername(u.username);
        stat.executeQuery("select no_rek from rekening where username='"+u.username+"';");
        while(set.next()){
            u.no_rek=set.getString("no_rek");
            u.setNo_rek(u.no_rek);
        }
        System.out.println("Selamat datang "+u.namadepan+" "+u.namabelakang+"!");
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
            break;
        case 4:
            tr.addTabungan();
            break;
        case 5:
            tr.TransferUang();
            break;
        case 6:
            tr.addDeposito();
            break;
        case 7:
            System.out.println("Terima Kasih sudah menggunakan Bank OOP Memoria");
            System.out.println("Melakukan Log Out.....");
            break;
        }
    } while (pilih!=7);
}
}
}catch (Exception e){System.out.println("Error: "+e.getMessage());}  
}

    
    static void register(){
    System.out.println("Anda ingin melakukan registerasi");
    System.out.println("Harap mempersiapkan berkas yang akan diinput nomornya");
    System.out.println("=======================================================");
    System.out.print("Nama Depan : ");
    u.namadepan = sc.nextLine();
    System.out.print("Nama Belakang : ");
    u.namabelakang = sc.next();
    System.out.print("No. KTP : ");
    u.id_ktp = sc.next();
    System.out.print("Tanggal Lahir (YYYY-MM-DD) : ");
    u.tgl_lahir = sc.next();
    System.out.print("Username : ");
    u.username = sc.next();
    System.out.print("Pin : "); 
    u.pin = sc.next();
    acak = r.nextInt(99999999);
    u.no_rek = Integer.toString(acak);
    u.adduser();
    System.out.println(u.namadepan+", anda sudah terdaftar di Bank OOP Memoria");
    System.out.println("Akun anda bernomor rekening "+u.no_rek);
    }
    
}