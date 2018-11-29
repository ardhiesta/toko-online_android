package id.web.ardhi.tokoonline;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.web.ardhi.tokoonline.model.Barang;
import id.web.ardhi.tokoonline.model.Kategori;
import id.web.ardhi.tokoonline.rest.ApiClient;
import id.web.ardhi.tokoonline.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KategoriActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private AdapterBarang adapterBarang;
    List<Barang> barangList;
    List<Kategori> kategoriList;
    ApiInterface apiService;

    public KategoriActivity() {
        apiService = ApiClient.getClient().create(ApiInterface.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displayKategori(navigationView);
        displayBarang(R.id.nav_noKategori);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        item.setCheckable(true);
        item.setChecked(true);
        displayBarang(id);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void displayBarang(int id) {
        TextView kheader = findViewById(R.id.kategori_title);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        if (id != R.id.nav_noKategori) {
            kheader.setVisibility(View.VISIBLE);
            kheader.setText(String.format("Kategori %s", navigationView.getMenu().getItem(id).getTitle()));
            Call<List<Barang>> call = apiService.getAPIDataWithKategori(id);
            call.enqueue(new Callback<List<Barang>>() {
                @Override
                public void onResponse(@NonNull Call<List<Barang>> call, @NonNull Response<List<Barang>> response) {
                    barangList = response.body();
                    adapterBarang = new AdapterBarang(KategoriActivity.this, barangList);
                    recyclerView = findViewById(R.id.barang_list);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(KategoriActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapterBarang);
                }

                @Override
                public void onFailure(@NonNull Call<List<Barang>> call, @NonNull Throwable t) {
                    Toast.makeText(KategoriActivity.this, "Silahkan Periksa Koneksi Internet Anda", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            kheader.setVisibility(View.GONE);
            Call<List<Barang>> call = apiService.getAPIData();
            call.enqueue(new Callback<List<Barang>>() {
                @Override
                public void onResponse(@NonNull Call<List<Barang>> call, @NonNull Response<List<Barang>> response) {
                    barangList = response.body();
                    adapterBarang = new AdapterBarang(KategoriActivity.this, barangList);
                    recyclerView = findViewById(R.id.barang_list);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(KategoriActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapterBarang);
                }

                @Override
                public void onFailure(@NonNull Call<List<Barang>> call, @NonNull Throwable t) {
                    Toast.makeText(KategoriActivity.this, "Silahkan Periksa Koneksi Internet Anda", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void displayKategori(final NavigationView navigationView) {
        Call<List<Kategori>> call = apiService.getAPIKategori();
        call.enqueue(new Callback<List<Kategori>>() {
            @Override
            public void onResponse(@NonNull Call<List<Kategori>> call, @NonNull Response<List<Kategori>> response) {
                Menu menu = navigationView.getMenu();
                kategoriList = response.body();
                if (kategoriList != null) {
                    for(int i = 0; i < kategoriList.size(); i++) {
                        menu.add(R.id.nav_menu,kategoriList.get(i).getIdKategori(), Menu.FIRST + i, kategoriList.get(i).getNamaKategori());
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<Kategori>> call, @NonNull Throwable t) {
                Toast.makeText(KategoriActivity.this, "Silahkan Periksa Koneksi Internet Anda", Toast.LENGTH_LONG).show();
            }
        });
    }
}
