package com.example.weatherapp;
//страница настроек
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.content.SharedPreferences;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.app.AppCompatDelegate;


public class SettingsActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "theme_prefs";
    private static final String UNIT_KEY = "temperature_unit";
    private static final String THEME_KEY = "is_dark_theme";

    @Override //метод создание меню настроек.
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings); //Устанавливает макет

        Toolbar toolbar = findViewById(R.id.settings_toolbar);
        setSupportActionBar(toolbar); //Находит Toolbar в макете и устанавливает его как панель действий.

        SwitchCompat themeSwitch = findViewById(R.id.themeSwitch); //Находит переключатель для выбора темы.
        SwitchCompat metricSwitch = findViewById(R.id.metricSwitch); //Находит переключатель для выбора единицы измерения температуры.

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE); // для чтения и записи настроек.

        boolean isFahrenheit = sharedPreferences.getBoolean(UNIT_KEY, false);
        metricSwitch.setChecked(isFahrenheit); //Получает значение, указывающее, используется ли шкала Фаренгейта.

        boolean isDarkTheme = sharedPreferences.getBoolean(THEME_KEY, false);
        themeSwitch.setChecked(isDarkTheme);//Устанавливает состояние переключателя единиц измерения в соответствии с сохраненным значением.

        themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(THEME_KEY, isChecked);
            editor.apply();

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        metricSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(UNIT_KEY, isChecked);
            editor.apply();

            setResult(RESULT_OK);
        });
    }



    @Override //обрабатывает нажатие на элементы меню.
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }
}