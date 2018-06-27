package com.example.deer.loopingviewpager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.rd.PageIndicatorView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LoopingViewPager mViewPager;
    DemoInfiniteAdapter mAdapter;
    PageIndicatorView mIndicatorView;
    Button mChangeDataSetButton;

    TextView mChangePageLabel;
    Button mPage1;
    Button mPage2;
    Button mPage3;
    Button mPage4;
    Button mPage5;
    Button mPage6;

    private int currentDataSet = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.viewpager);
        mIndicatorView = findViewById(R.id.indicator);
        mChangeDataSetButton = findViewById(R.id.change_dataset);
        mChangeDataSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDataset();
            }
        });

        mChangePageLabel = findViewById(R.id.change_page_label);
        mPage1 = findViewById(R.id.page1);
        mPage2 = findViewById(R.id.page2);
        mPage3 = findViewById(R.id.page3);
        mPage4 = findViewById(R.id.page4);
        mPage5 = findViewById(R.id.page5);
        mPage6 = findViewById(R.id.page6);

        mAdapter = new DemoInfiniteAdapter(this, createDummyItems(), true);
        mViewPager.setAdapter(mAdapter);

        // Custom bind indicator
        mIndicatorView.setCount(mViewPager.getIndicatorCount());
        mViewPager.setIndicatorPageChangeListener(new LoopingViewPager.IndicatorPageChangeListener() {
            @Override
            public void onIndicatorProgress(int selectingPosition, float progress) {
                mIndicatorView.setProgress(selectingPosition, progress);
            }

            @Override
            public void onIndicatorPageChange(int newIndicatorPosition) {
//                mIndicatorView.setSelection(newIndicatorPosition);
            }
        });

        View.OnClickListener pageSelector = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer number = Integer.valueOf(((Button) v).getText().toString());
                mViewPager.setCurrentItem(mAdapter.isInfinite() ? number : number - 1);
            }
        };

        mPage1.setOnClickListener(pageSelector);
        mPage2.setOnClickListener(pageSelector);
        mPage3.setOnClickListener(pageSelector);
        mPage4.setOnClickListener(pageSelector);
        mPage5.setOnClickListener(pageSelector);
        mPage6.setOnClickListener(pageSelector);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewPager.resumeAutoScroll();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mViewPager.pauseAutoScroll();
    }

    private ArrayList<Integer> createDummyItems() {
        ArrayList<Integer> items = new ArrayList<>();
        items.add(0, 1);
        items.add(1, 2);
        items.add(2, 3);
        items.add(3, 4);
        items.add(4, 5);
        items.add(5, 6);
        items.add(6, 0);
        return items;
    }

    private ArrayList<Integer> createSecondDummyItems() {
        ArrayList<Integer> items = new ArrayList<>();
        items.add(0, 1);
        items.add(1, 2);
        return items;
    }

    private ArrayList<Integer> createThirdDummyItems() {
        ArrayList<Integer> items = new ArrayList<>();
        items.add(0, 1);
        return items;
    }

    private void changeDataset() {
        if (currentDataSet == 1) {
            mAdapter.setItemList(createSecondDummyItems());
            currentDataSet++;
            toggleSixButtons(false);
        } else if (currentDataSet == 2) {
            mAdapter.setItemList(createThirdDummyItems());
            currentDataSet++;
            toggleSixButtons(false);
        } else {
            mAdapter.setItemList(createDummyItems());
            currentDataSet = 1;
            toggleSixButtons(true);
        }
        mIndicatorView.setCount(mViewPager.getIndicatorCount());
        mViewPager.reset();
    }

    private void toggleSixButtons(boolean isVisible) {
        int status = isVisible ? View.VISIBLE : View.GONE;
        mChangePageLabel.setVisibility(status);
        mPage1.setVisibility(status);
        mPage2.setVisibility(status);
        mPage3.setVisibility(status);
        mPage4.setVisibility(status);
        mPage5.setVisibility(status);
        mPage6.setVisibility(status);
    }
}
