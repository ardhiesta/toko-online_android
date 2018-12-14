package id.web.ardhi.tokoonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

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

        Intent intent = getIntent();
        Picasso.get().load(intent.getStringExtra("gambar")).into(des_img);
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

}
