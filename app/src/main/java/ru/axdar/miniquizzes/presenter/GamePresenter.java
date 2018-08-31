package ru.axdar.miniquizzes.presenter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.StorageMetadata;

import ru.axdar.miniquizzes.model.Config;
import ru.axdar.miniquizzes.model.firebase.FirebaseHelper;
import ru.axdar.miniquizzes.view.IGameView;

/**
 * Created by ildar2244 on 31.08.2018.
 */
public class GamePresenter {

    private IGameView gameView;

    public GamePresenter(IGameView gameView) {
        this.gameView = gameView;
    }

    public void getFirebaseImageUri(String imageName) {
        FirebaseHelper.getReferenceStorage()
                .child(Config.FOLDER_IMAGES)
                .child(imageName)
                .getDownloadUrl()
                .addOnSuccessListener(uri -> {

                })
                .addOnFailureListener(this::handleError);
    }

    private void handleError(Throwable error) {
        gameView.errorLoadImage(error.getMessage());
    }
}
