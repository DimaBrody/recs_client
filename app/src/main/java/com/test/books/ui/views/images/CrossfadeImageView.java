package com.test.books.ui.views.images;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.test.books.R;

public class CrossfadeImageView extends AppCompatImageView {

    private float mCrossfade = 0.0F;
    Drawable[] mLayers;
    LayerDrawable mLayer;


    public CrossfadeImageView(Context context) {
        super(context);
        this.init(null);
    }

    public CrossfadeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(attrs);
    }

    public CrossfadeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = this.getContext().obtainStyledAttributes(attrs, R.styleable.CrossfadeImageView);
            Drawable drawable = a.getDrawable(R.styleable.CrossfadeImageView_altDrawable);
            mCrossfade = a.getFloat(R.styleable.CrossfadeImageView_crossfade, 0f);

            a.recycle();
            if (drawable != null) {
                this.mLayers = new Drawable[2];
                this.mLayers[0] = this.getDrawable();
                this.mLayers[1] = drawable;
                this.mLayer = new LayerDrawable(this.mLayers);
                this.mLayer.getDrawable(1).setAlpha((int) (255.0F * this.mCrossfade));
                super.setImageDrawable(this.mLayer);
            }
        }

    }

    public void setAltDrawable(Drawable drawable) {
        if (drawable != null) {
            this.mLayers = new Drawable[2];
            this.mLayers[0] = this.getDrawable();
            this.mLayers[1] = drawable;
            this.mLayer = new LayerDrawable(this.mLayers);
            this.mLayer.getDrawable(1).setAlpha((int) (255.0F * this.mCrossfade));
            super.setImageDrawable(this.mLayer);
        }
    }

    public void setCrossfade(float crossfade) {
        this.mCrossfade = crossfade;
        if (this.mLayers != null) {
            this.mLayer.getDrawable(1).setAlpha((int) (255.0F * this.mCrossfade));
            super.setImageDrawable(this.mLayer);
        }

    }

    public float getCrossfade() {
        return this.mCrossfade;
    }

}