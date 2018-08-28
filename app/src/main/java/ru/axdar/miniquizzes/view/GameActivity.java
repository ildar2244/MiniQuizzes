package ru.axdar.miniquizzes.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.axdar.miniquizzes.R;

public class GameActivity extends AppCompatActivity {

    @BindView(R.id.adView)
    AdView adView;
    @BindView(R.id.tv_quiz_question)
    TextView tvQuizQuestion;
    @BindView(R.id.layout_quiz_buttons)
    LinearLayout layoutQuizButtons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

    }

}
