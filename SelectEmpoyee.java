package com.example.appmobie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SelectEmpoyee extends AppCompatActivity {
//btne1 chỉ đến logine1.xml, btne2 chỉ đến logine2.xml, btne3 chỉ đến logine3.xml, btne4 chỉ đến logine4.xml, btne5 chỉ đến lohine5.xml, btne6 chỉ đến logine6.xml
//btnex chỉ về home.xml
    private Button btnE1, btnE2, btnE3, btnE4, btnE5, btnE6;
    private ImageView btnEx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_empoyee);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnE1 = findViewById(R.id.btne1);
        btnE2= findViewById(R.id.btne2);
        btnE3 = findViewById(R.id.btne3);
        btnE4 = findViewById(R.id.btne4);
        btnE5 = findViewById(R.id.btne5);
        btnE6 = findViewById(R.id.btne6);
        btnEx = findViewById(R.id.btnex);

        btnE1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectEmpoyee.this, logine1.class);
                startActivity(intent);
            }
        });
        btnE2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectEmpoyee.this, logine2.class);
                startActivity(intent);
            }
        });
        btnE3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectEmpoyee.this, logine3.class);
                startActivity(intent);
            }
        });
        btnE4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectEmpoyee.this, logine4.class);
                startActivity(intent);
            }
        });
        btnE5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectEmpoyee.this, logine5.class);
                startActivity(intent);
            }
        });
        btnE6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectEmpoyee.this, logine6.class);
                startActivity(intent);
            }
        });
        btnEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectEmpoyee.this, Home.class);
                startActivity(intent);
            }
        });

    }
}