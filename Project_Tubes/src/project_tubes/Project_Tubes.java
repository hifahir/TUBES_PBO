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
        PegawaiDAO dao = new PegawaiDAO();
        ArrayList<BadanKeuangan> bk = dao.getAllKeuangan();
        int saldo = bk.get(0).getSaldo();

        VerifikasiSystem vs = new VerifikasiSystem("verifikasi","12345",saldo);
        ArrayList<Karyawan> kw = dao.getAllKaryawan();
        
        //Bulan & tahun awal
        int hari = 1;
        int bulan = 1;
        int tahun = 2023;
        
        AplikasiController aplikasiController = new AplikasiController();
        aplikasiController.setDao(dao);
        if (!bk.isEmpty()) { // Check if the list is not empty
            BadanKeuangan firstBadanKeuangan = bk.get(0);
            aplikasiController.setBadanKeuangan(firstBadanKeuangan);
        } else {
            System.out.println("Error");
        }
        
        for (Karyawan karyawan : kw) {
            aplikasiController.setKaryawan(karyawan);
        }
        
        aplikasiController.setVerifikasiSystem(vs);
        aplikasiController.showDefaultView(hari, bulan, tahun);

    }
}
