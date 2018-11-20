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
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class KategoriActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private AdapterBarang adapterBarang;
    private ArrayList<DataBarang> dataBarangArrayList;
    private ArrayList<DataBarang> temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        displayBarang("all");

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
        TextView kheader = findViewById(R.id.kategori_title);
        if (id == R.id.nav_kategori1) {
            kheader.setText("Kategori : kesehatan");
            displayBarang("kesehatan");
        } else if (id == R.id.nav_kategori2) {
            kheader.setText("Kategori : mobil");
            displayBarang("mobil");
        } else if (id == R.id.nav_kategori3) {
            kheader.setText("Kategori : pakaian");
            displayBarang("pakaian");
        }

        adapterBarang = new AdapterBarang(dataBarangArrayList);

        recyclerView = findViewById(R.id.daftar_barang);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(KategoriActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterBarang);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void displayBarang(String kategori) {
        TextView kheader = findViewById(R.id.kategori_title);
        dataBarangArrayList = new ArrayList<>();
        switch (kategori){
            case "all":
                dataBarangArrayList.add(new DataBarang("Alamat Gambar", "Susu Bayi", "Rp. 5000,00","Deskripsi susu bayi","kesehatan"));
                dataBarangArrayList.add(new DataBarang("Alamat Gambar", "Avanza", "Rp. 500.000.000,00","Deskripsi avanza","mobil"));
                dataBarangArrayList.add(new DataBarang("Alamat Gambar", "Jaket Kulit", "Rp. 500.000.000,00","Deskripsi jaket kulit","pakaian"));
                break;
            case "kesehatan":
                dataBarangArrayList.add(new DataBarang("Alamat Gambar", "Susu Bayi", "Rp. 5000,00","Deskripsi susu bayi","kesehatan"));
                dataBarangArrayList.add(new DataBarang("Alamat Gambar", "Susu Bayi", "Rp. 5000,00","Deskripsi susu bayi","kesehatan"));
                dataBarangArrayList.add(new DataBarang("Alamat Gambar", "Susu Bayi", "Rp. 5000,00","Deskripsi susu bayi","kesehatan"));
                dataBarangArrayList.add(new DataBarang("Alamat Gambar", "Susu Bayi", "Rp. 5000,00","Deskripsi susu bayi","kesehatan"));
                break;
            case "mobil":
                dataBarangArrayList.add(new DataBarang("Alamat Gambar", "Avanza", "Rp. 500.000.000,00","Deskripsi avanza","mobil"));
                dataBarangArrayList.add(new DataBarang("Alamat Gambar", "Avanza", "Rp. 500.000.000,00","Deskripsi avanza","mobil"));
                dataBarangArrayList.add(new DataBarang("Alamat Gambar", "Avanza", "Rp. 500.000.000,00","Deskripsi avanza","mobil"));
                dataBarangArrayList.add(new DataBarang("Alamat Gambar", "Avanza", "Rp. 500.000.000,00","Deskripsi avanza","mobil"));
                break;
            case "pakaian":
                dataBarangArrayList.add(new DataBarang("Alamat Gambar", "Jaket Kulit", "Rp. 500.000.000,00","Deskripsi jaket kulit","pakaian"));
                dataBarangArrayList.add(new DataBarang("Alamat Gambar", "Jaket Kulit", "Rp. 500.000.000,00","Deskripsi jaket kulit","pakaian"));
                dataBarangArrayList.add(new DataBarang("Alamat Gambar", "Jaket Kulit", "Rp. 500.000.000,00","Deskripsi jaket kulit","pakaian"));
                dataBarangArrayList.add(new DataBarang("Alamat Gambar", "Jaket Kulit", "Rp. 500.000.000,00","Deskripsi jaket kulit","pakaian"));
                break;
        }
        if (!kategori.equals("all")) {
            kheader.setText(kategori);
        } else kheader.setText("");

        adapterBarang = new AdapterBarang(dataBarangArrayList);

        recyclerView = findViewById(R.id.daftar_barang);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(KategoriActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterBarang);
    }
}
