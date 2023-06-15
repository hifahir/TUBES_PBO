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
    
    public void updateAdmin(BadanKeuangan keuangan, int pengurangan) {
        String sql = "UPDATE badan_keuangan SET saldo = saldo - ? WHERE username = ?";
        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            statement.setInt(1, pengurangan);
            statement.setString(2, keuangan.getUsername());
            statement.executeUpdate();

            // Update the saldo of the BadanKeuangan object
            keuangan.setSaldo(keuangan.getSaldo() - pengurangan);
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }



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

    
    public void insertLembur(Karyawan karyawan, int hari, int bulan, int tahun) {
        String sql = "INSERT INTO transaksilembur (lemburWaktuItu, pajakWaktuItu, haribulantahun, username) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)) {
            // Mengambil data upah lembur dan pajak dari objek Karyawan
            String key = hari + "-" + bulan + "-" + tahun;
            
            statement.setInt(1, karyawan.getLemburWaktuItu());
            statement.setInt(2, karyawan.getPajakWaktuItu());
            statement.setString(3, key);
            statement.setString(4,karyawan.getUsername());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
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
                keuangan.setSaldo(result.getInt(3));
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


}
