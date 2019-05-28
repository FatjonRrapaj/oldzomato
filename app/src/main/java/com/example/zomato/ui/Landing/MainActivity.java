package com.example.zomato.ui.landing;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.util.Log;
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
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity implements LoginFragment.OnItemSelectedListener, SignUpFragment.OnItemSelectedListener {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            Gson gson = new Gson();
            String serializedUser = gson.toJson(currentUser);
            presentActivity(new NavigationActivity(), "firebaseUser", serializedUser);
        } else {
            presentFragment(R.id.fragment_holder_main, new LoginFragment());
        }
    }

    @Override
    public void loginClicked(String email, String password) {
        //TODO: show loader here
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            FirebaseUser user = firebaseAuth.getCurrentUser();
//                            Gson gson = new Gson();
//                            String serializedUser = gson.toJson(user);
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
        //TODO: show loader here.
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Gson gson = new Gson();
                            User newUser = new User(firstName,lastName,email);
                            String serializedNewUser = gson.toJson(newUser);
                            presentActivity(new NavigationActivity(), "newUser",serializedNewUser);
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
