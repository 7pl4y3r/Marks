package com.apps.a7pl4y3r.marks.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.apps.a7pl4y3r.marks.Data;
import com.apps.a7pl4y3r.marks.DisciplineAdapter;
import com.apps.a7pl4y3r.marks.R;
import com.apps.a7pl4y3r.marks.ViewModel;
import com.apps.a7pl4y3r.marks.room.Discipline;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRecyclerView();

        FloatingActionButton fab = findViewById(R.id.fab_main);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, AddDiscipline.class), Data.REQUEST_ADD_DISCIPLINE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK) {

            String title = data.getStringExtra(Data.EXTRA_TITLE);

            switch (requestCode) {

                case Data.REQUEST_ADD_DISCIPLINE:
                    viewModel.insert(new Discipline(title, "0"));
                    Toast.makeText(this, "Discipline inserted!", Toast.LENGTH_SHORT).show();
                    break;

                case Data.REQUEST_EDIT_DISCIPLINE:
                    Discipline discipline = new Discipline(title, "0");
                    discipline.setId(data.getIntExtra(Data.EXTRA_ID, -1));
                    viewModel.update(discipline);
                    Toast.makeText(this, "Discipline updated", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    Toast.makeText(this, "Some kind of error happened", Toast.LENGTH_SHORT).show();
                    break;

            }

        }

    }

    private void setRecyclerView() {

        RecyclerView recyclerView = findViewById(R.id.recycler_view_main);
        final DisciplineAdapter adapter = new DisciplineAdapter();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        //ViewModel
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.getDisciplines().observe(this, new Observer<List<Discipline>>() {
            @Override
            public void onChanged(List<Discipline> disciplines) {
                adapter.submitList(disciplines);
            }
        });


        //ClickListener
        adapter.setOnItemClickListener(new DisciplineAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Discipline discipline) {

                Intent intent = new Intent(MainActivity.this, EditDiscipline.class);

                intent.putExtra(Data.EXTRA_ID, discipline.getId());
                intent.putExtra(Data.EXTRA_TITLE, discipline.getTitle());

                startActivityForResult(intent, Data.REQUEST_EDIT_DISCIPLINE);

            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                viewModel.delete(adapter.getDisciplineAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Deleted item", Toast.LENGTH_SHORT).show();

            }

        }).attachToRecyclerView(recyclerView);

    }

}
