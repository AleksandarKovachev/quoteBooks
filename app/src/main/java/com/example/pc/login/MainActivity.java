package com.example.pc.login;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.pc.login.Books.BookFragment;
import com.example.pc.login.LoginRegister.Login;
import com.example.pc.login.LoginRegister.Register;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (!(isNetworkAvailable(this))){
            Snackbar snackbar = Snackbar.make(drawerLayout, "Turn your internet on", Snackbar.LENGTH_LONG);
            snackbar.show();
        }else {
            Snackbar snackbar = Snackbar.make(drawerLayout, "Your internet is on", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                break;
            case 1:
                fragment = new BookFragment();
                title = getString(R.string.tittle_book);
                break;
            case 2:
                fragment = new Quotes_Tab();
                title = getString(R.string.tittle_quote);
                break;
            case 3:
                fragment = new Login();
                title = getString(R.string.title_activity_login);
                break;
            case 4:
                fragment = new Register();
                title = getString(R.string.title_activity_register);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            getFragmentManager().popBackStackImmediate();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(null);
            fragmentManager.popBackStack();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public boolean isNetworkAvailable(final Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
}
