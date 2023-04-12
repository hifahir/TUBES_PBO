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

    public int getSaldo() {
        return saldo;
    }
    
    public void berikanGaji(Karyawan karyawan, int jumlah){
        int gaji = karyawan.getGaji();
        if(saldo >= jumlah ){
            if (jumlah <= gaji){
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
}
