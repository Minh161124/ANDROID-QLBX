package com.example.appmobie;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class logine1 extends AppCompatActivity {
//btnlogin kiểm tra đúng tk, mk trong mysql  sau đó hiện tb đăng nhập thành công rồi chuyển sang trang QLNV.xml
//btnexit chỉ về trang selectemployee.xml
    private Button btnLogin, btnEX;
    private String kn = "Dang nhap thanh cong";
    private EditText etUsername, etPassword;
    private UserLogin user;
    private CheckBox an;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_logine1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        an = findViewById(R.id.cbxpass);
        btnLogin = findViewById(R.id.btnlogin);
        btnEX = findViewById(R.id.btnexit);
        etUsername = findViewById(R.id.EtPhone);
        etPassword = findViewById(R.id.EtPassword);
        user = new UserLogin();

        etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        an.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
                etPassword.setSelection(etPassword.getText().length());
        });



        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            new Thread(() -> {
                String response = user.loginUser(username, password);
                runOnUiThread(() -> {
                    if (response.contains(kn)) {

                        Intent intent = new Intent(logine1.this, Add_Employee.class);
                        startActivity(intent);

                    } else {

                        Toast.makeText(logine1.this, "Login Response: " + response, Toast.LENGTH_SHORT).show();

                    }
                });
            }).start();
        });

        btnEX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(logine1.this, SelectEmpoyee.class);
                startActivity(intent);
            }
        });
    }
}