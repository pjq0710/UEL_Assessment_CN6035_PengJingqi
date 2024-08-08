package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class HoneyAdapter extends ArrayAdapter<Honey> {
    private int cartTotalItems = 0;
    private HashMap<Integer, Integer> cart;

    public HoneyAdapter(Context context, ArrayList<Honey> honeyList) {
        super(context, 0, honeyList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_honey, parent, false);
        }

        Honey honey = getItem(position);

        ImageView honeyImage = convertView.findViewById(R.id.honeyImage);
        TextView honeyTitle = convertView.findViewById(R.id.honeyTitle);
        TextView honeyPrice = convertView.findViewById(R.id.honeyPrice);
        Button addToCartButton = convertView.findViewById(R.id.addToCartButton);

        honeyImage.setImageResource(honey.getImageResId());
        honeyTitle.setText(honey.getName());
        honeyPrice.setText(honey.getPrice());

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理添加到购物车的逻辑
                cartTotalItems++;
                //cart.put(honey.getImageResId(), Integer.getInteger(honey.getPrice()));
                Toast.makeText(v.getContext(), "已加入购物车"+cartTotalItems+"件", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
