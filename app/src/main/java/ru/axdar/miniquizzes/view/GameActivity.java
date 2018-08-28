package ru.axdar.miniquizzes.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.axdar.miniquizzes.Common;
import ru.axdar.miniquizzes.R;
import ru.axdar.miniquizzes.model.dto.QuestionDTO;
import ru.axdar.miniquizzes.model.dto.QuizDTO;

public class GameActivity extends AppCompatActivity {

    @BindView(R.id.adView)
    AdView adView;
    @BindView(R.id.tv_quiz_question)
    TextView tvQuizQuestion;
    @BindView(R.id.layout_quiz_buttons)
    LinearLayout layoutQuizButtons;

    private QuizDTO quizDTO;
    private List<QuestionDTO> questionDTOList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        quizDTO = getIntent().getParcelableExtra(Common.PARCELABLE_QUIZ);
        questionDTOList = quizDTO.getQuestionsDto();
        String text = questionDTOList.get(0).getQuestion();

        tvQuizQuestion.setText(text);

    }

}
