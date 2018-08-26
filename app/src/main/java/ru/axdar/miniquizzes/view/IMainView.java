package ru.axdar.miniquizzes.view;

import java.util.List;

import ru.axdar.miniquizzes.model.dto.QuizDTO;

/**
 * Created by ildar2244 on 26.08.2018.
 */
public interface IMainView {

    //TODO: заменить на VO
    void showQuizList(List<QuizDTO> quizVO);

    void showErrorMain(String errorText);


}
