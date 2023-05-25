/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_tubes;

import java.util.*;

/**
 *
 * @author RH
 */
public class LemburSystem {
    private List<String> kodeLembur;
    private Set<String> usedKodeLembur;

    public LemburSystem() {
        this.kodeLembur = new ArrayList<>();
        this.usedKodeLembur = new HashSet<>();
    }

    public void tambahKodeLembur(String kode) {
        kodeLembur.add(kode);
    }

    public List<String> getKodeLembur() {
        return kodeLembur;
    }

    public boolean isKodeLemburUsed(String kode) {
        return usedKodeLembur.contains(kode);
    }

    public void addUsedKodeLembur(String kode) {
        usedKodeLembur.add(kode);
    }

    public Set<String> getUsedKodeLembur() {
        return usedKodeLembur;
    }
}