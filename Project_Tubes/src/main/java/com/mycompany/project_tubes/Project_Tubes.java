/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.project_tubes;

/**
 *
 * @author putra
 */

import java.util.Scanner;

public class Project_Tubes {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // Username & Password BadanKeuangan
        // Username = admin
        // Password = 12345
        BadanKeuangan bk = new BadanKeuangan("admin", "12345");
        
        // Username & Password Karyawan
        // Username = karyu
        // Password = 12345
        Karyawan kw = new Karyawan("karyu", "12345");
        
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
            System.out.println("Masukkan username Karyawan: ");
            String uname = input.next();
            System.out.println("Masukkan password Karyawan: ");
            String ps = input.next();
            if (kw.Login(uname, ps)){
                System.out.println("Login karyawan berhasil!");
            }
            else{
                System.out.println("Login karyawan gagal!");
            }
        }
    }
}
