/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UIPackage;

import BackendSystemPackage.BadanKeuangan;
import BackendSystemPackage.Karyawan;
import BackendSystemPackage.LemburSystem;
import BackendSystemPackage.VerifikasiSystem;
import DBPegawai.DAOInterface;
import DBPegawai.PegawaiDAO;
import java.util.*;

/**
 *
 * @author RH
 */
public class AplikasiController implements SimpanListener{
    private BadanKeuangan badanKeuangan;
    private Karyawan karyawan;
    private ArrayList<Karyawan> karyawanList;
    private VerifikasiSystem verifikasiSystem;
    private LoginFrame loginFrame;
    private LoginBadanKeuanganFrame loginBadanKeuanganFrame;
    private MenuBadanKeuanganFrame menuBadanKeuanganFrame;
    private MenuKaryawanFrame menuKaryawanFrame;
    private SlipGajiFrame slipGajiFrame;
    private int hari;
    private int bulan;
    private int tahun;
    private PegawaiDAO dao;
    
    public AplikasiController(){
        this.dao = new PegawaiDAO();
    }
    
    public void setBadanKeuangan(BadanKeuangan badanKeuangan) {
        this.badanKeuangan = badanKeuangan;
    }
    
    public void setVerifikasiSystem(VerifikasiSystem verifikasiSystem){
        this.verifikasiSystem = verifikasiSystem;
    }
    
//    public ArrayList<Karyawan> getKaryawanList() {
//        return karyawanList;
//    }
    
    public void setKaryawan(Karyawan karyawan) {
        this.karyawan = karyawan;
    }

    public void setKaryawanList(ArrayList<Karyawan> karyawanList) {
        this.karyawanList = karyawanList;
    }

    public void setDao(PegawaiDAO dao) {
        this.dao = dao;
    }
    
    
    
    public void showDefaultView(int hari, int bulan, int tahun){
        loginFrame = new LoginFrame();
        loginFrame.addSimpanListener(this);
        loginFrame.setTanggalText(hari, bulan, tahun);
        loginFrame.setLocationRelativeTo(null);
        
        badanKeuangan.setDao(dao);
        karyawan.setDao(dao);
        
        loginBadanKeuanganFrame = new LoginBadanKeuanganFrame();
        loginBadanKeuanganFrame.setBadanKeuangan(badanKeuangan);
        loginBadanKeuanganFrame.addSimpanListener(this);
        loginBadanKeuanganFrame.setLocationRelativeTo(null);
        
        slipGajiFrame = new SlipGajiFrame();
        slipGajiFrame.addSimpanListener(this);
        slipGajiFrame.setVerifikasiSystem(verifikasiSystem);
        slipGajiFrame.setLocationRelativeTo(null);
        
        menuBadanKeuanganFrame = new MenuBadanKeuanganFrame(hari, bulan, tahun);
        menuBadanKeuanganFrame.setHari(hari);
        menuBadanKeuanganFrame.setBulan(bulan);
        menuBadanKeuanganFrame.setTahun(tahun);
        menuBadanKeuanganFrame.setBadanKeuangan(badanKeuangan);
        menuBadanKeuanganFrame.setKaryawan(karyawan);
        menuBadanKeuanganFrame.setVerifikasiSystem(verifikasiSystem);
        menuBadanKeuanganFrame.setKaryawanList(karyawanList);
        menuBadanKeuanganFrame.addSimpanListener(this);
        menuBadanKeuanganFrame.setLocationRelativeTo(null);
        menuBadanKeuanganFrame.setAplikasiController(this);
        menuBadanKeuanganFrame.setDao(dao);
        
        menuKaryawanFrame = new MenuKaryawanFrame(hari, bulan, tahun);
        menuKaryawanFrame.setHari(hari);
        menuKaryawanFrame.setBulan(bulan);
        menuKaryawanFrame.setTahun(tahun);
        menuKaryawanFrame.setDao(dao);
        menuKaryawanFrame.addSimpanListener(this);
        menuKaryawanFrame.setVerifikasiSystem(verifikasiSystem);
        menuKaryawanFrame.setKaryawanList(karyawanList);
        menuKaryawanFrame.setKaryawan(karyawan);
        menuKaryawanFrame.setLocationRelativeTo(null);
        
        loginFrame.setVisible(true);
    }

    @Override
    public void simpanOpsiLogin(int opsi) {
        if (opsi == 1){
            loginFrame.setVisible(false);
            loginBadanKeuanganFrame.setVisible(true);
        }else if (opsi  == 2){
            loginFrame.setVisible(false);
            menuKaryawanFrame.setVisible(true);
        }
    }

    @Override
    public void simpanOpsiLoginBadanKeuangan(int opsi) {
        if (opsi == 1){
            loginBadanKeuanganFrame.setVisible(false);
            menuBadanKeuanganFrame.setVisible(true);
        }else if(opsi == 2){
            loginBadanKeuanganFrame.setVisible(false);
            loginFrame.setVisible(true);
        }
    }
    
    @Override
    public void simpanOpsiMenuBadanKeuangan(int opsi){
        if (opsi == 7){
            menuBadanKeuanganFrame.setVisible(false);
            loginFrame.setVisible(true);
        } else if (opsi == 15){
            slipGajiFrame.setSlipGaji();
            slipGajiFrame.setVisible(true);
        } else if (opsi == 16){
            slipGajiFrame.setSlipLembur();
            slipGajiFrame.setVisible(true);
        } else if (opsi == 18){
            loginFrame.setTanggalText(menuBadanKeuanganFrame.getHari(), menuBadanKeuanganFrame.getBulan(), menuBadanKeuanganFrame.getTahun());
            menuBadanKeuanganFrame.setTanggal(menuBadanKeuanganFrame.getHari(), menuBadanKeuanganFrame.getBulan(), menuBadanKeuanganFrame.getTahun());
            menuKaryawanFrame.setTanggal(menuBadanKeuanganFrame.getHari(), menuBadanKeuanganFrame.getBulan(), menuBadanKeuanganFrame.getTahun());
        }
    }

    @Override
    public void simpanOpsiVerifikasi(int opsi) {
        if (opsi == 1){
            slipGajiFrame.setVisible(false);
        }
    }

    @Override
    public void simpanOpsiMenuKaryawan(int opsi) {
        if (opsi == 1){
            menuKaryawanFrame.setVisible(false);
            loginFrame.setVisible(true);
        } else if (opsi == 18){
            loginFrame.setTanggalText(menuKaryawanFrame.getHari(), menuKaryawanFrame.getBulan(), menuKaryawanFrame.getTahun());
            menuBadanKeuanganFrame.setTanggal(menuKaryawanFrame.getHari(), menuKaryawanFrame.getBulan(), menuKaryawanFrame.getTahun());
            menuKaryawanFrame.setTanggal(menuKaryawanFrame.getHari(), menuKaryawanFrame.getBulan(), menuKaryawanFrame.getTahun());
        }
    }
    
//    public void updateSaldo(){
//        dao.updateAdmin(this.badanKeuangan);
//        this.menuBadanKeuanganFrame.getjLabel8().setText(String.valueOf(this.badanKeuangan.getSaldo()));
//    }
    
}
