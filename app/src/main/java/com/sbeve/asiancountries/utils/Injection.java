package com.sbeve.asiancountries.utils;

import android.content.Context;

import com.sbeve.asiancountries.data.MainRepository;
import com.sbeve.asiancountries.data.retrofit.RetrofitInit;
import com.sbeve.asiancountries.data.room.CountriesDatabase;

public class Injection {

    private Injection() {
    }

    public static MainRepository provideRepository(Context context) {
        return new MainRepository(
                CountriesDatabase.getCountriesDatabaseInstance(context).getDao(),
                RetrofitInit.accessApi
        );
    }
}
