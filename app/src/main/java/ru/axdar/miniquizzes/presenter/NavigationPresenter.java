package ru.axdar.miniquizzes.presenter;

import android.util.Log;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.axdar.miniquizzes.model.Model;
import ru.axdar.miniquizzes.model.dto.CategoryDTO;
import ru.axdar.miniquizzes.view.INavigationView;

/**
 * Created by ildar2244 on 21.08.2018.
 */
public class NavigationPresenter implements IPresenter {

    private INavigationView navigationView;
    private Model model;
    private Disposable disposable;

    public NavigationPresenter(INavigationView navigationView) {
        this.navigationView = navigationView;
        this.model = new Model();
    }

    /**
     * запрос списка категорий
     */
    public void getCategories() {
        disposable = model
                .getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::responseCategories, this::handleError);
    }

    /**
     * обработка результата запроса списка категорий: отправляю во вьюху
     */
    private void responseCategories(List<CategoryDTO> list) {
        navigationView.showCategories(list);
    }

    /**
     * обработка ошибок запроса списка категорий: берём текст сообщения и отправляю во вьюху
     */
    private void handleError(Throwable error) {
        navigationView.showError(error.getLocalizedMessage());
        Log.d("MyTAG", "PRESENTER-error: " + error.getCause());
    }

    @Override
    public void onDestroy() {
        disposable.dispose();
    }
}
