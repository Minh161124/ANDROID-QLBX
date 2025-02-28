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

public class Add_Employee extends AppCompatActivity {
    //Item View Employee chuyển sang View_Employee, Item Add Employee chuyển sang Add_Employee
    private RadioButton rbNam, rbNu;
    private EditText etMaNV, etTen, etTuoi, etSDT, etLuong, etChucVu;
    private Button btnAdd, btnEdit, btnDelete;
    private EmployeeManager emp;
    private ImageView Exit;
    private Spinner SN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_employee);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rbNam = findViewById(R.id.rdiomale);
        rbNu = findViewById(R.id.rdiofemale);
        etMaNV = findViewById(R.id.edtxtmdv);
        etTen = findViewById(R.id.edtxtname);
        etTuoi = findViewById(R.id.editTextNumber);
        etSDT = findViewById(R.id.edtxtphone);
        etLuong = findViewById(R.id.edtxtwage);
        etChucVu = findViewById(R.id.edtxtpos);
        btnAdd = findViewById(R.id.BtnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.BtnEdit);
        Exit = findViewById(R.id.btnex);
        SN = findViewById(R.id.spinner_qlnv);

        emp = new EmployeeManager();

        btnAdd.setOnClickListener(v -> {
            String id = etMaNV.getText().toString();
            String name = etTen.getText().toString();
            int age = Integer.parseInt(etTuoi.getText().toString());
            String sex =  rbNam.isChecked() ? "Nam" : "Nữ";
            String phone = etSDT.getText().toString();
            String salary = etLuong.getText().toString();
            String position = etChucVu.getText().toString();

            if (id.isEmpty() || name.isEmpty() || sex.isEmpty() || phone.isEmpty() || salary.isEmpty() || position.isEmpty()) {
                Toast.makeText(Add_Employee.this, "Vui lòng điền tất cả các trường", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                String response = emp.addEmployee(id, name, age, sex, phone, salary, position );
                runOnUiThread(() -> Toast.makeText(Add_Employee.this, "Add Response: " + response, Toast.LENGTH_SHORT).show());
            }).start();
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idStr = etMaNV.getText().toString();
                if (idStr.isEmpty()) {
                    Toast.makeText(Add_Employee.this, "Vui lòng nhập ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                int id = Integer.parseInt(idStr);

                new Thread(() -> {
                    String response = emp.deleteEmployee(id);
                    runOnUiThread(() -> Toast.makeText(Add_Employee.this, "Delete Response: " + response, Toast.LENGTH_SHORT).show());
                }).start();
            }
        });

        btnEdit.setOnClickListener(v -> {
            String id = etMaNV.getText().toString();
            if (id.isEmpty()) {
                Toast.makeText(Add_Employee.this, "Vui lòng nhập ID", Toast.LENGTH_SHORT).show();
                return;
            }

            String name = etTen.getText().toString();
            int age = Integer.parseInt(etTuoi.getText().toString());
            String sex =  rbNam.isChecked() ? "Nam" : "Nữ";
            String phone = etSDT.getText().toString();
            String salary = etLuong.getText().toString();
            String position = etChucVu.getText().toString();

            new Thread(() -> {
                String response = emp.updateEmployee(id, name, age, sex, phone, salary, position);
                runOnUiThread(() -> Toast.makeText(Add_Employee.this, "Update Response: " + response, Toast.LENGTH_SHORT).show());
            }).start();
        });

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_Employee.this, SelectEmpoyee.class);
                startActivity(intent);
            }
        });

        String[] options = {"Select Activity", "View Employees", "Add Employee"};

        ArrayAdapter<String> adapteremp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapteremp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SN.setAdapter(adapteremp);

        SN.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        Intent intentB = new Intent(Add_Employee.this, View_Employee.class);
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
                etMaNV.setText(parts[0].replace("Mã nhân viên: ", ""));
                etTen.setText(parts[1].replace("Tên nhân viên: ", ""));
                etTuoi.setText(parts[2].replace("Tuổi: ", ""));
                etSDT.setText(parts[3].replace("Số điện thoại: ", ""));
                etLuong.setText(parts[4].replace("Lương: ", ""));
                etChucVu.setText(parts[5].replace("Chức vụ: ", ""));

            }
        }
    }
}