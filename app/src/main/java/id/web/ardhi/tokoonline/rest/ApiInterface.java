package id.web.ardhi.tokoonline.rest;

import java.util.List;

import id.web.ardhi.tokoonline.model.Barang;
import id.web.ardhi.tokoonline.model.Kategori;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("toko-online/public/api/kategori")
    Call<List<Kategori>> getAPIKategori();

    @GET("toko-online/public/api/produk")
    Call<List<Barang>> getAPIData();

    @GET("toko-online/public/api/produk/kategori/{id_kategori}")
    Call<List<Barang>> getAPIDataWithKategori(@Path("id_kategori") int id_kategori);
}
