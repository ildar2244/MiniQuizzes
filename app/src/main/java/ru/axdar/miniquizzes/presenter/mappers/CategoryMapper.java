package ru.axdar.miniquizzes.presenter.mappers;

import java.util.List;

import io.reactivex.functions.Function;
import ru.axdar.miniquizzes.data.dto.CategoryDTO;
import ru.axdar.miniquizzes.presenter.vo.CategoryVO;

/**
 * Created by ildar2244 on 21.08.2018.
 */
public class CategoryMapper implements Function<List<CategoryDTO>, List<CategoryVO>> {
    @Override
    public List<CategoryVO> apply(List<CategoryDTO> categoryDTOS) throws Exception {

        return null;
    }
}
