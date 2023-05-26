/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_tubes;

/**
 *
 * @author RH
 */
public class VerifikasiSystem extends BadanKeuangan{
    
    public VerifikasiSystem(String username, String password, int saldo) {
        super(username, password, saldo);
    }
    
    public void verifikasi(Karyawan karyawan, int bulan, int tahun) {
        String kode = generateRandomString(10);

        printSlipGaji(karyawan, bulan, tahun, kode);
    }

    
    private void printSlipGaji(Karyawan karyawan, int bulan, int tahun, String kode) {
        System.out.println("===========================================");
        System.out.println("|          SLIP GAJI KARYAWAN             |");
        System.out.println("===========================================");
        System.out.println("| Nama                   : " + karyawan.username);
        System.out.println("| Bulan                  : " + bulan);
        System.out.println("| Tahun                  : " + tahun);
        System.out.println("| Gaji                   : " + karyawan.getGaji());
        System.out.println("| Pajak                  : " + karyawan.getPajak());
        System.out.println("| Kode Verifikasi        : " + kode);
        System.out.println("===========================================");
    }


    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
}
