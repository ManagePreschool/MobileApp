package com.hufi.truongmamnon;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.hufi.truongmamnon.Class.HocSinh;
import com.hufi.truongmamnon.Email.ConfigMail;
import com.hufi.truongmamnon.Email.SendMail;
import com.hufi.truongmamnon.SQL.SQL;
import com.hufi.truongmamnon.databinding.ActivityDrawerBinding;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DrawerActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityDrawerBinding binding;

    SQL db;

    ImageView imageView;
    TextView txtName, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarDrawer.toolbar);
        binding.appBarDrawer.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_thongtincuabe, R.id.nav_diemdanh, R.id.nav_tinhhinhsuckhoe, R.id.nav_hocphi)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_drawer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View navHeader = navigationView.getHeaderView(0);

        imageView = navHeader.findViewById(R.id.imageView);
        txtName = navHeader.findViewById(R.id.txtName);
        txtEmail = navHeader.findViewById(R.id.txtEmail);

        db = new SQL();

        if (db.isConnected() == false) {
            Toast.makeText(DrawerActivity.this, "Không thể kết nối đến server.", Toast.LENGTH_SHORT).show();
            return;
        }

        int maHS = getIntent().getIntExtra("maHS", 0);
        //String username = getIntent().getStringExtra("username");

        HocSinh hs = db.getHocSinh(maHS);

        String email = hs.getEmailPhuHuynh();
        ConfigMail.EMAIL_RECIEVED = email;

        txtEmail.setText(email);
        txtName.setText(hs.getHo() + " " + hs.getTen());

        String image = hs.getHinhAnh();

        if (image != "") {
            byte[] bytes = Base64.decode(image,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

            imageView.setImageBitmap(bitmap);
        }
        else
            imageView.setImageResource(R.drawable.defaultavt);

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String time = df.format(Calendar.getInstance().getTime());

        int ma = hs.getMa();
        String trangThaiDiemDanh = "";
        try {
            trangThaiDiemDanh = db.getDiemDanhVang(Calendar.getInstance().getTime(), ma);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (trangThaiDiemDanh.equals("") == true)
            trangThaiDiemDanh = "Có mặt";

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.cancelAll();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My notification", "My notification", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setVibrationPattern(new long[]{0, 100, 100, 100});        //0, 1000, 500, 1000
            channel.enableVibration(false);
            notificationManager.createNotificationChannel(channel);

            NotificationCompat.Builder noti = new NotificationCompat.Builder(this, "My notification");

            noti.setContentTitle("Điểm danh ngày hôm nay (" + time + ")")
                    .setContentText(trangThaiDiemDanh)
                    //builder.setSmallIcon(R.mipmap.ic_launcher_round);
                    .setSmallIcon(R.drawable.mamnon)
                    .setAutoCancel(false)
                    .setOnlyAlertOnce(true);

            notificationManager.notify(1, noti.build());
        }

        //Creating SendMail object
        try {
            SendMail sm = new SendMail(this, ConfigMail.EMAIL_RECIEVED, "Login Confirmation", "Đã đăng nhập bằng tài khoản " + email
                    + "\n\nTrạng thái điểm danh ngày hôm nay: " + trangThaiDiemDanh);   //(context, email recieved, title, content)
            sm.execute();
        }
        catch (Exception e){
            Log.v("err","Error while sending mail:"+e.getMessage());
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            //String path = getPath(selectedImageUri);

            Bitmap bmp = null;
            ByteArrayOutputStream bos = null;
            byte[] bt = null;
            String hinhAnh = null;
            try {
                //Upload
                bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImageUri));
                bos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                bt = bos.toByteArray();

                hinhAnh = Base64.encodeToString(bt, Base64.DEFAULT);

                int ma = getIntent().getIntExtra("maHS", 0);
                db.uploadHinhHocSinh(ma, hinhAnh);

                //Load image
                HocSinh hs = db.getHocSinh(ma);
                String image = hs.getHinhAnh();
                if (image != "") {
                    byte[] bytes = Base64.decode(image,Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    imageView.setImageBitmap(bitmap);
                }
                else
                    imageView.setImageResource(R.drawable.defaultavt);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_logout:
                finish();
                return true;

            case R.id.action_feedback:
                Intent intent = new Intent(DrawerActivity.this, FeedbackActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item); // important line
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_drawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}