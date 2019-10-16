package com.example.tbprassetyo.einstein;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tbprassetyo.einstein.model.Akun;

public class RegisterActivity extends AppCompatActivity {

    private EditText mNisn;
    private EditText mKelas;
    private EditText mUsername;
    private EditText mPassword;
    private Button mRegButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mNisn = (EditText) findViewById(R.id.nisn_register_form);
        mKelas = (EditText) findViewById(R.id.kelas_register_form);
        mUsername = (EditText) findViewById(R.id.username_register_form);
        mPassword = (EditText) findViewById(R.id.password_register_form);

        mRegButton = (Button) findViewById(R.id.register_button);


        mRegButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                String nisn = mNisn.getText().toString();
                String kelas = mKelas.getText().toString();
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();

                ChatActivity.getDBHelper().addNewAkun(new Akun(nisn,kelas,username,password));
                finish();
            }
        });
    }


}
