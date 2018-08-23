package ru.axdar.miniquizzes.model.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.axdar.miniquizzes.model.dto.CategoryDTO;
import ru.axdar.miniquizzes.model.dto.ModelDTO;

/**
 * Created by ildar2244 on 20.08.2018.
 */
public interface IApiClient {

    @GET("/{db_name}/{param}.json")
    Observable<ModelDTO> loadData(
            @Path("db_name") String db_name,
            @Path("param") String param
    );

    @GET("/{db_name}/{param}.json")
    Observable<List<CategoryDTO>> getCategories(
            @Path("db_name") String db_name,
            @Path("param") String param
    );

}
