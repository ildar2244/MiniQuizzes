package ru.axdar.miniquizzes.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ildar2244 on 21.08.2018.
 *
 */
public class ModelDTO {
    @SerializedName("categories")
    @Expose
    private List<CategoryDTO> categories = null;
    @SerializedName("quizzes")
    @Expose
    private List<QuizDTO> quizzes = null;

    public List<CategoryDTO> getCategoriesDto() {
        return categories;
    }

    public void setCategoriesDto(List<CategoryDTO> categories) {
        this.categories = categories;
    }

    public List<QuizDTO> getQuizzesDto() {
        return quizzes;
    }

    public void setQuizzesDto(List<QuizDTO> quizzes) {
        this.quizzes = quizzes;
    }
}
