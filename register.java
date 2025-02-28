package com.example.appmobie;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//btnlogin chuyển đến trang logincus.xml
//btnregister luưu vào sql sau đó tb đăng nhập thành công, chuyển về trang logincus
//btnexit chuyển về home.xml
public class register extends AppCompatActivity {
    private Button btnLogin, btnEX, btnRegister;
    private String kn = "dang nhap thanh cong";
    private EditText etUsername, etPassword, etRePassword, etPhonenumber;
    private UserLogin user;
    private RadioButton rdNam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnLogin = findViewById(R.id.btnlogin);
        btnEX = findViewById(R.id.btnexit);
        etUsername = findViewById(R.id.edtxtname);
        etPassword = findViewById(R.id.EtPassword);
        etRePassword = findViewById(R.id.edtxtcfpass);
        btnRegister = findViewById(R.id.btnregister);
        etPhonenumber = findViewById(R.id.EtPhone);
        rdNam = findViewById(R.id.rdiomale);
        user = new UserLogin();

        etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        etRePassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        btnRegister.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            int phonenumber = Integer.parseInt(etPhonenumber.getText().toString());
            String sex =  rdNam.isChecked() ? "Nam" : "Nữ";
            String password = etPassword.getText().toString();
            String confirmPassword = etRePassword.getText().toString();
            if (!password.equals(confirmPassword)) {

                Toast.makeText(register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }
            new Thread(() -> {
                String response = user.registerUser(username, phonenumber, sex, password);
                runOnUiThread(() -> Toast.makeText(register.this, "Register Response: " + response, Toast.LENGTH_SHORT).show());
            }).start();
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this, Logincus.class);
                startActivity(intent);
            }
        });

        btnEX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this, Home.class);
                startActivity(intent);
            }
        });
    }
}