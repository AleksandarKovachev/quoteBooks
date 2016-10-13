package com.example.pc.login.Quote;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc.login.Adapter.QuoteAdapter;
import com.example.pc.login.MySql.Downloader;
import com.example.pc.login.R;
import com.example.pc.login.SQL.DBPref;

import java.util.ArrayList;

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

        if ((isNetworkAvailable(getContext()))) {
            swipeRefreshLayout.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            swipeRefreshLayout.setRefreshing(true);
                                            new Downloader(getContext(), urlAddress, recyclerView, type).execute();
                                            swipeRefreshLayout.setRefreshing(false);
                                        }
                                    }
            );
        }

        return rootView;
    }

    @Override
    public void onRefresh() {
        if (!(isNetworkAvailable(getContext()))) {
            QuoteAdapter quoteAdapter;

            ArrayList<Quote> quoteList = new ArrayList<Quote>();

            DBPref pref = new DBPref(getContext());
            Cursor c = pref.getVals();

            if (c.moveToFirst()) {
                do {
                    Quote quote = new Quote();
                    quote.setQuote(c.getString(c.getColumnIndex("quote")));
                    quote.setAuthor(c.getString(c.getColumnIndex("author")));
                    quote.setImageUrl("nqma");

                    quoteList.add(quote);
                } while (c.moveToNext());
            }

            quoteAdapter = new QuoteAdapter(getContext(), R.layout.main_list_quote, quoteList);

            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(llm);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(quoteAdapter);
            swipeRefreshLayout.setRefreshing(false);
        } else {
            new Downloader(getContext(), urlAddress, recyclerView, type).execute();
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    public boolean isNetworkAvailable(final Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
}
