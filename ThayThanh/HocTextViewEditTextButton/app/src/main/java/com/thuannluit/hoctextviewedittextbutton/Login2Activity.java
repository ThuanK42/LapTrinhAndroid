package com.thuannluit.hoctextviewedittextbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;

public class Login2Activity extends AppCompatActivity {
    EditText email;
    EditText password;
    Button register;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        setupUI();
        setupListeners();
    }
    private void setupUI() {
        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPassword);
        register = findViewById(R.id.btnRegister);
        login = findViewById(R.id.btnLogin);
    }
    private void setupListeners() {
        login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                checkUsername();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

            }
        });
    }
    void checkUsername() {
        boolean isValid = true;
        if (isEmpty(email)) {
            email.setError("You must enter email to login!");
            isValid = false;
        } else {
            if (!isEmail(email)) {
                email.setError("Enter valid email!");
                isValid = false;
            }
        }

        if (isEmpty(password)) {
            password.setError("You must enter password to login!");
            isValid = false;
        } else {
            if (password.getText().toString().length() < 4) {
                password.setError("Password must be at least 4 chars long!");
                isValid = false;
            }
        }

        //check email and password
        //IMPORTANT: here should be call to backend or safer function for local check; For example simple check is cool
        //For example simple check is cool
        if (isValid) {
            String usernameValue = email.getText().toString();
            String passwordValue = password.getText().toString();
            if (usernameValue.equals("lytutrong02@gmail.com") && passwordValue.equals("admin")) {
                //everything checked we open new activity
                Intent i = new Intent(Login2Activity.this, Hello.class);
                startActivity(i);
                //we close this activity
                this.finish();
            } else {
                Toast t = Toast.makeText(this, "Wrong email or password!", Toast.LENGTH_SHORT);
                t.show();
            }
        }
    }
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
}
