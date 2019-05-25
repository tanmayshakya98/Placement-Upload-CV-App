package com.projects.deathstroke.placement;

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

public class Companydetails extends AppCompatActivity {
    DatabaseReference cref;
    TextView t1,t2,t3,t4;
    String c,l,j,p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companydetails);

        t1 = (TextView)findViewById(R.id.Companyname);
        t2 = (TextView)findViewById(R.id.Location);
        t3 = (TextView)findViewById(R.id.Jobprofile);
        t4 = (TextView) findViewById(R.id.Pac);

        cref = FirebaseDatabase.getInstance().getReference().child("Company").child("VMware");

       cref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               j= dataSnapshot.child("JobProfile").getValue().toString();
               l= dataSnapshot.child("Location").getValue().toString();
               p= dataSnapshot.child("Package").getValue().toString();
               t1.setText("VMware");
                t2.setText(l);
                t3.setText(j);
                t4.setText(p);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
