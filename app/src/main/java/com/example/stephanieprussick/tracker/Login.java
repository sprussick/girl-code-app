package com.example.stephanieprussick.tracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    FirebaseAuth auth;
    EditText e1, e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        auth = FirebaseAuth.getInstance();

    }

    public void login(View v)
    {
        auth.signInWithEmailAndPassword(e1.getText().toString(), e2.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    FirebaseUser user = auth.getCurrentUser();
                    if (user.isEmailVerified())
                    {
                        Intent intent = new Intent(Login.this, UserLocationActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Email is not verified yet", Toast.LENGTH_LONG).show();
                    }
//                    Toast.makeText(getApplicationContext(), "User log in successful", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(Login.this, UserLocationActivity.class);
//                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Wrong credentials", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
