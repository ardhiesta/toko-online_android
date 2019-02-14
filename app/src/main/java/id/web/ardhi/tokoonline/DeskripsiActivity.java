package id.web.ardhi.tokoonline;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

import id.web.ardhi.tokoonline.rest.ApiClient;

public class DeskripsiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskripsi);

        ImageView des_img = findViewById(R.id.des_img);
        TextView nam_txt = findViewById(R.id.nam_txt);
        TextView har_txt = findViewById(R.id.har_txt);
        TextView des_txt = findViewById(R.id.des_txt);
        TextView kat_txt = findViewById(R.id.kat_txt);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String urlGambar = ApiClient.BASE_URL+ "/uploads/"+intent.getStringExtra("gambar");
        Picasso.get().load(urlGambar)
                .resize(256, 256)
//                .placeholder(R.drawable.baseline_cached_24)
//                .error(R.drawable.baseline_error_outline_24)
                .into(des_img);
        nam_txt.setText(intent.getStringExtra("nama"));
        har_txt.setText(String.format("Rp. %s",
                NumberFormat.getNumberInstance(Locale.ITALY).format(Integer.parseInt(intent.getStringExtra("harga")))));
        des_txt.setText(intent.getStringExtra("deskripsi"));
        kat_txt.setText(intent.getStringExtra("kategori"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
