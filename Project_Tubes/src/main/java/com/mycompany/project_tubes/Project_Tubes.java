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
        
        
        
        // Username & Password Beberapa akun karyawan yang telah ada
        Karyawan kw1 = new Karyawan("Fariz", "12345", 0);
        Karyawan kw2 = new Karyawan("Nurul", "12345", 0);
        Karyawan kw3 = new Karyawan("Raffa", "12345", 0);
        Karyawan kw4 = new Karyawan("Rihan", "12345", 0);
        //Membuat ArrayList untuk menyimpan objek Karyawan
        ArrayList<Karyawan> kl = new ArrayList<>();
        kl.add(kw1);
        kw1.infoGaji(0);
        kl.add(kw2);
        kl.add(kw3);
        kl.add(kw4);
        
        // Username & Password akun BadanKeuangan
        BadanKeuangan bk = new BadanKeuangan("admin", "12345", 1000000000);
        bk.berikanGaji(kw1, 1000000);
        
        
        System.out.println("Pilih akun (1 = BadanKeuangan, 2 = Karyawan): ");
        int akun = input.nextInt();
        if (akun == 1){
            System.out.println("Masukkan username Badan Keuangan: ");
            String uname = input.next();
            System.out.println("Masukkan password Badan Keuangan: ");
            String ps = input.next();
            if (bk.Login(uname, ps)){
                System.out.println("Login berhasil!");
            }
            else{
                System.out.println("Login gagal!");
            }
            
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
