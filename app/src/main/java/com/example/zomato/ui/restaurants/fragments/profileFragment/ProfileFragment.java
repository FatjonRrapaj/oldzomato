package com.example.zomato.ui.restaurants.fragments.profileFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.zomato.R;
import com.example.zomato.db.objects.User;
import com.example.zomato.ui.base.BaseFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ProfileFragment extends BaseFragment {

    @BindView(R.id.name_profile)
    TextView nameTextView;

    @BindView(R.id.email_profile)
    EditText emailEditText;

    @BindView(R.id.password_profile)
    EditText passwordEditText;

    @BindView(R.id.city_profile)
    TextView selectedCityTextView;


    private Unbinder unbinder;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private User user;

    @OnClick(R.id.update_email_profile)
    void updateEmailClicked() {
        if (!emailEditText.getText().toString().equals(user.getEmail().toString())) {
            showProgressBar("Updating email...");
            firebaseUser.updateEmail(emailEditText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    dismissProgressDialog();
                    if (task.isSuccessful()) {
                        Toast.makeText(ProfileFragment.this.getContext(), "Email updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ProfileFragment.this.getContext(), "Failed to update the email", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @OnClick(R.id.update_password_profile)
    void updatePasswordClicked() {
        if (passwordEditText.getText().toString().length() >= 6) {
            showProgressBar("Updating password...");
            firebaseUser.updateEmail(passwordEditText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(ProfileFragment.this.getContext(), "Password updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ProfileFragment.this.getContext(), "Failed to update the password", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @OnClick(R.id.sign_out_btn)
    void signOutClicked() {
        firebaseAuth.signOut();
        EventBus.getDefault().post("Signed Out");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseUser = getFirebaseUser();
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseReference = database.getReference("users").child(firebaseUser.getUid());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                nameTextView.setText(user.getFirstName() + " " + user.getLastName());
                emailEditText.setText(user.getEmail());
                passwordEditText.setHint("Change password...");
                selectedCityTextView.setText(user.getSelectedCityName());
                System.out.println(user);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
