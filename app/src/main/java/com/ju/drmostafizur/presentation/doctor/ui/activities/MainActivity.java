package com.ju.drmostafizur.presentation.doctor.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.ju.drmostafizur.R;
import com.ju.drmostafizur.presentation.doctor.ui.fragments.DrSettingFragment;
import com.ju.drmostafizur.presentation.doctor.ui.fragments.FoTabFragment;
import com.ju.drmostafizur.presentation.login.LoginActivity;
import com.ju.drmostafizur.presentation.patient.ui.activities.SerialBookedActivity;
import com.ju.drmostafizur.presentation.patient.ui.fragments.PatientDashboardFragment;
import com.ju.drmostafizur.utills.MySharePreferences;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private TextView textViewUserName;
    private TextView textViewSoftwareDate;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
       // toolbar.setLogo(R.drawable.rsz_logo_title);
        setSupportActionBar(toolbar);
        setTitle("Mr. Mostafiz");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorIcons));


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerView, new FoTabFragment()).commit();
        View view = navigationView.getHeaderView(0);

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerView, new FoTabFragment()).commit();

        } else if (id == R.id.nav_setting) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerView, new DrSettingFragment()).commit();
        }
        else if(id == R.id.nav_share){
            Toast.makeText(this, "This feature is only for pro version", Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(getApplicationContext(), SerialBookedActivity.class));
        }

        else if(id == R.id.nav_sign_out){
            new AlertDialog.Builder(this)
                    .setTitle("Dr Mostafizur")
                    .setMessage("Do you really wanna Logout?")
                   // .setIcon(R.drawable.rsz_logo_title)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            logout();
                        }})
                    .setNegativeButton(android.R.string.no, null).show();
        }
        else if (id == R.id.nav_send){
            Toast.makeText(this, "This feature is only for pro version", Toast.LENGTH_SHORT).show();
        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {

            new AlertDialog.Builder(this)
                    .setTitle("Dr.Mostafizur")
                    .setMessage("Do you really want to Exit?")
                   // .setIcon(R.drawable.rsz_logo_title)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            finish();

                        }})
                    .setNegativeButton(android.R.string.no, null).show();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void logout(){
        new MySharePreferences(this).setBoolean(MySharePreferences.CHECKED, false);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}
