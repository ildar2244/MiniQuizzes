package ru.axdar.miniquizzes.data.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ildar2244 on 21.08.2018.
 */
public class QuizDTO implements Parcelable {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.dateCreate);
        dest.writeString(this.title);
        dest.writeString(this.category);
        dest.writeValue(this.categoryId);
        dest.writeList(this.questions);
    }

    public QuizDTO() {
    }

    protected QuizDTO(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.dateCreate = in.readString();
        this.title = in.readString();
        this.category = in.readString();
        this.categoryId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.questions = new ArrayList<QuestionDTO>();
        in.readList(this.questions, QuestionDTO.class.getClassLoader());
    }

    public static final Parcelable.Creator<QuizDTO> CREATOR = new Parcelable.Creator<QuizDTO>() {
        @Override
        public QuizDTO createFromParcel(Parcel source) {
            return new QuizDTO(source);
        }

        @Override
        public QuizDTO[] newArray(int size) {
            return new QuizDTO[size];
        }
    };
}
