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

public class Add_Ticket extends AppCompatActivity {
    private EditText EtMave, EtMakh, EtTenkh, EtTuoikh, EtTennv, EtTuoinv, EtSdt, EtGia;
    private Button btnAdd, btnEdit, btnDelete;
    private ImageView Exit;
    private Spinner SN, SnRoute, SnSeat, SnMdv;
    private RadioButton RdNam, Rdnu;
    private TicketsManager tic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_ticket);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EtMave = findViewById(R.id.edtxtmdve);
        EtMakh = findViewById(R.id.edtxtmkh);
        EtTenkh = findViewById(R.id.edtxtname);
        EtTennv = findViewById(R.id.edtxtename);
        EtTuoikh = findViewById(R.id.cusAge);
        EtTuoinv = findViewById(R.id.empAge);
        EtSdt = findViewById(R.id.edtxtphone);
        EtGia = findViewById(R.id.edtxtfare);
        SnRoute = findViewById(R.id.spinner_buses);
        SnSeat = findViewById(R.id.spinner_seat);
        SnMdv = findViewById(R.id.spinner_mdv);
        SN = findViewById(R.id.spinner_qldve);
        RdNam = findViewById(R.id.rdiomale);
        btnAdd = findViewById(R.id.BtnAdd);
        Exit = findViewById(R.id.btnex);
        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.BtnEdit);

        tic = new TicketsManager();

        btnAdd.setOnClickListener(v -> {
            int IDve = Integer.parseInt(EtMave.getText().toString());
            int IDkhach = Integer.parseInt(EtMakh.getText().toString());
            int agekh = Integer.parseInt(EtTuoikh.getText().toString());
            int agenv = Integer.parseInt(EtTuoinv.getText().toString());
            int phone = Integer.parseInt(EtSdt.getText().toString());
            int fare = Integer.parseInt(EtGia.getText().toString());
            String seat = SnSeat.getSelectedItem().toString();
            String IDdv = SnMdv.getSelectedItem().toString();
            String sexkh =  RdNam.isChecked() ? "Nam" : "Nữ";
            String sexnv = RdNam.isChecked() ? "Nam" : "Nữ";
            String TenNV = EtTennv.getText().toString();
            String Tenkhach = EtTenkh.getText().toString();
            String route = SnRoute.getSelectedItem().toString();

            if (TenNV.isEmpty() || Tenkhach.isEmpty()) {
                Toast.makeText(Add_Ticket.this, "Vui lòng điền tất cả các trường", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                String response = tic.addTic(IDve, IDkhach, Tenkhach, agekh, sexkh,TenNV, agenv, sexnv, phone, fare, route, seat, IDdv );
                runOnUiThread(() -> Toast.makeText(Add_Ticket.this, "Add Response: " + response, Toast.LENGTH_SHORT).show());
            }).start();
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idStr = EtMave.getText().toString();
                if (idStr.isEmpty()) {
                    Toast.makeText(Add_Ticket.this, "Vui lòng nhập ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                int id = Integer.parseInt(idStr);

                new Thread(() -> {
                    String response = tic.deleteTic(id);
                    runOnUiThread(() -> Toast.makeText(Add_Ticket.this, "Delete Response: " + response, Toast.LENGTH_SHORT).show());
                }).start();
            }
        });

        btnEdit.setOnClickListener(v -> {
            String IDve = EtMave.getText().toString();
            if (IDve.isEmpty()) {
                Toast.makeText(Add_Ticket.this, "Vui lòng nhập ID", Toast.LENGTH_SHORT).show();
                return;
            }

            int IDkhach = Integer.parseInt(EtMakh.getText().toString());
            int agekh = Integer.parseInt(EtTuoikh.getText().toString());
            int agenv = Integer.parseInt(EtTuoinv.getText().toString());
            int phone = Integer.parseInt(EtSdt.getText().toString());
            int fare = Integer.parseInt(EtGia.getText().toString());
            String seat = SnSeat.getSelectedItem().toString();
            String IDdv = SnMdv.getSelectedItem().toString();
            String sexkh =  RdNam.isChecked() ? "Nam" : "Nữ";
            String sexnv = RdNam.isChecked() ? "Nam" : "Nữ";
            String TenNV = EtTennv.getText().toString();
            String Tenkhach = EtTenkh.getText().toString();
            String route = SnRoute.getSelectedItem().toString();

            if (TenNV.isEmpty() || Tenkhach.isEmpty()) {
                Toast.makeText(Add_Ticket.this, "Vui lòng điền tất cả các trường", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                String response = tic.updateTic(IDve, IDkhach, Tenkhach, agekh, sexkh,TenNV, agenv, sexnv, phone, fare, route, seat, IDdv );
                runOnUiThread(() -> Toast.makeText(Add_Ticket.this, "Update Response: " + response, Toast.LENGTH_SHORT).show());
            }).start();
        });

        SnSeat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(Add_Ticket.this, "Selected Item: " + item,Toast.LENGTH_SHORT).show();
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
        SnSeat.setAdapter(adapter1);



        SnMdv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(Add_Ticket.this, "Selected Item: " + item,Toast.LENGTH_SHORT).show();
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
        SnMdv.setAdapter(adaptermdv);



        SnRoute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(Add_Ticket.this, "Selected Item: " + item,Toast.LENGTH_SHORT).show();
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

        String[] options = {"Select Activity", "View Tickets", "Add Tickets"};

        ArrayAdapter<String> adaptertic = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adaptertic.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SN.setAdapter(adaptertic);

        SN.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        Intent intentB = new Intent(Add_Ticket.this, View_Ticket.class);
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
            if (parts.length == 10) {
                EtMave.setText(parts[0].replace("Mã vé: ", ""));
                EtMakh.setText(parts[1].replace("Mã Khách hàng: ", ""));
                EtTenkh.setText(parts[2].replace("Tên Khách hàng: ", ""));
                EtTuoikh.setText(parts[3].replace("Tuổi Khách hàng: ", ""));
                EtTennv.setText(parts[4].replace("Tên nhân viên: ", ""));
                EtTuoinv.setText(parts[5].replace("Tuổi nhân viên: ", ""));
                EtSdt.setText(parts[6].replace("Số điện thoại: ", ""));
                EtGia.setText(parts[9].replace("Phí: ", ""));

            }
        }

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentB = new Intent(Add_Ticket.this, SelectEmpoyee.class);
                startActivity(intentB);
            }
        });
    }
}