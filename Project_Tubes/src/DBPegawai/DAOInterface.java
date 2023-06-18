package DBPegawai;
import java.util.List;
import BackendSystemPackage.Karyawan;
import BackendSystemPackage.BadanKeuangan;
import java.util.ArrayList;
public interface DAOInterface {
    public ArrayList<Karyawan> getAllKaryawan();
    public ArrayList<BadanKeuangan> getAllKeuangan();
    public void insertGaji(Karyawan karyawan, int bulan, int tahun, int gaji, int pajak);
    public void insertLembur(Karyawan karyawan, int hari, int bulan, int tahun, int harga, int pajak);
    public int getLemburWaktuItu(Karyawan karyawan, int hari, int bulan, int tahun);
    public int getPajakWaktuItu(Karyawan karyawan, int hari, int bulan, int tahun);
    public boolean isBulanTahunExists(Karyawan karyawan, String bulanTahun);
    public boolean isHariBulanTahunExists(Karyawan karyawan, String haribulanTahun);
    public boolean isBisaLembur(Karyawan karyawan);
    public void updateWaktuLembur(Karyawan karyawan, int jamLembur);
    public int getWaktuLembur(Karyawan karyawan);
}
