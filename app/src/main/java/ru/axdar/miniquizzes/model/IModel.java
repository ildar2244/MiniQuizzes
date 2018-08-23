package ru.axdar.miniquizzes.model;

import java.util.List;

import io.reactivex.Observable;
import ru.axdar.miniquizzes.model.dto.CategoryDTO;

/**
 * Created by ildar2244 on 22.08.2018.
 */
public interface IModel {

    Observable<List<CategoryDTO>> getCategories(String dbName, String folder);
}
