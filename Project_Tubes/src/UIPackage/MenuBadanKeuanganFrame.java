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
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
        setTanggalBadanKeuangan(hari, bulan, tahun);
        
        jButton1.addActionListener(this);
        jButton2.addActionListener(this);
        jButton3.addActionListener(this);
        jButton5.addActionListener(this);
        jButton6.addActionListener(this);
        jButton7.addActionListener(this);
        jButton8.addActionListener(this);
        jButton9.addActionListener(this);
        jButton10.addActionListener(this);
        jButton11.addActionListener(this);
        jButton15.addActionListener(this);
        jButton17.addActionListener(this);
        jButton18.addActionListener(this);
        editGaji.addActionListener(this);
        editWaktuLembur.addActionListener(this);
        konfirmasiDataButton.addActionListener(this);
        kalkulasiButton.addActionListener(this);
        
        jLabel13.setVisible(false);
        jLabel14.setVisible(false);
        jButton10.setVisible(false);
        jButton11.setVisible(false);
        editGaji.setVisible(false);
        editWaktuLembur.setVisible(false);
        konfirmasiDataButton.setVisible(false);
        hasilKalkulasi.setVisible(false);
        
        jTabbedPane1.setSelectedComponent(mainMenu);
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
    
    public void setTanggalBadanKeuangan(int hari, int bulan, int tahun) {
        LocalDate date = LocalDate.of(tahun, bulan, hari);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("d MMMM, 'tahun' yyyy", new Locale("id")));
        jLabel28.setText(formattedDate);
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

    public void setDao(PegawaiDAO dao) {
        this.dao = dao;
    }
    
    public void setBadanKeuangan(BadanKeuangan badanKeuangan) {
        this.badanKeuangan = badanKeuangan;
        jLabel7.setText("Selamat datang, "+badanKeuangan.getUsername()+"!");
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButton1){
            try {
                String dataBulanTahun = getBulan()+"-"+getTahun();
                // Retrieve data from the transaksigaji table based on the year
                ArrayList<Object[]> dataGaji = dao.getDataGajiBulanIni(dataBulanTahun);

                // Create a DefaultTableModel to hold the table data
                DefaultTableModel tableModel = new DefaultTableModel();

                // Add columns to the table model
                tableModel.addColumn("Karyawan");
                tableModel.addColumn("Jabatan");
                tableModel.addColumn("Tanggal");
                tableModel.addColumn("Gaji");
                tableModel.addColumn("Lembur");
                tableModel.addColumn("Pajak");
                tableModel.addColumn("Status Pembayaran");

                // Populate rows in the table model with data from the transaksiGajiList
                for (Object[] rowData : dataGaji) {
                    tableModel.addRow(rowData);
                }

                // Set the table model for jTable1
                jTable2.setModel(tableModel);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Tahun harus berupa integer!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            jTabbedPane1.setSelectedComponent(tab1);
        } else if (e.getSource() == kalkulasiButton){
            int totalGaji = dao.getTotalGajiFromdatagaji();
            int totalLembur = dao.getTotalLemburFromdatagaji();
            int totalPengeluaran = totalGaji + totalLembur;
            
            String hasilText = "Total gaji bulan ini: " + totalGaji + "\n"
                    + "Total lembur bulan ini: " + totalLembur + "\n"
                    + "Total yang harus dibayarkan: " + totalPengeluaran;

            hasilKalkulasi.setText(hasilText);
            hasilKalkulasi.setVisible(true);
        } else if (e.getSource() == jButton2) {
            jTabbedPane1.setSelectedComponent(tab2); 
        } else if (e.getSource() == jButton3) {
            jTabbedPane1.setSelectedComponent(tab3); 
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
                DecimalFormat decimalFormat = new DecimalFormat("#");
                String formattedGaji = decimalFormat.format(gaji);
                int waktuLembur = karyawanDitemukan.getWaktuLembur();
                
                jLabel10.setText("Jabatan                : "+jabatan);
                gajiPokokInfo.setText(formattedGaji);
                if (karyawanDitemukan.getbisaLembur()){
                    lemburInfo.setText(String.valueOf(waktuLembur));
                    editWaktuLembur.setVisible(true);
                }else{
                    lemburInfo.setText("Tidak terkualifikasi lembur.");
                }
                
                editGaji.setVisible(true);
                konfirmasiDataButton.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(this, "Tidak ada Username tersebut.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == editGaji){
            String gajiPokokInfoText = gajiPokokInfo.getText();
            String username = jTextField1.getText();
            List<Karyawan> karyawanList = dao.getAllKaryawan();
            Karyawan karyawanDitemukan = null;
            
            for (Karyawan karyawan : karyawanList){
                if (username.equals(karyawan.getUsername())) {
                    karyawanDitemukan = karyawan;
                    break;
                }
            }
            
            try{
                dao.updateGaji(karyawanDitemukan, Integer.parseInt(gajiPokokInfoText));
                JOptionPane.showMessageDialog(this, "Update gaji karyawan berhasil!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }catch (NumberFormatException n){
                JOptionPane.showMessageDialog(this, "Update dibatalkan. Nilai gaji tidak valid.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == editWaktuLembur){
            String waktuLembur = lemburInfo.getText();
            String username = jTextField1.getText();
            List<Karyawan> karyawanList = dao.getAllKaryawan();
            Karyawan karyawanDitemukan = null;
            
            for (Karyawan karyawan : karyawanList){
                if (username.equals(karyawan.getUsername())) {
                    karyawanDitemukan = karyawan;
                    break;
                }
            }
            
            try{
                dao.updateWaktuLembur(karyawanDitemukan, Integer.parseInt(waktuLembur));
                JOptionPane.showMessageDialog(this, "Update waktu lembur karyawan berhasil!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }catch (NumberFormatException n){
                JOptionPane.showMessageDialog(this, "Update dibatalkan. Nilai lembur tidak valid.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == konfirmasiDataButton){
            int confirmation = JOptionPane.showConfirmDialog(this, "Apakah Anda ingin mengkonfirmasi data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            
            String username = jTextField1.getText();
            List<Karyawan> karyawanList = dao.getAllKaryawan();
            Karyawan karyawanDitemukan = null;
            
            for (Karyawan karyawan : karyawanList){
                if (username.equals(karyawan.getUsername())) {
                    karyawanDitemukan = karyawan;
                    break;
                }
            }
            
            int jamLembur = dao.getWaktuLembur(karyawanDitemukan);
            int hargaLembur = 150000;
            int totalHargaLembur = jamLembur * hargaLembur;
    
            if (confirmation == JOptionPane.YES_OPTION) {
                String key = getBulan()+"-"+getTahun();
                if (dao.dataGajiBulanTahun(karyawanDitemukan, key)){
                    int confirmation2 = JOptionPane.showConfirmDialog(this, "Data ini ada. Update?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION){
                        dao.updateDataGaji(karyawanDitemukan, getBulan(), getTahun(), totalHargaLembur);
                    }else{
                        dispose();
                    }
                }else{
                    dao.insertDataGaji(karyawanDitemukan, getBulan(), getTahun(), totalHargaLembur);
                }
            } else {
                dispose();
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
                
                int totalHargaLembur = 0;
                if(dao.isBisaLembur(karyawanDitemukan)){
                    int jamLembur = dao.getWaktuLembur(karyawanDitemukan);
                    int hargaLembur = 150000;
                    totalHargaLembur = jamLembur * hargaLembur;
                }else{
                    totalHargaLembur = 0;
                }
                jLabel4.setText("Upah lembur yang perlu dibayarkan adalah sebesar Rp. "+totalHargaLembur);
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
            
            int bulanGaji = getBulan();
            int tahunGaji = getTahun();
            
            int totalHargaLembur = 0;
            if(dao.isBisaLembur(karyawanDitemukan)){
                int jamLembur = dao.getWaktuLembur(karyawanDitemukan);
                int hargaLembur = 150000;
                totalHargaLembur = jamLembur * hargaLembur;
            }else{
                totalHargaLembur = 0;
            }
            
            badanKeuangan.berikanGaji(karyawanDitemukan, bulanGaji, tahunGaji, karyawanDitemukan.getGaji(), totalHargaLembur); 
        }else if (e.getSource() == jButton11){
            JOptionPane.showMessageDialog(this, "Pembayaran dibatalkan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            jLabel13.setVisible(false);
            jLabel14.setVisible(false);
            jButton10.setVisible(false);
            jButton11.setVisible(false);
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
        }else if (e.getSource() == jButton17) {
            String inputTahun = jTextField8.getText();
            int tahun;

            try {

                // Retrieve data from the transaksigaji table based on the year
                ArrayList<Object[]> transaksiList = dao.getTransaksiByYear(inputTahun);

                // Create a DefaultTableModel to hold the table data
                DefaultTableModel tableModel = new DefaultTableModel();

                // Add columns to the table model
                tableModel.addColumn("Karyawan");
                tableModel.addColumn("Gaji");
                tableModel.addColumn("Upah Lembur");
                tableModel.addColumn("Pajak");
                tableModel.addColumn("Tanggal");

                // Populate rows in the table model with data from the transaksiGajiList
                for (Object[] rowData : transaksiList) {
                    tableModel.addRow(rowData);
                }

                // Set the table model for jTable1
                jTable1.setModel(tableModel);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Tahun harus berupa integer!", "Error", JOptionPane.ERROR_MESSAGE);
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
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        tab1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        kalkulasiButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        hasilKalkulasi = new javax.swing.JTextArea();
        tab2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        infoLembur = new javax.swing.JLabel();
        lemburInfo = new javax.swing.JTextField();
        editWaktuLembur = new javax.swing.JButton();
        gajiPokokInfo = new javax.swing.JTextField();
        editGaji = new javax.swing.JButton();
        konfirmasiDataButton = new javax.swing.JButton();
        tab3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
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
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton17 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        mainMenu = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();

        jLabel20.setText("jLabel20");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jButton2.setText("Pengaturan Data karyawan");

        jButton3.setText("Transfer Pembayaran");

        jButton5.setText("Percetakan");

        jButton6.setText("Setting Waktu");

        jButton7.setText("Keluar");

        jLabel7.setText("jLabel7");

        jLabel28.setText("jLabel28");

        jButton1.setText("Data Gaji");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
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
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addContainerGap(146, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, -1));

        jLabel1.setText("Informasi Data Gaji Bulan Ini");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable2);

        jLabel8.setText("Kalkulasikan total pembayaran bulan ini:");

        kalkulasiButton.setText("Kalkulasi");

        hasilKalkulasi.setColumns(20);
        hasilKalkulasi.setRows(5);
        jScrollPane3.setViewportView(hasilKalkulasi);

        javax.swing.GroupLayout tab1Layout = new javax.swing.GroupLayout(tab1);
        tab1.setLayout(tab1Layout);
        tab1Layout.setHorizontalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(kalkulasiButton))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(tab1Layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(jLabel1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        tab1Layout.setVerticalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(kalkulasiButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab6", tab1);

        jLabel2.setText("DATA KARYAWAN:");

        jLabel9.setText("Username Karyawan:");

        jButton8.setText("Submit");

        jLabel10.setText("Jabatan:");
        jLabel10.setToolTipText("");

        jLabel11.setText("Gaji Pokok: Rp.");

        infoLembur.setText("Waktu Lembur Bulan ini:");

        editWaktuLembur.setText("Edit");

        editGaji.setText("Edit");

        konfirmasiDataButton.setText("Konfirmasi Data");

        javax.swing.GroupLayout tab2Layout = new javax.swing.GroupLayout(tab2);
        tab2.setLayout(tab2Layout);
        tab2Layout.setHorizontalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(jLabel2))
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(tab2Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(35, 35, 35)
                                    .addComponent(jButton8))
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(tab2Layout.createSequentialGroup()
                                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(infoLembur, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lemburInfo)
                                    .addComponent(gajiPokokInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                                .addGap(40, 40, 40)
                                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(editWaktuLembur)
                                    .addComponent(editGaji)))
                            .addComponent(konfirmasiDataButton))))
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
                .addGap(39, 39, 39)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(gajiPokokInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(editGaji)))
                .addGap(18, 18, 18)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(infoLembur)
                    .addComponent(lemburInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editWaktuLembur))
                .addGap(18, 18, 18)
                .addComponent(konfirmasiDataButton)
                .addContainerGap(149, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", tab2);

        jLabel3.setText("Transfer Pembayaran");

        jLabel12.setText("Username Karyawan: ");

        jButton9.setText("Submit");

        jLabel13.setText("jLabel13");

        jLabel14.setText("jLabel14");

        jButton10.setText("Ya");

        jButton11.setText("Tidak");

        jLabel4.setText("jLabel4");

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
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(tab3Layout.createSequentialGroup()
                                .addComponent(jButton10)
                                .addGap(18, 18, 18)
                                .addComponent(jButton11))
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addGap(13, 13, 13)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton11))
                .addContainerGap(189, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", tab3);

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

        jLabel23.setText("Cetak Histori Tahunan");

        jLabel24.setText("Tahun:");

        jButton17.setText("Cetak Gaji");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout tab5Layout = new javax.swing.GroupLayout(tab5);
        tab5.setLayout(tab5Layout);
        tab5Layout.setHorizontalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab5Layout.createSequentialGroup()
                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab5Layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jLabel5))
                    .addGroup(tab5Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jLabel23)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(tab5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab5Layout.createSequentialGroup()
                        .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(tab5Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)))
                        .addGap(3, 3, 3)
                        .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton15)
                            .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30))
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
                        .addComponent(jButton15)))
                .addGap(18, 18, 18)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab5", tab5);

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel29.setText("Pilih Menu");

        javax.swing.GroupLayout mainMenuLayout = new javax.swing.GroupLayout(mainMenu);
        mainMenu.setLayout(mainMenuLayout);
        mainMenuLayout.setHorizontalGroup(
            mainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainMenuLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel29)
                .addContainerGap(392, Short.MAX_VALUE))
        );
        mainMenuLayout.setVerticalGroup(
            mainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainMenuLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel29)
                .addContainerGap(395, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab7", mainMenu);

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
    private javax.swing.JButton editGaji;
    private javax.swing.JButton editWaktuLembur;
    private javax.swing.JTextField gajiPokokInfo;
    private javax.swing.JTextArea hasilKalkulasi;
    private javax.swing.JLabel infoLembur;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JButton kalkulasiButton;
    private javax.swing.JButton konfirmasiDataButton;
    private javax.swing.JTextField lemburInfo;
    private javax.swing.JPanel mainMenu;
    private javax.swing.JPanel tab1;
    private javax.swing.JPanel tab2;
    private javax.swing.JPanel tab3;
    private javax.swing.JPanel tab5;
    private javax.swing.JPanel tab6;
    // End of variables declaration//GEN-END:variables

    
}
