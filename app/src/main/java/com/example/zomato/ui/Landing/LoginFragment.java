package com.example.zomato.ui.Landing;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.zomato.R;
import com.example.zomato.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginFragment extends Fragment {

    @BindView(R2.id.email)
    TextView email;
    @BindView(R2.id.password)
    TextView password;

    @OnClick(R.id.login)
    void login() {
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        if(emailText.length() == 0) {
            Toast.makeText(LoginFragment.this.getContext(),"Please enter your email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(passwordText.length() == 0) {
            Toast.makeText(LoginFragment.this.getContext(),"Please enter your password",Toast.LENGTH_SHORT).show();
            return;
        }
        listener.loginClicked(emailText, passwordText);

    }

    @OnClick(R.id.register)
    void register(){
        listener.onRegisterClicked();
    }

    private Unbinder unbinder;

    public  interface OnItemSelectedListener {
        public  void loginClicked(String email, String password);
        public void onRegisterClicked();
    }

    private OnItemSelectedListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement LoginFragment.OnItemSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment,container,false);
        unbinder = ButterKnife.bind(this,view);
        return  view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
