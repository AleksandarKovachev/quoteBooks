package com.example.pc.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc.login.Adapter.ViewPagerAdapter;
import com.example.pc.login.Quote.InputNewQuote;
import com.example.pc.login.Quote.QuoteFragment;
import com.github.florent37.bubbletab.BubbleTab;

public class Quotes_Tab extends Fragment {

    private BubbleTab tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_quote, container, false);

        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (BubbleTab) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        return rootView;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new QuoteFragment(), "QUOTES");
        adapter.addFragment(new InputNewQuote(), "INPUT NEW QUOTE");
        viewPager.setAdapter(adapter);
    }
}
