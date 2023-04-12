/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_tubes;

/**
 *
 * @author RH
 */
public class BadanKeuangan extends User {
    private int saldo;
    
    
    public BadanKeuangan(String username, String password, int saldo){
        super(username, password);
        this.saldo = saldo;
    }
    
    public void kurangSaldo(int jumlah){
        jumlah -= saldo;
    }
    
   public void berikanGaji(Karyawan karyawan, int jumlah){
       if(saldo >= jumlah ){
           karyawan.infoGaji(jumlah);
           kurangSaldo(jumlah);
           System.out.println("Gaji berhasil diberikan kepada " + karyawan.username);
       }
       else{
           System.out.println("Gaji gagal diberikan kepada "+ karyawan.username +". Saldo tidak mencukupi");
       }
   }
}

