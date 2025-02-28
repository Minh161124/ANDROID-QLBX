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

public class Add_Car extends AppCompatActivity {
    private EditText EtBienso, EtNhaxe, EtTaixe, EtSdt, EtGia;
    private RadioButton RdNam, RdNu;
    private Spinner SnAge, SnRoute, SnSev, SnSit, SN;
    private Button btnAdd, btnEdit, btnDelete;
    private ImageView Exit;
    private CarManager car;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_car);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SN =findViewById(R.id.spinner_qlx);
        EtBienso = findViewById(R.id.edtxtbks);
        EtNhaxe = findViewById(R.id.edtxtname);
        EtTaixe = findViewById(R.id.edtxtdrivername);
        EtSdt = findViewById(R.id.edtxtphone);
        EtGia = findViewById(R.id.edtxtfare);
        RdNu = findViewById(R.id.rdiofemale);
        RdNam = findViewById(R.id.rdiomale);
        SnSev = findViewById(R.id.spinner_mdv);
        SnRoute = findViewById(R.id.spinner_buses);
        SnAge = findViewById(R.id.spinner_age);
        SnSit = findViewById(R.id.spinner_seat);
        btnAdd = findViewById(R.id.BtnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.BtnEdit);
        Exit = findViewById(R.id.btnex);
        car = new CarManager();

        btnAdd.setOnClickListener(v -> {
            String plate = EtBienso.getText().toString();
            String bus = EtNhaxe.getText().toString();
            String driver = EtTaixe.getText().toString();
            String phone = EtSdt.getText().toString();
            String sex =  RdNam.isChecked() ? "Nam" : "Nữ";
            String age = SnAge.getSelectedItem().toString();
            String fare = EtGia.getText().toString();
            String route = SnRoute.getSelectedItem().toString();
            String sit = SnSit.getSelectedItem().toString();
            String sevice = SnSev.getSelectedItem().toString();


            if (plate.isEmpty() || bus.isEmpty() || sex.isEmpty() || phone.isEmpty() || driver.isEmpty()) {
                Toast.makeText(Add_Car.this, "Vui lòng điền tất cả các trường", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                String response = car.addCar(plate, bus, driver, age, sex, phone, fare, route, sit, sevice );
                runOnUiThread(() -> Toast.makeText(Add_Car.this, "Add Response: " + response, Toast.LENGTH_SHORT).show());
            }).start();
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idStr = EtBienso.getText().toString();
                if (idStr.isEmpty()) {
                    Toast.makeText(Add_Car.this, "Vui lòng nhập ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                int id = Integer.parseInt(idStr);

                new Thread(() -> {
                    String response = car.deleteCar(id);
                    runOnUiThread(() -> Toast.makeText(Add_Car.this, "Delete Response: " + response, Toast.LENGTH_SHORT).show());
                }).start();
            }
        });

        btnEdit.setOnClickListener(v -> {
            String plate = EtBienso.getText().toString();
            if (plate.isEmpty()) {
                Toast.makeText(Add_Car.this, "Vui lòng nhập ID", Toast.LENGTH_SHORT).show();
                return;
            }
            String bus = EtNhaxe.getText().toString();
            String driver = EtTaixe.getText().toString();
            String phone = EtSdt.getText().toString();
            String sex =  RdNam.isChecked() ? "Nam" : "Nữ";
            String age = SnAge.getSelectedItem().toString();
            String fare = EtGia.getText().toString();
            String route = SnRoute.getSelectedItem().toString();
            String sit = SnSit.getSelectedItem().toString();
            String sevice = SnSev.getSelectedItem().toString();

            new Thread(() -> {
                String response = car.updateCar(plate, bus, driver, age, sex, phone, fare, route, sit, sevice );
                runOnUiThread(() -> Toast.makeText(Add_Car.this, "Update Response: " + response, Toast.LENGTH_SHORT).show());
            }).start();
        });

        String carData = getIntent().getStringExtra("carData");


        if (carData != null) {
            String[] parts = carData.split("\n");
            if (parts.length == 6) {
                EtBienso.setText(parts[0].replace("Biển số: ", ""));
                EtNhaxe.setText(parts[1].replace("Nhà xe: ", ""));
                EtTaixe.setText(parts[2].replace("Tài xế: ", ""));
                EtSdt.setText(parts[3].replace("Số điện thoại: ", ""));
                EtGia.setText(parts[4].replace("Phí: ", ""));
            }
        }

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_Car.this, SelectEmpoyee.class);
                startActivity(intent);
            }
        });

        SnAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(Add_Car.this, "Selected Item: " + item,Toast.LENGTH_SHORT).show();
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


        SnRoute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(Add_Car.this, "Selected Item: " + item,Toast.LENGTH_SHORT).show();
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
        SnRoute.setAdapter(adapter3);



        String[] options = {"Select Activity", "View Car", "Add Car"};

        ArrayAdapter<String> adaptercar = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adaptercar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SN.setAdapter(adaptercar);

        SN.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        Intent intentB = new Intent(Add_Car.this, View_Car.class);
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


        SnSit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(Add_Car.this, "Selected Item: " + item,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayList<String> arrayList1 = new ArrayList<>();
        arrayList1.add("4 chỗ");
        arrayList1.add("5 chỗ");
        arrayList1.add("7 chỗ");
        arrayList1.add("16 chỗ");
        arrayList1.add("30 chỗ");
        arrayList1.add("35 chỗ");
        arrayList1.add("45 chỗ");
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList1);
        adapter1.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        SnSit.setAdapter(adapter1);



        SnSev.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(Add_Car.this, "Selected Item: " + item,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayList<String> arrayListmdv = new ArrayList<>();
        arrayListmdv.add("DV01");
        arrayListmdv.add("DV02");
        arrayListmdv.add("DV03");
        arrayListmdv.add("DV04");
        arrayListmdv.add("DV05");
        ArrayAdapter<String> adaptermdv = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayListmdv);
        adaptermdv.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        SnSev.setAdapter(adaptermdv);

    }
}