package com.example.notassqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DataBaseManager manager;
    private Cursor cursor;
    private ListView lista;
    private SimpleCursorAdapter adapter;
    private TextView CampText;
    private ImageButton BusqNota;
    private ImageButton DeleteN;
    private Button ExitApli;
    private Button AgragarN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new DataBaseManager(this);
        lista = findViewById(R.id.ListView);

        CampText = findViewById(R.id.editText);
        BusqNota = (ImageButton) findViewById(R.id.imageButton2);
        BusqNota.setOnClickListener((View.OnClickListener) this);
        DeleteN = (ImageButton) findViewById(R.id.butt);

        manager.insertar("tutorial", "Es necesario puto");
        manager.insertar("aplicacion", "jaja eres puto");

        String[] fron = new String[]{manager.CN_TITLE, manager.CN_CONTEND};
        int[] to = new int[]{android.R.id.text1, android.R.id.text2};
        cursor = manager.cargarCursorNotas();
        adapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, cursor, fron, to, 0);
        lista.setAdapter(adapter);


    }

    public void onClick(View view) {
        if (view.getId() == R.id.imageButton2) {

            Cursor c = manager.buscarNota(CampText.getText().toString());
            adapter.changeCursor(c);
        }
    }
}