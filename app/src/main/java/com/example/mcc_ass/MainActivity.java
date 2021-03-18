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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
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
        });

        readDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.collection("users")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {


                                    ArrayList<Person> persons = new ArrayList<>();

                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Person yourClass = new Person();
                                        yourClass.setName((String) document.getData().get("person_name"));
                                        yourClass.setNumber((String) document.getData().get("person_number"));
                                        yourClass.setAddress((String) document.getData().get("person_address"));

                                        persons.add(yourClass);


                                    }

                                    RecyclerView rv = findViewById(R.id.recyclerView);
                                    AdapterPerson adapter = new AdapterPerson(persons, R.layout.person_item);
                                    rv.setAdapter(adapter);
                                    LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
                                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                                    rv.setLayoutManager(llm);

                                } else {
                                    Log.w("TAG", "Error getting documents.", task.getException());
                                }
                            }
                        });


            }
        });


    }
}

