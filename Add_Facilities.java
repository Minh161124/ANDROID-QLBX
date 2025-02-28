package com.example.appmobie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Add_Facilities extends AppCompatActivity {
    private EditText EtMaVT, EtTenVT, EtGia;
    private Button btnAdd,btnEdit, btnDelete;
    private ImageView Exit;
    private Spinner SnSL, SnHSD, SN;
    private FacilitiesManager fac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_facilities);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SN = findViewById(R.id.spinner_qlcsvc);
        SnSL = findViewById(R.id.spinner_total);
        SnHSD = findViewById(R.id.spinner_hsd);
        EtMaVT = findViewById(R.id.edtxtmvt);
        EtTenVT = findViewById(R.id.edtxtname);
        EtGia = findViewById(R.id.edtxtfare);
        btnAdd = findViewById(R.id.BtnAdd);
        Exit = findViewById(R.id.btnex);
        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.BtnEdit);

        fac = new FacilitiesManager();

        btnAdd.setOnClickListener(v -> {
            String mavt = EtMaVT.getText().toString();
            String tenvt = EtTenVT.getText().toString();
            String soluong = SnSL.getSelectedItem().toString();
            String gia = EtGia.getText().toString();
            String hsd = SnHSD.getSelectedItem().toString();


            if (mavt.isEmpty() || tenvt.isEmpty() || gia.isEmpty()) {
                Toast.makeText(Add_Facilities.this, "Vui lòng điền tất cả các trường", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                String response = fac.addFaci (mavt, tenvt, soluong, hsd, gia);
                runOnUiThread(() -> Toast.makeText(Add_Facilities.this, "Add Response: " + response, Toast.LENGTH_SHORT).show());
            }).start();
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idStr = EtMaVT.getText().toString();
                if (idStr.isEmpty()) {
                    Toast.makeText(Add_Facilities.this, "Vui lòng nhập ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                int id = Integer.parseInt(idStr);

                new Thread(() -> {
                    String response = fac.deleteFac(id);
                    runOnUiThread(() -> Toast.makeText(Add_Facilities.this, "Delete Response: " + response, Toast.LENGTH_SHORT).show());
                }).start();
            }
        });

        btnEdit.setOnClickListener(v -> {
            String mavt = EtMaVT.getText().toString();
            if (mavt.isEmpty()) {
                Toast.makeText(Add_Facilities.this, "Vui lòng nhập ID", Toast.LENGTH_SHORT).show();
                return;
            }

            String tenvt = EtTenVT.getText().toString();
            String soluong = SnSL.getSelectedItem().toString();
            String gia = EtGia.getText().toString();
            String hsd = SnHSD.getSelectedItem().toString();


            if (mavt.isEmpty() || tenvt.isEmpty() || gia.isEmpty()) {
                Toast.makeText(Add_Facilities.this, "Vui lòng điền tất cả các trường", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                String response = fac.updateFac(mavt, tenvt, soluong, hsd, gia);
                runOnUiThread(() -> Toast.makeText(Add_Facilities.this, "Update Response: " + response, Toast.LENGTH_SHORT).show());
            }).start();
        });

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_Facilities.this, SelectEmpoyee.class);
                startActivity(intent);
            }
        });

        SnSL.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(Add_Facilities.this, "Selected Item: " + item,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayList<String> arrayListmvt = new ArrayList<>();
        arrayListmvt.add("1");
        arrayListmvt.add("2");
        arrayListmvt.add("3");
        arrayListmvt.add("4");
        arrayListmvt.add("5");
        ArrayAdapter<String> adaptermvt = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayListmvt);
        adaptermvt.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        SnSL.setAdapter(adaptermvt);


        SnHSD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(Add_Facilities.this, "Selected Item: " + item,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 1; i <= 90; i++) {
            arrayList.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        SnHSD.setAdapter(adapter);

        Spinner spinnerdvi = findViewById(R.id.spinner_dvi);
        spinnerdvi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(Add_Facilities.this, "Selected Item: " + item,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayList<String> arrayListdvi = new ArrayList<>();
        arrayListdvi.add("Chiếc");
        arrayListdvi.add("Kg");
        ArrayAdapter<String> adapterdvi = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayListdvi);
        adapterdvi.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerdvi.setAdapter(adapterdvi);

        String[] options = {"Select Activity", "View Car", "Add Car"};

        ArrayAdapter<String> adapterfac = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapterfac.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SN.setAdapter(adapterfac);

        SN.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        Intent intentB = new Intent(Add_Facilities.this, View_Facilities.class);
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
            if (parts.length == 5) {
                EtMaVT.setText(parts[0].replace("Mã thiết bị: ", ""));
                EtTenVT.setText(parts[1].replace("Tên thiết bị: ", ""));
                EtGia.setText(parts[4].replace("Giá vật tư: ", ""));


            }
        }
    }
}