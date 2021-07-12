package com.sbeve.asiancountries.data.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.sbeve.asiancountries.model.Country;
import com.sbeve.asiancountries.utils.Converters;

@Database(entities = Country.class, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class CountriesDatabase extends RoomDatabase {
    private static final String DB_NAME = "countries_database";
    private static CountriesDatabase countriesDatabase;

    public static synchronized CountriesDatabase getCountriesDatabaseInstance(Context context) {
        if (countriesDatabase == null) {
            countriesDatabase = Room.databaseBuilder(context.getApplicationContext(), CountriesDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return countriesDatabase;
    }

    public abstract CountriesDatabaseDao getDao();
}
