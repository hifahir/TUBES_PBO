/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackendSystemPackage;

import DBPegawai.PegawaiDAO;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author RH
 */
public class Karyawan extends User{
    private int gaji;
    private String jabatan;
    private int gajiDidapatkan;
    private int waktuLembur;
    private int waktuLemburDone;
    private boolean bisaLembur;
    private int upahLembur;
    private double pajak;
    private int pajakTerbayarkan;
    public int upahLemburWaktuItu;
    public int gajiWaktuItu;
    public int pajakWaktuItu;
    public int lemburWaktuItu;
    private PegawaiDAO dao;

    public String getPassword() {
        return password;
    }
    
    public Karyawan(){
    
    }

    public void setGaji(int gaji) {
        this.gaji = gaji;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Karyawan(String username, String password, String jabatan){
        super(username, password);
        this.jabatan = jabatan;
        setGaji(jabatan);
        this.gajiDidapatkan = 0;
        this.waktuLembur = 0;
        this.bisaLembur = jabatan.equals("Staff");
        this.pajak = 0.05;
        this.dao = new PegawaiDAO();
    }

    public void setBisaLembur(boolean bisaLembur) {
        this.bisaLembur = bisaLembur;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public void setPajak(double pajak) {
        this.pajak = pajak;
    }
    
    public String getUsername(){
        return username;
    }

    public String getJabatan(){
        return jabatan;
    }
    
    public boolean getbisaLembur() {
        return bisaLembur;
    }

    public void setGajiWaktuItu(int gajiWaktuItu) {
        this.gajiWaktuItu = gajiWaktuItu;
    }

    public void setPajakWaktuItu(int pajakWaktuItu) {
        this.pajakWaktuItu = pajakWaktuItu;
    }

    public void setLemburWaktuItu(int lemburWaktuItu) {
        this.lemburWaktuItu = lemburWaktuItu;
    }

    public int getGajiWaktuItu() {
        return gajiWaktuItu;
    }

    public int getPajakWaktuItu() {
        return pajakWaktuItu;
    }

    public int getLemburWaktuItu() {
        return lemburWaktuItu;
    }

    public void setDao(PegawaiDAO dao) {
        this.dao = dao;
    }

    public void setWaktuLembur(int waktuLembur) {
        this.waktuLembur = waktuLembur;
    }
    
    public void inputWaktuLembur(Karyawan karyawan, int waktuLembur) {
        dao.updateWaktuLembur(karyawan, upahLembur);
        JOptionPane.showMessageDialog(null, "Waktu lembur telah ditambahkan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void tambahWaktuLembur(int waktuLembur){
        this.waktuLembur += waktuLembur;
    }
    
    public void resetWaktuLembur(){
        this.waktuLembur = 0;
    }

    public int getWaktuLemburDone() {
        return waktuLemburDone;
    }
    
    public void tambahWaktuLemburDone(int waktuLemburDone){
        this.waktuLemburDone += waktuLemburDone;
    }
    
    public void tambahUpahLembur(int upahLembur){
        this.upahLembur += upahLembur;
    }

    public int getUpahLembur() {
        return upahLembur;
    }

    public int getGajiDidapatkan() {
        return gajiDidapatkan;
    }

    public int getWaktuLembur() {
        return waktuLembur;
    }

    
    public void setGaji(String jabatan){
        switch(jabatan){
            case "Manager":
                this.gaji = 9000000;
                break;
            case "Supervisor":
                this.gaji = 10000000;
                break;
            case "Staff":
                this.gaji = 7500000;
                break;
            default:
                this.gaji = 0;
        }
    }
    
    public int getGaji() {
        return gaji;
    }
    
    public void tambahGaji(int tambahanGaji){
        this.gajiDidapatkan += tambahanGaji;
    }
    
    public int getgajiDidapatkan(){
        return gajiDidapatkan;
    }

    public double getPajak() {
        return pajak;
    }

    public int getPajakTerbayarkan() {
        return pajakTerbayarkan;
    }
    
    public int potonganPajak(int jumlah) {
        double pajakGaji = jumlah * pajak;
        pajakTerbayarkan += (int) pajakGaji; // tambahkan nilai pajak yang dipotong ke atribut pajakDipotong
        return (int) pajakGaji;
    }
    
}
