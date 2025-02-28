package com.example.appmobie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Add_Sevice extends AppCompatActivity {
    private EditText EtMadv, EtTendv, EtGiave;
    private SeviceManager sev;
    private Button btnAdd,btnEdit, btnDelete;
    private ImageView Exit;
    private Spinner SN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_sevice);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SN = findViewById(R.id.spinner_qldv);
        EtMadv = findViewById(R.id.edtxtmdv);
        EtTendv = findViewById(R.id.edtxtname);
        EtGiave = findViewById(R.id.edtxtfare);
        btnAdd = findViewById(R.id.btnadd);
        Exit = findViewById(R.id.btnex);
        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.BtnEdit);

        sev = new SeviceManager();

        btnAdd.setOnClickListener(v -> {
            String madv = EtMadv.getText().toString();
            String tendv = EtTendv.getText().toString();
            String giave = EtGiave.getText().toString();



            if (madv.isEmpty() || tendv.isEmpty() || giave.isEmpty()) {
                Toast.makeText(Add_Sevice.this, "Vui lòng điền tất cả các trường", Toast.LENGTH_SHORT).show();
                return;
            }
            new Thread(() -> {
                String response = sev.addSev (madv, tendv, giave);
                runOnUiThread(() -> Toast.makeText(Add_Sevice.this, "Add Response: " + response, Toast.LENGTH_SHORT).show());
            }).start();
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idStr = EtMadv.getText().toString();
                if (idStr.isEmpty()) {
                    Toast.makeText(Add_Sevice.this, "Vui lòng nhập ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                int id = Integer.parseInt(idStr);

                new Thread(() -> {
                    String response = sev.deleteSev(id);
                    runOnUiThread(() -> Toast.makeText(Add_Sevice.this, "Delete Response: " + response, Toast.LENGTH_SHORT).show());
                }).start();
            }
        });

        btnEdit.setOnClickListener(v -> {
            String madv = EtMadv.getText().toString();
            if (madv.isEmpty()) {
                Toast.makeText(Add_Sevice.this, "Vui lòng nhập ID", Toast.LENGTH_SHORT).show();
                return;
            }

            String tendv = EtTendv.getText().toString();
            String giave = EtGiave.getText().toString();

            if (madv.isEmpty() || tendv.isEmpty() || giave.isEmpty()) {
                Toast.makeText(Add_Sevice.this, "Vui lòng điền tất cả các trường", Toast.LENGTH_SHORT).show();
                return;
            }
            new Thread(() -> {
                String response = sev.updateSev(madv, tendv, giave);
                runOnUiThread(() -> Toast.makeText(Add_Sevice.this, "Update Response: " + response, Toast.LENGTH_SHORT).show());
            }).start();
        });

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_Sevice.this, SelectEmpoyee.class);
                startActivity(intent);
            }
        });

        String[] options = {"Select Activity", "View Car", "Add Car"};

        ArrayAdapter<String> adaptersev = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adaptersev.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SN.setAdapter(adaptersev);

        SN.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        Intent intentB = new Intent(Add_Sevice.this, View_Sevice.class);
                        startActivity(intentB);
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        String Data = getIntent().getStringExtra("Data");

        if (Data != null) {
            String[] parts = Data.split("\n");
            if (parts.length == 3) {
                EtMadv.setText(parts[0].replace("Mã dịch vụ: ", ""));
                EtTendv.setText(parts[1].replace("Tên dịch vụ: ", ""));
                EtGiave.setText(parts[2].replace("Giá vé: ", ""));


            }
        }
    }
}