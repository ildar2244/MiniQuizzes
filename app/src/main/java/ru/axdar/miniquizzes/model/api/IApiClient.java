package ru.axdar.miniquizzes.model.api;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import ru.axdar.miniquizzes.model.Config;
import ru.axdar.miniquizzes.model.dto.CategoryDTO;
import ru.axdar.miniquizzes.model.dto.ModelDTO;

/**
 * Created by ildar2244 on 20.08.2018.
 */
public interface IApiClient {

    @GET(Config.PATH_ROOT)
    Flowable<ModelDTO> loadData();

    @GET(Config.PATH_CATEGORIES)
    Observable<List<CategoryDTO>> getCategories();

}
