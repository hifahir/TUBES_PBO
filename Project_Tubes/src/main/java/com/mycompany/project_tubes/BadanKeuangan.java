/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_tubes;

import java.util.*;

/**
 *
 * @author RH
 */
public class BadanKeuangan extends User {
    private int saldo;
    
    public BadanKeuangan(String username, String password, int saldo){
        super(username, password);
        this.saldo = saldo;
    }

    public int getSaldo() {
        return saldo;
    }
    
    // Untuk mengurus Lembur
    public void isiKodeLembur(LemburSystem lemburSystem, String kode) {
        lemburSystem.tambahKodeLembur(kode);
    }
    public void printKodeLembur(LemburSystem lemburSystem) {
        System.out.println("Kode Lembur: " + lemburSystem.getKodeLembur().toString());
    }
    public void printUsedKodeLembur(LemburSystem lemburSystem) {
        System.out.println("Kode Lembur yang telah digunakan: ");
        for (String kode : lemburSystem.getUsedKodeLembur()) {
            System.out.println(kode);
        }
    }
    
    

    public void berikanGaji(Karyawan karyawan, int bulan, int tahun) {
        int gaji = karyawan.getGaji();
        if (saldo >= gaji) {
            String key = bulan + "-" + tahun;
            if (karyawan.getGajiWaktuItu() != null && karyawan.getGajiWaktuItu().containsKey(key)) {
                System.out.println("Gaji pada bulan " + bulan + " tahun " + tahun + " untuk " + karyawan.username + " sudah diberikan sebelumnya");
            } else {
                saldo -= gaji;
                int pajak = karyawan.potonganPajak(gaji);
                karyawan.tambahGaji(gaji - pajak);
                if (karyawan.getGajiWaktuItu() == null) {
                    karyawan.setGajiWaktuItu(new HashMap<>());
                }
                karyawan.getGajiWaktuItu().put(key, gaji - pajak);
                if (karyawan.getPajakWaktuItu() == null) { // tambahan untuk membuat objek Map pajakWaktuItu jika belum ada
                    karyawan.setPajakWaktuItu(new HashMap<>());
                }
                karyawan.getPajakWaktuItu().put(key, pajak); // tambahan untuk menyimpan nilai pajak pada objek Karyawan
                System.out.println("Gaji sebesar " + (gaji - pajak) + " telah diberikan ke " + karyawan.username + " pada bulan " + bulan + " tahun " + tahun);
                System.out.println("Pajak yang terbayarkan adalah " + pajak);
            }
        } else {
            System.out.println("Gaji gagal diberikan kepada " + karyawan.username + ". Saldo tidak mencukupi");
        }
    }

    
    // Untuk mengurus upah lembur
    public void berikanUangLembur(Karyawan karyawan, int bulan, int tahun){
        int jamLembur = karyawan.getWaktuLembur();
        int hargaLembur = 150000;
        int total = jamLembur * hargaLembur;
        
        if (karyawan.getbisaLembur() && jamLembur != 0) {
            if (saldo >= total) {
                saldo -= total;
                int pajak = karyawan.potonganPajak((int) total);
                karyawan.tambahUpahLembur((int) total - pajak);
                karyawan.tambahWaktuLemburDone(jamLembur);
                karyawan.resetWaktuLembur();
                System.out.println("Upah uang lembur sebesar " + (total - pajak) + " telah diberikan ke " + karyawan.username);
                System.out.println("Pajak yang terbayarkan adalah " + pajak);
            } else {
                System.out.println("Upah lembur gagal diberikan kepada " + karyawan.username + ". Saldo tidak mencukupi");
            }
        } else {
            System.out.println("Karyawan tidak memenuhi syarat lembur");
        }
    }
}
