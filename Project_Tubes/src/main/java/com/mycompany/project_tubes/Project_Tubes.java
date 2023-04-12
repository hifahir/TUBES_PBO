/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.project_tubes;

/**
 *
 * @author putra
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Project_Tubes {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // Username & Password akun BadanKeuangan
        BadanKeuangan bk = new BadanKeuangan("admin", "12345", 1000000000); 
        
        // Username & Password Beberapa akun karyawan yang telah ada
        Karyawan kw1 = new Karyawan("Fariz", "12345", "Manager");
        Karyawan kw2 = new Karyawan("Nurul", "12345", "Staff");
        Karyawan kw3 = new Karyawan("Raffa", "12345", "Staff");
        Karyawan kw4 = new Karyawan("Rihan", "12345", "Supervisor");
        //Membuat ArrayList untuk menyimpan objek Karyawan
        ArrayList<Karyawan> kl = new ArrayList<>();
        kl.add(kw1);
        kl.add(kw2);
        kl.add(kw3);
        kl.add(kw4);  
        
        System.out.println("Pilih akun (1 = BadanKeuangan, 2 = Karyawan): ");
        int akun = input.nextInt();
        
        // Kalau akun 1 / BadanKaryawan
        if (akun == 1){
            System.out.println("Masukkan username Badan Keuangan: ");
            String uname = input.next();
            System.out.println("Masukkan password Badan Keuangan: ");
            String ps = input.next();
            if (bk.Login(uname, ps)){
                //Kalau login berhasil, akan memperlihatkan menu untuk BadanKeuangan
                System.out.println("Login berhasil!");
                System.out.println("Selamat datang, "+bk.username+"!");
                
                int pilihanMenu;
                do {
                    // Tampilkan menu pilihan
                    System.out.println("Menu Badan Keuangan:");
                    System.out.println("1. Lihat saldo");
                    System.out.println("2. Melihat data karyawan");
                    System.out.println("3. Memilih karyawan untuk diberi gaji");
                    System.out.println("0. Keluar");
                    System.out.print("Pilihan: ");
                    pilihanMenu = input.nextInt();
                    
                    switch (pilihanMenu){
                        case 1:
                            System.out.println("Saldo: "+bk.getSaldo());
                            break;
                        case 2:
                            System.out.println("Data karyawan:");
                            System.out.println(kw1.username + ", " + kw1.getJabatan() + ", Gaji jabatan: " + kw1.getGaji());
                            System.out.println(kw2.username + ", " + kw2.getJabatan() + ", Gaji jabatan: " + kw2.getGaji());
                            System.out.println(kw3.username + ", " + kw3.getJabatan() + ", Gaji jabatan: " + kw3.getGaji());
                            System.out.println(kw4.username + ", " + kw4.getJabatan() + ", Gaji jabatan: " + kw4.getGaji());
                            break;
                        case 3:
                            System.out.println("Pilih karyawan untuk diberi gaji:");
                            System.out.println("1. " + kw1.username);
                            System.out.println("2. " + kw2.username);
                            System.out.println("3. " + kw3.username);
                            System.out.println("4. " + kw4.username);
                            
                            System.out.print("Pilihan: ");
                            int pilihan = input.nextInt();
                            System.out.print("Masukkan jumlah gaji: ");
                            int jumlahGaji = input.nextInt();
                            
                            switch (pilihan) {
                                case 1:
                                    bk.berikanGaji(kw1, jumlahGaji);
                                    break;
                                case 2:
                                    bk.berikanGaji(kw2, jumlahGaji);
                                    break;
                                case 3:
                                    bk.berikanGaji(kw3, jumlahGaji);
                                    break;
                                case 4:
                                    bk.berikanGaji(kw4, jumlahGaji);
                                    break;
                                default:
                                    System.out.println("Pilihan tidak valid.");
                                    break;
                            }
                    }
                } while(pilihanMenu != 0);

            }
            else{
                System.out.println("Login gagal!");
            }

        // Akun 2 / Karyawan
        } else if (akun == 2){
            boolean loginKaryawan = false;
            System.out.println("Masukkan username Karyawan: ");
            String uname = input.next();
            System.out.println("Masukkan password Karyawan: ");
            String ps = input.next();
            for (Karyawan karyawan : kl){
                if (karyawan.Login(uname, ps)){
                    loginKaryawan = true;
                    break;
                }
            }
            if (loginKaryawan){
                System.out.println("Login karyawan berhasil!");
            }else{
                System.out.println("Login karyawan gagal!");
            }
        }
    }
}
