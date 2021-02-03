package com.example.whichchatter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListUsersActivity extends AppCompatActivity {

    ArrayList<User> listUsers;
    RecyclerView recycler;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    Button logOut;
    DatabaseReference baseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        recycler = findViewById(R.id.recyclerUser);
        logOut = findViewById(R.id.buttonLogOut);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        listUsers = new ArrayList<User>();

        baseDate = FirebaseDatabase.getInstance().getReference().child("users");

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                auth.signOut();
                finish();

            }
        });

        ValueEventListener valueLise = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    FirebaseUser baseUser = FirebaseAuth.getInstance().getCurrentUser();
                    User userRuser = dataSnapshot.getValue(User.class);

                    if (!(baseUser.getUid().equals(userRuser.idUID))) {
                        listUsers.add(userRuser);
                        AdapterItem adapter= new AdapterItem(listUsers);
                        recycler.setAdapter(adapter);
                        recycler.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        baseDate.addValueEventListener(valueLise);
    }
}