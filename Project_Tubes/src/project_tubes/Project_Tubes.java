/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package project_tubes;

/**
 *
 * @author putra
 */

import BackendSystemPackage.VerifikasiSystem;
import BackendSystemPackage.LemburSystem;
import BackendSystemPackage.Karyawan;
import BackendSystemPackage.BadanKeuangan;
import UIPackage.AplikasiController;
import java.util.*;

public class Project_Tubes {

    public static void main(String[] args) {
        
        // Username & Password akun BadanKeuangan
        //BadanKeuangan bk = new BadanKeuangan("admin", "12345", 1000000000); 
        
        // Username & Password sistem Verifikasi
        //VerifikasiSystem vs = new VerifikasiSystem("verifikasi","12345",bk.getSaldo());
        
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
        
        //Bulan & tahun awal
        int hari = 1;
        int bulan = 1;
        int tahun = 2023;
        
        AplikasiController aplikasiController = new AplikasiController();
        //aplikasiController.setBadanKeuangan(bk);
        aplikasiController.setKaryawanList(kl);
        //aplikasiController.setVerifikasiSystem(vs);
        aplikasiController.showDefaultView(hari, bulan, tahun);
        
    }
}
