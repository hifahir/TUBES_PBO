package DBPegawai;
import BackendSystemPackage.BadanKeuangan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import BackendSystemPackage.Karyawan;
import java.util.Map;
import javax.swing.JOptionPane;

public class PegawaiDAO implements DAOInterface{
    ArrayList<Karyawan> listKaryawan;
    ArrayList<BadanKeuangan> listKeuangan;

    @Override
    public ArrayList<Karyawan> getAllKaryawan() {
        listKaryawan = new ArrayList<>();
        Statement statement;
        try{
            statement = DBConnector.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM karyawan");
            
            while(result.next()){
                Karyawan karyawan = new Karyawan();
                karyawan.setUsername(result.getString(1));
                karyawan.setPassword(result.getString(2));
                karyawan.setJabatan(result.getString(3));
                karyawan.setGaji(result.getInt(4));
                karyawan.setBisaLembur(result.getBoolean(5));
                karyawan.setPajak(result.getDouble(6));
                karyawan.setWaktuLembur(result.getInt(7));
                listKaryawan.add(karyawan);
            }
            statement.close();
            result.close();

            return listKaryawan;
        }
        catch(SQLException e){
            Logger.getLogger(PegawaiDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return listKaryawan;
    }
    
    @Override
    public void insertDataGaji(Karyawan karyawan, int bulan, int tahun, int hargaTotalLembur) {
        String sql = "INSERT INTO datagaji (gaji, lembur, jabatan, pajak, dataBulanTahun, username) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            String key = bulan + "-" + tahun;

            statement.setInt(1, karyawan.getGaji());
            statement.setInt(2, hargaTotalLembur);
            statement.setString(3, karyawan.getJabatan());
            statement.setInt(4, karyawan.potonganPajak(karyawan.getGaji()+karyawan.getLemburWaktuItu()));
            statement.setString(5, key);
            statement.setString(6, karyawan.getUsername());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void updateDataGaji(Karyawan karyawan, int bulan, int tahun, int hargaTotalLembur) {
        String sql = "UPDATE datagaji SET gaji = ?, lembur = ?, jabatan = ?, pajak = ? WHERE dataBulanTahun = ? AND username = ?";
        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            String key = bulan + "-" + tahun;

            statement.setInt(1, karyawan.getGaji());
            statement.setInt(2, hargaTotalLembur);
            statement.setString(3, karyawan.getJabatan());
            statement.setInt(4, karyawan.potonganPajak(karyawan.getGaji() + hargaTotalLembur));
            statement.setString(5, key);
            statement.setString(6, karyawan.getUsername());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void updateStatus(Karyawan karyawan, int bulan, int tahun) {
        String sql = "UPDATE datagaji SET statusPembayaran = ? WHERE dataBulanTahun = ? AND username = ?";
        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            String key = bulan + "-" + tahun;

            statement.setBoolean(1, true);
            statement.setString(2, key);
            statement.setString(3, karyawan.getUsername());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
    @Override
    public boolean dataGajiBulanTahun(Karyawan karyawan, String dataBulanTahun) {
        String sql = "SELECT COUNT(*) FROM datagaji WHERE username = ? AND dataBulanTahun = ?";
        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            statement.setString(1, karyawan.getUsername());
            statement.setString(2, dataBulanTahun);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public void insertTransaksi(Karyawan karyawan, int bulan, int tahun, int gaji, int lembur, int pajak) {
        String sql = "INSERT INTO transaksigaji (gajiWaktuItu, lemburWaktuItu, pajakWaktuItu, bulanTahun, username) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            String key = bulan + "-" + tahun;

            statement.setInt(1, gaji);
            statement.setInt(2, lembur);
            statement.setInt(3, pajak);
            statement.setString(4, key);
            statement.setString(5, karyawan.getUsername());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void updateWaktuLembur(Karyawan karyawan, int jamLembur) {
        String sql = "UPDATE karyawan SET waktuLembur = ? WHERE username = ?";
        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            statement.setInt(1, jamLembur);
            statement.setString(2, karyawan.getUsername());
            statement.executeUpdate();

            karyawan.setWaktuLembur(jamLembur);
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }
    
    @Override
    public void updateGaji(Karyawan karyawan, int gaji) {
        String sql = "UPDATE karyawan SET gaji = ? WHERE username = ?";
        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            statement.setInt(1, gaji);
            statement.setString(2, karyawan.getUsername());
            statement.executeUpdate();

            karyawan.setGaji(gaji);
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }
    
    @Override
    public ArrayList<BadanKeuangan> getAllKeuangan(){
        listKeuangan = new ArrayList<>();
        Statement statement;
        try{
            statement = DBConnector.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM badan_keuangan");
            
            while(result.next()){
                BadanKeuangan keuangan = new BadanKeuangan();
                keuangan.setUsername(result.getString(1));
                keuangan.setPassword(result.getString(2));
                listKeuangan.add(keuangan);
            }
            statement.close();
            result.close();

            return listKeuangan;
        }
        catch(SQLException e){
            Logger.getLogger(PegawaiDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return listKeuangan;
    }

    @Override
    public boolean isBulanTahunExists(Karyawan karyawan, String bulanTahun) {
        String sql = "SELECT COUNT(*) FROM transaksigaji WHERE username = ? AND bulanTahun = ?";
        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            statement.setString(1, karyawan.getUsername());
            statement.setString(2, bulanTahun);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isBisaLembur(Karyawan karyawan) {
        String sql = "SELECT COUNT(*) FROM karyawan WHERE username = ? AND bisalembur = ?";
        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            statement.setString(1, karyawan.getUsername());
            statement.setBoolean(2, karyawan.getbisaLembur());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public int getWaktuLembur(Karyawan karyawan) {
        String sql = "SELECT waktuLembur FROM karyawan WHERE username = ?";
        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            statement.setString(1, karyawan.getUsername());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("waktuLembur"); // Retrieve the value from the "waktuLembur" column
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Default value if no result is found or an error occurs
    }
    
    @Override
    public int getLemburWaktuItu(Karyawan karyawan, int bulan, int tahun) {
        String sql = "SELECT lemburWaktuItu FROM transaksigaji WHERE bulanTahun = ? AND username = ?";
        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            String key = bulan + "-" + tahun;
            statement.setString(1, key);
            statement.setString(2, karyawan.getUsername());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("lemburWaktuItu");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    @Override
    public int getPajakWaktuItu(Karyawan karyawan, int bulan, int tahun) {
        String sql = "SELECT pajakWaktuItu FROM transaksigaji WHERE bulanTahun = ? AND username = ?";
        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            String key = bulan + "-" + tahun;
            statement.setString(1, key);
            statement.setString(2, karyawan.getUsername());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("pajakWaktuItu");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public ArrayList<Object[]> getDataGajiBulanIni(String dataBulanTahun) {
        ArrayList<Object[]> dataGajiList = new ArrayList<>();
        String sql = "SELECT * FROM datagaji WHERE dataBulanTahun LIKE ?";
        
        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            statement.setString(1, "%" + dataBulanTahun);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");

                // Assuming the columns in the datagaji table are of type int, int, String, int, String, String,
                // modify the following line accordingly based on the actual column types
                Object[] rowData = {
                    resultSet.getString("username"),
                    resultSet.getString("jabatan"),
                    resultSet.getString("dataBulanTahun"),
                    resultSet.getInt("gaji"),
                    resultSet.getInt("lembur"),
                    resultSet.getInt("pajak"),
                    resultSet.getBoolean("statusPembayaran")
                };

                dataGajiList.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataGajiList;
    }
    
    public ArrayList<Object[]> getTransaksiByYear(String bulanTahun) {
        ArrayList<Object[]> transaksiGajiList = new ArrayList<>();
        String sql = "SELECT * FROM transaksigaji WHERE bulanTahun LIKE ?";

        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            statement.setString(1, "%-" + bulanTahun);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String bulanTahunValue = resultSet.getString("bulanTahun");
                String[] parts = bulanTahunValue.split("-");
                String tahun = parts[1];

                // Assuming the columns in the transaksigaji table are of type String,
                // modify the following line accordingly based on the actual column types
                Object[] rowData = {
                    resultSet.getString("username"),
                    resultSet.getString("gajiWaktuItu"),
                    resultSet.getString("lemburWaktuItu"),
                    resultSet.getString("pajakWaktuItu"),
                    resultSet.getString("bulanTahun")
                };

                transaksiGajiList.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transaksiGajiList;
    }
    
    public int getTotalGajiFromdatagaji() {
        int totalGaji = 0;
        String sql = "SELECT SUM(gaji) AS total FROM datagaji";

        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                totalGaji = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalGaji;
    }
    
    public int getTotalLemburFromdatagaji() {
        int totalLembur = 0;
        String sql = "SELECT SUM(lembur) AS total FROM datagaji";

        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                totalLembur = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalLembur;
    }
    
    
}
