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
import ru.axdar.miniquizzes.model.dto.QuizDTO;

/**
 * Created by ildar2244 on 26.08.2018.
 */
public class QuizzesAdapter extends RecyclerView.Adapter<QuizzesAdapter.ViewHolder> {

    private List<QuizDTO> list;

    public QuizzesAdapter(List<QuizDTO> list) {
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_quiz_title)
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void addQuizToAdapter(List<QuizDTO> quizDTOList) {
        list = quizDTOList;
        notifyDataSetChanged();
    }
}
