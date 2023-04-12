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
    private String jabatan;
    private int totalGaji;
    
    public Karyawan(String username, String password, String jabatan){
        super(username, password);
        this.jabatan = jabatan;
        setGaji(jabatan);
        this.totalGaji = 0;
    }

    public String getJabatan(){
        return jabatan;
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
        this.totalGaji += tambahanGaji;
    }
    
    public void infoGaji(int jumlah){
        System.out.println("Total gaji anda sekarang adalah Rp." + this.totalGaji);
    }
}
