package com.example.pc.login.LoginRegister;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.pc.login.R;

public class Login extends Fragment implements View.OnClickListener{

    EditText usernameEt, passwordEt;
    Button login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_login, container, false);


        usernameEt = (EditText) rootView.findViewById(R.id.username);
        passwordEt = (EditText) rootView.findViewById(R.id.password);

        login = (Button) rootView.findViewById(R.id.login);
        login.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        String username = usernameEt.getText().toString();
        String password = passwordEt.getText().toString();
        String type = "login";

        BackgroundWorker backgroundWorker = new BackgroundWorker(getContext());
        backgroundWorker.execute(type, username, password);
    }
}
