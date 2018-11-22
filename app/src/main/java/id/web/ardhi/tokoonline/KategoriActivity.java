package id.web.ardhi.tokoonline;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.web.ardhi.tokoonline.model.Barang;
import id.web.ardhi.tokoonline.model.Data;
import id.web.ardhi.tokoonline.rest.ApiClient;
import id.web.ardhi.tokoonline.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KategoriActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private AdapterBarang adapterBarang;
    List<Barang> dataBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        displayBarang("semua");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_semua){
            displayBarang("semua");
        } else if (id == R.id.nav_kategori1) {
            displayBarang("1");
        } else if (id == R.id.nav_kategori2) {
            displayBarang("2");
        } else if (id == R.id.nav_kategori3) {
            displayBarang("3");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void displayBarang(String kategori) {
        TextView kheader = findViewById(R.id.kategori_title);
        switch (kategori){
            case "semua":
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

                Call<Data> call = apiService.getData();

                call.enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        dataBarang = response.body().getData();
                        adapterBarang = new AdapterBarang(KategoriActivity.this, dataBarang);
                        recyclerView = findViewById(R.id.daftar_barang);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(KategoriActivity.this);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapterBarang);
                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {
                        Toast.makeText(KategoriActivity.this, "Silahkan Periksa Koneksi Internet Anda", Toast.LENGTH_LONG).show();
                    }
                });
                break;
        }
        if (!kategori.equals("semua")) {
            kheader.setVisibility(View.VISIBLE);
            kheader.setText(String.format("Kategori %s", kategori));
        } else kheader.setVisibility(View.GONE);
    }
}
