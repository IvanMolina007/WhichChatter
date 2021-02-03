package com.example.whichchatter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText emailSign = (EditText) findViewById(R.id.editTextEmail);
        final EditText passwordSign = (EditText) findViewById(R.id.editTextPassword);
        Button login = (Button) findViewById(R.id.buttonContinue);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailSign.getText().toString().isEmpty() || passwordSign.getText().toString().isEmpty()) {
                    Toast empty = Toast.makeText(getApplicationContext(), "You have left a blank", Toast.LENGTH_SHORT);
                    empty.show();
                } else {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(emailSign.getText().toString(), passwordSign.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast correct = Toast.makeText(getApplicationContext(), "User Sign is successfully", Toast.LENGTH_SHORT);
                                correct.show();
                                Intent nextActivityIntent = new Intent(getApplicationContext(), ListUsersActivity.class);
                                startActivity(nextActivityIntent);

                            } else {
                                Toast incorrect = Toast.makeText(getApplicationContext(), "User could not be Sign, check email and password", Toast.LENGTH_SHORT);
                                incorrect.show();
                            }
                        }
                    });
                }
            }
        });
    }

    public void onClickCreate(View view) {
        Intent nextActivityIntent = new Intent(this, CreateActivity.class);
        startActivity(nextActivityIntent);
    }
}