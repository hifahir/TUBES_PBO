package DBPegawai;
import java.util.List;
import BackendSystemPackage.Karyawan;
import BackendSystemPackage.BadanKeuangan;
import java.util.ArrayList;
public interface DAOInterface {
    public void updateAdmin(BadanKeuangan keuangan, int pengurangan);
    public ArrayList<Karyawan> getAllKaryawan();
    public ArrayList<BadanKeuangan> getAllKeuangan();
    public void insertGaji(Karyawan karyawan, int bulan, int tahun);
    public void insertLembur(Karyawan karyawan, int hari, int bulan, int tahun);
    public boolean isBulanTahunExists(Karyawan karyawan, String bulanTahun);
    public boolean isHariBulanTahunExists(Karyawan karyawan, String haribulanTahun);
}
