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
    public Map<String, Integer> gajiWaktuItu;
    public Map<String, Integer> pajakWaktuItu;
    
    public Karyawan(String username, String password, String jabatan){
        super(username, password);
        this.jabatan = jabatan;
        setGaji(jabatan);
        this.gajiDidapatkan = 0;
        this.waktuLembur = 0;
        this.bisaLembur = jabatan.equals("Staff");
        this.pajak = 0.05;
    }

    public String getJabatan(){
        return jabatan;
    }
    
    public boolean getbisaLembur() {
        return bisaLembur;
    }
    
    public Map<String, Integer> getGajiWaktuItu() {
        return gajiWaktuItu;
    }
    
    public void setGajiWaktuItu(Map<String, Integer> gajiWaktuItu) {
        this.gajiWaktuItu = gajiWaktuItu;
    }
    
    public Map<String, Integer> getPajakWaktuItu() {
        return pajakWaktuItu;
    }
    
    public void setPajakWaktuItu(Map<String, Integer> pajakWaktuItu) {
        this.pajakWaktuItu = pajakWaktuItu;
    }

    
    public void inputWaktuLembur(LemburSystem lemburSystem, String kode, int waktuLembur) {
        if (lemburSystem.getKodeLembur().contains(kode)) {
            // periksa apakah kode lembur sudah pernah digunakan sebelumnya
            if (!lemburSystem.isKodeLemburUsed(kode)) {
                lemburSystem.addUsedKodeLembur(kode);
                tambahWaktuLembur(waktuLembur); // tambahkan waktu lembur karyawan
                System.out.println("Waktu lembur telah ditambahkan");
            } else {
                System.out.println("Kode lembur sudah pernah digunakan sebelumnya");
            }
        } else {
            System.out.println("Kode lembur tidak ditemukan");
        }
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
