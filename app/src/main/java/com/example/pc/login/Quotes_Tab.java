package com.example.pc.login;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pc.login.Adapter.ViewPagerAdapter;
import com.example.pc.login.Quote.InputNewQuote;
import com.example.pc.login.Quote.QuoteFragment;

public class Quotes_Tab extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_quote, container, false);


        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        return rootView;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new QuoteFragment(), "QUOTES");
        adapter.addFragment(new InputNewQuote(), "INPUT NEW QUOTE");
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {
        TextView tabOne = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        tabOne.setText("QUOTES");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_profile_icon, 0, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        tabTwo.setText("INPUT NEW QUOTE");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_menu_send, 0, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);
    }
}
