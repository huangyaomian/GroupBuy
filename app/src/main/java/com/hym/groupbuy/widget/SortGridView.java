package com.hym.groupbuy.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;
import android.widget.GridView;

public class SortGridView extends GridView {


    public SortGridView(Context context) {
        super(context);
    }

    public SortGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SortGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
