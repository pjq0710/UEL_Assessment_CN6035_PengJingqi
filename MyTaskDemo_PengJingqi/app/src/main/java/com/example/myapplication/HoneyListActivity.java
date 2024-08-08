package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HoneyListActivity extends AppCompatActivity {

    private HashMap<Integer, Integer> cart;
    private TextView cartCount;
    private int cartTotalItems = 0;

    private ArrayList<Honey> honeyList = new ArrayList<>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_honey_list);

        ListView listView = findViewById(R.id.listView);

        List<Honey> honeyList = new ArrayList<Honey>();

        String[] honeyNames = {
                "蜜之珍太白山土蜂蜜", "英国缇树蜂蜜",
                "康维他麦卢卡蜂蜜", "法国开启蜜月蜂蜜",
                "俄罗斯西伯利亚森林椴树蜂蜜", "澳大利亚优思益桉树蜂蜜",
                "新溪岛蜂蜜", "丹麦Jakobsens 蜂蜜",
                "新西兰瑞琪奥兰麦卢卡蜂蜜", "澳洲哈登土蜂蜜"
        };
        String[] honeyPrices = {"$50", "$60", "$55", "$40", "$70", "$65", "$80", "$75", "$85", "$90"};
        int[] honeyImages = {
                R.drawable.honey1, R.drawable.honey2,
                R.drawable.honey3, R.drawable.honey4,
                R.drawable.honey5, R.drawable.honey6,
                R.drawable.honey7, R.drawable.honey8,
                R.drawable.honey9, R.drawable.honey10
        };

        for (int i = 0; i < honeyNames.length; i++) {
            Honey honey = new Honey(honeyNames[i],honeyPrices[i],honeyImages[i]);
            honeyList.add(honey);
        }

        HoneyAdapter adapter = new HoneyAdapter(this, (ArrayList<Honey>) honeyList);
        listView.setAdapter(adapter);

        findViewById(R.id.home_button).setOnClickListener(v -> finish());
        findViewById(R.id.clear_cart_button).setOnClickListener(v -> clearCart());
        findViewById(R.id.calculate_button).setOnClickListener(v -> calculateTotal());
        findViewById(R.id.confirm_order_button).setOnClickListener(v -> confirmOrder());
    }

    public void addToCart(int index, int price) {
        cartTotalItems++;
        cart.put(index, price);
        Toast.makeText(this, "已加入购物车", Toast.LENGTH_SHORT).show();
    }

    private void clearCart() {
        //cart.clear();
        cartTotalItems = 0;
        Toast.makeText(this, "购物车已清空", Toast.LENGTH_SHORT).show();
    }

    private void calculateTotal() {
        int totalPrice = 0;
/*        for (int price : cart.values()) {
            totalPrice += price;
        }*/
        //showPopup("总价: $" + totalPrice);
        Toast.makeText(this, "一共加入购物车**件商品，总价: $***", Toast.LENGTH_SHORT).show();
    }

    private void confirmOrder() {
        int totalPrice = 0;
/*        for (int price : cart.values()) {
            totalPrice += price;
        }*/
        new AlertDialog.Builder(this)
                .setTitle("确认下单")
                //.setMessage("您的购物车商品总价是: $" + totalPrice + "\n是否确认下单？")
                .setMessage("您的购物车商品总价是: $***是否确认下单？")
                .setPositiveButton("确认", (dialog, which) -> {
                    Toast.makeText(this, "下单成功，购物车已清空", Toast.LENGTH_SHORT).show();
                    //clearCart();
                    //cart.clear();
                    cartTotalItems = 0;
                })
                .setNegativeButton("取消", null)
                .show();
    }

    private void showPopup(String message) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage(message)
                .setCancelable(true)
                .create();
        dialog.show();

        dialog.setOnDismissListener(dialog1 -> {
            dialog.dismiss();
        });
    }
}
