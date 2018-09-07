package ru.axdar.miniquizzes.data.firebase;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import ru.axdar.miniquizzes.data.Config;

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
