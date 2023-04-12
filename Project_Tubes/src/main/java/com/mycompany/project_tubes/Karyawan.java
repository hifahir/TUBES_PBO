/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_tubes;

/**
 *
 * @author RH
 */
public class Karyawan extends User{
    private int gaji;
    
    public Karyawan(String username, String password, int gaji){
        super(username, password);
        this.gaji = gaji;
    }
    
    public void infoGaji(int jumlah){
        gaji += jumlah;
        System.out.println("Gaji anda sekarang adalah Rp." + gaji);
    }
}
