package com.example.akhrorjon.miniprojectoniss;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class AccountMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    LinearLayout depositBtn;
    LinearLayout withdrawBtn;
    LinearLayout transferBtn;
    FloatingActionButton fab;
    DrawerLayout drawer;
    TextView Tv1,Tv2;

    EditText depEd;

    ArrayList<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        persons = new ArrayList<Person>();
        loadData();

        this.setTitle("Inha Banking System");
        ///////////////////
        String bankaStr = getIntent().getExtras().getString("bankaccount");


        for (Person person: persons)
            if (person.getBaccount().equals(bankaStr)) {
                String ful = person.getFname();
                Tv1 = (TextView) findViewById(R.id.usernameTv);
                Tv2 = (TextView) findViewById(R.id.balanceTv);
                Tv1.setText(ful);

            }



        ///////////////////

        depositBtn = (LinearLayout)findViewById(R.id.depositXML);
        withdrawBtn = (LinearLayout)findViewById(R.id.withdrawXML);
        transferBtn = (LinearLayout)findViewById(R.id.transferXML);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        depositBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AccountMain.this);
                View mView = getLayoutInflater().inflate(R.layout.deposit, null);

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });



        withdrawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AccountMain.this);
                View mView = getLayoutInflater().inflate(R.layout.withdraw, null);
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

            }
        });

        transferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AccountMain.this);
                View mView = getLayoutInflater().inflate(R.layout.transfer, null);
                Spinner mSpinner = (Spinner) mView.findViewById(R.id.chooseUserSpinnerXML);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AccountMain.this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.Customerlist));

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(adapter);


                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

       // fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_people_black_24dp));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AccountMain.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_show, null);
                mBuilder.setView(mView);
                TextView textView = (TextView)mView.findViewById(R.id.setTv);
                String text = "";
                for (int i=0; i<persons.size(); i++){
                    text = text + persons.get(i).getUname() + " " + persons.get(i).getBaccount() + "\n";
                }
                textView.setText(text);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

/*    public void depositF(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(AccountMain.this);
        View mView = getLayoutInflater().inflate(R.layout.deposit, null);
        EditText balanceed = (EditText)mView.findViewById(R.id.depmoneyEd);

        String depmoneyStr = balanceed.getText().toString();
        int depmoneyInt = Integer.parseInt(depmoneyStr);



        mBuilder.setView(mView);





    }*/


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
                Toast.makeText(AccountMain.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.account_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_change_pwd) {
            Intent intent = new Intent(this, changePwd.class);
            startActivity(intent);
        } else if (id == R.id.nav_delete_user) {
            Toast.makeText(getApplicationContext(), "Delete user", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }








}
