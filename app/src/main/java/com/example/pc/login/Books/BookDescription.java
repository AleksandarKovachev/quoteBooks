package com.example.pc.login.Books;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.login.Adapter.BookAdapter;
import com.example.pc.login.MySql.PicassoClient;
import com.example.pc.login.R;

import java.util.List;

public class BookDescription extends AppCompatActivity {

    ImageView img;
    TextView title, author, year, description, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_tab);

        String position = getIntent().getStringExtra("POSITION");

        List<Book> data = BookAdapter.getData();

        Book book = data.get(Integer.parseInt(position));

        img = (ImageView) findViewById(R.id.bookImage);

        title = (TextView) findViewById(R.id.bookTitle);
        author = (TextView) findViewById(R.id.bookAuthor);
        year = (TextView) findViewById(R.id.bookYear);
        description = (TextView) findViewById(R.id.bookDescription);
        price = (TextView) findViewById(R.id.bookPrice);

        PicassoClient.downloadImage(this, book.getImageUrl(), img);
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        year.setText(book.getYear());
        price.setText(book.getPrice());
        description.setText(book.getDescription());
    }
}
