package com.example.stephanieprussick.tracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.karan.churi.PermissionManager.PermissionManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    PermissionManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if (user == null)
        {
            setContentView(R.layout.activity_main);
            manager = new PermissionManager() {};
            manager.checkAndRequestPermissions(this);

        }
        else
        {
            Intent myIntent = new Intent(MainActivity.this,UserLocationActivity.class);
            startActivity(myIntent);
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        manager.checkResult(requestCode,permissions,grantResults);
        ArrayList<String> denied_permissions = manager.getStatus().get(0).denied;

        if (denied_permissions.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Permissions enabled", Toast.LENGTH_SHORT).show();
        }


    }



    public void signUpClicked(View v)
    {
        Intent myIntent = new Intent(MainActivity.this, Register.class);
        startActivity(myIntent);
    }

    public void loginClicked(View v)
    {
        Intent myIntent = new Intent(MainActivity.this, Login.class);
        startActivity(myIntent);
    }
}
