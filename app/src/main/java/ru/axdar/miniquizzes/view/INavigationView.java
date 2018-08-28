package ru.axdar.miniquizzes.view;

import java.util.List;

import ru.axdar.miniquizzes.model.dto.CategoryDTO;

/**
 * Created by ildar2244 on 23.08.2018.
 */
public interface INavigationView extends IView {

    //TODO: заменить на VO
    void showCategories(List<CategoryDTO> vo);

    void onItemClick();

}
