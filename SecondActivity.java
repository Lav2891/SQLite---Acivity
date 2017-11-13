package com.example.ashwin.sqlitetry3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ashwin on 11/9/2017.
 */

public class SecondActivity extends AppCompatActivity {

    TextView t;
    MyView mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiry_second);

        t=(TextView)findViewById(R.id.textview);
        mv = new MyView(this);
        t.setText(mv.view());

    }
   /* public void view(View view) {
        String data = mv.view();
        t.setText(data);
    }*/

}
