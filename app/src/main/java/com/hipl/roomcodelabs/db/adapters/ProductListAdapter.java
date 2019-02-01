package com.hipl.roomcodelabs.db.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hipl.roomcodelabs.MainActivity;
import com.hipl.roomcodelabs.NewProductActivity;
import com.hipl.roomcodelabs.R;
import com.hipl.roomcodelabs.db.entities.Product;

import java.util.List;

import static com.hipl.roomcodelabs.NewProductActivity.EXTRA_REPLY;

/**
 * Created by karanjm on 15-09-2018.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;
    private List<Product> mProducts; // Cached copy of products
    private Context mContext;

    public ProductListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView = mInflater.inflate(R.layout.row_products, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final WordViewHolder holder, int position) {
        if (mProducts != null) {
            Product current = mProducts.get(position);
            holder.wordItemView.setText(current.getPName() + "\n" + current.getPDescription() + "\n" + current.getPSellPrice());

            holder.wordItemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int DRAWABLE_RIGHT = 2;

                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        if (event.getRawX() >= (holder.wordItemView.getRight() - holder.wordItemView.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            Intent replyIntent = new Intent(mContext, NewProductActivity.class);
                            Gson gson = new Gson();
                            String word = gson.toJson(mProducts.get(holder.getAdapterPosition()));
                            replyIntent.putExtra(EXTRA_REPLY, word);
                            ((MainActivity) mContext).startActivityForResult(replyIntent, 101);
                            return true;
                        }
                    }
                    return false;
                }
            });
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Product");
        }
    }

    public Product getItem(int pos) {
        return mProducts.get(pos);
    }

    public void setProducts(List<Product> words) {
        mProducts = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mProducts != null)
            return mProducts.size();
        else
            return 0;
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }
}