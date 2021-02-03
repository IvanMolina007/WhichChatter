package com.example.whichchatter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class ChatActivity extends AppCompatActivity {

    TextView name;
    ArrayList<Messages> listMessages;
    RecyclerView recycler;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference baseDate;
    Button returning;
    ImageButton send;
    Bundle b;
    String IdUIDUserReceived;
    EditText mensagger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        b = getIntent().getExtras();
        name = (TextView) findViewById(R.id.textViewChatName);
        mensagger = findViewById(R.id.editTextSenMessage);
        name.setText(b.getString("nameUser"));
        IdUIDUserReceived = b.getString("idUIDuser");
        baseDate = FirebaseDatabase.getInstance().getReference().child("messages");

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        recycler = findViewById(R.id.recyclerMessage);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        listMessages = new ArrayList<Messages>();
        returning = findViewById(R.id.button);
        send = findViewById(R.id.imageButtonSend);

        returning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newMessage(mensagger.getText().toString(), user.getUid(), IdUIDUserReceived );
                mensagger.setText("");
            }
        });

        ValueEventListener valueLise = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listMessages.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    FirebaseUser baseUser = FirebaseAuth.getInstance().getCurrentUser();
                    Messages messagesRuser = dataSnapshot.getValue(Messages.class);

                    if (baseUser.getUid().equals(messagesRuser.sent) && (IdUIDUserReceived.equals(messagesRuser.received))) {
                        listMessages.add(messagesRuser);
                        AdapterMessage adapter= new AdapterMessage(listMessages);
                        recycler.setAdapter(adapter);
                        recycler.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
                    } else {
                        if ((baseUser.getUid().equals(messagesRuser.received) && (IdUIDUserReceived.equals(messagesRuser.sent)))) {
                            listMessages.add(messagesRuser);
                            AdapterMessage adapter= new AdapterMessage(listMessages);
                            recycler.setAdapter(adapter);
                            recycler.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        baseDate.addValueEventListener(valueLise);

    }

    public void newMessage(String message, String sent, String received) {
        Messages menssage = new Messages(message, sent, received);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("messages").child(sent+" - "+received+ " " + Calendar.getInstance().getTime().toString()).setValue(menssage);
    }
}