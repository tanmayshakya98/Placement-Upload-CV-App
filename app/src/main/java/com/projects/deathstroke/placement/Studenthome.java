package com.projects.deathstroke.placement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Studenthome extends AppCompatActivity {
    String name;
    public static  final String USER = "com.projects.deathstroke.USER";
    Button P,C,U;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studenthome);
        Intent i = getIntent();
        name= i.getStringExtra(MainActivity.USERNAME);
        P = (Button)findViewById(R.id.Personal_Details);
        C = (Button)findViewById(R.id.Company_Details);
        U = (Button)findViewById(R.id.Upload_Documents);

        P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            gotopersonal();
            }
        });

        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotocompany();
            }
        });
        U.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoupload();
            }
        });
    }

    void gotoupload() {
        Intent upload = new Intent(this,Upload.class);
        startActivity(upload);
    }

    void gotocompany() {
        Intent company = new Intent(this,Companydetails.class);
        startActivity(company);
    }

    void gotopersonal() {
        Intent personal = new Intent(this ,Personaldetails.class);
        personal.putExtra(USER,name);
        startActivity(personal);
    }

}
