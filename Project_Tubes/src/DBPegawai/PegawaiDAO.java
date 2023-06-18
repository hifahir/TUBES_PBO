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
    public void insertGaji(Karyawan karyawan, int bulan, int tahun, int gaji, int pajak) {
        String sql = "INSERT INTO transaksigaji (gajiWaktuItu, pajakWaktuItu, bulanTahun, username) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            String key = bulan + "-" + tahun;

            statement.setInt(1, gaji);
            statement.setInt(2, pajak);
            statement.setString(3, key);
            statement.setString(4, karyawan.getUsername());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertLembur(Karyawan karyawan, int hari, int bulan, int tahun, int harga, int pajak) {
        String sql = "INSERT INTO transaksilembur (lemburWaktuItu, pajakWaktuItu, haribulantahun, username) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            // Mengambil data upah lembur dan pajak dari objek Karyawan
            String key = hari + "-" + bulan + "-" + tahun;

            statement.setInt(1, harga); // Use the 'harga' parameter
            statement.setInt(2, pajak); // Use the 'pajak' parameter
            statement.setString(3, key);
            statement.setString(4, karyawan.getUsername());

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
    public boolean isHariBulanTahunExists(Karyawan karyawan, String haribulanTahun) {
        String sql = "SELECT COUNT(*) FROM transaksilembur WHERE username = ? AND haribulantahun = ?";
        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            statement.setString(1, karyawan.getUsername());
            statement.setString(2, haribulanTahun);
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
    public int getLemburWaktuItu(Karyawan karyawan, int hari, int bulan, int tahun) {
        String sql = "SELECT lemburWaktuItu FROM transaksilembur WHERE haribulantahun = ? AND username = ?";
        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            String key = hari + "-" + bulan + "-" + tahun;
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
    public int getPajakWaktuItu(Karyawan karyawan, int hari, int bulan, int tahun) {
        String sql = "SELECT pajakWaktuItu FROM transaksilembur WHERE haribulantahun = ? AND username = ?";
        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            String key = hari + "-" + bulan + "-" + tahun;
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
    
    public ArrayList<Object[]> getTransaksiGajiByYear(String bulanTahun) {
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
                    resultSet.getString("gajiWaktuItu"),
                    resultSet.getString("pajakWaktuItu"),
                    resultSet.getString("bulanTahun"),
                    resultSet.getString("username")
                };

                transaksiGajiList.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transaksiGajiList;
    }

    public ArrayList<Object[]> getTransaksiLemburByYear(String year) {
        ArrayList<Object[]> transaksiLemburList = new ArrayList<>();
        String sql = "SELECT * FROM transaksilembur WHERE hariBulanTahun LIKE ?";

        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            statement.setString(1, "%-%-" + year);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Assuming the columns in the transaksilembur table are of type String,
                // modify the following line accordingly based on the actual column types
                Object[] rowData = {
                    resultSet.getString("lemburWaktuItu"),
                    resultSet.getString("pajakWaktuItu"),
                    resultSet.getString("haribulantahun"),
                    resultSet.getString("username")
                };

                transaksiLemburList.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transaksiLemburList;
    }


}
