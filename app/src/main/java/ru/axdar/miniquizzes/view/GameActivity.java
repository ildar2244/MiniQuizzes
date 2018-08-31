package ru.axdar.miniquizzes.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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
import ru.axdar.miniquizzes.presenter.GamePresenter;
import ru.axdar.miniquizzes.view.adapter.SliderAdapter;

public class GameActivity extends AppCompatActivity
        implements View.OnClickListener, IGameView {

    @BindView(R.id.adView)
    AdView adView;
    @BindView(R.id.layout_quiz_buttons)
    LinearLayout layoutQuizButtons;
    @BindView(R.id.tv_count_question)
    TextView tvCount;
    @BindView(R.id.toolbar_game)
    Toolbar toolbar;
    @BindView(R.id.tab_view)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private GamePresenter presenterGame;

    private QuizDTO quizDTO;
    private List<QuestionDTO> questionDTOList;

    private int correctAnswer; //сюда пишем ID-кнопки правильного ответа
    private int countQuestions; //кол-во пройденных вопросов
    private int currentQuestion = 0; //id текущего вопроса

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        //adView.loadAd(adRequest);

        //сначала получаем данные
        quizDTO = getIntent().getParcelableExtra(Common.PARCELABLE_QUIZ);
        questionDTOList = quizDTO.getQuestionsDto();
        //затем работаем с тулбар
        initToolbar();
        presenterGame = new GamePresenter(this);
        //передаём список ответов, чтобы создать кнопки с ответами
        createAnswerButtons(questionDTOList.get(0).getAnswersDto());
        checkImageQuestion(true);

    }

    /**
     * наполняем тулбар смыслом
     */
    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
        //кол-во пройденных вопросов
        tvCount.setText(String.format("%1$s/%2$s", countQuestions, questionDTOList.size()));
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

    /**
     * Реализация под вопрос с картинкой и без
     */
    private void checkImageQuestion(boolean check) {
        int tabCount; //кол-во вкладок
        if (check) {
            tabCount = 2; //вкладки под текст и картинку
        } else {
            tabCount = 1; //вкладка только под текст
        }
        SliderAdapter sliderAdapter = new SliderAdapter(this, tabCount,
                questionDTOList.get(currentQuestion), presenterGame);
        viewPager.setAdapter(sliderAdapter);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    @Override
    public void errorLoadImage(String message) {
        Toast.makeText(this, R.string.toast_error_load_image, Toast.LENGTH_SHORT).show();
    }
}
