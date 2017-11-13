package com.example.ashwin.sqlitetry3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText name, password;
    MyView m;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=(EditText)findViewById(R.id.edittextname);
        password=(EditText)findViewById(R.id.edittextpass);
        next=(Button)findViewById(R.id.buttonnext);
        m=new MyView(this);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

    }

    public void add(View view){
        String n = name.getText().toString();
        String p = password.getText().toString();
        if (n.isEmpty() || p.isEmpty()) {
            Message.message(getApplicationContext(), "Enter both name and password");
        }
        else {
            long id = m.add(n, p);
            if (id <= 0) {
                Message.message(getApplicationContext(), "Insertion Unsuccessful");
                name.setText("");
                password.setText("");
            } else {
                Message.message(getApplicationContext(), "Insertion Successful");
                name.setText("");
                password.setText("");
            }
        }
    }

    public void view(View view){
        String data = m.view();
        Message.message(this,data);
    }

    /*public void next(){
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }*/

}
