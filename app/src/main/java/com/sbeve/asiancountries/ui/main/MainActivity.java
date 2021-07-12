package com.sbeve.asiancountries.ui.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.sbeve.asiancountries.R;
import com.sbeve.asiancountries.data.room.CountriesDatabase;
import com.sbeve.asiancountries.data.room.CountriesDatabaseDao;
import com.sbeve.asiancountries.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public final static String IS_DATA_DOWNLOADED_KEY = "is_data_downloaded";
    public SharedPreferences sharedPreferences;
    ActivityMainBinding binding;
    NavController navController;
    NavHostFragment navHostFragment;
    CountriesDatabase countriesDatabase;
    CountriesDatabaseDao countriesDatabaseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(binding.mainNavHost.getId());
        navController = navHostFragment.getNavController();
        sharedPreferences = getPreferences(MODE_PRIVATE);
        countriesDatabase = CountriesDatabase.getCountriesDatabaseInstance(this);
        countriesDatabaseDao = countriesDatabase.getDao();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }
}
