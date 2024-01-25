package id.fakhrads.datapribadi.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_data")
public class DataPribadi {
    @jakarta.persistence.Id
    @Id
    private Long nik;

    @Column(nullable = false, name = "nama_lengkap")
    private String namaLengkap;

    @Transient
    private int umur;

    @Column(nullable = true, name = "jenis_kelamin")
    private String jenisKelamin;

    @Column(nullable = true, name = "tanggal_lahir")
    private LocalDate tanggalLahir;

    @Column(nullable = true, name = "alamat")
    private String alamat;

    @Column(nullable = true, name = "negara")
    private String negara;

    public Long getNik() {
        return nik;
    }

    public void setNik(Long nik) {
        this.nik = nik;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public LocalDate getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(LocalDate tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNegara() {
        return negara;
    }

    public void setNegara(String negara) {
        this.negara = negara;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }
}
