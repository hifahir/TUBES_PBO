package DBPegawai;
import DBPegawai.DBConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import BackendSystemPackage.Karyawan;

public class PegawaiDAO implements DAOInterface{
    List<Karyawan> listKaryawan;
    
    public void insert(Karyawan karyawan){
        String sql;
        sql = "INSERT into karyawan ( username, password, jabatan, gaji, bisalmebur, pajak) VALUES (?,?,?,?,?,?,?)";
        try(PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql)){
            statement.setString(1, karyawan.getUsername());
            statement.setString(2, karyawan.getPassword());
        }
        catch(SQLException e){
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }
    
    public List<Karyawan> getAll(){
        
    }
}
