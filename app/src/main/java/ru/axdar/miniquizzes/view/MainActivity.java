package ru.axdar.miniquizzes.view;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.axdar.miniquizzes.R;
import ru.axdar.miniquizzes.model.dto.CategoryDTO;
import ru.axdar.miniquizzes.model.dto.QuizDTO;
import ru.axdar.miniquizzes.presenter.MainPresenter;
import ru.axdar.miniquizzes.presenter.NavigationPresenter;
import ru.axdar.miniquizzes.view.adapter.CategoriesAdapter;
import ru.axdar.miniquizzes.view.adapter.QuizzesAdapter;

public class MainActivity extends AppCompatActivity implements INavigationView, IMainView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_list)
    RecyclerView recyclerViewCategories;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.recycler_view_quizzes)
    RecyclerView recyclerViewQuizzes;

    private NavigationPresenter presenterNav;
    private CategoriesAdapter categoriesAdapter;
    private MainPresenter presenterMain;
    private QuizzesAdapter quizzesAdapter;
    private int categoryId = 10; // id выбираемой категории


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        presenterNav = new NavigationPresenter(this);
        presenterMain = new MainPresenter(this);
        // NavView init
        initNavView();
        // MainContent
        initMainView();
    }

    /**
     * Создаются необходимые объекты для NavView и вызывается Presenter
     */
    private void initNavView() {
        // UI-elements
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerViewCategories.setLayoutManager(llm);
        categoriesAdapter = new CategoriesAdapter(new ArrayList<>(), presenterMain, this);
        recyclerViewCategories.setAdapter(categoriesAdapter);
        recyclerViewCategories.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        );
        // work with NavPresenter
        presenterNav.getCategories();
    }

    /**
     * UI-элементы основной части активити
     */
    private void initMainView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerViewQuizzes.setLayoutManager(manager);
        quizzesAdapter = new QuizzesAdapter(this, new ArrayList<>());
        recyclerViewQuizzes.setAdapter(quizzesAdapter);
        recyclerViewQuizzes.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        );
        // work with MainPresenter
        presenterMain.getQuizByCategory(categoryId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterNav.onDestroy();
        presenterMain.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void showCategories(List<CategoryDTO> vo) {
        categoriesAdapter.addCategoriesToAdapter(vo);
    }

    @Override
    public void onItemClick() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void showError(String error) {
        Log.d("MyTAG", "ERROR: " + error);
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showQuizList(List<QuizDTO> quizVO) {
        quizzesAdapter.addQuizToAdapter(quizVO);
    }

    @Override
    public void showErrorMain(String errorText) {
        Log.d("MyTAG", "ERROR: " + errorText);
    }
}
