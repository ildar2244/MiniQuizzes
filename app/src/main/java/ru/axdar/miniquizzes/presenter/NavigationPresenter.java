package ru.axdar.miniquizzes.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import ru.axdar.miniquizzes.model.Config;
import ru.axdar.miniquizzes.model.dto.CategoryDTO;
import ru.axdar.miniquizzes.model.firebase.FirebaseHelper;
import ru.axdar.miniquizzes.view.INavigationView;

/**
 * Created by ildar2244 on 21.08.2018.
 */
public class NavigationPresenter implements INavigationPresenter {

    private INavigationView navigationView;
    private FirebaseHelper firebaseHelper;

    public NavigationPresenter(INavigationView navigationView) {
        this.navigationView = navigationView;
        this.firebaseHelper = new FirebaseHelper();
    }

    private void responseCategories(List<CategoryDTO> list) {
        navigationView.showCategories(list);
    }

    private void handleError(Throwable error) {
        navigationView.showError(error.getLocalizedMessage());
    }

    @Override
    public void getCategories() {
        Log.d("MyTAG", "PRESENTER-1");
        firebaseHelper.getCategories().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("MyTAG", "PRESENTER-snapshot" + dataSnapshot);
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    DataSnapshot qSnap = postSnapshot.child(Config.PATH_CATEGORIES);
                    Log.d("MyTAG", "PRESENTER-value" + qSnap);
//                    CategoryDTO categoryDTO = postSnapshot.getValue(CategoryDTO.class);
//                    Log.d("MyTAG", "PRESENTER-value" + categoryDTO);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("MyTAG", "ERROR: " + databaseError.getMessage());
            }
        });
    }

    @Override
    public void onDestroy() {

    }
}
