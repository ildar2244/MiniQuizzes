package ru.axdar.miniquizzes.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ildar2244 on 21.08.2018.
 * Ответы на вопросы
 */
public class AnswerDTO {
    //текст ответа
    @SerializedName("text")
    @Expose
    //статус ответа: верно-неверно
    private String text;
    @SerializedName("check")
    @Expose
    private Boolean check;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }
}
