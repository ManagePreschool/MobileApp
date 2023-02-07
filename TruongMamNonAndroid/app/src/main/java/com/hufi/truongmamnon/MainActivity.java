package com.hufi.truongmamnon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hufi.truongmamnon.Email.ConfigMail;
import com.hufi.truongmamnon.SQL.SQL;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    EditText txtMaHS, txtPassword;
    SQL db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        txtMaHS = findViewById(R.id.txtMaHS);
        txtPassword = findViewById(R.id.txtPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db = new SQL();

                if (db.isConnected() == false) {
                    Toast.makeText(MainActivity.this, "Không thể kết nối đến server.", Toast.LENGTH_SHORT).show();
                    return;
                }

                int maHS = Integer.parseInt(txtMaHS.getText().toString());
                String password = txtPassword.getText().toString();

                if (txtMaHS.getText().toString().equals("") == true || password.equals("") == true) {
                    Toast.makeText(MainActivity.this, "Chưa nhập mã HS/mật khẩu.", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean isExist = db.isUserExist(maHS, password);

                    if (isExist) {
                        ConfigMail.MA = maHS;

                        Intent intent = new Intent(MainActivity.this, DrawerActivity.class);
                        //intent.putExtra("name", name);
                        intent.putExtra("maHS", maHS);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(MainActivity.this, "Sai mã HS/mật khẩu.", Toast.LENGTH_SHORT).show();

                    try {
                        db.Close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
    }
}