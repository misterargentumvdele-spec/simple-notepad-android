package com.example.simplenotepad;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final String PREFS_NAME = "SimpleNotepadPrefs";
    private static final String KEY_NOTE = "saved_note";
    private EditText editTextNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNote = findViewById(R.id.editTextNote);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnClear = findViewById(R.id.btnClear);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        editTextNote.setText(prefs.getString(KEY_NOTE, ""));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
                Toast.makeText(MainActivity.this, "Заметка сохранена", Toast.LENGTH_SHORT).show();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNote.setText("");
                saveNote();
                Toast.makeText(MainActivity.this, "Очищено", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveNote();
    }

    private void saveNote() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        prefs.edit().putString(KEY_NOTE, editTextNote.getText().toString()).apply();
    }
}
