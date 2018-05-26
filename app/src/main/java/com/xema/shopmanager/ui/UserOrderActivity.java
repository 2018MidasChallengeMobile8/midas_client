package com.xema.shopmanager.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.xema.shopmanager.R;
import com.xema.shopmanager.adapter.OrderCategoryAdapter;
import com.xema.shopmanager.adapter.OrderMenuAdapter;
import com.xema.shopmanager.model.BuyDetail;
import com.xema.shopmanager.model.OrderCategory;
import com.xema.shopmanager.model.OrderMenu;
import com.xema.shopmanager.ui.dialog.OrderDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserOrderActivity extends AppCompatActivity {

    @BindView(R.id.tb_user_main)
    Toolbar tbUserMain;
    @BindView(R.id.order_list_category)
    RecyclerView orderListCategory;
    @BindView(R.id.order_list_menu)
    RecyclerView orderListMenu;
    @BindView(R.id.order_im_cart)
    ImageView orderImCart;

    private RequestManager mRequestManager;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter menuAdapter;
    private ArrayList<OrderCategory> category_list;
    private ArrayList<OrderMenu> menu_list;
    private ArrayList<BuyDetail> buy_detail_list;
    private LinearLayoutManager linearLayoutManager;
    private int po;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order);
        category_list = new ArrayList<>();
        menu_list = new ArrayList<>();
        buy_detail_list = new ArrayList<>();
        ButterKnife.bind(this);
        initToolbar();
        setRecyclerView();

        orderImCart.setOnClickListener(v ->
        {
            Intent intent = new Intent(this,UserBuyDetailActivity.class);
            intent.putExtra("list",buy_detail_list);
            startActivity(intent);
        });
    }

    public void getNum(String num, int status) {
        int cnt = Integer.parseInt(num);
        int price = Integer.parseInt(menu_list.get(po).getPrice());
        price = price * cnt;
        //바로 주문시
        if (status == 1) {

            buy_detail_list.add(new BuyDetail(menu_list.get(po).getName(),num,String.valueOf(price)));
            Intent intent = new Intent(getApplicationContext(),UserBuyDetailActivity.class);
            intent.putExtra("list",buy_detail_list);
            startActivity(intent);

        } else {
            buy_detail_list.add(new BuyDetail(menu_list.get(po).getName(),num,String.valueOf(price)));

            AlertDialog.Builder alert = new AlertDialog.Builder(UserOrderActivity.this);
            alert.setMessage("장바구니로 가겠습니까?")
                    .setCancelable(false)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(),UserBuyDetailActivity.class);
                            intent.putExtra("list",buy_detail_list);
                            startActivity(intent);
                        }
                    }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                        return;
                }
            }).show();


        }
        Log.d("user_order", num + " !!!!!!! ");
    }

    public void get(int position) {
        OrderDialog dialog = new OrderDialog(this, menu_list.get(position).getName()
                , menu_list.get(position).getPrice());
        dialog.setListener(this::getNum);
        dialog.show();
    }

    private void setRecyclerView() {

        mRequestManager = Glide.with(this);
        mAdapter = new OrderCategoryAdapter(category_list, mRequestManager);
        menuAdapter = new OrderMenuAdapter(menu_list, mRequestManager, new OrderMenuAdapter.onMenuClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d("user_order", position + "eeee");
                po = position;
                int num = position;
                get(po);
            }
        });

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        orderListCategory.setHasFixedSize(true);
        orderListCategory.setLayoutManager(linearLayoutManager);
        orderListCategory.setAdapter(mAdapter);

        orderListMenu.setHasFixedSize(true);
        orderListMenu.setLayoutManager(new LinearLayoutManager(this));
        orderListMenu.setAdapter(menuAdapter);
        setCategoryList();
        setMenuList();
    }

    private void setCategoryList() {
        category_list.clear();

        category_list.add(new OrderCategory(getResources()
                .getDrawable(R.drawable.ic_coffe), "커피/라뗴"));
        mAdapter.notifyDataSetChanged();
        category_list.add(new OrderCategory(getResources()
                .getDrawable(R.drawable.ic_coffee3), "에이드/쥬스"));
        mAdapter.notifyDataSetChanged();
        category_list.add(new OrderCategory(getResources()
                .getDrawable(R.drawable.ic_coffee2), "차/프라페"));
        mAdapter.notifyDataSetChanged();
        category_list.add(new OrderCategory(getResources()
                .getDrawable(R.drawable.ic_coffee4), "스무디"));
        mAdapter.notifyDataSetChanged();
        category_list.add(new OrderCategory(getResources()
                .getDrawable(R.drawable.ic_bakery), "베이커리"));
        category_list.add(new OrderCategory(getResources()
                .getDrawable(R.drawable.ic_side), "사이드"));

        mAdapter.notifyDataSetChanged();

    }

    private void setMenuList() {
        menu_list.clear();
        menu_list.add(new OrderMenu("http://www.natuur-pop.com/_upload/coffee/20171131028_10064.jpg",
                "커피/라뗴", "아이스티 복숭아", "5300"));
        menu_list.add(new OrderMenu("http://www.natuur-pop.com/_upload/coffee/20171131028_10064.jpg",
                "커피/라뗴", "아이스티 복숭아", "5300"));
        menu_list.add(new OrderMenu("http://www.natuur-pop.com/_upload/coffee/20171131028_10064.jpg",
                "커피/라뗴", "아이스티 복숭아", "5300"));
        menu_list.add(new OrderMenu("http://www.natuur-pop.com/_upload/coffee/20171131028_10064.jpg",
                "커피/라뗴", "아이스티 복숭아", "5300"));
        menu_list.add(new OrderMenu("http://www.natuur-pop.com/_upload/coffee/20171131028_10064.jpg",
                "커피/라뗴", "아이스티 복숭아", "5300"));
        menu_list.add(new OrderMenu("http://www.natuur-pop.com/_upload/coffee/20171131028_10064.jpg",
                "커피/라뗴", "아이스티 복숭아", "5300"));
        menu_list.add(new OrderMenu("https://mcdonalds.co.kr/uploadFolder/product/prov_201501280310302840.png",
                "커피/라뗴", "아이스티 복숭아", "5300"));
        menu_list.add(new OrderMenu("http://www.natuur-pop.com/_upload/coffee/20171131028_10064.jpg",
                "커피/라뗴", "아이스티 복숭아", "5300"));
        menu_list.add(new OrderMenu("http://www.natuur-pop.com/_upload/coffee/20171131028_10064.jpg",
                "커피/라뗴", "아이스티 복숭아", "5300"));
        menu_list.add(new OrderMenu("http://www.natuur-pop.com/_upload/coffee/20171131028_10064.jpg",
                "커피/라뗴", "아이스티 복숭아", "5300"));
        menu_list.add(new OrderMenu("http://www.natuur-pop.com/_upload/coffee/20171131028_10064.jpg",
                "커피/라뗴", "아이스티 복숭아", "5300"));

        mAdapter.notifyDataSetChanged();
    }

    private void initToolbar() {
        setSupportActionBar(tbUserMain);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
