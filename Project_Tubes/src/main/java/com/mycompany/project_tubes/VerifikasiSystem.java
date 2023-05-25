/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_tubes;
import java.util.Random;
/**
 *
 * @author RH
 */
public class VerifikasiSystem{
    public String generateKodeVerifikasi(){
        Random random = new Random();
        int angka1 = random.nextInt(10);
        int angka2 = random.nextInt(10);
        int angka3 = random.nextInt(10);
        int angka4 = random.nextInt(10);
        String kodeVerifikasi = String.format("%d%d%d%d", angka1, angka2,
                angka3, angka4);
        
        return kodeVerifikasi;
    }
}