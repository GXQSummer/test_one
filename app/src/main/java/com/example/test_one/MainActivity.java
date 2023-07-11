package com.example.test_one;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText id, pwd;
    private Button login;
    private CheckBox rmb;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.id);
        pwd = findViewById(R.id.pwd);
        //哈哈哈哈
        login = findViewById(R.id.login);
        rmb = findViewById(R.id.is_rmb);
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        boolean isRemember = pref.getBoolean("isRmb", false);
        if (isRemember) {
            id.setText(pref.getString("id", ""));
            pwd.setText(pref.getString("pwd", ""));
            rmb.setChecked(true);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editId = id.getText().toString();
                String editPwd = pwd.getText().toString();

                if (editId.equals("admin") && editPwd.equals("123456")) {
                    editor = pref.edit();
                    if (rmb.isChecked()) {
                        editor.putString("id", editId);
                        editor.putString("pwd", editPwd);
                        editor.putBoolean("isRmb", true);
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}