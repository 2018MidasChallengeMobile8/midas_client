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
import android.widget.Button;
import android.widget.EditText;

import com.xema.shopmanager.R;
import com.xema.shopmanager.adapter.BuyDetailAdapter;
import com.xema.shopmanager.data.RestClient;
import com.xema.shopmanager.data.RetroService;
import com.xema.shopmanager.model.BuyDetail;
import com.xema.shopmanager.model.OrderItemList;
import com.xema.shopmanager.model.OrderMenuList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserBuyDetailActivity extends AppCompatActivity {
    private RecyclerView.Adapter mAdapter;
    private ArrayList<BuyDetail> buy_detail_list;
    private ArrayList<BuyDetail> intent_list;
    private ArrayList<OrderItemList> item_list;
    private OrderMenuList order_list;
    private RetroService retroService;
    private RestClient<RetroService> restClient;

    @BindView(R.id.buy_detail_btn_order)
    Button buyDetailBtnOrder;
    @BindView(R.id.tb_buy_detail)
    Toolbar tbBuyDetail;
    @BindView(R.id.buy_detail_list)
    RecyclerView buyDetailList;
    @BindView(R.id.buy_detail_edit)
    EditText buyDetailEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_buy_detail);
        ButterKnife.bind(this);
        buy_detail_list = new ArrayList<>();
        item_list = new ArrayList<>();

        initToolbar();
        setRecyclerView();
        //Intent intent = getIntent();

        buyDetailBtnOrder.setOnClickListener(v ->
        {
            Connect();
            item_list.clear();
           for(BuyDetail list : buy_detail_list)
           {
               item_list.add(new OrderItemList(list.getId(),Integer.parseInt(list.getNum())));
           }
           order_list = new OrderMenuList("1","1",buyDetailEdit.getText().toString(),
                   item_list);

            Call<String> call = retroService.postOrder(order_list);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.d("order_detail","연결 성공");

                    AlertDialog.Builder alert = new AlertDialog.Builder(UserBuyDetailActivity.this);
                    alert.setMessage("정말 주문하시겠습니까?")
                            .setCancelable(false)
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(getApplicationContext(),UserMainActivity.class));
                                    finish();
                                }
                            }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    }).show();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("order_detail",t.getMessage());
                }
            });

        });
    }

    private void Connect()
    {
        restClient = new RestClient<>();
        retroService = restClient.getClient(RetroService.class);
    }

    private void initToolbar() {
        setSupportActionBar(tbBuyDetail);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    private void resetAdapter()
    {
        mAdapter.notifyDataSetChanged();
    }
    private void setRecyclerView() {

        mAdapter = new BuyDetailAdapter(buy_detail_list , new BuyDetailAdapter.onClickListener() {
            @Override
            public void onClick(View view, int position, int status) {
                //+
                if(status == 1)
                {
                    int num = Integer.parseInt(buy_detail_list.get(position).getNum()) + 1;
                    buy_detail_list.get(position).setNum(String.valueOf(num));
                }
                else {
                    int num = Integer.parseInt(buy_detail_list.get(position).getNum()) - 1;
                    buy_detail_list.get(position).setNum(String.valueOf(num));
                }
                resetAdapter();
            }

        });
        buyDetailList.setHasFixedSize(true);
        buyDetailList.setLayoutManager(new LinearLayoutManager(this));
        buyDetailList.setAdapter(mAdapter);
        setList();
    }

    private void setList()
    {
        buy_detail_list.clear();
        Intent intent = getIntent();
        intent_list = (ArrayList<BuyDetail>)intent.getSerializableExtra("list");
        buy_detail_list.addAll(intent_list);
        mAdapter.notifyDataSetChanged();
    }
}
