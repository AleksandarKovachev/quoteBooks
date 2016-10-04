package com.example.pc.login.LoginRegister;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.pc.login.R;

public class Register extends Fragment implements View.OnClickListener{

    EditText usernameEt, passwordEt, password2Et, emailEt;
    Button register;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_register, container, false);

        usernameEt = (EditText) rootView.findViewById(R.id.usernameReg);
        passwordEt = (EditText) rootView.findViewById(R.id.password1);
        password2Et = (EditText) rootView.findViewById(R.id.password2);
        emailEt = (EditText) rootView.findViewById(R.id.email);

        register = (Button) rootView.findViewById(R.id.register);
        register.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        String username = usernameEt.getText().toString();
        String password = passwordEt.getText().toString();
        String password2 = password2Et.getText().toString();
        String email = emailEt.getText().toString();
        String type = "register";

        if(password.equals(password2)){
            BackgroundWorker backgroundWorker = new BackgroundWorker(getContext());
            backgroundWorker.execute(type, username, password, email);
        }else{
            AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
            alertDialog.setTitle("Password incorrect");
            alertDialog.setMessage("The passwords does not equals");
            alertDialog.show();
        }
    }
}
