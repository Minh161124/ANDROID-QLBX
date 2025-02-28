package com.example.appmobie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Home extends AppCompatActivity {
//btncus chỉ đến searchbus, btnemployee chỉ đến selectemployee.xml, btnbusdriver chỉ đến selectbusdriver.xml
//imghome chỉ về activity_main.xml
//btnadmin chỉ đến loginad.xml
    private Button btnCus, btnEmp, btnBD, btnAd;
    private ImageView impHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnCus = findViewById(R.id.btncus);
        btnEmp = findViewById(R.id.btnemployee);
        btnAd = findViewById(R.id.btnadmin);
        btnBD = findViewById(R.id.btnbusdriver);
        impHome = findViewById(R.id.imghome);

        btnCus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Logincus.class);
                startActivity(intent);
            }
        });
        btnEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, SelectEmpoyee.class);
                startActivity(intent);
            }
        });
        btnBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, SelectEmpoyee.class);
                startActivity(intent);
            }
        });
        btnAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, loginad.class);
                startActivity(intent);
            }
        });
        impHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}