package ru.axdar.miniquizzes.data.repository.local;

import android.util.Log;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.realm.Realm;
import io.realm.RealmResults;
import ru.axdar.miniquizzes.data.dbo.CategoryDBO;

/**
 * Created by ildar2244 on 06.09.2018.
 */
public class LocalDataSource implements ILocalDataSource {

    @Override
    public Flowable<List<CategoryDBO>> loadCategoriesFromLocal() {
        return Flowable.create(emitter -> {
            Realm realm = Realm.getDefaultInstance();
            RealmResults results = realm.where(CategoryDBO.class).findAll();
            Log.d("MyTAG", "REALM-load: " + results);
            realm.copyFromRealm(results);
            if (results != null && results.size()>0) {
                emitter.onNext(results);
                emitter.onComplete();
            } else {
                emitter.onComplete();
            }
            //realm.close();
        }, BackpressureStrategy.BUFFER);
    }

    @Override
    public void saveCategoriesToLocal(CategoryDBO categoryDBO) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Log.d("MyTAG", "REALM-save: " + categoryDBO);
        realm.insert(categoryDBO);
        realm.commitTransaction();
        //realm.close();
    }
}
