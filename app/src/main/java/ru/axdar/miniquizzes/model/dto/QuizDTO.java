package ru.axdar.miniquizzes.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ildar2244 on 21.08.2018.
 */
public class QuizDTO {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("dateCreate")
    @Expose
    private String dateCreate;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("questions")
    @Expose
    private List<QuestionDTO> questions = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<QuestionDTO> getQuestionsDto() {
        return questions;
    }

    public void setQuestionsDto(List<QuestionDTO> questions) {
        this.questions = questions;
    }
}
