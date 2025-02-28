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

public class SelectAd extends AppCompatActivity {
//btnad1 chuyển đến View_Employee.xml, btnad2 đến View_Car.xml, btnad3 đến View_Tickets, btnad4 đến View_Secive.xml, btnad5 đến View_Customer.xml, btnad6 đến View_Facilitives.xml
private Button btnE1, btnE2, btnE3, btnE4, btnE5, btnE6;
    private ImageView btnEx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_ad);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnE1 = findViewById(R.id.btnad1);
        btnE2= findViewById(R.id.btnad2);
        btnE3 = findViewById(R.id.btnad3);
        btnE4 = findViewById(R.id.btnad4);
        btnE5 = findViewById(R.id.btnad5);
        btnE6 = findViewById(R.id.btnad6);
        btnEx = findViewById(R.id.btnex);

        btnE1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAd.this, View_Employee.class);
                startActivity(intent);
            }
        });
        btnE2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAd.this, View_Car.class);
                startActivity(intent);
            }
        });
        btnE3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAd.this, View_Ticket.class);
                startActivity(intent);
            }
        });
        btnE4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(SelectAd.this, View_Sevice.class);
                startActivity(intent);
            }
        });
        btnE5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAd.this, View_Customer.class);
                startActivity(intent);
            }
        });
        btnE6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(SelectAd.this, View_Facilities.class);
                startActivity(intent);
            }
        });
        btnEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAd.this, Home.class);
                startActivity(intent);
            }
        });
    }
}