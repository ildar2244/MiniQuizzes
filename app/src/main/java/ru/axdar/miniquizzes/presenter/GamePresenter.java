package ru.axdar.miniquizzes.presenter;

import ru.axdar.miniquizzes.data.Config;
import ru.axdar.miniquizzes.data.firebase.FirebaseHelper;
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
