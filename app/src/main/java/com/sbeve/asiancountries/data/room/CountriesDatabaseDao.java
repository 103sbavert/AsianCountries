package com.sbeve.asiancountries.data.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.sbeve.asiancountries.model.Country;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface CountriesDatabaseDao {
    @Query("SELECT * FROM countries ORDER BY name ASC")
    LiveData<List<Country>> getAllCountries();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertCountries(List<Country> countries);

    @Query("DELETE FROM countries")
    void deleteAll();
}
