package com.xema.shopmanager.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.xema.shopmanager.R;
import com.xema.shopmanager.adapter.BuyListAdapter;
import com.xema.shopmanager.common.BaseApplication;
import com.xema.shopmanager.model.BuyList;
import com.xema.shopmanager.model.PostOrder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserBuyListActivity extends AppCompatActivity {
    @BindView(R.id.tb_buy_list)
    Toolbar tbBuyList;
    @BindView(R.id.buy_spinner_year)
    Spinner buySpinnerYear;
    @BindView(R.id.buy_spinner_month)
    Spinner buySpinnerMonth;
    @BindView(R.id.buy_list)
    RecyclerView buyList;

    private ArrayList<BuyList> buy_list;
    private RecyclerView.Adapter mAdapter;
    private PostOrder postOrder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_buy_list);
        ButterKnife.bind(this);
        buy_list = new ArrayList<>();

        initToolbar();
        initSpinner();
        setRecyclerView();
    }

    private void initSpinner() {
        ArrayAdapter yearAdapter = ArrayAdapter.createFromResource(this,
                R.array.date_year, android.R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        buySpinnerYear.setAdapter(yearAdapter);
        buySpinnerYear.setSelection(0);
        buySpinnerMonth.setSelection(4);

        ArrayAdapter monthAdapter = ArrayAdapter.createFromResource(this,
                R.array.date_month, android.R.layout.simple_spinner_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        buySpinnerMonth.setAdapter(monthAdapter);

        buySpinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("spinner", buySpinnerYear.getSelectedItem().toString() + "!!!"
                        + buySpinnerMonth.getSelectedItem().toString());
                setList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setRecyclerView() {
        mAdapter = new BuyListAdapter(buy_list, new BuyListAdapter.onClickListener() {
            @Override
            public void onClick(View view, int position) {

            }
        });
        buyList.setHasFixedSize(true);
        buyList.setLayoutManager(new LinearLayoutManager(this));
        buyList.setAdapter(mAdapter);
        setList();


    }

    private void setList()
    {
        buy_list.clear();
        String year = buySpinnerYear.getSelectedItem().toString().replace("년","");
        String month = buySpinnerMonth.getSelectedItem().toString().replace("월","");
        postOrder = new PostOrder("1","1",2,Integer.parseInt(year),
                5);
        Log.d("buy_list",year + " // " + month);
        retrofit2.Call<ArrayList<BuyList>> call = BaseApplication.service.getOrders("1","1",2,Integer.parseInt(year), 5);

        call.enqueue(new Callback<ArrayList<BuyList>>() {
            @Override
            public void onResponse(Call<ArrayList<BuyList>> call, Response<ArrayList<BuyList>> response) {
                if(response.body() != null) {
                    Log.d("buy_list", response.body().get(0).getComment() + "연결 성공");
                    buy_list.addAll(response.body());
                    Log.d("ttt", response.toString());
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<BuyList>> call, Throwable t) {
                Log.d("buy_list",t.getMessage());

            }
        });

    }
    private void initToolbar() {
        setSupportActionBar(tbBuyList);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
