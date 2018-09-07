package ru.axdar.miniquizzes.presenter;

import android.util.Log;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.axdar.miniquizzes.data.dbo.CategoryDBO;
import ru.axdar.miniquizzes.data.repository.DataRepository;
import ru.axdar.miniquizzes.data.repository.remote.RemoteDataSource;
import ru.axdar.miniquizzes.data.dto.CategoryDTO;
import ru.axdar.miniquizzes.view.INavigationView;

/**
 * Created by ildar2244 on 21.08.2018.
 */
public class NavigationPresenter implements IPresenter {

    private INavigationView navigationView;
    private RemoteDataSource remoteDataSource;
    private Disposable disposable, disLocal;

    //parts with Realm
    private DataRepository dataRepository;

    public NavigationPresenter(INavigationView navigationView) {
        this.navigationView = navigationView;
        this.remoteDataSource = new RemoteDataSource();
        this.dataRepository = new DataRepository();
    }

    /**
     * запрос списка категорий
     */
    public void getCategories() {
        /*disposable = remoteDataSource
                .getCategoriesFromRemote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::responseCategories, this::handleError);*/
    }

    /**
     * обработка результата запроса списка категорий: отправляю во вьюху
     */
    private void responseCategories(List<CategoryDBO> list) {
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
        if (disLocal.isDisposed()) {
            disLocal.dispose();
        }
    }

    public void getCategoriesFromLocal() {
        disLocal = dataRepository.loadCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::responseCategories, this::handleError);
    }

    private void responseLocal(List<CategoryDBO> list) {
        Log.d("MyTAG", "LOCAL: " + list);
    }
}
