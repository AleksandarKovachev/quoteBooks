package com.example.pc.login.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.pc.login.Books.Book;
import com.example.pc.login.Books.BookDescription;
import com.example.pc.login.MySql.PicassoClient;
import com.example.pc.login.R;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private Context context;
    private int layoutId;

    private static List<Book> data;

    public static List<Book> getData() {
        return data;
    }

    public BookAdapter(Context context, int textViewResourceId, List<Book> objects) {
        this.context = context;
        layoutId = textViewResourceId;
        data = objects;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_list_books, parent, false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BookViewHolder holder, final int position) {
        YoYo.with(Techniques.FadeInUp).playOn(holder.cardView);
        Book book = data.get(position);
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());

        PicassoClient.downloadImage(context, book.getImageUrl(), holder.img);

        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(holder.menu);
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pos = "" + position;
                Intent intent = new Intent(context, BookDescription.class);
                intent.putExtra("POSITION", pos);
                context.startActivity(intent);
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pos = "" + position;
                Intent intent = new Intent(context, BookDescription.class);
                intent.putExtra("POSITION", pos);
                context.startActivity(intent);
            }
        });
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        public TextView title, author;
        public ImageView img, menu;
        public CardView cardView;

        public BookViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            author = (TextView) view.findViewById(R.id.author);
            img = (ImageView) view.findViewById(R.id.cover);

            cardView = (CardView) view.findViewById(R.id.carr_view_book);

            menu = (ImageView) view.findViewById(R.id.overflow);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_books, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        public MyMenuItemClickListener() {

        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_share:
                    Toast.makeText(context, "Share", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_favorite:
                    Toast.makeText(context, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }
}
