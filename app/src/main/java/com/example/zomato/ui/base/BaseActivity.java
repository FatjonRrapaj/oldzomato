package com.example.zomato.ui.base;

import android.app.Activity;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Iterator;
import java.util.Map;

public class BaseActivity extends AppCompatActivity {

    protected void presentFragment(int path, Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(path, fragment);
        ft.commit();
    }

    protected void presentActivity(BaseActivity activity, Map<String, String> arguments ) {
        Intent intent = new Intent(getBaseContext(),activity.getClass());
        for (Map.Entry<String, String> entry : arguments.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            intent.putExtra(key,value);
        }
        startActivity(intent);
    }

    protected void presentActivity(BaseActivity activity) {
        Intent intent = new Intent(getBaseContext(),activity.getClass());
        startActivity(intent);
    }

    protected void presentActivity(BaseActivity activity,String argumentKey, String argumentValue) {
        Intent intent = new Intent(getBaseContext(),activity.getClass());
        intent.putExtra(argumentKey,argumentValue);
        startActivity(intent);
    }
}
