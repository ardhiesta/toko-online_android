package id.web.ardhi.tokoonline.rest;

import java.util.List;

import id.web.ardhi.tokoonline.model.Produk;
import id.web.ardhi.tokoonline.model.Kategori;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("api/kategori")
    Call<List<Kategori>> getAPIKategori();

    @GET("api/produk")
    Call<List<Produk>> getAPIData();

    @GET("api/produk/kategori/{id_kategori}")
    Call<List<Produk>> getAPIDataWithKategori(@Path("id_kategori") int id_kategori);

    @GET("api/produk/nama/{keyword}")
    Call<List<Produk>> getAPIDataWithSearch(@Path("keyword") String keyword);
}
