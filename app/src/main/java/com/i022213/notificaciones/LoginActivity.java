package com.i022213.notificaciones;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.i022213.notificaciones.Data.DataUser;
import com.i022213.notificaciones.Models.User;
import com.i022213.notificaciones.Views.ContainerActivity;
import com.i022213.notificaciones.Views.CreateAccountActivity;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    DataUser dataUser;
    Button login;
    EditText usernameLogin,passwordLogin;
    public static User userLogin;
    List<User> userList;
    String[] findUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.btnLogin);
        usernameLogin = (EditText) findViewById(R.id.l_username);
        passwordLogin = (EditText) findViewById(R.id.l_password);
        dataUser = new DataUser(this);
        dataUser.open();

        userLogin = dataUser.checkStatusLogin();
        if(userLogin == null ){

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(usernameLogin.getText().toString().equals("")||passwordLogin.getText().toString().equals(""))
                    {
                        Toast.makeText(getApplicationContext(), getString(R.string.info_to_correct), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        findUser = new String[2];
                        findUser = dataUser.findUser(usernameLogin.getText().toString(),passwordLogin.getText().toString());
                        if (findUser[0].equals((usernameLogin.getText().toString())) && findUser[1].equals((passwordLogin.getText().toString())) ){
                            dataUser.statusOn(usernameLogin.getText().toString(),passwordLogin.getText().toString());
                            Toast.makeText(getApplicationContext(), getString(R.string.info_welcome), Toast.LENGTH_SHORT).show();
                            goCreateContainer();
                            System.exit(0);
                        }else
                        {Toast.makeText(getApplicationContext(), getString(R.string.info_not_welcome) , Toast.LENGTH_SHORT).show();}

                    }
                }
            });

        }else {
            goCreateContainer();
        }



    }

    public void onShowAccount(View view){
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public void goCreateContainer(){
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }


}