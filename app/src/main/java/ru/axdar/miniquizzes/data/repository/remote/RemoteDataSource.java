package ru.axdar.miniquizzes.data.repository.remote;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import ru.axdar.miniquizzes.data.api.ApiClient;
import ru.axdar.miniquizzes.data.dto.CategoryDTO;
import ru.axdar.miniquizzes.data.dto.ModelDTO;
import ru.axdar.miniquizzes.data.dto.QuizDTO;

/**
 * Created by ildar2244 on 22.08.2018.
 */
public class RemoteDataSource implements IRemoteDataSource {

    @Override
    public Observable<List<CategoryDTO>> getCategoriesFromRemote() {
        return ApiClient.connectDatabase().getCategories();
    }

    @Override
    public Flowable<ModelDTO> getModel() {
        return ApiClient.connectDatabase().loadData();
    }

    @Override
    public Flowable<List<QuizDTO>> getQuizByCategory() {
        return ApiClient.connectDatabase().getQuizzes();
    }

    @Override
    public Flowable<List<CategoryDTO>> loadCategoriesFromRemote() {
        return ApiClient.connectDatabase().getCategoriesSecond();
    }
}
