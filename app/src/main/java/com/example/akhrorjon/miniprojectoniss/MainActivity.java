package com.example.akhrorjon.miniprojectoniss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;

    TextView createAccount;
    ArrayList<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Inha Banking Program");
    createAccount = (TextView)findViewById(R.id.txt_sign_in_create);
        username = (EditText)findViewById(R.id.edit_sign_in_mail);
        password = (EditText)findViewById(R.id.edit_sign_in_password);

    createAccount.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this,SignUp.class);
            startActivity(intent);
            finish();
        }
    });


        persons = new ArrayList<Person>();

        loadData();
    }

    private void setTextToTextView(){
        String text = "";
        for (int i=0; i<persons.size(); i++){
            text = text + persons.get(i).getUname() + " " + persons.get(i).getUpass() + " " + persons.get(i).getSalt() + "\n";
        }
    }

    public void loadData(){
        persons.clear();

        File file = getApplicationContext().getFileStreamPath("Users.txt");
        String lineFromFile;
        if(file.exists()){
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("Users.txt")));
                while ((lineFromFile = reader.readLine()) != null){
                    StringTokenizer tokens = new StringTokenizer(lineFromFile,":");
                    Person person = new Person(tokens.nextToken(),tokens.nextToken(),tokens.nextToken());
                    persons.add(person);
                }
                reader.close();
                setTextToTextView();
            }catch (IOException e){
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void btnLog(View v) throws NoSuchAlgorithmException {
        String usernamelog1 = username.getText().toString();
        String passwordlog1 = password.getText().toString();
        String hashpass = sha128(passwordlog1);
        for (Person person: persons)
            if (person.getUname().equals(usernamelog1)&&person.getUpass().equals(hashpass)) {
                Intent intent = new Intent(MainActivity.this, AccountMain.class);
                finish();
                startActivity(intent);
//hello
            }
    }

    private boolean findPerson(String username) {

        for (Person person: persons) {
            if (person.getUname().equals(username))
                return true;
        }
        return false;
    }

    static String sha128(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }



}
