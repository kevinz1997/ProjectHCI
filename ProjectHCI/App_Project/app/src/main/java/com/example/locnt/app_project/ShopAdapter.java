package com.example.locnt.app_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.viewHolder> {

    ArrayList<DataShop> arrayList;
    Context context;
    LayoutInflater inflater;
    private RecyclerView recyclerView;

    private class VIEW_TYPES {
        public static final int Footer = 1;
        public static final int Normal = 2;
    }

    boolean isFooter = false;

    public ShopAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public ShopAdapter(ArrayList<DataShop> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview;
        switch (viewType) {
            case VIEW_TYPES.Normal:
                isFooter = false;
                itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
                break;
            case VIEW_TYPES.Footer:
                isFooter = true;
                itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer, parent, false);
                break;
            default:
                isFooter = false;
                itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        }
        return new viewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        if (!isFooter) {
            holder.tvName.setText("Tên sân: " + arrayList.get(position).getName() + "\n" + "Địa chỉ: " + arrayList.get(position).getAddress());
            holder.imgView.setImageResource(arrayList.get(position).getImg());
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView imgView;
        Button btnHomeBook;

        public viewHolder(final View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.txtName);
            imgView = itemView.findViewById(R.id.img_View);
//            btnHomeBook = itemView.findViewById(R.id.btnHomeBook);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (arrayList.get(arrayList.size() - 1).isFooter()) {
                        if (getAdapterPosition() == (arrayList.size() - 1)) {
                            return;
                        }
                    }
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("name", arrayList.get(getAdapterPosition()).getName());
                    intent.putExtra("phone", arrayList.get(getAdapterPosition()).getPhone());
                    intent.putExtra("addr", arrayList.get(getAdapterPosition()).getAddress());
                    context.startActivity(intent);
                }
            });
//            btnHomeBook.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, BookActivity.class);
//                    intent.putExtra("pitchName", arrayList.get(getAdapterPosition()).getName());
//                    context.startActivity(intent);
//                }
//            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (arrayList.get(position).isFooter()) {
            return VIEW_TYPES.Footer;
        }
        return super.getItemViewType(position);
    }
}
