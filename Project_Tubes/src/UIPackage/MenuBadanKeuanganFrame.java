/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UIPackage;

import BackendSystemPackage.BadanKeuangan;
import BackendSystemPackage.Karyawan;
import BackendSystemPackage.VerifikasiSystem;
import DBPegawai.DAOInterface;
import DBPegawai.PegawaiDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author RH
 */
public class MenuBadanKeuanganFrame extends javax.swing.JFrame implements ActionListener {
    private AplikasiController aplikasiController;
    private BadanKeuangan badanKeuangan;
    private VerifikasiSystem verifikasiSystem;
    private Karyawan karyawan;
    private ArrayList<Karyawan> karyawanList;
    private SimpanListener simpan;
    private SlipGajiFrame slipGajiFrame;
    private int hari;
    private int bulan;
    private int tahun;
    private PegawaiDAO dao;
    
    
    /**
     * Creates new form MenuBadanKeuanganFrame
     */
    
    public MenuBadanKeuanganFrame(int hari, int bulan, int tahun) {
        initComponents();
        this.hari = hari;
        this.bulan = bulan;
        this.tahun = tahun;
        this.dao = new PegawaiDAO();
        setTanggal(hari, bulan, tahun);
        
        jButton1.addActionListener(this);
        jButton2.addActionListener(this);
        jButton3.addActionListener(this);
        jButton4.addActionListener(this);
        jButton5.addActionListener(this);
        jButton6.addActionListener(this);
        jButton7.addActionListener(this);
        jButton8.addActionListener(this);
        jButton9.addActionListener(this);
        jButton10.addActionListener(this);
        jButton11.addActionListener(this);
        jButton12.addActionListener(this);
        jButton13.addActionListener(this);
        jButton14.addActionListener(this);
        jButton15.addActionListener(this);
        jButton16.addActionListener(this);
        jButton17.addActionListener(this);
        jButton18.addActionListener(this);
        
        jLabel13.setVisible(false);
        jLabel14.setVisible(false);
        jButton10.setVisible(false);
        jButton11.setVisible(false);
        jLabel16.setVisible(false);
        jLabel17.setVisible(false);
        jButton13.setVisible(false);
        jButton14.setVisible(false);
    }

    public JLabel getjLabel8() {
        return jLabel8;
    }
    
    public void setBulan(int bulan) {
        this.bulan = bulan;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }
    
    public void setHari(int hari){
        this.hari = hari;
    }
    
    public void setTanggal(int hari, int bulan, int tahun){
        switch (this.bulan){
            case 1 -> jLabel28.setText(this.hari+", Januari, tahun "+this.tahun);
            case 2 -> jLabel28.setText(this.hari+", Februari, tahun "+this.tahun);
            case 3 -> jLabel28.setText(this.hari+", Maret, tahun "+this.tahun);
            case 4 -> jLabel28.setText(this.hari+", April, tahun "+this.tahun);
            case 5 -> jLabel28.setText(this.hari+", Mei, tahun "+this.tahun);
            case 6 -> jLabel28.setText(this.hari+", Juni, tahun "+this.tahun);
            case 7 -> jLabel28.setText(this.hari+", Juli, tahun "+this.tahun);
            case 8 -> jLabel28.setText(this.hari+", Agustus, tahun "+this.tahun);
            case 9 -> jLabel28.setText(this.hari+", September, tahun "+this.tahun);
            case 10 -> jLabel28.setText(this.hari+", Oktober, tahun "+this.tahun);
            case 11 -> jLabel28.setText(this.hari+", November, tahun "+this.tahun);
            case 12 -> jLabel28.setText(this.hari+", Desember, tahun "+this.tahun);
        }
    }

    public int getHari() {
        return hari;
    }

    public int getBulan() {
        return bulan;
    }

    public int getTahun() {
        return tahun;
    }
    
    
    
    public void setBadanKeuangan(BadanKeuangan badanKeuangan) {
        this.badanKeuangan = badanKeuangan;
        jLabel7.setText("Selamat datang, "+badanKeuangan.getUsername()+"!");
        jLabel8.setText("Rp. "+String.valueOf(badanKeuangan.getSaldo()));
    }
    
    private void updateSaldoLabel() {
        jLabel8.setText("Rp. " + String.valueOf(badanKeuangan.getSaldo()));
    }
    
    public void setAplikasiController(AplikasiController aplikasiController) {
        this.aplikasiController = aplikasiController;
    }

    public void setKaryawan(Karyawan karyawan) {
        this.karyawan = karyawan;
    }
    
    public void setKaryawanList(ArrayList<Karyawan> karyawanList) {
        this.karyawanList = karyawanList;
    }
    
