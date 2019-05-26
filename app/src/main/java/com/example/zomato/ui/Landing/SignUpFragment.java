package com.example.zomato.ui.Landing;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.zomato.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SignUpFragment extends Fragment {

    @BindView(R.id.first_name)
    EditText firstName;
    @BindView(R.id.last_name)
    EditText lastName;
    @BindView(R.id.email_register)
    TextView email;
    @BindView(R.id.password_register)
    TextView password;
    @BindView(R.id.retype_password)
    TextView retypePassword;
    @BindView(R.id.male_radiobutton)
    RadioButton maleRadioButton;
    @BindView(R.id.female_radiobutton)
    RadioButton femaleRadioButton;

    @OnClick(R.id.signup_button)
    void signupClicked() {

        String firstNameText = firstName.getText().toString();
        String lastNameText = lastName.getText().toString();
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        String retypePasswordText = retypePassword.getText().toString();

        if (firstNameText.length() == 0) {
            Toast.makeText(SignUpFragment.this.getContext(), "First name is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (lastNameText.length() == 0) {
            Toast.makeText(SignUpFragment.this.getContext(), "Last name is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (emailText.length() == 0) {
            Toast.makeText(SignUpFragment.this.getContext(), "Email is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (passwordText.length() < 6) {
            Toast.makeText(SignUpFragment.this.getContext(), "Password must be at least 6 digits", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!retypePasswordText.equals(password.getText().toString())) {
            Toast.makeText(SignUpFragment.this.getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        listener.signUpClicked(emailText, passwordText, firstNameText, lastNameText);
    }

    @OnClick(R.id.login_already)
    void login() {
        listener.loginClicked();
    }

    private Unbinder unbinder;

    public interface OnItemSelectedListener {
        public void signUpClicked(String email, String password, String firstName, String lastName);

        public void loginClicked();
    }

    private OnItemSelectedListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString() + "must implement SignUpFragment.OnItemSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
