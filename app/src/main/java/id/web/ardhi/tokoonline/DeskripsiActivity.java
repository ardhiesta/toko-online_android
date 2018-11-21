package id.web.ardhi.tokoonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeskripsiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskripsi);

        Intent intent = getIntent();
        TextView nam_txt = findViewById(R.id.nam_txt);
        nam_txt.setText(intent.getStringExtra("nama"));
        TextView har_txt = findViewById(R.id.har_txt);
        har_txt.setText(intent.getStringExtra("harga"));
        TextView des_txt = findViewById(R.id.des_txt);
        des_txt.setText(intent.getStringExtra("deskripsi"));
        TextView kat_txt = findViewById(R.id.kat_txt);
        kat_txt.setText(intent.getStringExtra("kategori"));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
