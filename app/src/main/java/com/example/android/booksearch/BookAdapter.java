package com.example.android.booksearch;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Sagar on 8/30/2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    private static class ViewHolder {
        public ImageView ivCover;
        public TextView tvTitle;
        public TextView tvAuthor;
    }

    public BookAdapter(Context context, ArrayList<Book> mBooks) {
        super(context, 0, mBooks);
    }

    // Translates a particular `Book` given a position
    // into a relevant row within an AdapterView

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Book book = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view

        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_book, parent, false);
            viewHolder.ivCover = (ImageView)convertView.findViewById(R.id.ivBookCover);
            viewHolder.tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
            viewHolder.tvAuthor = (TextView)convertView.findViewById(R.id.tvAuthor);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        // Populate the data into the template view using the data object
        viewHolder.tvTitle.setText(book.getTitle());
        viewHolder.tvAuthor.setText(book.getAuthor());
        Picasso.with(getContext())
                .load(Uri.parse(book.getCoverUrl()))
                .error(R.drawable.ic_error_black_24dp)
                .into(viewHolder.ivCover);

        return convertView;

    }
}
