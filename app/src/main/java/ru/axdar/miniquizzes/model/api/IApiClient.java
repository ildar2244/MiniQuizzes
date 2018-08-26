package ru.axdar.miniquizzes.model.api;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import ru.axdar.miniquizzes.model.Config;
import ru.axdar.miniquizzes.model.dto.CategoryDTO;
import ru.axdar.miniquizzes.model.dto.ModelDTO;
import ru.axdar.miniquizzes.model.dto.QuizDTO;

/**
 * Created by ildar2244 on 20.08.2018.
 */
public interface IApiClient {

    /**
     * гет-запрос данных из корневого каталога
     */
    @GET(Config.PATH_ROOT)
    Flowable<ModelDTO> loadData();

    /**
     * гет-запрос данных из подкаталога Категорий
     */
    @GET(Config.PATH_CATEGORIES)
    Observable<List<CategoryDTO>> getCategories();

    /**
     * гет-запрос данных из подкаталога Викторины
     */
    @GET(Config.PATH_QUIZZES)
    Flowable<List<QuizDTO>> getQuizzes();

}
