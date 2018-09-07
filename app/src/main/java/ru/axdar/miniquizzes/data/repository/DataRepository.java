package ru.axdar.miniquizzes.data.repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.realm.Realm;
import ru.axdar.miniquizzes.data.dbo.CategoryDBO;
import ru.axdar.miniquizzes.data.repository.local.LocalDataSource;
import ru.axdar.miniquizzes.data.repository.remote.RemoteDataSource;

/**
 * Created by ildar2244 on 06.09.2018.
 */
public class DataRepository implements IDataRepository {

    private LocalDataSource localData;
    private RemoteDataSource remoteData;

    public DataRepository() {
        this.localData = new LocalDataSource();
        this.remoteData = new RemoteDataSource();
    }

    @Override
    public Flowable<List<CategoryDBO>> loadCategories() {
        /*return Flowable.concat(mLocal.loadCategoriesFromLocal(), loadFromNet())
                .filter(categoryDBOS -> !categoryDBOS.isEmpty())
                .firstOrError()
                .toFlowable();*/
        return localData.loadCategoriesFromLocal()
                .filter(list -> !list.isEmpty())
                .switchIfEmpty(loadFromNet());
    }

    @Override
    public void saveCategory(CategoryDBO categoryDBO) {
        localData.saveCategoriesToLocal(categoryDBO);
    }

    private Flowable<List<CategoryDBO>> loadFromNet() {
        return remoteData.loadCategoriesFromRemote()
                .flatMap(Flowable::fromIterable)
                .map(categoryDTO -> new CategoryDBO(categoryDTO.getId(), categoryDTO.getTitleRu()))
                .doOnNext(this::saveCategory)
                .toList()
                .toFlowable();
    }
}
