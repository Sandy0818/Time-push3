package com.example.time;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;

public class work_display extends AppCompatActivity {

    Bundle extras;
    String wname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_display);

        final TextView name =  findViewById(R.id.w_title);
        final TextView loc = findViewById(R.id.w_location);
        final TextView desc = findViewById(R.id.w_descp);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        extras = getIntent().getExtras();
        if(extras == null)
            wname = null;
        else
            wname = (String) extras.get("Wshop_name");

        db.collection("users").document("user1").collection("workshop list").document(wname)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Log.d("wshop", documentSnapshot.toString());
                        name.setText(documentSnapshot.get("Title").toString());

                        loc.setText(documentSnapshot.get("Location").toString());
                        desc.setText(documentSnapshot.get("Descp").toString());
                    }
                });
    }
}
