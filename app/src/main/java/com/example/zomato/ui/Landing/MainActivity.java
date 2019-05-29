package com.example.zomato.ui.landing;

import androidx.annotation.NonNull;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.example.zomato.R;
import com.example.zomato.db.objects.User;
import com.example.zomato.ui.base.BaseActivity;
import com.example.zomato.ui.restaurants.NavigationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;


public class MainActivity extends BaseActivity implements LoginFragment.OnItemSelectedListener, SignUpFragment.OnItemSelectedListener {

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            presentActivity(new NavigationActivity());
        } else {
            presentFragment(R.id.fragment_holder_main, new LoginFragment());
        }
    }

    @Override
    public void loginClicked(String email, String password) {
        showProgressBar("Logging in...");
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        dismissProgressDialog();
                        if (task.isSuccessful()) {
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("password",password);
                            editor.apply();
                            presentActivity(new NavigationActivity());
                        } else {
                            Toast.makeText(getApplication(), "Failed to log in", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onRegisterClicked() {
        presentFragment(R.id.fragment_holder_main, new SignUpFragment());
    }


    @Override
    public void signUpClicked(String email, String password, String firstName, String lastName) {
        showProgressBar("Please wait...");
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        dismissProgressDialog();
                        if (task.isSuccessful()) {
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("password",password);
                            editor.apply();
                            User newUser = new User(firstName,lastName,email);
                            databaseReference.child("users").child(firebaseAuth.getCurrentUser().getUid()).setValue(newUser);
                            presentActivity(new NavigationActivity());
                        } else {
                            Toast.makeText(getApplication(), "Failed to create user", Toast.LENGTH_SHORT).show();
                            task.addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e("failReasong",e.getMessage());
                                    e.printStackTrace();
                                }
                            });
                        }
                    }
                });
    }

    @Override
    public void reLoginClicked() {
        presentFragment(R.id.fragment_holder_main, new LoginFragment());
    }
}
