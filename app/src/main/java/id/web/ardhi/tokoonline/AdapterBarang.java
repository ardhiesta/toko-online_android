package id.web.ardhi.tokoonline;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.ViewHolderBarang> {
    private ArrayList<DataBarang> dataList;
    private OnItemClickListener mlistener;

    AdapterBarang(ArrayList<DataBarang> dataList) {
        this.dataList = dataList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mlistener = listener;
    }

    @NonNull
    @Override
    public AdapterBarang.ViewHolderBarang onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_barang, parent, false);
        return new ViewHolderBarang(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBarang.ViewHolderBarang holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtHarga.setText(dataList.get(position).getHarga());
        holder.txtKategori.setText("Kategori : " + dataList.get(position).getKategori());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    class ViewHolderBarang extends RecyclerView.ViewHolder{
        private TextView txtNama;
        private TextView txtHarga;
        private TextView txtKategori;
        ViewHolderBarang(@NonNull View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.txt_nama);
            txtHarga = (TextView) itemView.findViewById(R.id.txt_harga);
            txtKategori = (TextView) itemView.findViewById(R.id.txt_kategori);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mlistener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            mlistener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
