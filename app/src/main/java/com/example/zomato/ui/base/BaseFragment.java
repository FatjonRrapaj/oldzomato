package com.example.zomato.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void showProgressBar(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(BaseFragment.this.getContext());
            progressDialog.setTitle("Loading");
        }
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    protected void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onResume() {
        dismissProgressDialog();
        super.onResume();
    }
}
