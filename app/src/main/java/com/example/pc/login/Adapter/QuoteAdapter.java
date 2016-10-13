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
import com.example.pc.login.MySql.PicassoClient;
import com.example.pc.login.Quote.Quote;
import com.example.pc.login.R;

import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.MyViewHolder> {

    private Context context;
    private int layoutId;
    private List<Quote> data;

    public QuoteAdapter(Context context, int textViewResourceId, List<Quote> objects) {
        this.context = context;
        layoutId = textViewResourceId;
        data = objects;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_list_quote, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        YoYo.with(Techniques.FadeInUp).playOn(holder.cardView);
        Quote quote = data.get(position);
        holder.twQuote.setText(quote.getQuote());
        holder.twAuthor.setText(quote.getAuthor());


        PicassoClient.downloadImage(context, quote.getImageUrl(), holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, data.get(position).getQuote(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(holder.menu, position);
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView twQuote, twAuthor;
        public ImageView img, menu;
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);

            twQuote = (TextView) view.findViewById(R.id.tw_quote);
            twAuthor = (TextView) view.findViewById(R.id.tw_author);
            img = (ImageView) view.findViewById(R.id.imgList);

            cardView = (CardView) view.findViewById(R.id.carr_view);

            menu = (ImageView) view.findViewById(R.id.imgMenu);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    private void showPopupMenu(View view, int position) {
        // inflate menu
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_quote, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(data.get(position).getQuote()));
        popup.show();
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        String quote;

        public MyMenuItemClickListener(String quote) {
            this.quote = quote;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_share:
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("text/plain");
                    share.putExtra(Intent.EXTRA_TEXT, quote);

                    context.startActivity(Intent.createChooser(share, "Share this quote with:"));
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
