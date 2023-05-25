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
        Scanner scanner = new Scanner(System.in);

        // Membuat objek BadanKeuangan
        BadanKeuangan badanKeuangan = new BadanKeuangan("nama_pengguna", "kata_sandi", 100000000);

        // Membuat objek Karyawan
        Karyawan karyawan1 = new Karyawan("karyawan1", "kata_sandi1", "Manager", 2);
        Karyawan karyawan2 = new Karyawan("karyawan2", "kata_sandi2", "Supervisor", 3);
        Karyawan karyawan3 = new Karyawan("karyawan3", "kata_sandi3", "Staff", 1);

        // Menambahkan karyawan ke BadanKeuangan
        badanKeuangan.tambahKaryawan(karyawan1);
        badanKeuangan.tambahKaryawan(karyawan2);
        badanKeuangan.tambahKaryawan(karyawan3);

        // Meminta input bulan dari pengguna
       System.out.print("Masukkan jumlah bulan: ");
        int jumlahBulan = scanner.nextInt();

        int[] bulan = new int[jumlahBulan];
        for (int i = 0; i < jumlahBulan; i++) {
            System.out.print("Masukkan bulan ke-" + (i + 1) + ": ");
            int inputBulan = scanner.nextInt();

            // Validasi bulan
            if (inputBulan >= 1 && inputBulan <= 12) {
                bulan[i] = inputBulan;
            } else {
                System.out.println("Bulan yang dimasukkan tidak valid.");
                i--; // Mengulangi input untuk bulan yang tidak valid
            }
        }

        // Menambahkan gaji karyawan untuk setiap bulan yang dimasukkan
        for (Karyawan karyawan : badanKeuangan.getKaryawanList()) {
            for (int i = 0; i < jumlahBulan; i++) {
                System.out.print("Masukkan gaji untuk bulan " + bulan[i] + " (Karyawan: " + karyawan.getUsername() + "): ");
                int tambahanGaji = scanner.nextInt();
                badanKeuangan.berikanGaji(karyawan, tambahanGaji);
            }
        }

        // Memanggil method infoGajiBulanTahun untuk bulan yang dimasukkan dan tahun 2023
        badanKeuangan.infoGajiBulanTahun(bulan, 2023);

        scanner.close();
    }
        
        

}
