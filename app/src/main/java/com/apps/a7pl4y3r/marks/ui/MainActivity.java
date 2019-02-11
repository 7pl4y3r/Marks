package com.apps.a7pl4y3r.marks.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.apps.a7pl4y3r.marks.Data;
import com.apps.a7pl4y3r.marks.DisciplineAdapter;
import com.apps.a7pl4y3r.marks.R;
import com.apps.a7pl4y3r.marks.ViewModel;
import com.apps.a7pl4y3r.marks.room.Discipline;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ViewModel viewModel;
    private boolean wantsToEdit = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDrawerLayout();
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.nav_main_edit:

                if (wantsToEdit) {

                    Toast.makeText(this, "Done editing", Toast.LENGTH_SHORT).show();
                    wantsToEdit = false;

                } else {

                    Toast.makeText(this, "Press the item you want to edit", Toast.LENGTH_SHORT).show();
                    wantsToEdit = true;

                }

                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setDrawerLayout() {

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        drawerLayout = findViewById(R.id.drawer_main);
        NavigationView navigationView = findViewById(R.id.navigation_view_main);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

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

                if (wantsToEdit) {

                    Intent intent = new Intent(MainActivity.this, EditDiscipline.class);

                    intent.putExtra(Data.EXTRA_ID, discipline.getId());
                    intent.putExtra(Data.EXTRA_TITLE, discipline.getTitle());

                    startActivityForResult(intent, Data.REQUEST_EDIT_DISCIPLINE);

                } else {
                    //Will start ViewMarks.class
                }

            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                Discipline discipline = adapter.getDisciplineAt(viewHolder.getAdapterPosition());
                viewModel.delete(discipline);
                Toast.makeText(MainActivity.this, "Deleted item", Toast.LENGTH_SHORT).show();

            }

        }).attachToRecyclerView(recyclerView);

    }

}
