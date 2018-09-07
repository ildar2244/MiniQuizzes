package ru.axdar.miniquizzes.data.repository.remote;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import ru.axdar.miniquizzes.data.dto.CategoryDTO;
import ru.axdar.miniquizzes.data.dto.ModelDTO;
import ru.axdar.miniquizzes.data.dto.QuizDTO;

/**
 * Created by ildar2244 on 22.08.2018.
 */
public interface IRemoteDataSource {

    Observable<List<CategoryDTO>> getCategoriesFromRemote();

    Flowable<ModelDTO> getModel();

    Flowable<List<QuizDTO>> getQuizByCategory();

    Flowable<List<CategoryDTO>> loadCategoriesFromRemote();
}
