/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackendSystemPackage;

import java.util.*;
import javax.swing.JOptionPane;
import DBPegawai.PegawaiDAO;

/**
 *
 * @author RH
 */
public class BadanKeuangan extends User {
    private int saldo;
    private PegawaiDAO dao;
    private BadanKeuangan keuangan;
    
    public BadanKeuangan() {}

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public BadanKeuangan(String username, String password, int saldo){
        super(username, password);
        this.saldo = saldo;
        this.dao = new PegawaiDAO();
        this.keuangan = new BadanKeuangan();
    }
    
    public String getUsername(){
        return username;
    }

    public int getSaldo() {
        return saldo;
    }

    public String getPassword() {
        return password;
    }

    public void setDao(PegawaiDAO dao) {
        this.dao = dao;
    }
    
    
    
    // Untuk mengurus Lembur
    public void printKodeLembur(LemburSystem lemburSystem) {
        System.out.println("Kode Lembur: " + lemburSystem.getKodeLembur().toString());
    }

    public void berikanGaji(Karyawan karyawan, int bulan, int tahun) {
        int gaji = karyawan.getGaji();
        int pajak = karyawan.potonganPajak(gaji);
        BadanKeuangan badanKeuangan = dao.getAllKeuangan().get(0);
        
        String key = bulan + "-" + tahun;
        boolean lemburDiberikanSebelumnya = dao.isHariBulanTahunExists(karyawan, key);

        if (saldo >= gaji) {
            if (lemburDiberikanSebelumnya) {
                JOptionPane.showMessageDialog(null, "Upah lembur pada tanggal bulan " + bulan + " tahun " + tahun 
                        + " untuk " + karyawan.getUsername() + " sudah diberikan sebelumnya",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else{
                saldo -= gaji;
                karyawan.tambahGaji(gaji - pajak);

                dao.insertGaji(karyawan, bulan, tahun, gaji, pajak);
                dao.updateAdmin(badanKeuangan, gaji);
                JOptionPane.showMessageDialog(null, "Gaji sebesar Rp. " + (gaji - pajak) 
                            + " dengan pajak Rp. " + pajak + " telah diberikan ke " + karyawan.username 
                            + " pada bulan " + bulan + " tahun " 
                            + tahun
                            , "Informasi"
                            , JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Gaji gagal diberikan kepada " 
                    + karyawan.getUsername() + ". Saldo tidak mencukupi",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Untuk mengurus upah lembur

    public void berikanUangLembur(Karyawan karyawan, int hari, int bulan, int tahun) {
        int jamLembur = karyawan.getWaktuLembur();
        int hargaLembur = 150000;
        int total = jamLembur * hargaLembur;

        String key = hari + "-" + bulan + "-" + tahun;

        boolean lemburDiberikanSebelumnya = dao.isHariBulanTahunExists(karyawan, key);
        if (lemburDiberikanSebelumnya) {
            JOptionPane.showMessageDialog(null, "Upah lembur pada tanggal " 
                    + hari + " bulan " + bulan + " tahun " + tahun 
                    + " untuk " + karyawan.getUsername() + " sudah diberikan sebelumnya",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (saldo >= total) {
                saldo -= total;
                int pajak = karyawan.potonganPajak(total);
                karyawan.tambahUpahLembur(total - pajak);
                karyawan.upahLemburWaktuItu = total - pajak;
                karyawan.tambahWaktuLemburDone(jamLembur);
                karyawan.resetWaktuLembur();

                JOptionPane.showMessageDialog(null, "Upah uang lembur sebesar Rp. " 
                        + (total - pajak) 
                        + " dengan potongan pajak yang dibayarkan sebesar Rp. "
                        + pajak + " telah diberikan ke " 
                        + karyawan.getUsername()
                        + " pada tanggal " + hari + " bulan " + bulan + " tahun " + tahun,
                        "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Upah lembur gagal diberikan kepada " 
                        + karyawan.getUsername() + ". Saldo tidak mencukupi",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        dao.insertLembur(karyawan, hari, bulan, tahun);
        dao.updateAdmin(this.keuangan, total);
    }


}
