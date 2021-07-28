package com.github.comunidade.ativa.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * Class that represents imagemCapa from project on firebase database
 *
 * @author ronanlima
 */
public class ProportionThreeTwoImageView extends AppCompatImageView {
    public ProportionThreeTwoImageView(Context context) {
        super(context);
    }

    public ProportionThreeTwoImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProportionThreeTwoImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int threeTwoHeight = View.MeasureSpec.getSize(widthMeasureSpec) * 2 / 3;
        int threeTwoHeightSpec = View.MeasureSpec.makeMeasureSpec(threeTwoHeight, View.MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, threeTwoHeightSpec);
    }
}
