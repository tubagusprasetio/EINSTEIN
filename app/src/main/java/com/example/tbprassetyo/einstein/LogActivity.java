package com.example.tbprassetyo.einstein;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.tbprassetyo.einstein.data.DBHelper;

public class LogActivity extends AppCompatActivity {

    private TextView usernameLog;
    private TextView nisnlog;
    private TextView correctionTextMateri;
    private TextView correctionTextV;
    private TextView topTextCa;
    private TextView topTextCw;

    private DBHelper dbHelper;

    @Override
    protected  void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_log);

        usernameLog          = findViewById(R.id.username);
        nisnlog              = findViewById(R.id.NISN_log);
        correctionTextMateri = findViewById(R.id.correction_materi);
        correctionTextV      = findViewById(R.id.correction_v);
        topTextCa            = findViewById(R.id.top_ca);
        topTextCw            = findViewById(R.id.top_cw);

        dbHelper = new DBHelper(this);

    }

    private void ShowLog(){

    }
}
