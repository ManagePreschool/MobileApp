package com.hufi.truongmamnon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

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
                R.id.nav_thongtincuabe, R.id.nav_tinhhinhsuckhoe, R.id.nav_hocphi)
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

        String email = getIntent().getStringExtra("email");
        //String username = getIntent().getStringExtra("username");

        HocSinh hs = db.getHocSinh(email);

        txtEmail.setText(email);
        txtName.setText(hs.getHo() + " " + hs.getTen());

        //Creating SendMail object
        try {
            SendMail sm = new SendMail(this, ConfigMail.EMAIL_RECIEVED, "Login Confirmation", "Đã đăng nhập bằng tài khoản " + email);   //(context, email recieved, title, content)
            sm.execute();
        }
        catch (Exception e){
            Log.v("err","Error while sending mail:"+e.getMessage());
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