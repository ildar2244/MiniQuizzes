package ru.axdar.miniquizzes.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.axdar.miniquizzes.R;
import ru.axdar.miniquizzes.data.dbo.CategoryDBO;
import ru.axdar.miniquizzes.data.dto.CategoryDTO;
import ru.axdar.miniquizzes.presenter.MainPresenter;
import ru.axdar.miniquizzes.view.INavigationView;

/**
 * Created by ildar2244 on 26.08.2018.
 * Класс-адаптер для отображения списка Категорий
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private MainPresenter mainPresenter;
    private INavigationView navigationView;
    private List<CategoryDBO> list;

    public CategoriesAdapter(List<CategoryDBO> list, MainPresenter mainPresenter,
                             INavigationView navigationView) {
        this.list = list;
        this.mainPresenter = mainPresenter;
        this.navigationView = navigationView;
    }

    @NonNull
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.ViewHolder holder, int position) {

        String title = list.get(position).getTitleRu();

        holder.tvTitle.setText(title);

        holder.tvTitle.setOnClickListener(v -> {
            int catID = list.get(position).getId();
//            mainPresenter.getQuizByCategory(catID);
//            navigationView.onItemClick();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_cat_title)
        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * подгружаем список в адаптер
     */
    public void addCategoriesToAdapter(List<CategoryDBO> categoryDBOList) {
        list = categoryDBOList;
        notifyDataSetChanged();
    }
}
