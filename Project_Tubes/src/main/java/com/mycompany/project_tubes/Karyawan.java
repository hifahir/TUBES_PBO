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
    private int gajiDidapatkan;
    private int jumlahLembur;
    private int gajiPerluDibayar;
    
    public Karyawan(String username, String password, String jabatan, int jumlahLembur){
        super(username, password);
        this.jabatan = jabatan;
        setGaji(jabatan);
        this.gajiDidapatkan = 0;
        this.gajiPerluDibayar = 0;
        this.jumlahLembur = jumlahLembur;
    }

    public String getJabatan(){
        return jabatan;
    }
    
    public int jumlahLembur(){
        return jumlahLembur;
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

   public int getGajiPerluDibayar(int bulan) {
    int totalLembur = jumlahLembur() * 125000;
    int gajiPerluDibayar = gaji + totalLembur;
    return gajiPerluDibayar;
}

}
