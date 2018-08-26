package ru.axdar.miniquizzes.view;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.axdar.miniquizzes.R;
import ru.axdar.miniquizzes.model.dto.CategoryDTO;
import ru.axdar.miniquizzes.presenter.NavigationPresenter;
import ru.axdar.miniquizzes.view.adapter.CategoriesAdapter;

public class MainActivity extends AppCompatActivity implements INavigationView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_list)
    RecyclerView recyclerViewCategories;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private NavigationPresenter presenterNav;
    private CategoriesAdapter categoriesAdapter;


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

        recyclerViewCategories = findViewById(R.id.nav_list);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerViewCategories.setLayoutManager(llm);
        categoriesAdapter = new CategoriesAdapter(new ArrayList<>());
        recyclerViewCategories.setAdapter(categoriesAdapter);

        presenterNav = new NavigationPresenter(this);
        presenterNav.showCategories();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterNav.onDestroy();
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
    public void showError(String error) {
        Log.d("MyTAG", "ERROR: " + error);
    }
}
