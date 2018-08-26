package ru.axdar.miniquizzes.model;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import ru.axdar.miniquizzes.model.dto.CategoryDTO;
import ru.axdar.miniquizzes.model.dto.ModelDTO;
import ru.axdar.miniquizzes.model.dto.QuizDTO;

/**
 * Created by ildar2244 on 22.08.2018.
 */
public interface IModel {

    Observable<List<CategoryDTO>> getCategories();

    Flowable<ModelDTO> getModel();

    Flowable<List<QuizDTO>> getQuizByCategory();
}
