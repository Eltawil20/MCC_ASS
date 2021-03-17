package com.example.mcc_ass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.textservice.SpellCheckerInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText number;
    EditText address;

    TextView readName;
    TextView readNumber;
    TextView readAddress;

    Button saveDb;
    Button readDb;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference ref = db.collection("users").document("Data");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        number = findViewById(R.id.num);
        address = findViewById(R.id.address);

//        readName = findViewById(R.id.readName);
//        readNumber = findViewById(R.id.readNum);
//        readAddress = findViewById(R.id.readAddress);

        saveDb = findViewById(R.id.btnSave);
        readDb = findViewById(R.id.btnRead);


        saveDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String personName = name.getText().toString();
                String personNumber = number.getText().toString();
                String personAddress = address.getText().toString();

                // Create a new user with a first and last name
                Map<String, Object> user = new HashMap<>();
                user.put("person_name", personName);
                user.put("person_number", personNumber);
                user.put("person_address", personAddress);


// Add a new document with a generated ID

                ref.set(user).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Log.d("TAG", "DocumentSnapshot added with ID:");

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);

                    }
                });

            }
        });


        readDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if (documentSnapshot.exists()) {

                            String readName2 = documentSnapshot.getString("person_name");
                            String readNumber2 = documentSnapshot.getString("person_number");
                            String readAddress2 = documentSnapshot.getString("person_address");

                            Person p = new Person();
                            p.setName(readName2);
                            p.setNumber(readNumber2);
                            p.setAddress(readAddress2);

                            ArrayList<Person> persons = new ArrayList<>();
                            persons.add(p);

                            RecyclerView rv = findViewById(R.id.recyclerView);
                            AdapterPerson adapter = new AdapterPerson(persons, R.layout.person_item);
                            rv.setAdapter(adapter);
                            LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
                            llm.setOrientation(LinearLayoutManager.VERTICAL);
                            rv.setLayoutManager(llm);

                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error read document", e);
                    }
                });

            }

        });




    /*
    public void saveToFirebase(View v){

        String personName = name.getText().toString();
        String personNumber = number.getText().toString();
        String personAddress = address.getText().toString();

        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("person_name", personName);
        user.put("person_number", personNumber);
        user.put("person_address", personAddress);

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                    }
                });
    }
     */

//    public void readFromFirebase(View v){
//
//    }

    }
}

