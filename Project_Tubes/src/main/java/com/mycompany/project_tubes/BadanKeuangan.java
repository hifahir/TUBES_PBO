/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_tubes;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author RH
 */
public class BadanKeuangan extends User {
    private int saldo;
    private List<Karyawan> karyawanList;
    
    public BadanKeuangan(String username, String password, int saldo){
        super(username, password);
        this.saldo = saldo;
        karyawanList = new ArrayList<>();
    }

    public int getSaldo() {
        return saldo;
    }
    
    public void berikanGaji(Karyawan karyawan, int jumlah){
        int gaji = karyawan.getGaji();
        if(saldo >= jumlah ){
            if (jumlah <= (gaji+karyawan.jumlahLembur()*125000)){
                saldo -= jumlah;
                karyawan.tambahGaji(jumlah);
                System.out.println("Gaji sebesar " + jumlah + " telah diberikan ke " + karyawan.username);
            }else{
                System.out.println("Gaji gagal diberikan kepada "+karyawan.username+". jumlah gaji melebihi dari jumlah gaji yang seharusnya diberikan");
            }
        }
        else{
            System.out.println("Gaji gagal diberikan kepada "+ karyawan.username +". Saldo tidak mencukupi");
        }
    }
    public void tambahKaryawan(Karyawan karyawan) {
        karyawanList.add(karyawan);
    }
    
    

     public void infoGajiBulanTahun(int[] bulan, int tahun) {
        System.out.println("Info Gaji Tahun " + tahun + ":");

        for (Karyawan karyawan : karyawanList) {
            System.out.println("Karyawan: " + karyawan.getUsername());
            System.out.println("Jabatan: " + karyawan.getJabatan());

            for (int i = 0; i < bulan.length; i++) {
                int gajiPerluDibayar = karyawan.getGajiPerluDibayar(bulan[i]);
                System.out.println("Gaji yang Harus Dibayar Bulan " + bulan[i] + " Tahun " + tahun + ": " + gajiPerluDibayar);
            }

            System.out.println("Gaji yang Telah Diterima: " + karyawan.getgajiDidapatkan());
            System.out.println();
        }
    }

    Iterable<Karyawan> getKaryawanList() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
