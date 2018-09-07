package ru.axdar.miniquizzes.data.dbo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ildar2244 on 06.09.2018.
 */
public class CategoryDBO extends RealmObject {

    @PrimaryKey
    private Integer id;
    private String titleRu;

    public CategoryDBO() {
    }

    public CategoryDBO(Integer id, String titleRu) {
        this.id = id;
        this.titleRu = titleRu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitleRu() {
        return titleRu;
    }

    public void setTitleRu(String titleRu) {
        this.titleRu = titleRu;
    }
}
