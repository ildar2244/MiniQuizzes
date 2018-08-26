package ru.axdar.miniquizzes.model;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import ru.axdar.miniquizzes.model.api.ApiClient;
import ru.axdar.miniquizzes.model.dto.CategoryDTO;
import ru.axdar.miniquizzes.model.dto.ModelDTO;
import ru.axdar.miniquizzes.model.dto.QuizDTO;

/**
 * Created by ildar2244 on 22.08.2018.
 */
public class Model implements IModel {

    @Override
    public Observable<List<CategoryDTO>> getCategories() {
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
}
