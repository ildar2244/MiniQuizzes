package ru.axdar.miniquizzes.presenter;

import java.util.List;

import ru.axdar.miniquizzes.model.dto.CategoryDTO;
import ru.axdar.miniquizzes.view.INavigationView;

/**
 * Created by ildar2244 on 21.08.2018.
 */
public class NavigationPresenter implements IPresenter {

    private INavigationView navigationView;

    private void responseCategories(List<CategoryDTO> list) {
        navigationView.showCategories(list);
    }

    private void handleError(Throwable error) {
        navigationView.showError(error.getLocalizedMessage());
    }

    @Override
    public void onDestroy() {

    }
}
