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
    EditText txtUsername, txtPassword;
    SQL db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db = new SQL();

                if (db.isConnected() == false) {
                    Toast.makeText(MainActivity.this, "Không thể kết nối đến server.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String email = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                if (email.equals("") == true || password.equals("") == true) {
                    Toast.makeText(MainActivity.this, "Chưa nhập email/password.", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean isExist = db.isUserExist(email, password);

                    if (isExist) {
                        ConfigMail.EMAIL_RECIEVED = email;

                        Intent intent = new Intent(MainActivity.this, DrawerActivity.class);
                        //intent.putExtra("name", name);
                        intent.putExtra("email", email);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(MainActivity.this, "Sai email/password.", Toast.LENGTH_SHORT).show();

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