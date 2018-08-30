package ru.axdar.miniquizzes.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.axdar.miniquizzes.Common;
import ru.axdar.miniquizzes.R;
import ru.axdar.miniquizzes.model.dto.AnswerDTO;
import ru.axdar.miniquizzes.model.dto.QuestionDTO;
import ru.axdar.miniquizzes.model.dto.QuizDTO;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.adView)
    AdView adView;
    @BindView(R.id.tv_quiz_question)
    TextView tvQuizQuestion;
    @BindView(R.id.layout_quiz_buttons)
    LinearLayout layoutQuizButtons;
    @BindView(R.id.tv_count_question)
    TextView tvCount;
    @BindView(R.id.toolbar_game)
    Toolbar toolbar;

    private QuizDTO quizDTO;
    private List<QuestionDTO> questionDTOList;

    private int correctAnswer; //сюда пишем ID-кнопки правильного ответа
    private int countQuestions; //кол-во пройденных вопросов

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        initToolbar();

        quizDTO = getIntent().getParcelableExtra(Common.PARCELABLE_QUIZ);
        questionDTOList = quizDTO.getQuestionsDto();

        //for example
        String text = questionDTOList.get(0).getQuestion();
        tvQuizQuestion.setText(text);
        tvCount.setText(String.format("%1$s/%2$s", countQuestions, questionDTOList.size()));

        //передаём список ответов, чтобы создать кнопки с ответами
        createAnswerButtons(questionDTOList.get(0).getAnswersDto());

    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    /**
     * Создаются кнопки вариантов ответа
     */
    private void createAnswerButtons(List<AnswerDTO> answers) {
        for (int i=0; i < answers.size(); i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            Button button = new Button(this);
            button.setId(i); //устанавливаем id, чтобы потом узнать верный ответ
            button.setText(answers.get(i).getText());
            button.setOnClickListener(this::onClick);

            //фиксируем правильный ответ
            if (answers.get(i).getCheck())
                correctAnswer = button.getId();

            // загружаем в Layout
            layoutQuizButtons.addView(button, params);
        }
    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button)view;
        Log.d("tag-but", "click-id: " + clickedButton.getId());
        String message = "";
        if (clickedButton.getId() == correctAnswer) {
            message = "TRUE";
        } else {
            message = "FALSE";
        }
        Toast.makeText(this, "answer: " + message, Toast.LENGTH_SHORT).show();
    }
}
