package id.web.ardhi.tokoonline.rest;

import id.web.ardhi.tokoonline.model.Data;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("toko-online/public/api/produk")
    Call<Data> getData();
}
