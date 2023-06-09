/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackendSystemPackage;

import BackendSystemPackage.Karyawan;
import BackendSystemPackage.BadanKeuangan;
import javax.swing.*;

/**
 *
 * @author RH
 */
public class VerifikasiSystem extends BadanKeuangan{
    
    private String nama;
    private int hari;
    private int bulan;
    private int tahun;
    private int gaji;
    private int lembur;
    private double pajak;
    private boolean verifikasiAda;

    public boolean isVerifikasiAda() {
        return verifikasiAda;
    }
    
    public VerifikasiSystem(String username, String password, int saldo) {
        super(username, password, saldo);
    }
    
    public void verifikasiGaji(Karyawan karyawan, int bulan, int tahun) {
        String key = bulan + "-" + tahun;
        if (karyawan.getGajiWaktuItu() != null && karyawan.getGajiWaktuItu().containsKey(key)) {
            nama = karyawan.username;
            this.bulan = bulan;
            this.tahun = tahun;
            gaji = karyawan.getGaji();
            pajak = karyawan.getPajak();
            verifikasiAda = true;
        } else {
            JOptionPane.showMessageDialog(null, "Gaji pada bulan " 
                    + bulan + " tahun " + tahun + " untuk " + karyawan.username 
                    + " tidak ada/belum dibayarkan!"
                    ,"Error", JOptionPane.ERROR_MESSAGE);
            verifikasiAda = false;
        }
    }
    
    public void verifikasiLembur(Karyawan karyawan, int hari, int bulan, int tahun){
        String key = hari + "-" + bulan + "-" + tahun;
        if (karyawan.getLemburWaktuItu() != null && karyawan.getLemburWaktuItu().containsKey(key)) {
            nama = karyawan.username;
            this.hari = hari;
            this.bulan = bulan;
            this.tahun = tahun;
            lembur = karyawan.upahLemburWaktuItu;
            pajak = karyawan.getPajak();
            verifikasiAda = true;
        } else {
            JOptionPane.showMessageDialog(null, "Upah lembur pada tanggal " 
                    + hari + " " + bulan + " " + tahun + " untuk " + karyawan.username 
                    + " tidak ada/belum dibayarkan!"
                    ,"Error", JOptionPane.ERROR_MESSAGE);
            verifikasiAda = false;
        }
    }

    public String getNama() {
        return nama;
    }

    public int getHari() {
        return hari;
    }

    public int getBulan() {
        return bulan;
    }

    public int getTahun() {
        return tahun;
    }

    public int getGaji() {
        return gaji;
    }

    public int getLembur() {
        return lembur;
    }

    public double getPajak() {
        return pajak;
    }
}
