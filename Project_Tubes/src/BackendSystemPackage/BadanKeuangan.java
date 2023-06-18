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
    private PegawaiDAO dao;
    private BadanKeuangan keuangan;
    
    public BadanKeuangan() {}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public BadanKeuangan(String username, String password){
        super(username, password);
        this.dao = new PegawaiDAO();
        this.keuangan = new BadanKeuangan();
    }
    
    public String getUsername(){
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setDao(PegawaiDAO dao) {
        this.dao = dao;
    }
    
    public void berikanGaji(Karyawan karyawan, int bulan, int tahun, int gaji, int lembur) {
        int pajak = karyawan.potonganPajak(gaji+lembur);
        
        String key = bulan + "-" + tahun;
        boolean transaksiUdahSebelumnya = dao.isBulanTahunExists(karyawan, key);
        
        if (transaksiUdahSebelumnya) {
            JOptionPane.showMessageDialog(null, "Upah lembur pada tanggal bulan " + bulan + " tahun " + tahun 
                + " untuk " + karyawan.getUsername() + " sudah diberikan sebelumnya",
                "Error", JOptionPane.ERROR_MESSAGE);
        } else{
            dao.insertTransaksi(karyawan, bulan, tahun, gaji, lembur, pajak);
            dao.updateStatus(karyawan, bulan, tahun);
            
            if (dao.isBisaLembur(karyawan)){
                JOptionPane.showMessageDialog(null, "Gaji sebesar Rp. "+ gaji
                    + " dengan upah lembur sebesar Rp. "+lembur
                    + " dipotong pajak sebesar Rp. " + pajak + " telah diberikan ke " + karyawan.username 
                    + " pada bulan " + bulan + " tahun " 
                    + tahun
                    , "Informasi"
                    , JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Gaji sebesar Rp. " + (gaji - pajak) 
                    + " dengan pajak Rp. " + pajak + " telah diberikan ke " + karyawan.username 
                    + " pada bulan " + bulan + " tahun " 
                    + tahun
                    , "Informasi"
                    , JOptionPane.INFORMATION_MESSAGE);
            }  
        }
    }
   
}
