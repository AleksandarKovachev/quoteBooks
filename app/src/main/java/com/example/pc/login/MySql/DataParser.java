package com.example.pc.login.MySql;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pc.login.Adapter.BookAdapter;
import com.example.pc.login.Adapter.QuoteAdapter;
import com.example.pc.login.Books.Book;
import com.example.pc.login.Quote.Quote;
import com.example.pc.login.R;
import com.example.pc.login.SQL.DBPref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataParser extends AsyncTask<Void, Void, Boolean> {

    Context c;
    String text;
    private RecyclerView recyclerView;
    QuoteAdapter quoteAdapter;
    String type;

    BookAdapter bookAdapter;
    ArrayList<Quote> quoteList = new ArrayList<>();
    ArrayList<Book> bookList = new ArrayList<>();

    public DataParser(Context c, String text, RecyclerView lv, String type) {
        this.c = c;
        this.text = text;
        this.recyclerView = lv;
        this.type = type;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);

        if (type.equals("quotes")) {
            if (result) {
                quoteAdapter = new QuoteAdapter(c, R.layout.main_list_quote, quoteList);

                LinearLayoutManager llm = new LinearLayoutManager(c);
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(llm);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(quoteAdapter);
            }
        }
        if (type.equals("books")) {
            if (result) {
                bookAdapter = new BookAdapter(c, R.layout.main_list_books, bookList);

                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(c, 2);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(bookAdapter);
            }
        }
    }

    private Boolean parseData() {

        if (type.equals("quotes")) {
            try {
                JSONArray ja = new JSONArray(text);
                JSONObject jo = null;

                quoteList.clear();
                Quote quote;

                for (int i = 0; i < ja.length(); i++) {
                    jo = ja.getJSONObject(i);
                    String quoteDb = jo.getString("quote");
                    String author = jo.getString("author");
                    String imageUrl = jo.getString("imageurl");

                    DBPref pref = new DBPref(c);
                    pref.addRecord(quoteDb, author);

                    quote = new Quote();
                    quote.setQuote(quoteDb);
                    quote.setAuthor(author);
                    quote.setImageUrl(imageUrl);
                    quoteList.add(quote);
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (type.equals("books")){
            try {
                JSONArray ja = new JSONArray(text);
                JSONObject jo = null;

                bookList.clear();
                Book book;

                for (int i = 0; i < ja.length(); i++) {
                    jo = ja.getJSONObject(i);
                    String title = jo.getString("title");
                    String author = jo.getString("author");
                    String imageUrl = jo.getString("imageurl");
                    String description = jo.getString("description");
                    String price = jo.getString("price");
                    String year = jo.getString("year");
                    book = new Book();
                    book.setTitle(title);
                    book.setAuthor(author);
                    book.setImageUrl(imageUrl);
                    book.setDescription(description);
                    book.setPrice(price);
                    book.setYear(year);
                    bookList.add(book);
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
