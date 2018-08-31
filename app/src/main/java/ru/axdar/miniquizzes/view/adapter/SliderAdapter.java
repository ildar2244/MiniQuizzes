package ru.axdar.miniquizzes.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ru.axdar.miniquizzes.R;
import ru.axdar.miniquizzes.model.dto.QuestionDTO;

/**
 * Created by ildar2244 on 31.08.2018.
 */
public class SliderAdapter extends PagerAdapter {

    private Context context;
    private int countTab;
    private QuestionDTO questionDTO;

    public SliderAdapter(Context context, int countTab, QuestionDTO questionDTO) {
        this.context = context;
        this.countTab = countTab;
        this.questionDTO = questionDTO;
    }

    @Override
    public int getCount() {
        return countTab;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int resId = -1;
        switch (position) {
            case 0:
                resId = R.layout.slider_item_text;
                break;
            case 1:
                resId = R.layout.slider_item_image;
                break;
        }
        View view = inflater.inflate(resId, container, false);

        TextView tv = view.findViewById(R.id.tv_question);
        ImageView iv = view.findViewById(R.id.img_question);

        switch (position) {
            case 0:
                String textQuestion = questionDTO.getQuestion();
                tv.setText(textQuestion);
                break;
            case 1:
                // здесь работаем с ImageView
                break;
        }

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String[] titles = context.getResources().getStringArray(R.array.tab_slider_titles);
        return titles[position];
    }
}
