package ru.axdar.miniquizzes.view.adapter;

import android.view.View;

/**
 * Created by ildar2244 on 27.08.2018.
 */
public interface ItemClickListener {
    void onClick(View view, int position, boolean isLongClick);
}
