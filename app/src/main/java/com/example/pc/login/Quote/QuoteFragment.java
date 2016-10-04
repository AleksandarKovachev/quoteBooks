package com.example.pc.login.Quote;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc.login.MySql.Downloader;
import com.example.pc.login.R;

public class QuoteFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private String urlAddress = "http://www.serverformyquoteapplication.netne.net/quotes.php";
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private String type = "quotes";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_quote_fragment, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        new Downloader(getContext(), urlAddress, recyclerView, type).execute();
                                        swipeRefreshLayout.setRefreshing(false);
                                    }
                                }
        );
        return rootView;
    }

    @Override
    public void onRefresh() {
        new Downloader(getContext(), urlAddress, recyclerView, type).execute();
        swipeRefreshLayout.setRefreshing(false);
    }
}
