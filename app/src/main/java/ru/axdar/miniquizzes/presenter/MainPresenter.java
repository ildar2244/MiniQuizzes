package ru.axdar.miniquizzes.presenter;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.axdar.miniquizzes.data.repository.remote.RemoteDataSource;
import ru.axdar.miniquizzes.data.dto.QuizDTO;
import ru.axdar.miniquizzes.view.IMainView;

/**
 * Created by ildar2244 on 21.08.2018.
 */
public class MainPresenter implements IPresenter{

    private IMainView mainView;
    private RemoteDataSource remoteDataSource;
    private Disposable disposable;

    public MainPresenter(IMainView mainView) {
        this.mainView = mainView;
        this.remoteDataSource = new RemoteDataSource();
    }

    public void getQuizByCategory(int catID) {
        mainView.showProgressBar();
        disposable = remoteDataSource
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
        mainView.hideProgressBar();
        mainView.showErrorMain(error.getLocalizedMessage());
    }

    @Override
    public void onDestroy() {
        disposable.dispose();
    }
}
