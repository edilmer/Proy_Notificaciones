package com.i022213.notificaciones.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.i022213.notificaciones.Adapters.UserAdapter;
import com.i022213.notificaciones.Data.DataUser;
import com.i022213.notificaciones.LoginActivity;
import com.i022213.notificaciones.Models.User;
import com.i022213.notificaciones.R;

import java.util.List;

public class CreateAccountActivity extends AppCompatActivity {

    Button createAccount;
    EditText name,username,email,password, passwordConfirm;
    DataUser dataUser;
    ListView lista;
    List<User> listarusuarios;
    UserAdapter adapterUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        showTolbar(getResources().getString(R.string.txt_title_toolbar),true);

        createAccount = (Button) findViewById(R.id.btn_account);
        name = (EditText) findViewById(R.id.id_account_name);
        username = (EditText) findViewById(R.id.id_account_user);
        email = (EditText) findViewById(R.id.id_account_email);
        password = (EditText) findViewById(R.id.id_account_paswordAcount);
        passwordConfirm  = (EditText) findViewById(R.id.id_account_paswordConfirm);



        dataUser = new DataUser(this);
        dataUser.open();

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(username.getText().toString().equals("")||password.getText().toString().equals("")
                        ||passwordConfirm.getText().toString().equals("")
                        ||name.getText().toString().equals("")||email.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), getString(R.string.info_to_correct), Toast.LENGTH_SHORT).show();
                }
                // check if both password matches
                else if(!password.getText().toString().equals(passwordConfirm.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), getString(R.string.txt_incorrect_passwords), Toast.LENGTH_SHORT).show();

                }
                else
                {
                    createData();
                    Toast.makeText(getApplicationContext(),getString(R.string.txt_successful_registration), Toast.LENGTH_SHORT).show();
                    Loginactivity();
                    System.exit(0);




                }
            }
        });

    }


    private void showTolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    private void createData(){
        User user = new User();
        user.setName(name.getText().toString());
        user.setEmail(email.getText().toString());
        user.setUsername(username.getText().toString());
        user.setPassword(password.getText().toString());
        user.setStatus("false");

        dataUser.create(user);
    }

    public void Loginactivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}