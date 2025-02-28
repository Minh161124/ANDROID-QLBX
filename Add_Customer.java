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

public class Add_Customer extends AppCompatActivity {
    private EditText EtMakh, EtTenkh, EtSdt, EtGia;
    private Button btnAdd, btnEdit, btnDelete;
    private ImageView Exit;
    private Spinner SnSev, SnMv, SnAge, Snroute, Snqlk, SN;
    private RadioButton RdNam, RdNu;
    private CustomerManager cus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_customer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SN = findViewById(R.id.spinner_qlk);
        EtMakh = findViewById(R.id.edtxtmdv);
        EtTenkh = findViewById(R.id.edtxtname);
        EtSdt = findViewById(R.id.edtxtphone);
        EtGia = findViewById(R.id.edtxtfare);
        btnAdd = findViewById(R.id.BtnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.BtnEdit);
        Exit = findViewById(R.id.btnex);
        SnSev = findViewById(R.id.spinner_mdv);
        SnMv = findViewById(R.id.spinner_mdve);
        SnAge = findViewById(R.id.spinner_age);
        Snroute = findViewById(R.id.spinner_buses);
        RdNam = findViewById(R.id.rdiomale);


        cus = new CustomerManager();

        btnAdd.setOnClickListener(v -> {
            String Makhach = EtMakh.getText().toString();
            String Tenkhach = EtTenkh.getText().toString();
            String phone = EtSdt.getText().toString();
            String sex =  RdNam.isChecked() ? "Nam" : "Nữ";
            String age = SnAge.getSelectedItem().toString();
            String fare = EtGia.getText().toString();
            String route = Snroute.getSelectedItem().toString();
            String sevice = SnSev.getSelectedItem().toString();
            String Mave = SnMv.getSelectedItem().toString();


            if (Makhach.isEmpty() || Tenkhach.isEmpty() || sex.isEmpty() || phone.isEmpty() || fare.isEmpty()) {
                Toast.makeText(Add_Customer.this, "Vui lòng điền tất cả các trường", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                String response = cus.addCus(Makhach, Tenkhach, sevice, Mave, age, sex, phone, fare, route );
                runOnUiThread(() -> Toast.makeText(Add_Customer.this, "Add Response: " + response, Toast.LENGTH_SHORT).show());
            }).start();
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idStr = EtMakh.getText().toString();
                if (idStr.isEmpty()) {
                    Toast.makeText(Add_Customer.this, "Vui lòng nhập ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                int id = Integer.parseInt(idStr);

                new Thread(() -> {
                    String response = cus.deleteCus(id);
                    runOnUiThread(() -> Toast.makeText(Add_Customer.this, "Delete Response: " + response, Toast.LENGTH_SHORT).show());
                }).start();
            }
        });

        btnEdit.setOnClickListener(v -> {
            String Makhach = EtMakh.getText().toString();
            if (Makhach.isEmpty()) {
                Toast.makeText(Add_Customer.this, "Vui lòng nhập ID", Toast.LENGTH_SHORT).show();
                return;
            }

            String Tenkhach = EtTenkh.getText().toString();
            String phone = EtSdt.getText().toString();
            String sex =  RdNam.isChecked() ? "Nam" : "Nữ";
            String age = SnAge.getSelectedItem().toString();
            String fare = EtGia.getText().toString();
            String route = Snroute.getSelectedItem().toString();
            String sevice = SnSev.getSelectedItem().toString();
            String Mave = SnMv.getSelectedItem().toString();

            if (Makhach.isEmpty() || Tenkhach.isEmpty() || sex.isEmpty() || phone.isEmpty() || fare.isEmpty()) {
                Toast.makeText(Add_Customer.this, "Vui lòng điền tất cả các trường", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                String response = cus.updateCus(Makhach, Tenkhach, sevice, Mave, age, sex, phone, fare, route);
                runOnUiThread(() -> Toast.makeText(Add_Customer.this, "Update Response: " + response, Toast.LENGTH_SHORT).show());
            }).start();
        });

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_Customer.this, SelectEmpoyee.class);
                startActivity(intent);
            }
        });



        SnAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(Add_Customer.this, "Selected Item: " + item,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 4; i <= 90; i++) {
            arrayList.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        SnAge.setAdapter(adapter);


        SnMv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(Add_Customer.this, "Selected Item: " + item,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayList<String> arrayList2 = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            arrayList2.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList2);
        adapter2.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        SnMv.setAdapter(adapter2);


        SnSev.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(Add_Customer.this, "Selected Item: " + item,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayList<String> arrayList1 = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            arrayList1.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);
        adapter1.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        SnSev.setAdapter(adapter1);


        Snroute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(Add_Customer.this, "Selected Item: " + item,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayList<String> arrayList3 = new ArrayList<>();{
            arrayList3.add("Bến xe Mỹ Đình - Bến xe An Sương");
            arrayList3.add("Bến xe Mỹ Đình - Bến xe Nước Ngầm");
            arrayList3.add("Bến xe Mỹ Đình - Bến xe Giáp Bát");
        }
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList3);
        adapter3.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        Snroute.setAdapter(adapter3);

        String[] options = {"Select Activity", "View Car", "Add Car"};

        ArrayAdapter<String> adaptercus = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adaptercus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SN.setAdapter(adaptercus);

        SN.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        Intent intentB = new Intent(Add_Customer.this, View_Customer.class);
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
            if (parts.length == 6) {
                EtMakh.setText(parts[0].replace("Mã khách hàng: ", ""));
                EtTenkh.setText(parts[1].replace("Tên khách hàng: ", ""));
                EtSdt.setText(parts[2].replace("Số điện thoại: ", ""));
                EtGia.setText(parts[3].replace("Phí: ", ""));

            }
        }
    }
}