    public ArrayList<Karyawan> getKaryawanList() {
        return karyawanList;
    }

    public void setVerifikasiSystem(VerifikasiSystem verifikasiSystem) {
        this.verifikasiSystem = verifikasiSystem;
    }
    
    public void addSimpanListener(SimpanListener simpan){
        this.simpan = simpan;
    }
    
//    private void printPembayaranGaji(Karyawan karyawan, int tahun) {
//        String keyPrefix = "-" + tahun;
//
//        if (gajiWaktuItu == null) {
//            jTextArea1.setText("Belum ada pembayaran gaji untuk karyawan " + karyawan.getUsername() + " pada tahun " + tahun);
//        } else {
//            TreeMap<Integer, String> sortedBulanMap = new TreeMap<>();
//
//            for (String key : gajiWaktuItu.keySet()) {
//                if (key.endsWith(keyPrefix)) {
//                    int bulan = Integer.parseInt(key.substring(0, key.indexOf("-")));
//                    sortedBulanMap.put(bulan, key);
//                }
//            }
//
//            StringBuilder sb = new StringBuilder();
//            sb.append("Pembayaran gaji untuk karyawan ").append(karyawan.getUsername()).append(" pada tahun ").append(tahun).append(":").append("\n");
//
//            for (Map.Entry<Integer, String> entry : sortedBulanMap.entrySet()) {
//                int bulan = entry.getKey();
//                String key = entry.getValue();
//
//                int gaji = gajiWaktuItu.get(key);
//                int pajak = pajakWaktuItu.get(key);
//
//                sb.append("Bulan ").append(bulan).append(": Gaji sebesar Rp. ").append(gaji);
//                sb.append(", dengan pajak Rp. ").append(pajak);
//                sb.append("\n");
//            }
//
//            jTextArea1.setText(sb.toString());
//        }
//    }
//
//    private void printPembayaranLembur(Karyawan karyawan, int tahun) {
//        String keyPrefix = "-" + tahun;
//
//        if (karyawan.getbisaLembur() && lemburWaktuItu != null) {
//            TreeMap<String, Integer> sortedLemburMap = new TreeMap<>();
//
//            for (String key : lemburWaktuItu.keySet()) {
//                if (key.endsWith(keyPrefix)) {
//                    sortedLemburMap.put(key, lemburWaktuItu.get(key));
//                }
//            }
//
//            StringBuilder sb = new StringBuilder();
//            sb.append("Pembayaran lembur untuk karyawan ").append(karyawan.getUsername()).append(" pada tahun ").append(tahun).append(":").append("\n");
//
//            for (Map.Entry<String, Integer> entry : sortedLemburMap.entrySet()) {
//                String key = entry.getKey();
//                int lembur = entry.getValue();
//
//                int hari = Integer.parseInt(key.substring(0, key.indexOf("-")));
//                int bulan = Integer.parseInt(key.substring(key.indexOf("-") + 1, key.lastIndexOf("-")));
//
//                sb.append("Hari ").append(hari).append(", Bulan ").append(bulan).append(": Lembur sebesar Rp. ").append(lembur);
//                sb.append("\n");
//            }
//
//            jTextArea1.setText(sb.toString());
//        } else {
//            jTextArea1.setText("Karyawan " + karyawan.getUsername() + " tidak memiliki data lembur pada tahun " + tahun);
//        }
//    }




    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButton1) {
            jTabbedPane1.setSelectedComponent(tab1); 
        } else if (e.getSource() == jButton2) {
            jTabbedPane1.setSelectedComponent(tab2); 
        } else if (e.getSource() == jButton3) {
            jTabbedPane1.setSelectedComponent(tab3); 
        } else if (e.getSource() == jButton4) {
            jTabbedPane1.setSelectedComponent(tab4); 
        } else if (e.getSource() == jButton5) {
            jTabbedPane1.setSelectedComponent(tab5); 
        } else if (e.getSource() == jButton6) {
            jTabbedPane1.setSelectedComponent(tab6); 
        } else if (e.getSource() == jButton7) {
            simpan.simpanOpsiMenuBadanKeuangan(7);
        } else if (e.getSource() == jButton8) {
            simpan.simpanOpsiMenuBadanKeuangan(8);
            List<Karyawan> karyawanList = dao.getAllKaryawan();
            String username = jTextField1.getText();
            boolean ditemukan = false;
            Karyawan karyawanDitemukan = null;
            
            for (Karyawan karyawan : karyawanList){
                if (username.equals(karyawan.getUsername())) {
                    karyawanDitemukan = karyawan;
                    ditemukan = true;
                    break;
                }
            }
            
            if (ditemukan){
                String jabatan = karyawanDitemukan.getJabatan();
                double gaji = karyawanDitemukan.getGaji();
                
                jLabel10.setText("Jabatan                : "+jabatan);
                jLabel11.setText("Gaji dari jabatan : Rp. "+String.format("%.2f",gaji));
                //simpan.updateSaldo();
            }else{
                JOptionPane.showMessageDialog(this, "Tidak ada Username tersebut.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == jButton9){
            ArrayList<Karyawan> karyawanList = dao.getAllKaryawan();
            
            String username = jTextField2.getText();
            boolean ditemukan = false;
            Karyawan karyawanDitemukan = null;
            
            for (Karyawan karyawan : karyawanList){
                if (username.equals(karyawan.getUsername())) {
                    karyawanDitemukan = karyawan;
                    ditemukan = true;
                    break;
                }
            }
            
            if (ditemukan){
                double gaji = karyawanDitemukan.getGaji();
                jLabel13.setText("Gaji yang perlu dibayarkan adalah sebesar Rp. "+String.format("%.2f",gaji));
                jLabel14.setText("Bayar?");
                jLabel13.setVisible(true);
                jLabel14.setVisible(true);
                jButton10.setVisible(true);
                jButton11.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(this, "Tidak ada Username tersebut.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else if (e.getSource() == jButton10){
            ArrayList<Karyawan> karyawanList = dao.getAllKaryawan();
            
            String username = jTextField2.getText();
            Karyawan karyawanDitemukan = null;
            
            for (Karyawan karyawan : karyawanList){
                if (username.equals(karyawan.getUsername())) {
                    karyawanDitemukan = karyawan;
                    break;
                }
            }
            
            badanKeuangan.berikanGaji(karyawanDitemukan, getBulan(), getTahun());
            
        }else if (e.getSource() == jButton11){
            JOptionPane.showMessageDialog(this, "Pembayaran dibatalkan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            jLabel13.setVisible(false);
            jLabel14.setVisible(false);
            jButton10.setVisible(false);
            jButton11.setVisible(false);
        }else if (e.getSource() == jButton12){
            ArrayList<Karyawan> karyawanList = dao.getAllKaryawan();
            
            String username = jTextField3.getText();
            boolean ditemukan = false;
            Karyawan karyawanDitemukan = null;
            
            for (Karyawan karyawan : karyawanList){
                if (username.equals(karyawan.getUsername())) {
                    karyawanDitemukan = karyawan;
                    ditemukan = true;
                    break;
                }
            }
            
            if (ditemukan){
                int jamLembur = karyawanDitemukan.getWaktuLembur();
                int hargaLembur = 150000;
                int total = jamLembur * hargaLembur;
                if (karyawanDitemukan.getbisaLembur() && jamLembur != 0){
                    jLabel16.setText("Waktu yang diajukan adalah: "+jamLembur+" jam.");
                    jLabel17.setText("Yang harus dibayar adalah: Rp. "+total+". Bayar?");
                    jLabel16.setVisible(true);
                    jLabel17.setVisible(true);
                    jButton13.setVisible(true);
                    jButton14.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(this, "Karyawan tidak memenuhi persyaratan lembur", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Tidak ada Username tersebut.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else if (e.getSource() == jButton13){
            ArrayList<Karyawan> karyawanList = dao.getAllKaryawan();
            
            String username = jTextField3.getText();
            Karyawan karyawanDitemukan = null;
            
            for (Karyawan karyawan : karyawanList){
                if (username.equals(karyawan.getUsername())) {
                    karyawanDitemukan = karyawan;
                    break;
                }
            }
            
            badanKeuangan.berikanUangLembur(karyawanDitemukan, getHari(), getBulan(), getTahun());
            updateSaldoLabel();
        }else if (e.getSource() == jButton14){
            JOptionPane.showMessageDialog(this, "Pembayaran dibatalkan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            jLabel16.setVisible(false);
            jLabel17.setVisible(false);
            jButton13.setVisible(false);
            jButton14.setVisible(false);
        }else if (e.getSource() == jButton15){
            ArrayList<Karyawan> karyawanList = dao.getAllKaryawan();
            
            String username = jTextField4.getText();
            String inputBulan = jTextField6.getText();
            String inputTahun = jTextField7.getText();
            Karyawan karyawanDitemukan = null;
            
            for (Karyawan karyawan : karyawanList){
                if (username.equals(karyawan.getUsername())) {
                    karyawanDitemukan = karyawan;
                    break;
                }
            }
            
            if (username != null && inputBulan != null && inputTahun != null){
                int bulanParse = Integer.parseInt(inputBulan);
                int tahunParse = Integer.parseInt(inputTahun);
                verifikasiSystem.verifikasiGaji(karyawanDitemukan, bulanParse, tahunParse);
                simpan.simpanOpsiMenuBadanKeuangan(15);
            }else{
                JOptionPane.showMessageDialog(this, "Cetak tidak berhasil, pastikan inputan ada, berupa integer dan bulan nilainya 1-12",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else if (e.getSource() == jButton16){
            ArrayList<Karyawan> karyawanList = dao.getAllKaryawan();
            
            String username = jTextField4.getText();
            String inputHari = jTextField5.getText();
            String inputBulan = jTextField6.getText();
            String inputTahun = jTextField7.getText();
            Karyawan karyawanDitemukan = null;
            
            for (Karyawan karyawan : karyawanList){
                if (username.equals(karyawan.getUsername())) {
                    karyawanDitemukan = karyawan;
                    break;
                }
            }
            
            if (username != null && inputBulan != null && inputTahun != null){
                int hariParse = Integer.parseInt(inputHari);
                int bulanParse = Integer.parseInt(inputBulan);
                int tahunParse = Integer.parseInt(inputTahun);
                verifikasiSystem.verifikasiLembur(karyawanDitemukan, hariParse, bulanParse, tahunParse);
                simpan.simpanOpsiMenuBadanKeuangan(16);
            }else{
                JOptionPane.showMessageDialog(this, "Cetak tidak berhasil, pastikan inputan ada, berupa integer dan bulan nilainya 1-12",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else if (e.getSource() == jButton17){
            ArrayList<Karyawan> karyawanList = dao.getAllKaryawan();
            
            String username = jTextField4.getText();
            String inputTahun = jTextField8.getText();
            Karyawan karyawanDitemukan = null;
            
            for (Karyawan karyawan : karyawanList){
                if (username.equals(karyawan.getUsername())) {
                    karyawanDitemukan = karyawan;
                    break;
                }
            }
            
            if (karyawanDitemukan != null) {
                int tahun;
                try {
                    tahun = Integer.parseInt(inputTahun);
//                    printPembayaranGaji(karyawanDitemukan, tahun);
//                    printPembayaranLembur(karyawanDitemukan, tahun);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Tahun harus berupa integer!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }   
        }else if (e.getSource() == jButton18){
            String inputHari = jTextField9.getText();
            String inputBulan = jTextField10.getText();
            String inputTahun = jTextField11.getText();
            
            int hariParse = Integer.parseInt(inputHari);
            int bulanParse = Integer.parseInt(inputBulan);
            int tahunParse = Integer.parseInt(inputTahun);
            
            this.hari = hariParse;
            this.bulan = bulanParse;
            this.tahun = tahunParse;
            JOptionPane.showMessageDialog(this, "Waktu telah diubah.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            simpan.simpanOpsiMenuBadanKeuangan(18);
        }
    }
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel20 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        tab1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tab2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tab3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        tab4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        tab6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        tab5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton17 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jLabel20.setText("jLabel20");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jButton1.setText("Lihat Saldo");

        jButton2.setText("Melihat data karyawan");

        jButton3.setText("Transfer Gaji");

        jButton4.setText("Transfer Upah Lembur");

        jButton5.setText("Percetakan");

        jButton6.setText("Setting Waktu");

        jButton7.setText("Keluar");

        jLabel7.setText("jLabel7");

        jLabel28.setText("jLabel28");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel28)
                .addGap(31, 31, 31)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, -1));

        jLabel1.setText("Saldo Badan Keuangan saat ini:");

        jLabel8.setText("jLabel8");

        javax.swing.GroupLayout tab1Layout = new javax.swing.GroupLayout(tab1);
        tab1.setLayout(tab1Layout);
        tab1Layout.setHorizontalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(223, Short.MAX_VALUE))
        );
        tab1Layout.setVerticalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addContainerGap(213, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab1", tab1);

        jLabel2.setText("DATA KARYAWAN:");

        jLabel9.setText("Username Karyawan:");

        jButton8.setText("Submit");

        javax.swing.GroupLayout tab2Layout = new javax.swing.GroupLayout(tab2);
        tab2.setLayout(tab2Layout);
        tab2Layout.setHorizontalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(tab2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jButton8))
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(jLabel2)))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        tab2Layout.setVerticalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel2)
                .addGap(40, 40, 40)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8))
                .addGap(60, 60, 60)
                .addComponent(jLabel10)
                .addGap(31, 31, 31)
                .addComponent(jLabel11)
                .addContainerGap(236, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", tab2);

        jLabel3.setText("Transfer Gaji");

        jLabel12.setText("Username Karyawan: ");

        jButton9.setText("Submit");

        jLabel13.setText("jLabel13");

        jLabel14.setText("jLabel14");

        jButton10.setText("Ya");

        jButton11.setText("Tidak");

        javax.swing.GroupLayout tab3Layout = new javax.swing.GroupLayout(tab3);
        tab3.setLayout(tab3Layout);
        tab3Layout.setHorizontalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab3Layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(jLabel3))
                    .addGroup(tab3Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(tab3Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jButton9))
                            .addGroup(tab3Layout.createSequentialGroup()
                                .addComponent(jButton10)
                                .addGap(18, 18, 18)
                                .addComponent(jButton11))
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        tab3Layout.setVerticalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel3)
                .addGap(40, 40, 40)
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton11))
                .addContainerGap(218, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", tab3);

        jLabel4.setText("Transfer Upah Lembur");

        jLabel15.setText("Username Karyawan:");

        jButton12.setText("Submit");

        jLabel16.setText("jLabel16");

        jLabel17.setText("jLabel17");

        jButton13.setText("Ya");

        jButton14.setText("Tidak");

        javax.swing.GroupLayout tab4Layout = new javax.swing.GroupLayout(tab4);
        tab4.setLayout(tab4Layout);
        tab4Layout.setHorizontalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tab4Layout.createSequentialGroup()
                        .addComponent(jButton13)
                        .addGap(18, 18, 18)
                        .addComponent(jButton14))
                    .addGroup(tab4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(tab4Layout.createSequentialGroup()
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton12))))
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        tab4Layout.setVerticalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel4)
                .addGap(37, 37, 37)
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12))
                .addGap(29, 29, 29)
                .addComponent(jLabel16)
                .addGap(26, 26, 26)
                .addComponent(jLabel17)
                .addGap(32, 32, 32)
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13)
                    .addComponent(jButton14))
                .addContainerGap(185, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab4", tab4);

        jLabel6.setText("Setting Waktu");

        jLabel25.setText("Hari:");

        jLabel26.setText("Bulan:");

        jLabel27.setText("Tahun:");

        jButton18.setText("Submit");

        javax.swing.GroupLayout tab6Layout = new javax.swing.GroupLayout(tab6);
        tab6.setLayout(tab6Layout);
        tab6Layout.setHorizontalGroup(
            tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab6Layout.createSequentialGroup()
                .addGroup(tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab6Layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(jLabel6))
                    .addGroup(tab6Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField9)
                            .addComponent(jTextField10)
                            .addComponent(jTextField11, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))
                    .addGroup(tab6Layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(jButton18)))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        tab6Layout.setVerticalGroup(
            tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab6Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel6)
                .addGap(36, 36, 36)
                .addGroup(tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jButton18)
                .addContainerGap(170, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab6", tab6);

        jLabel5.setText("Cetak Slip");

        jButton15.setText("Cetak Slip Gaji");

        jLabel18.setText("Username:");

        jLabel19.setText("Hari:");

        jLabel21.setText("Bulan:");

        jLabel22.setText("Tahun:");

        jButton16.setText("Cetak Slip Lembur");

        jLabel23.setText("Cetak Pembayaran Tahunan");

        jLabel24.setText("Tahun:");

        jButton17.setText("Cetak");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout tab5Layout = new javax.swing.GroupLayout(tab5);
        tab5.setLayout(tab5Layout);
        tab5Layout.setHorizontalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab5Layout.createSequentialGroup()
                        .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab5Layout.createSequentialGroup()
                                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab5Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab5Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(3, 3, 3)
                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton16)
                    .addComponent(jButton15)
                    .addComponent(jButton17))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(tab5Layout.createSequentialGroup()
                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab5Layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jLabel5))
                    .addGroup(tab5Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab5Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jLabel23)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tab5Layout.setVerticalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab5Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(12, 12, 12)
                        .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(18, 18, 18)
                        .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)))
                    .addGroup(tab5Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jButton15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton16)))
                .addGap(18, 18, 18)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab5", tab5);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, -36, 540, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuBadanKeuanganFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuBadanKeuanganFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuBadanKeuanganFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuBadanKeuanganFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new MenuBadanKeuanganFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JPanel tab1;
    private javax.swing.JPanel tab2;
    private javax.swing.JPanel tab3;
    private javax.swing.JPanel tab4;
    private javax.swing.JPanel tab5;
    private javax.swing.JPanel tab6;
    // End of variables declaration//GEN-END:variables

    
}
