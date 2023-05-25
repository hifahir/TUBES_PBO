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
    
<<<<<<< HEAD
    public void isiKodeLembur(LemburSystem lemburSystem, String kode) {
        lemburSystem.tambahKodeLembur(kode);
    }
    
    public void printKodeLembur(LemburSystem lemburSystem) {
        System.out.println("Kode Lembur: " + lemburSystem.getKodeLembur().toString());
    }

    public void printUsedKodeLembur(LemburSystem lemburSystem) {
        System.out.println("Kode Lembur yang telah digunakan: ");
        for (String kode : lemburSystem.getUsedKodeLembur()) {
            System.out.println(kode);
        }
    }
    
    public void berikanGaji(Karyawan karyawan){
        int gaji = karyawan.getGaji();
        if(saldo >= gaji ){
            saldo -= gaji;
            int pajak = karyawan.potonganPajak(gaji); // hitung pajak dan tambahkan ke method potonganPajak
            karyawan.tambahGaji(gaji-pajak);
            System.out.println("Gaji sebesar " + (gaji - pajak) + " telah diberikan ke " + karyawan.username);
            System.out.println("Pajak yang terbayarkan adalah "+pajak);
        }
        else{
            System.out.println("Gaji gagal diberikan kepada "+ karyawan.username +". Saldo tidak mencukupi");
        }
    }  
    
    public void berikanUangLembur(Karyawan karyawan, int jumlah){
        int jamLembur = karyawan.jumlahWaktuLembur();
        int hargaLembur = 150000;
        int total = jamLembur * hargaLembur;
        
        if (karyawan.getbisaLembur() && jamLembur != 0){
            if (saldo >= total){
                saldo -= total;
                int pajak = karyawan.potonganPajak(total);
                karyawan.tambahUpahLembur(total-pajak);
                System.out.println("Upah uang lembur sebesar "+(total-pajak)+" telah diberikan ke "+karyawan.username);
                System.out.println("Pajak yang terbayarkan adalah "+pajak);
            }else{
                System.out.println("Upah lembur gagal diberikan kepada "+ karyawan.username +". Saldo tidak mencukupi");
            }
        }else{
            System.out.println("Karyawan tidak memenuhi syarat lembur");
        }
    }
}
=======
   public void berikanGaji(Karyawan karyawan, int jumlah){
       if(saldo >= jumlah ){
           karyawan.tambahGaji(jumlah);
           saldo -= jumlah;
           System.out.println("Gaji sebesar " + jumlah + " telah diberikan ke " + karyawan.username);
       }
       else{
           System.out.println("Gaji gagal diberikan kepada "+ karyawan.username +". Saldo tidak mencukupi");
       }
   }
}
>>>>>>> a2e014e78cf0759097c9756a13314acd7303a428
