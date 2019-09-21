package com.thuannluit.hoctextviewedittextbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText password;
    EditText password2;
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstName=findViewById(R.id.txtName);
        lastName=findViewById(R.id.txtLastName);
        email=findViewById(R.id.txtEmail);
        password=findViewById(R.id.txtPassword);
        password2=findViewById(R.id.txtPasswordAgain);
        register=findViewById(R.id.btnRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDataEntered();
            }
        });
    }
    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    void checkDataEntered(){
        if (isEmpty(firstName)) {
            Toast t = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_SHORT);
            t.show();
        }
        if (isEmpty(lastName)) {
            lastName.setError("Last name is required!");
        }
        if (isEmpty(password)) {
            password.setError("Password is required!");
        }
        if (isEmpty(password2)) {
            password2.setError("Password Again is required!");
        }
        if (isEmail(email) == false) {
            email.setError("Enter valid email!");
        }
    }

}
