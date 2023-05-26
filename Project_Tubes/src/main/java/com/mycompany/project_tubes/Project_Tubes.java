/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.project_tubes;

/**
 *
 * @author putra
 */

import java.util.*;

public class Project_Tubes {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        //LemburSystem
        LemburSystem ls = new LemburSystem();
        ls.tambahKodeLembur("KL001");
        ls.tambahKodeLembur("KL002");
        
        // Username & Password akun BadanKeuangan
        BadanKeuangan bk = new BadanKeuangan("admin", "12345", 1000000000); 
        
        // Username & Password Beberapa akun karyawan yang telah ada
        Karyawan kw1 = new Karyawan("Fariz", "12345", "Manager");
        Karyawan kw2 = new Karyawan("Nurul", "12345", "Staff");
        Karyawan kw3 = new Karyawan("Raffa", "12345", "Staff");
        Karyawan kw4 = new Karyawan("Rihan", "12345", "Supervisor");
        //Membuat ArrayList untuk menyimpan objek Karyawan
        ArrayList<Karyawan> kl = new ArrayList<>();
        kl.add(kw1);
        kl.add(kw2);
        kl.add(kw3);
        kl.add(kw4);
        
        //Bulan & tahun awal
        int bulan = 1;
        int tahun = 2023;
        
        int mainMenu;
        
