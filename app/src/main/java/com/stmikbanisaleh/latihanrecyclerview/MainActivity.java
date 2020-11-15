package com.stmikbanisaleh.latihanrecyclerview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView = null;
    private List<Dosen> list = new ArrayList<>();
    private DosenAdapter adapter = null;

    private DosenDao dao = null;
    private View viewDialog = null;
    private EditText textNama, textKompetensi;
    private CheckBox checkboxStatus;
    private Button btnSave, btnCancel, btnDelete;
    private AlertDialog dialog = null;
    private Dosen dosen = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        dao = AppDatabase.getDatabase(this).dosenDao();
        list = dao.getAll();
        adapter = new DosenAdapter(this, list, this);
        recyclerView.setAdapter(adapter);
        setupDialog();
    }

    private void setupDialog() {
        viewDialog = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);
        textNama = viewDialog.findViewById(R.id.editTextNama);
        textKompetensi = viewDialog.findViewById(R.id.editTextKompetensi);
        checkboxStatus = viewDialog.findViewById(R.id.checkboxStatus);
        btnSave = viewDialog.findViewById(R.id.btnSave);
        btnDelete = viewDialog.findViewById(R.id.btnDelete);
        btnCancel = viewDialog.findViewById(R.id.btnCancel);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Dosen Detail");
        builder.setView(viewDialog);
        dialog = builder.create();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textNama.getText().toString().isEmpty()) {
                    textNama.requestFocus();
                    textNama.setError("Nama dosen tidak boleh kosong");
                }else if (textKompetensi.getText().toString().isEmpty()){
                    textKompetensi.requestFocus();
                    textKompetensi.setError("Kompetensi dosen tidak boleh kosong");
                }else{
                    processSave();
                }

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processDelete();
            }
        });
    }

    private void showDialog() {
        textNama.setText(dosen.getNama());
        textKompetensi.setText(dosen.getKompetensi());
        checkboxStatus.setChecked(dosen.isStatus());
        dialog.show();
    }

    private void processSave() {
        dosen.setNama(textNama.getText().toString());
        dosen.setKompetensi(textKompetensi.getText().toString());
        dosen.setStatus(checkboxStatus.isChecked());
        try {
            if (dosen.getId() > 0) {
                dao.update(dosen);
            }else {
                long id = dao.insert(dosen);
                dosen.setId((int) id);
                list.add(0, dosen);
            }
            adapter.notifyDataSetChanged();
            dialog.dismiss();

            Toast.makeText(this, "Data dosen berhasil disimpan", Toast.LENGTH_SHORT).show();
        }catch (Exception ex) {
            Toast.makeText(this, "Data dosen gagal disimpan", Toast.LENGTH_LONG).show();
        }
    }

    private void processDelete() {
        try {
            list.remove(dosen);
            adapter.notifyDataSetChanged();
            dialog.dismiss();
            Toast.makeText(this, "Data dosen berhasil dihapus", Toast.LENGTH_SHORT).show();
        }catch (Exception ex) {
            Toast.makeText(this, "Data dosen gagal dihapus", Toast.LENGTH_LONG).show();
        }
    }

    public void buttonAddClick(View view) {
        dosen = new Dosen();
        dosen.setId(0);
        btnDelete.setVisibility(View.INVISIBLE);
        showDialog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Cari nama dosen");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    public void onClick(View view) {
        int position = recyclerView.getChildAdapterPosition(view);
        dosen = list.get(position);
        btnDelete.setVisibility(View.VISIBLE);
        showDialog();
    }
}
