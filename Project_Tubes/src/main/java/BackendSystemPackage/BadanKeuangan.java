/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackendSystemPackage;

import java.util.*;
import javax.swing.JOptionPane;

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
    
    public String getUsername(){
        return username;
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
                JOptionPane.showMessageDialog(null, "Gaji pada bulan " 
                        + bulan + " tahun " + tahun + " untuk " + karyawan.username 
                        + " sudah diberikan sebelumnya"
                        ,"Error", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(null, "Gaji sebesar Rp. " + (gaji - pajak) 
                        + " dengan pajak Rp. " + pajak + " telah diberikan ke " + karyawan.username 
                        + " pada bulan " + bulan + " tahun " 
                        + tahun
                        , "Informasi"
                        , JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Gaji gagal diberikan kepada " 
                    + karyawan.username + ". Saldo tidak mencukupi"
                    ,"Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    // Untuk mengurus upah lembur
    public void berikanUangLembur(Karyawan karyawan, int hari, int bulan, int tahun) {
        int jamLembur = karyawan.getWaktuLembur();
        int hargaLembur = 150000;
        int total = jamLembur * hargaLembur;

        String key = hari + "-" + bulan + "-" + tahun;

        if (saldo >= total) {
            if (karyawan.getLemburWaktuItu() != null && karyawan.getLemburWaktuItu().containsKey(key)) {
                JOptionPane.showMessageDialog(null, "Upah lembur pada tanggal " 
                        + hari + " bulan " + bulan + " tahun " + tahun + " untuk " + karyawan.username
                        + " sudah diberikan sebelumnya"
                        ,"Error", JOptionPane.ERROR_MESSAGE);
            } else {
                saldo -= total;
                int pajak = karyawan.potonganPajak(total);
                karyawan.tambahUpahLembur(total - pajak);
                karyawan.upahLemburWaktuItu = total-pajak;
                karyawan.tambahWaktuLemburDone(jamLembur);
                karyawan.resetWaktuLembur();

                if (karyawan.getLemburWaktuItu() == null) {
                    karyawan.setLemburWaktuItu(new HashMap<>());
                }
                karyawan.getLemburWaktuItu().put(key, karyawan.upahLemburWaktuItu);

                if (karyawan.getPajakWaktuItu() == null) {
                    karyawan.setPajakWaktuItu(new HashMap<>());
                }
                karyawan.getPajakWaktuItu().put(key, pajak);

                JOptionPane.showMessageDialog(null, "Upah uang lembur sebesar Rp. " 
                        + (total - pajak) 
                        + " dengan potongan pajak yang dibayarkan sebesar Rp. "
                        + pajak + " telah diberikan ke " 
                        + karyawan.username
                        + " pada tanggal " + hari + " bulan " + bulan + " tahun " + tahun
                        , "Informasi"
                        , JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Upah lembur gagal diberikan kepada " 
                    + karyawan.username + ". Saldo tidak mencukupi"
                    ,"Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
