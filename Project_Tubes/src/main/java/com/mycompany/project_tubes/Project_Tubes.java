/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.project_tubes;

/**
 *
 * @author putra
 */

import BackendSystemPackage.VerifikasiSystem;
import BackendSystemPackage.LemburSystem;
import BackendSystemPackage.Karyawan;
import BackendSystemPackage.BadanKeuangan;
import UIPackage.AplikasiController;
import java.util.*;

public class Project_Tubes {

    public static void main(String[] args) {
        //LemburSystem
        LemburSystem ls = new LemburSystem();
        ls.tambahKodeLembur("KL001");
        ls.tambahKodeLembur("KL002");
        
        // Username & Password akun BadanKeuangan
        BadanKeuangan bk = new BadanKeuangan("admin", "12345", 1000000000); 
        
        // Username & Password sistem Verifikasi
        VerifikasiSystem vs = new VerifikasiSystem("verifikasi","12345",bk.getSaldo());
        
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
        int hari = 1;
        int bulan = 1;
        int tahun = 2023;
        
        AplikasiController aplikasiController = new AplikasiController();
        aplikasiController.setBadanKeuangan(bk);
        aplikasiController.setKaryawanList(kl);
        aplikasiController.setVerifikasiSystem(vs);
        aplikasiController.setLemburSystem(ls);
        aplikasiController.showDefaultView(hari, bulan, tahun);
        


//                                case 2:
//                                    System.out.println("\nInformasi Gaji Anda:");
//                                    System.out.print("Input bulan: ");
//                                    bulan = input.nextInt();
//                                    System.out.print("Input tahun: ");
//                                    tahun = input.nextInt();
//                                    String key = bulan + "-" +tahun;
//                                    if (karyawan.gajiWaktuItu != null && karyawan.gajiWaktuItu.containsKey(key)) {
//                                        int gaji = karyawan.gajiWaktuItu.get(key);
//                                        System.out.println("Gaji pada bulan " + bulan + " tahun " + tahun + " untuk " + karyawan.username + " adalah " + gaji);
//                                        if (karyawan.pajakWaktuItu != null && karyawan.pajakWaktuItu.containsKey(key)) {
//                                            int pajak = karyawan.pajakWaktuItu.get(key);
//                                            System.out.println("Pajak pada bulan " + bulan + " tahun " + tahun + " untuk " + karyawan.username + " adalah " + pajak);
//                                        } else {
//                                            System.out.println("Belum ada data pajak pada bulan " + bulan + " tahun " + tahun + " untuk " + karyawan.username);
//                                        }
//                                    } else {
//                                        System.out.println("Belum ada data gaji pada bulan " + bulan + " tahun " + tahun + " untuk " + karyawan.username);
//                                    }
//                                    System.out.println("Total gaji yang telah didapatkan: "+karyawan.getGajiDidapatkan());
//                                    if (karyawan.getbisaLembur()){
//                                        System.out.println("Total waktu lembur: "+karyawan.getWaktuLemburDone());
//                                        System.out.println("Total upah lembur yang telah didapatkan: "+karyawan.getUpahLembur());
//                                    }
//                                    System.out.println("Total pajak yang telah dibayarkan: "+karyawan.getPajakTerbayarkan());
//                                    break;
//                                case 3:
//                                    System.out.println("\nPengajuan waktu lembur yang telah dilakukan:");
//                                    if (karyawan.getbisaLembur()){
//                                        System.out.println("Input kode lembur");
//                                        String kode = input.next();
//                                        System.out.println("Input waktu lembur (dalam satuan jam)");
//                                        int waktuLembur = input.nextInt();
//                                        karyawan.inputWaktuLembur(ls, kode, waktuLembur);
//                                        break;
//                                    }else{
//                                        System.out.println("Anda tidak memenuhi syarat untuk mengajukan waktu lembur\n");
//                                        break;
//                                    }   
//                                case 0:
//                                    System.out.println("Anda keluar dari sistem Karyawan.");
//                                    System.out.println("Anda akan dikembalikan ke program utama.\n");
//                                    break;
//                                default:
//                                    System.out.println("Pilihan tidak valid.");
//                            }
//                        }while (pilihanMenu != 0);
//                    }                     
//                }
//                if (!loginKaryawan){
//                    System.out.println("Login karyawan gagal!");
//                    System.out.println("Anda akan dikembalikan ke program utama.\n");
//                }
//            } else if (mainMenu == 3){
//                System.out.println("Terima kasih telah menggunakan program ini.");
//                break;
//            } else {
//                System.out.println("Pilihan tidak valid.");
//                break;
//            }
//        } while (mainMenu != 0);
    }
}
