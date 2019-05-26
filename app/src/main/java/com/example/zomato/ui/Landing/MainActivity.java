package com.example.zomato.ui.Landing;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.zomato.R;
import com.example.zomato.ui.base.BaseActivity;
import com.example.zomato.ui.restaurants.NavigationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends BaseActivity implements LoginFragment.OnItemSelectedListener, SignUpFragment.OnItemSelectedListener {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(this,"User Logged in",Toast.LENGTH_SHORT).show();
        } else {
            presentFragment(R.id.fragment_holder_main, new LoginFragment());
        }
    }

    @Override
    public void loginClicked(String email, String password) {
        //TODO: show loader here
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("logged in", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            presentActivity(new NavigationActivity());
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplication(),"Failed to log in",Toast.LENGTH_SHORT).show();
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
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            presentActivity(new NavigationActivity());

                        } else {
                            // If sign in fails, display a message to the user.
                        }
                    }
                });
    }

    @Override
    public void loginClicked() {
        presentFragment(R.id.fragment_holder_main, new LoginFragment());
    }
}
