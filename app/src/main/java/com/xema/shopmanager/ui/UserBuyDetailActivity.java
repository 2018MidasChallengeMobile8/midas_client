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
import android.widget.TextView;

import com.xema.shopmanager.R;
import com.xema.shopmanager.adapter.BuyDetailAdapter;
import com.xema.shopmanager.common.BaseApplication;
import com.xema.shopmanager.model.BuyDetail;
import com.xema.shopmanager.model.BuyList;
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
    private int sum;
    @BindView(R.id.buy_detail_txt_sum)
    TextView buyDetailTxtSum;
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
            item_list.clear();
            for (BuyDetail list : buy_detail_list) {
                item_list.add(new OrderItemList(list.getId(), Integer.parseInt(list.getNum())));
            }
            order_list = new OrderMenuList("2", "2", buyDetailEdit.getText().toString(),
                    item_list);

            Call<BuyList> call = BaseApplication.service.postOrder(order_list);

            call.enqueue(new Callback<BuyList>() {
                @Override
                public void onResponse(Call<BuyList> call, Response<BuyList> response) {
                    Log.d("order_detail", "연결 성공");

                    AlertDialog.Builder alert = new AlertDialog.Builder(UserBuyDetailActivity.this);
                    alert.setMessage("주문 완료되었습니다.")
                            .setCancelable(false)
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(getApplicationContext(), UserMainActivity.class));
                                    finish();
                                }
                            }).show();
                }

                @Override
                public void onFailure(Call<BuyList> call, Throwable t) {
                    Log.d("order_detail", t.getMessage());
                }
            });

        });
    }

    private void updateSum() {
        sum = 0;
        for (BuyDetail buyDetail : buy_detail_list) {
            sum += Integer.parseInt(buyDetail.getPrice());
        }
        buyDetailTxtSum.setText(String.valueOf(sum));
    }

    private void initToolbar() {
        setSupportActionBar(tbBuyDetail);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    private void resetAdapter() {
        updateSum();
        mAdapter.notifyDataSetChanged();
    }

    private void setRecyclerView() {

        mAdapter = new BuyDetailAdapter(buy_detail_list, new BuyDetailAdapter.onClickListener() {
            @Override
            public void onClick(View view, int position, int status) {
                //+
                if (status == 1) {
                    int num = Integer.parseInt(buy_detail_list.get(position).getNum()) + 1;

                    int price = Integer.parseInt(buy_detail_list.get(position).getPrice())
                            + Integer.parseInt(buy_detail_list.get(position).getPrice()) /
                            Integer.parseInt(buy_detail_list.get(position).getNum());

                    buy_detail_list.get(position).setNum(String.valueOf(num));
                    buy_detail_list.get(position).setPrice(String.valueOf(price));
                } else {
                    int num = Integer.parseInt(buy_detail_list.get(position).getNum()) - 1;
                    if (num == 0) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(UserBuyDetailActivity.this);
                        alert.setMessage("더 이상 뺄 수 없습니다.")
                                .setCancelable(false)
                                .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        return;
                                    }
                                }).show();
                    } else {
                        int price = Integer.parseInt(buy_detail_list.get(position).getPrice()) -
                                Integer.parseInt(buy_detail_list.get(position).getPrice()) /
                                        Integer.parseInt(buy_detail_list.get(position).getNum());
                        buy_detail_list.get(position).setNum(String.valueOf(num));
                        buy_detail_list.get(position).setPrice(String.valueOf(price));
                    }
                }
                resetAdapter();
            }

        });
        //buyDetailList.setHasFixedSize(true);
        buyDetailList.setLayoutManager(new LinearLayoutManager(this));
        buyDetailList.setAdapter(mAdapter);
        setList();
    }

    private void setList() {
        buy_detail_list.clear();
        Intent intent = getIntent();
        intent_list = (ArrayList<BuyDetail>) intent.getSerializableExtra("list");
        buy_detail_list.addAll(intent_list);
        updateSum();
        mAdapter.notifyDataSetChanged();
    }
}
