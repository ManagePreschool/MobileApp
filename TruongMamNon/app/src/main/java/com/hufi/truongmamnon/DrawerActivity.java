package com.hufi.truongmamnon;

import android.os.Bundle;
import android.util.Log;
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

import com.hufi.truongmamnon.Class.Account;
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
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
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

        Account account = db.getAccount(email);

        //txtName.setText(account.getUsername());
        txtEmail.setText(email);

        //test
        txtName.setText(account.getPassword());

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
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_drawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}