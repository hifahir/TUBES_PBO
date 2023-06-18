/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackendSystemPackage;

import DBPegawai.PegawaiDAO;
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
    private PegawaiDAO dao;

    public boolean isVerifikasiAda() {
        return verifikasiAda;
    }
    
    public VerifikasiSystem(String username, String password) {
        super(username, password);
        this.dao = new PegawaiDAO();
    }

    public void setDao(PegawaiDAO dao) {
        this.dao = dao;
    }
    
    public void verifikasiGaji(Karyawan karyawan, int bulan, int tahun) {
        String key = bulan + "-" + tahun;
        boolean bulanTahunExists = dao.isBulanTahunExists(karyawan, key);

        if (bulanTahunExists) {
            nama = karyawan.getUsername();
            this.bulan = bulan;
            this.tahun = tahun;
            gaji = karyawan.getGaji();
            lembur = dao.getLemburWaktuItu(karyawan, bulan, tahun);
            pajak = dao.getPajakWaktuItu(karyawan, bulan, tahun);
            verifikasiAda = true;
        } else {
            JOptionPane.showMessageDialog(null, "Pembayaran pada bulan " 
                    + bulan + " tahun " + tahun + " untuk " + karyawan.getUsername()
                    + " tidak ada",
                    "Error", JOptionPane.ERROR_MESSAGE);
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