        do {
            // Tampilkan menu pilihan
            System.out.println("Selamat datang di program ini.");
            System.out.print("Sekarang adalah bulan ");
            switch (bulan){
                case 1 -> System.out.print("Januari, tahun "+tahun+"\n");
                case 2 -> System.out.print("Februari, tahun "+tahun+"\n");
                case 3 -> System.out.print("Maret, tahun "+tahun+"\n");
                case 4 -> System.out.print("April, tahun "+tahun+"\n");
                case 5 -> System.out.print("Mei, tahun "+tahun+"\n");
                case 6 -> System.out.print("Juni, tahun "+tahun+"\n");
                case 7 -> System.out.print("Juli, tahun "+tahun+"\n");
                case 8 -> System.out.print("Agustus, tahun "+tahun+"\n");
                case 9 -> System.out.print("September, tahun "+tahun+"\n");
                case 10 -> System.out.print("Oktober, tahun "+tahun+"\n");
                case 11 -> System.out.print("November, tahun "+tahun+"\n");
                case 12 -> System.out.print("Desember, tahun "+tahun+"\n");
            }
            System.out.println("Menu:");
            System.out.println("1. Login akun Badan Keuangan");
            System.out.println("2. Login akun Karyawan");
            System.out.println("3. End program");
            System.out.print("Pilihan: ");
            mainMenu = input.nextInt();
            
            // Jika akun Badan Keuangan
            if (mainMenu == 1){
                System.out.println("Masukkan username Badan Keuangan: ");
                String uname = input.next();
                System.out.println("Masukkan password Badan Keuangan: ");
                String ps = input.next();
                if (bk.Login(uname, ps)){
                    //Kalau login berhasil, akan memperlihatkan menu untuk BadanKeuangan
                    System.out.println("Login berhasil!\n");
                    System.out.println("Selamat datang, "+bk.username+"!\n");

                    int pilihanMenu;
                    do {
                        // Tampilkan menu pilihan Badan Keuangan
                        System.out.println("Menu Badan Keuangan:");
                        System.out.println("1. Lihat saldo");
                        System.out.println("2. Melihat data karyawan");
                        System.out.println("3. Transfer gaji");
                        System.out.println("4. Transfer upah lembur");
                        System.out.println("5. Cetak laporan gaji perusahaan");
                        System.out.println("6. Setting waktu");
                        System.out.println("0. Keluar");
                        System.out.print("Pilihan: ");
                        pilihanMenu = input.nextInt();

                        switch (pilihanMenu){
                            case 1:
                                System.out.println("Saldo badan keuangan: "+bk.getSaldo()+"\n");
                                break;
                            case 2:
                                System.out.println("\nData karyawan:");
                                System.out.print("Masukkan username karyawan: ");
                                String inputUKar = input.next();
                                boolean ditemukan = false;
                                for (Karyawan karyawan : kl) {
                                    if (inputUKar.equals(karyawan.username)){
                                        System.out.println(karyawan.username + ", " + karyawan.getJabatan() + ", Gaji jabatan: " + karyawan.getGaji());
                                        System.out.println("");
                                        ditemukan = true;
                                    }
                                }
                                if (!ditemukan){
                                    System.out.println("Username tidak ada/salah.\n");
                                }
                                break;
                            case 3:
                                System.out.println("\nMasukkan username karyawan untuk diberi gaji:");
                                String usernamegaji = input.next();
                                Karyawan kselect = null;

                                for (Karyawan k : kl) {
                                    if (k.username.equals(usernamegaji)) {
                                        kselect = k;
                                        break;
                                    }
                                }

                                if (kselect != null) {
                                    System.out.println("Gaji yang perlu dibayarkan adalah sebesar "+kselect.getGaji()+". Bayar?");
                                    System.out.println("1. Ya");
                                    System.out.println("2. Tidak");
                                    int pilih = input.nextInt();
                                    if(pilih == 1){
                                        bk.berikanGaji(kselect, bulan, tahun);
                                    }else{
                                        System.out.println("Pembayaran dibatalkan.");
                                    }
                                } else {
                                    System.out.println("Karyawan dengan username tersebut tidak ditemukan.");
                                }
                                System.out.println("");
                                break;
                            case 4:
                                System.out.println("\nMasukkan username karyawan untuk diberi upah lembur:");
                                String username = input.next();
                                Karyawan kselectUpah = null;

                                for (Karyawan k : kl) {
                                    if (k.username.equals(username)) {
                                        kselectUpah = k;
                                        break;
                                    }
                                }

                                if (kselectUpah != null) {
                                    if (kselectUpah.getbisaLembur()){
                                        System.out.println("Waktu yang diajukan adalah: "+kselectUpah.getWaktuLembur()+" jam, setuju?");
                                        System.out.println("1. Ya");
                                        System.out.println("2. Tidak");
                                        int setuju = input.nextInt();
                                        if (setuju == 1){
                                            bk.berikanUangLembur(kselectUpah, bulan, tahun);
                                        }else{
                                            System.out.println("Pembayaran dibatalkan.");
                                        }
                                    }else{
                                        System.out.println("Karyawan tidak memenuhi syarat untuk lembur");
                                    }
                                } else {
                                    System.out.println("Karyawan dengan username tersebut tidak ditemukan.");
                                }
           
                                System.out.println("");
                                break;
                            case 5:
                                System.out.println("\nLAPORAN GAJI PERUSAHAAN");
                                int i = 1;
                                for (Karyawan karyawan : kl){
                                    System.out.println(i+". Karyawan: "+karyawan.username);
                                    System.out.println("Gaji yang telah dibayarkan: "+karyawan.getgajiDidapatkan());
                                    System.out.println("");
                                    ++i;
                                }
                                break;
                            case 6:
                                System.out.println("\nSetting Waktu");
                                System.out.print("Input Bulan (1-12 untuk Januari-Desember): ");
                                bulan = input.nextInt();
                                System.out.print("Input Tahun: ");
                                tahun = input.nextInt();
                                break;
                            case 0:
                                System.out.println("Anda keluar dari sistem Badan Keuangan.");
                                System.out.println("Anda akan dikembalikan ke program utama.\n");
                                break;
                            default:
                                System.out.println("Pilihan tidak valid.");
                        }
                    } while(pilihanMenu != 0);

                }
                else{
                    System.out.println("Login gagal!");
                    System.out.println("Anda akan dikembalikan ke program utama.\n");
                }

            // Akun 2 / Karyawan
            } else if (mainMenu == 2){
                boolean loginKaryawan = false;
                System.out.println("\nMasukkan username Karyawan: ");
                String uname = input.next();
                System.out.println("Masukkan password Karyawan: ");
                String ps = input.next();
                for (Karyawan karyawan : kl){
                    if (karyawan.Login(uname, ps)){
                        loginKaryawan = true;
                        System.out.println("Login karyawan berhasil!");
                        System.out.println("\nSelamat datang, "+karyawan.username+"!");
                        int pilihanMenu;
                        
                        do {
                            // Tampilkan Menu Pilihan Karyawan
                            System.out.println("Menu Karyawan:");
                            System.out.println("1. Lihat data anda");
                            System.out.println("2. Melihat informasi gaji");
                            System.out.println("3. Ajukan lembur");
                            System.out.println("0. Keluar");
                            System.out.print("Pilihan: ");
                            pilihanMenu = input.nextInt();
                            
                            switch (pilihanMenu){
                                case 1:
                                    System.out.println("\nData Anda:");
                                    System.out.println("Nama: "+karyawan.username);
                                    System.out.println("Jabatan: "+karyawan.getJabatan()+"\n");
                                    break;
                                case 2:
                                    System.out.println("\nInformasi Gaji Anda:");
                                    System.out.print("Input bulan: ");
                                    bulan = input.nextInt();
                                    System.out.print("Input tahun: ");
                                    tahun = input.nextInt();
                                    String key = bulan + "-" +tahun;
                                    if (karyawan.gajiWaktuItu != null && karyawan.gajiWaktuItu.containsKey(key)) {
                                        int gaji = karyawan.gajiWaktuItu.get(key);
                                        System.out.println("Gaji pada bulan " + bulan + " tahun " + tahun + " untuk " + karyawan.username + " adalah " + gaji);
                                        if (karyawan.pajakWaktuItu != null && karyawan.pajakWaktuItu.containsKey(key)) {
                                            int pajak = karyawan.pajakWaktuItu.get(key);
                                            System.out.println("Pajak pada bulan " + bulan + " tahun " + tahun + " untuk " + karyawan.username + " adalah " + pajak);
                                        } else {
                                            System.out.println("Belum ada data pajak pada bulan " + bulan + " tahun " + tahun + " untuk " + karyawan.username);
                                        }
                                    } else {
                                        System.out.println("Belum ada data gaji pada bulan " + bulan + " tahun " + tahun + " untuk " + karyawan.username);
                                    }
                                    System.out.println("Total gaji yang telah didapatkan: "+karyawan.getGajiDidapatkan());
                                    if (karyawan.getbisaLembur()){
                                        System.out.println("Total waktu lembur: "+karyawan.getWaktuLemburDone());
                                        System.out.println("Total upah lembur yang telah didapatkan: "+karyawan.getUpahLembur());
                                    }
                                    System.out.println("Total pajak yang telah dibayarkan: "+karyawan.getPajakTerbayarkan());
                                    break;
                                case 3:
                                    System.out.println("\nPengajuan waktu lembur yang telah dilakukan:");
                                    if (karyawan.getbisaLembur()){
                                        System.out.println("Input kode lembur");
                                        String kode = input.next();
                                        System.out.println("Input waktu lembur (dalam satuan jam)");
                                        int waktuLembur = input.nextInt();
                                        karyawan.inputWaktuLembur(ls, kode, waktuLembur);
                                        break;
                                    }else{
                                        System.out.println("Anda tidak memenuhi syarat untuk mengajukan waktu lembur\n");
                                        break;
                                    }   
                                case 0:
                                    System.out.println("Anda keluar dari sistem Karyawan.");
                                    System.out.println("Anda akan dikembalikan ke program utama.\n");
                                    break;
                                default:
                                    System.out.println("Pilihan tidak valid.");
                            }
                        }while (pilihanMenu != 0);
                    }                     
                }
                if (!loginKaryawan){
                    System.out.println("Login karyawan gagal!");
                    System.out.println("Anda akan dikembalikan ke program utama.\n");
                }
            } else if (mainMenu == 3){
                System.out.println("Terima kasih telah menggunakan program ini.");
                break;
            } else {
                System.out.println("Pilihan tidak valid.");
                break;
            }
        } while (mainMenu != 0);
    }
}
