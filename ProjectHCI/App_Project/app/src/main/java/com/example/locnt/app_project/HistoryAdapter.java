package com.example.locnt.app_project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.viewHolder> {
    private ArrayList<History> listData;
    private Context context;

    Date currentTime = Calendar.getInstance().getTime();
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    String formattedDate = df.format(currentTime);

    public HistoryAdapter(Context context) {
        this.context = context;
    }

    public HistoryAdapter(ArrayList<History> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_list, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        String s = "Tên sân: " + listData.get(position).getName() + "\n" + "Địa chỉ: " + listData.get(position).getAddr()
                + "\n" + "Ngày đặt sân: " + listData.get(position).getDate() + "\n" + "Giờ đặt sân: " + listData.get(position).getTime()
                + "\n" + "Giá: " + listData.get(position).getPrice();

        holder.tvName.setText(s);
        holder.imgView.setImageResource(listData.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView imgView;

        public viewHolder(final View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.txtNameHistory);
            imgView = itemView.findViewById(R.id.img_history);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    String date = listData.get(getAdapterPosition()).getDate();
                    try {
                        Date date1 = df.parse(date);
                        Date date2 = df.parse(formattedDate);
                        if (date1.after(date2)) {
                            removeItemFromList(getAdapterPosition());
                            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("position",listData.get(getAdapterPosition()).getName());
                            editor.commit();
//                            Toast.makeText(, "past date: " + "current" + date1 +"<"+ date2, Toast.LENGTH_SHORT).show();
                        } else {
//                            Toast.makeText(context, "past date: " + "current" + date1 +"<"+ date2, Toast.LENGTH_SHORT).show();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    return false;
                }
            });
        }
    }
    protected void removeItemFromList(final int position) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);

        alert.setTitle("HỦY ĐẶT SÂN");
        alert.setMessage("Bạn chắc chắn hủy đặt sân?");
        alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                removeAt(position);
            }
        });
        alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();
    }

    public void removeAt(int position) {
        listData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listData.size());
    }
}
