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
import DBPegawai.PegawaiDAO;

public class Project_Tubes {

    public static void main(String[] args) {
        
        
        // Username & Password akun BadanKeuangan
        //BadanKeuangan bk = new BadanKeuangan("admin", "12345", 1000000000); 
        
        
        
        // Username & Password sistem Verifikasi
        PegawaiDAO dao = new PegawaiDAO();
        ArrayList<BadanKeuangan> bk = dao.getAllKeuangan();
        int saldo = bk.get(3).getSaldo();

        VerifikasiSystem vs = new VerifikasiSystem("verifikasi","12345",saldo);
        // Username & Password Beberapa akun karyawan yang telah ada
        ArrayList<Karyawan> kw = dao.getAllKaryawan();
        
        //Bulan & tahun awal
        int hari = 1;
        int bulan = 1;
        int tahun = 2023;
        
        AplikasiController aplikasiController = new AplikasiController();
        if (!bk.isEmpty()) { // Check if the list is not empty
            BadanKeuangan firstBadanKeuangan = bk.get(0);
            aplikasiController.setBadanKeuangan(firstBadanKeuangan);
        } else {
            System.out.println("Error");
        }
        
        aplikasiController.setKaryawanList(kw);
        aplikasiController.setVerifikasiSystem(vs);
        aplikasiController.showDefaultView(hari, bulan, tahun);
        
    }
}
