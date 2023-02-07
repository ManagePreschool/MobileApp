package com.hufi.truongmamnon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hufi.truongmamnon.Class.HocSinh;
import com.hufi.truongmamnon.Email.ConfigMail;
import com.hufi.truongmamnon.Email.SendMail;
import com.hufi.truongmamnon.SQL.SQL;

import java.sql.SQLException;

public class FeedbackActivity extends AppCompatActivity {
    Button btnFeedback;
    EditText txtFeedback;
    TextView lbNguoiGui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        btnFeedback = findViewById(R.id.btnFeedback);
        txtFeedback = findViewById(R.id.txtFeedback);
        lbNguoiGui = findViewById(R.id.lbNguoiGui);

        SQL db = new SQL();

        HocSinh hs = db.getHocSinh(ConfigMail.MA);
        lbNguoiGui.setText(hs.getHoTenPhuHuynh());

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQL db = new SQL();

                if (db.isConnected() == false) {
                    Toast.makeText(FeedbackActivity.this, "Không thể kết nối đến server.", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean isSent;
                String feedback = txtFeedback.getText().toString();

                if (feedback.equals("") == true) {
                    Toast.makeText(FeedbackActivity.this, "Ô nhập phản hồi trống.", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        SendMail sm = new SendMail(FeedbackActivity.this, ConfigMail.EMAIL,
                                "Phụ huynh tên " + lbNguoiGui.getText().toString() + " đã gửi phản hồi", feedback);   //(context, email recieved, title, content)
                        sm.execute();

                        isSent = true;
                    }
                    catch (Exception e){
                        Log.v("err","Error while sending mail:"+e.getMessage());
                        Toast.makeText(FeedbackActivity.this, "Có lỗi xảy ra, không gửi được phản hồi.", Toast.LENGTH_SHORT).show();
                        isSent = false;
                    }

                    try {
                        db.Close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    if (isSent == true) {
                        Toast.makeText(FeedbackActivity.this, "Phản hồi của bạn đã được gửi.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}