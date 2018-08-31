package ru.axdar.miniquizzes.model.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import ru.axdar.miniquizzes.model.Config;

/**
 * Created by ildar2244 on 24.08.2018.
 */
public class FirebaseHelper {

    private static FirebaseStorage instanceStorage() {
        return FirebaseStorage.getInstance();
    }

    public static StorageReference getReferenceStorage() {
        return instanceStorage().getReferenceFromUrl(Config.STORAGE_URL);
    }
}
