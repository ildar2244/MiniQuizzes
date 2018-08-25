package ru.axdar.miniquizzes.model.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ru.axdar.miniquizzes.model.Config;

/**
 * Created by ildar2244 on 24.08.2018.
 */
public class FirebaseHelper {

    private FirebaseDatabase database;

    public FirebaseHelper() {
        database = FirebaseDatabase.getInstance();
    }

    public DatabaseReference getCategories() {
        return database.getReference(Config.DATABASE_NAME);
    }
}
