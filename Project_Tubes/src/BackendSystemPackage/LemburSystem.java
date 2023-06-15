/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackendSystemPackage;

import java.util.*;

/**
 *
 * @author RH
 */
public class LemburSystem {
    private String kodeLembur;
    private String usedKodeLembur;

    public LemburSystem() {
    }

    public String getKodeLembur() {
        return kodeLembur;
    }

    public boolean isKodeLemburUsed(String kode) {
        return usedKodeLembur.contains(kode);
    }

    public String getUsedKodeLembur() {
        return usedKodeLembur;
    }
}
