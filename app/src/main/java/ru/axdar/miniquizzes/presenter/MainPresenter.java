package ru.axdar.miniquizzes.presenter;

import android.util.Log;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.axdar.miniquizzes.model.Model;
import ru.axdar.miniquizzes.model.dto.QuizDTO;
import ru.axdar.miniquizzes.view.IMainView;

/**
 * Created by ildar2244 on 21.08.2018.
 */
public class MainPresenter implements IPresenter{

    private IMainView mainView;
    private Model model;
    private Disposable disposable;

    public MainPresenter(IMainView mainView) {
        this.mainView = mainView;
        this.model = new Model();
    }

    public void getQuizByCategory(int catID) {
        mainView.showProgressBar();
        disposable = model
                .getQuizByCategory()
                .flatMap(Flowable::fromIterable)
                .filter(quizDTO -> quizDTO.getCategoryId() == catID)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::responseQuiz, this::handleError);
    }

    private void responseQuiz(List<QuizDTO> list) {
        //Log.d("MyTAG", "PR_MAIN-response_list: " + list);
        mainView.showQuizList(list);
    }

    private void handleError(Throwable error) {
        //Log.d("MyTAG", "PR_MAIN-error: " + error.getCause());
        //TODO: Error timeout обработать
        mainView.showErrorMain(error.getLocalizedMessage());
    }

    @Override
    public void onDestroy() {
        disposable.dispose();
    }
}
