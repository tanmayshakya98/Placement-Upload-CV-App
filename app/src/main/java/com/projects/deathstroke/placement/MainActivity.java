package com.projects.deathstroke.placement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class MainActivity extends AppCompatActivity {
    public static final String USERNAME = "com.projects.deathstroke.placement.Username";
    private Button sign;
    public EditText username;
    public EditText password;
    DatabaseReference cref;
    String duser, dpass, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sign = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.Username);
        password = (EditText) findViewById(R.id.Password);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                cref = FirebaseDatabase.getInstance().getReference().child("Login").child(user);
                //cref.setValue(value);
                cref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        duser = dataSnapshot.child("Name:").getValue().toString();
                        dpass = dataSnapshot.child("Password:").getValue().toString();
                        type = dataSnapshot.child("Type:").getValue().toString();
                        check();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
    }

    void check() {
        String user = username.getText().toString();
        String pass = password.getText().toString();
        if (user.equals(duser)) {
            if (pass.equals(dpass)) {
                if (type.equals("student")) {
                    Toast.makeText(this, "Authenticated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this, Studenthome.class);
                    i.putExtra(USERNAME,user);
                    startActivity(i);
                } else if (type.equals("faculty")) {
                    Toast.makeText(this, "Authenticated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this, Facultyhome.class);

                    startActivity(i);

                }

            } else {
                Toast.makeText(this, "wrong details", Toast.LENGTH_LONG).show();
            }

        }
        else
        {
            Toast.makeText(this,"wrong details",Toast.LENGTH_LONG).show();
        }
    }
}
