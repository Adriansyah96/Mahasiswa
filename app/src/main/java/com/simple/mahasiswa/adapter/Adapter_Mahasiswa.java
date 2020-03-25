package com.simple.mahasiswa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.simple.mahasiswa.R;
import com.simple.mahasiswa.model.Mahasiswa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Adapter_Mahasiswa extends RecyclerView.Adapter<Adapter_Mahasiswa.ViewHolder> implements Filterable {
    ArrayList<Mahasiswa>datalist;
    private Context context;
    ArrayList<Mahasiswa> mahasiswaAll;

    public Adapter_Mahasiswa(ArrayList<Mahasiswa> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
        mahasiswaAll = new ArrayList<>(datalist);
        mahasiswaAll = new ArrayList<>();
    }

    @Override
    public Adapter_Mahasiswa.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,  int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Mahasiswa.ViewHolder holder, final int position) {
        final String Snim = datalist.get(position).getNim();
        final String Snama = datalist.get(position).getNama();
        final String Skelas = datalist.get(position).getKelas();

        holder.nim.setText(Snim);
        holder.nama.setText(Snama);
        holder.kelas.setText(Skelas);

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }


    @Override
    public Filter getFilter() {

        return myFilter;
    }

    Filter myFilter = new Filter() {

        //Automatic on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<Mahasiswa> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(mahasiswaAll);
            } else {
                for (Mahasiswa mahasiswa: mahasiswaAll) {
                    if (mahasiswa.toString().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(mahasiswa);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        //Automatic on UI thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            datalist.clear();
            datalist.addAll((Collection<? extends Mahasiswa>) filterResults.values);
            notifyDataSetChanged();
        }
    };


//    @Override
//    public int getItemCount() {
//        return datalist.size();
//    }
//
//    @Override
//    public Filter getFilter() {
//        return filter;
//    }
//
//    Filter filter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//
//            ArrayList<Mahasiswa> filteredList = new ArrayList<>();
//            if (constraint.toString().isEmpty()){
//                filteredList.addAll(datalist);
//            }else {
//                for (Mahasiswa mahasiswa : mahasiswaAll){
//                    if (mahasiswa.toString().toLowerCase().contains(constraint.toString().toLowerCase())){
//                        filteredList.add(mahasiswa);
//                    }
//                }
//            }
//
//            FilterResults filterResults = new FilterResults();
//            filterResults.values = filteredList;
//
//            return filterResults;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            datalist.clear();
//            datalist.addAll((Collection<? extends Mahasiswa>) results.values);
//            notifyDataSetChanged();
//        }
//    };



    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nim,nama,kelas;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nim = itemView.findViewById(R.id.txt_nim);
            nama = itemView.findViewById(R.id.txt_nama);
            kelas = itemView.findViewById(R.id.txt_kelas);
        }
    }
}
