package com.coquema.sean.dev.mypokemonworld.Common;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Sean Coquema on 10/03/2019.
 *
 */

public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

    private int itemoffset;

    public ItemOffsetDecoration(int itemoffset) {
        this.itemoffset = itemoffset;
    }

    public ItemOffsetDecoration(@NonNull Context context, @DimenRes int dimens) {

        this(context.getResources().getDimensionPixelSize(dimens));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(itemoffset, itemoffset, itemoffset, itemoffset);
    }
}
