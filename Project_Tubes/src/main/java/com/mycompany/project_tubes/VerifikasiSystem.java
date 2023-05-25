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
    
    public void printSlipGaji(Karyawan karyawan, int tanggal) {
        int gaji = karyawan.getGaji();
        
        System.out.println("=====================================");
        System.out.println("           SLIP GAJI");
        System.out.println("=====================================");
        System.out.println("Nama: " + karyawan.username);
        System.out.println("Tanggal: " + tanggal);
        System.out.println("Gaji: " + gaji);
        System.out.println("=====================================");
    }
}