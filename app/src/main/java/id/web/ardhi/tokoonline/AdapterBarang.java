package id.web.ardhi.tokoonline;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.ViewHolderBarang> {
    private ArrayList<DataBarang> dataList;
    Context context;
    AdapterBarang(Context context, ArrayList<DataBarang> dataList) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterBarang.ViewHolderBarang onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_barang, parent, false);
        return new ViewHolderBarang(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterBarang.ViewHolderBarang holder, final int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtHarga.setText(dataList.get(position).getHarga());
        holder.txtKategori.setText(String.format("Kategori : %s", dataList.get(position).getKategori()));
        holder.card_row_barang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deskripsiActivity = new Intent(context, DeskripsiActivity.class);
                deskripsiActivity.putExtra("gambar", dataList.get(position).getGambar());
                deskripsiActivity.putExtra("nama", dataList.get(position).getNama());
                deskripsiActivity.putExtra("harga", dataList.get(position).getHarga());
                deskripsiActivity.putExtra("deskripsi", dataList.get(position).getDeskripsi());
                deskripsiActivity.putExtra("kategori", dataList.get(position).getKategori());
                context.startActivity(deskripsiActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    class ViewHolderBarang extends RecyclerView.ViewHolder{
        private TextView txtNama;
        private TextView txtHarga;
        private TextView txtKategori;
        private CardView card_row_barang;
        ViewHolderBarang(@NonNull View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.txt_nama);
            txtHarga = (TextView) itemView.findViewById(R.id.txt_harga);
            txtKategori = (TextView) itemView.findViewById(R.id.txt_kategori);
            card_row_barang = (CardView) itemView.findViewById(R.id.card_row_barang);
        }
    }
}
