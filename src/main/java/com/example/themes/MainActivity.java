package com.example.themes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {
    SharedPreferences themeSettings;
    SharedPreferences.Editor editorSettings;
    ImageButton imageTheme;

    Button buttonGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeSettings = getSharedPreferences("SETTINGS", MODE_PRIVATE);

        if (!themeSettings.contains("MODE_NIGHT_ON")) {
            editorSettings = themeSettings.edit();
            editorSettings.putBoolean("MODE_NIGHT_ON", false);
            editorSettings.apply();
        }

        setCurrentTheme(); // Устанавливаем текущую тему перед загрузкой layout
        setContentView(R.layout.activity_main);
        imageTheme = findViewById(R.id.imageBtn);
        buttonGame = findViewById(R.id.startBtn);
        updateImageButton();


        buttonGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        imageTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (themeSettings.getBoolean("MODE_NIGHT_ON", false)) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editorSettings = themeSettings.edit();
                    editorSettings.putBoolean("MODE_NIGHT_ON", false);
                    editorSettings.apply();
                    Toast.makeText(MainActivity.this, "Темная тема отключена", Toast.LENGTH_SHORT).show();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editorSettings = themeSettings.edit();
                    editorSettings.putBoolean("MODE_NIGHT_ON", true);
                    editorSettings.apply();
                    Toast.makeText(MainActivity.this, "Темная тема включена", Toast.LENGTH_SHORT).show();
                }
                updateImageButton(); // Обновляем изображение кнопки
                recreate(); // Перезагрузка активности для применения темы
            }
        });
    }

    private void updateImageButton() {
        if (themeSettings.getBoolean("MODE_NIGHT_ON", false)) {
            imageTheme.setImageResource(R.drawable.moon); // Изображение для темной темы
        } else {
            imageTheme.setImageResource(R.drawable.sun); // Изображение для светлой темы
        }
    }

    private void setCurrentTheme() {
        if (themeSettings.getBoolean("MODE_NIGHT_ON", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
