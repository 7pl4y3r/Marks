package com.apps.a7pl4y3r.marks.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.apps.a7pl4y3r.marks.Data;
import com.apps.a7pl4y3r.marks.R;

public class AddDiscipline extends AppCompatActivity {

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
                saveDiscipline();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void saveDiscipline() {

        EditText editText = findViewById(R.id.edit_text_discipline);
        String text = editText.getText().toString();

        if (text.trim().isEmpty()) {
            Toast.makeText(this, "No discipline name found", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(Data.EXTRA_TITLE, text);

        setResult(RESULT_OK, intent);
        finish();

    }

}
