package com.apps.a7pl4y3r.marks.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.apps.a7pl4y3r.marks.Data;
import com.apps.a7pl4y3r.marks.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditDiscipline extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discipline_editor);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_done, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.item_done:
                updateDiscipline();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }


    private void updateDiscipline() {

        EditText editText = findViewById(R.id.edit_text_discipline);
        String title = editText.getText().toString();
        int id = getIntent().getIntExtra(Data.EXTRA_ID, -1);

        if (title.trim().isEmpty() || id == -1) {
            Toast.makeText(this, "Invalid data", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(Data.EXTRA_ID, id);
        data.putExtra(Data.EXTRA_TITLE, title);

        setResult(RESULT_OK, data);
        finish();

    }

}
