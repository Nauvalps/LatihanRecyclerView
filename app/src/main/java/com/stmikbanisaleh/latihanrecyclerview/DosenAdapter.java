package com.stmikbanisaleh.latihanrecyclerview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DosenAdapter extends RecyclerView.Adapter<DosenAdapter.DosenviewHolder> implements Filterable {

    private Context context;
    private List<Dosen> list = new ArrayList<>();
    private List<Dosen> listSelected = new ArrayList<>();
    private Map<String, String> initialColor = new HashMap<>();

    public DosenAdapter(Context context, List<Dosen> list) {
        this.context = context;
        this.list = list;
        this.listSelected = list;
        initialColor.put("N", "#aa00aa");
        initialColor.put("M", "#00aaaa");
        initialColor.put("S", "#0000aa");
        initialColor.put("H", "#aa0000");
        initialColor.put("R", "#aaaa00");
        initialColor.put("T", "#00aa00");
        initialColor.put("D", "#00aa00");
    }

    @NonNull
    @Override
    public DosenviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        DosenviewHolder holder = new DosenviewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DosenviewHolder holder, int position) {
        Dosen dosen = listSelected.get(position);
        String initial = dosen.getNama().substring(0, 1);
        String textStatus = dosen.isStatus() ? "Hadir" : "Tidak hadir";
        String colorStatus = dosen.isStatus() ? "#00aa00" : "#aa0000";
        holder.textInitial.setText(initial);
        holder.textNama.setText(dosen.getNama());
        holder.textKompetensi.setText(dosen.getKompetensi());
        holder.textStatus.setText(textStatus);
        holder.viewInitial.getBackground().setColorFilter(Color.parseColor(initialColor.get(initial)),
                PorterDuff.Mode.SRC_ATOP);
        holder.viewStatus.getBackground().setColorFilter(Color.parseColor(colorStatus), PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    public int getItemCount() {
        return listSelected.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Dosen> listFilter = new ArrayList<>();
                String search = constraint.toString();
                for (Dosen dosen: list) {
                    if (dosen.getNama().toLowerCase().contains(search.toLowerCase())){
                        listFilter.add(dosen);
                    }
                }
                listSelected = listFilter;
                FilterResults results = new FilterResults();
                results.values = listFilter;

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listSelected = (List<Dosen>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class DosenviewHolder extends RecyclerView.ViewHolder {
        LinearLayout viewInitial = null;
        TextView textNama, textStatus, textKompetensi, textInitial;
        View viewStatus = null;

        public DosenviewHolder(@NonNull View itemView) {
            super(itemView);
            viewInitial = itemView.findViewById(R.id.viewInitial);
            textNama = itemView.findViewById(R.id.textNama);
            textStatus = itemView.findViewById(R.id.textStatus);
            textKompetensi = itemView.findViewById(R.id.textKompetensi);
            textInitial = itemView.findViewById(R.id.textInitial);
            viewStatus = itemView.findViewById(R.id.viewStatus);
        }
    }
}
