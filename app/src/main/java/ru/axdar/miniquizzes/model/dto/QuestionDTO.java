package ru.axdar.miniquizzes.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ildar2244 on 21.08.2018.
 * Вопрос викторины
 */
public class QuestionDTO implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("question")
    @Expose
    private String question;
    //наличие картинки в вопросе
    @SerializedName("imgCheck")
    @Expose
    private Boolean imgCheck;
    @SerializedName("imgName")
    @Expose
    private String imgName;
    @SerializedName("answers")
    @Expose
    private List<AnswerDTO> answers = null;
    @SerializedName("answerTrue")
    @Expose
    private String answerTrue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Boolean getImgCheck() {
        return imgCheck;
    }

    public void setImgCheck(Boolean imgCheck) {
        this.imgCheck = imgCheck;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public List<AnswerDTO> getAnswersDto() {
        return answers;
    }

    public void setAnswersDto(List<AnswerDTO> answers) {
        this.answers = answers;
    }

    public String getAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(String answerTrue) {
        this.answerTrue = answerTrue;
    }
}
