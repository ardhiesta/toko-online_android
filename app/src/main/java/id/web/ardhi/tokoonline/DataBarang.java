package id.web.ardhi.tokoonline;

public class DataBarang {
    private String gambar;
    private String nama;
    private String harga;
    private String deskripsi;
    private String kategori;

    public DataBarang(String gambar, String nama, String harga, String deskripsi, String kategori) {
        this.gambar = gambar;
        this.nama = nama;
        this.harga = harga;
        this.deskripsi = deskripsi;
        this.kategori = kategori;
    }

    public String getGambar() {
        return gambar;
    }

    public String getNama() {
        return nama;
    }

    public String getHarga() {
        return harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getKategori() {
        return kategori;
    }
}
