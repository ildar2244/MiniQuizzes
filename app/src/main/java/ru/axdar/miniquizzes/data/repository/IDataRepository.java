package ru.axdar.miniquizzes.data.repository;

import java.util.List;

import io.reactivex.Flowable;
import ru.axdar.miniquizzes.data.dbo.CategoryDBO;

/**
 * Created by ildar2244 on 06.09.2018.
 */
public interface IDataRepository {

    Flowable<List<CategoryDBO>> loadCategories();

    void saveCategory(CategoryDBO categoryDBO);
}
