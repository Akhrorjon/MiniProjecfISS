package com.example.akhrorjon.miniprojectoniss;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class SignUp extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText uconpassword;
    EditText fullname;
    EditText bankaccount;
    TextView signUp;
    TextView back;
    ArrayList<Person> persons;

    public int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = (EditText)findViewById(R.id.edit_sign_in_mail);
        password = (EditText)findViewById(R.id.edit_sign_in_password1);
        uconpassword = (EditText)findViewById(R.id.edit_sign_in_password2);
        fullname = (EditText)findViewById(R.id.edit_sign_in_fullname);
        bankaccount = (EditText)findViewById(R.id.edit_sign_in_bankacc);
        signUp = (TextView)findViewById(R.id.signUp);
        back = (TextView)findViewById(R.id.txt_back_log_in);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(SignUp.this,MainActivity.class);
                finish();
                startActivity(in);
            }
        });

        persons = new ArrayList<Person>();

        loadData();

    }

    public void loadData(){
        persons.clear();

        File file = getApplicationContext().getFileStreamPath("Users.txt");
        String lineFromFile;
        if(file.exists()){
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("Users.txt")));
                while ((lineFromFile = reader.readLine()) != null){
                    StringTokenizer tokens = new StringTokenizer(lineFromFile,":");
                    Person person = new Person(tokens.nextToken(),tokens.nextToken(),tokens.nextToken(),tokens.nextToken(),tokens.nextToken(),tokens.nextToken());
                    persons.add(person);
                }
                reader.close();
            }catch (IOException e){
                Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean findPerson(String username) {

        for (Person person: persons) {
            if (person.getUname().equals(username))
                return true;
        }
        return false;
    }


    public void btnSave(View v) throws NoSuchAlgorithmException {
        String username1 = username.getText().toString();
        String password1 = password.getText().toString();
        String password2 = uconpassword.getText().toString();
        String fulname = fullname.getText().toString();
        String bankacc = bankaccount.getText().toString();
        String balance = "0";

        if(username1.length() < 13 && username1.length() > 5){
            if (password1.length()>=8 ) {
                if(password1.equals(password2)) {
                    if (fulname.length() < 30) {
                        if (bankacc.length() == 14) {

                            String hashpass = sha128(password1);
                            if (findPerson(username1))
                                return;

                            Random rand = new Random();
                            int n = rand.nextInt(1000000000) + 1;
                            String randsaltStr = Integer.toString(n);
                            Person person = new Person(username1, hashpass, randsaltStr, fulname, bankacc,balance);
                            persons.add(person);

                            try {
                                FileOutputStream file = openFileOutput("Users.txt", MODE_PRIVATE);
                                OutputStreamWriter outputFile = new OutputStreamWriter(file);
                                String temp;
                                for (int i = 0; i < persons.size(); i++) {

                                    outputFile.write(persons.get(i).getUname() + ":" + persons.get(i).getUpass() + ":" + persons.get(i).getSalt() + ":" + persons.get(i).getFname() + ":" + persons.get(i).getBaccount() + ":" + persons.get(i).getBalance() + "\n");
                                }
                                outputFile.flush();
                                outputFile.close();

                                Toast.makeText(SignUp.this, "Successfully saved", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(SignUp.this, MainActivity.class);
                                startActivity(i);
                            } catch (IOException e) {
                                Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignUp.this, "Password is not same with confirm password", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(SignUp.this, "Bank account please", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(SignUp.this, "Too long name!", Toast.LENGTH_SHORT).show();
                }

            }else {
                Toast.makeText(SignUp.this, "Your password should be minimun 8 character!", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(SignUp.this, "User ID size should be between 6 and 12", Toast.LENGTH_SHORT).show();
        }

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
