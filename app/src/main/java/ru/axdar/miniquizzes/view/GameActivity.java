package ru.axdar.miniquizzes.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
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
    private QuestionDTO questionDTO;

    private int correctAnswer; //сюда пишем ID-кнопки правильного ответа
    private int indexQuestion = 0, totalQuestion; //текущий вопрос и всего вопросов
    private boolean isSelected = true; // выбран ли уже ответ (нажатие кнопки)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        //сначала получаем данные
        quizDTO = getIntent().getParcelableExtra(Common.PARCELABLE_QUIZ);
        questionDTOList = quizDTO.getQuestionsDto();
        //затем работаем с тулбар
        initToolbar();
        presenterGame = new GamePresenter(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        totalQuestion = questionDTOList.size();
        showQuestion(indexQuestion);
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    protected void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    /**
     * наполняем тулбар смыслом
     */
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
        if (isSelected) {
            isSelected = false; // блокируем повторные клики
            Button clickedButton = (Button)view;
            if (clickedButton.getId() == correctAnswer) {
                alertTrueFalse();
            } else {
                Toast.makeText(this, R.string.toast_game_again_click, Toast.LENGTH_SHORT).show();
                isSelected = true;
            }
        }
    }

    /**
     * Реализация под вопрос с картинкой и без
     */
    private void checkImageQuestion(QuestionDTO questionDTO) {
        int tabCount; //кол-во вкладок
        if (questionDTO.getImgCheck()) {
            tabCount = 2; //вкладки под текст и картинку
        } else {
            tabCount = 1; //вкладка только под текст
        }
        SliderAdapter sliderAdapter =
                new SliderAdapter(this, tabCount, questionDTO, presenterGame);
        viewPager.setAdapter(sliderAdapter);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    @Override
    public void errorLoadImage(String message) {
        Toast.makeText(this, R.string.toast_error_load_image, Toast.LENGTH_SHORT).show();
    }

    /**
     * переход на следующий вопрос
     */
    private void showQuestion(int current) {
        // есть ли ещё вопросы в списке
        if (current < totalQuestion) {
            layoutQuizButtons.removeAllViews(); // очищаем от прежних Button-ответов
            questionDTO = questionDTOList.get(current);
            //кол-во пройденных вопросов
            String playProgress = String.format("%1$s/%2$s", (current+1), totalQuestion);
            tvCount.setText(playProgress);
            // проверка наличия картинки в вопросе
            checkImageQuestion(questionDTO);
            //передаём список ответов, чтобы создать кнопки с ответами
            createAnswerButtons(questionDTO.getAnswersDto());
            isSelected = true; // можно кликать кнопки ответов
        } else {
            alertFinishGame();
        }
    }

    /**
     * Диалоговое окно с правильным ответом и кнопкой дальше: след.вопрос или финиш.
     */
    private void alertTrueFalse() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Spanned trueAnswer = Html.fromHtml(questionDTO.getAnswerTrue());
        builder.setTitle(R.string.alert_game_answer_true)
                .setMessage(trueAnswer)
                .setPositiveButton(R.string.alert_game_button_next, (dialog, which) -> {
                    showQuestion(++indexQuestion);
                    dialog.cancel();
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Диалоговое окно при завершении игры.
     */
    private void alertFinishGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.alert_game_finish)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    finish();
                    dialog.cancel();
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
