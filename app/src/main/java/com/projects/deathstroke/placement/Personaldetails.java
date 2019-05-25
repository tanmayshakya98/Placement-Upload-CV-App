package com.projects.deathstroke.placement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Personaldetails extends AppCompatActivity {
    //FirebaseDatabase ref;
    String user,fname,lname,branch,spl,year;
    TextView f,l,b,s,y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaldetails);
        Intent i = getIntent();
        user = i.getStringExtra(Studenthome.USER);
        f = (TextView)findViewById(R.id.First);
        l= (TextView)findViewById(R.id.Last);
        b= (TextView)findViewById(R.id.Branch);
        s= (TextView)findViewById(R.id.Spl);
        y= (TextView)findViewById(R.id.Year);

        FirebaseApp.initializeApp(this);

        DatabaseReference cref ;
        cref= FirebaseDatabase.getInstance().getReference().child("Login").child(user);

        cref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fname = dataSnapshot.child("Name:").getValue().toString();
                lname =  dataSnapshot.child("Last:").getValue().toString();
                branch = dataSnapshot.child("Branch").getValue().toString();
                spl = dataSnapshot.child("Spl").getValue().toString();
                year = dataSnapshot.child("Year:").getValue().toString();
                printdetails();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    void printdetails() {
        f.setText(fname);
        l.setText(lname);
        b.setText(branch);
        s.setText(spl);
        y.setText(year);
    }


}
