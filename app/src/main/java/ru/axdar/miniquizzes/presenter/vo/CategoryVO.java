package ru.axdar.miniquizzes.presenter.vo;

/**
 * Created by ildar2244 on 21.08.2018.
 */
public class CategoryVO {

    private String title;
    private int id;

    public CategoryVO(String title, int id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
