package com.apps.a7pl4y3r.marks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apps.a7pl4y3r.marks.room.Discipline;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class DisciplineAdapter extends ListAdapter<Discipline, DisciplineAdapter.DisciplineHolder> {


    public DisciplineAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Discipline> DIFF_CALLBACK = new DiffUtil.ItemCallback<Discipline>() {
        @Override
        public boolean areItemsTheSame(@NonNull Discipline oldItem, @NonNull Discipline newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Discipline oldItem, @NonNull Discipline newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getMarksCount().equals(newItem.getMarksCount());
        }

    };

    @NonNull
    @Override
    public DisciplineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DisciplineHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DisciplineHolder holder, int position) {
        holder.title.setText(getItem(position).getTitle());
        holder.subtitle.setText(getItem(position).getMarksCount());
    }


    public interface OnItemClickListener {
        void onItemClick(Discipline discipline);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public class DisciplineHolder extends RecyclerView.ViewHolder {

        private View view;
        private TextView title;
        private TextView subtitle;

        public DisciplineHolder(@NonNull View view) {
            super(view);
            this.view = view;

            title = view.findViewById(R.id.card_title);
            subtitle = view.findViewById(R.id.card_subtitle);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                        listener.onItemClick(getItem(position));

                }
            });

        }
    }

}
