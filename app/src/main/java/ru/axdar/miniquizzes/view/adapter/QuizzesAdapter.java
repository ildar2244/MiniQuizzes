package ru.axdar.miniquizzes.view.adapter;

import android.content.Context;
import android.content.Intent;
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
import ru.axdar.miniquizzes.model.dto.QuizDTO;
import ru.axdar.miniquizzes.view.GameActivity;

/**
 * Created by ildar2244 on 26.08.2018.
 */
public class QuizzesAdapter extends RecyclerView.Adapter<QuizzesAdapter.ViewHolder> {

    private List<QuizDTO> list;
    private Context context;

    public QuizzesAdapter(Context context, List<QuizDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public QuizzesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_quiz, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizzesAdapter.ViewHolder holder, int position) {

        String text = list.get(position).getTitle();
        holder.title.setText(text);
        holder.setItemClickListener((view, position1, isLongClick) -> {
            context.startActivity(new Intent(context, GameActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_quiz_title)
        TextView title;

        private ItemClickListener itemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);  //обработчик клика на итем
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }
    }

    /**
     * подгружаем список в адаптер
     */
    public void addQuizToAdapter(List<QuizDTO> quizDTOList) {
        list = quizDTOList;
        notifyDataSetChanged();
    }
}
