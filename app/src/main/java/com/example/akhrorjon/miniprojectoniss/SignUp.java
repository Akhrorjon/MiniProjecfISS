package com.example.akhrorjon.miniprojectoniss;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText usrName;
    EditText passw;
    EditText confirmpassw;
    TextView signUp;
    TextView back;

    public int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        usrName = (EditText)findViewById(R.id.edit_sign_in_mail);
        passw = (EditText)findViewById(R.id.edit_sign_in_password1);
        confirmpassw = (EditText)findViewById(R.id.edit_sign_in_password2);
        signUp = (TextView)findViewById(R.id.signUp);
        back = (TextView)findViewById(R.id.txt_back_log_in);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name;
                String password;
                name = usrName.getText().toString();
                password = passw.getText().toString();

                SharedPreferences prefs = getSharedPreferences("Sign_Up", MODE_PRIVATE);

                int idName = prefs.getInt("count", 0); //0 is the default value.

                SharedPreferences.Editor editor = getSharedPreferences("Sign_Up", MODE_PRIVATE).edit();
                editor.putString(String.valueOf(idName), "Elena");
                idName = idName + 1;
                editor.putInt("count", idName);
                editor.apply();

                Toast.makeText(getApplicationContext(), String.valueOf(idName), Toast.LENGTH_LONG).show();


            }
        });
    }

}
