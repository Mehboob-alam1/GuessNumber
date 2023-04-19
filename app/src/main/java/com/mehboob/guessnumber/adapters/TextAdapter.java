package com.mehboob.guessnumber.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mehboob.guessnumber.NumberHandler;
import com.mehboob.guessnumber.R;
import com.mehboob.guessnumber.SessionManager;
import com.mehboob.guessnumber.activities.ModelNumbers;

import java.util.ArrayList;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.MyHolder> {
    private ArrayList<ModelNumbers> list;
    private Context context;
    private SessionManager sessionManager;
    public int selectedItemPos = -1;
    public int lastItemSelectedPos = -1;

    public TextAdapter(ArrayList<ModelNumbers> list, Context context) {
        this.list = list;
        this.context = context;
        sessionManager = new SessionManager(context);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {
        ModelNumbers data = list.get(position);
        if (position == selectedItemPos) {
            holder.selectedBackground();
           // sessionManager.saveNumber(list.get(position).getText());
            NumberHandler.user_selected=Integer.parseInt(list.get(position).getText());

        } else
            holder.defaultBackground();

        holder.textView.setText(data.getText());

        holder.itemView.setOnClickListener(view -> {
            selectedItemPos = position;

            if (lastItemSelectedPos == -1) {
                lastItemSelectedPos = selectedItemPos;
            } else {
                notifyItemChanged(lastItemSelectedPos);

                lastItemSelectedPos = selectedItemPos;
            }
            notifyItemChanged(selectedItemPos);


        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtName);
        }


        @SuppressLint("UseCompatLoadingForDrawables")
        public void defaultBackground() {
            textView.setBackground(context.getDrawable(R.drawable.bg_unselected));
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        public void selectedBackground() {
            textView.setBackground(context.getDrawable(R.drawable.bg_selected));
        }

    }
}
