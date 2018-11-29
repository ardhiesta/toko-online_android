package id.web.ardhi.tokoonline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kategori {

    @SerializedName("id_kategori")
    @Expose
    private Integer idKategori;
    @SerializedName("nama_kategori")
    @Expose
    private String namaKategori;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Kategori() {
    }

    public Integer getIdKategori() {
        return idKategori;
    }
    public String getNamaKategori() {
        return namaKategori;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}