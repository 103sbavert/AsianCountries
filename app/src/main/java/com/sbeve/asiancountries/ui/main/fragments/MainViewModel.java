package com.sbeve.asiancountries.ui.main.fragments;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sbeve.asiancountries.data.repository.MainRepository;
import com.sbeve.asiancountries.model.Country;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class MainViewModel extends ViewModel {

    private final MainRepository mainRepository;

    public MainViewModel(Context context) {
        this.mainRepository = new MainRepository(context);
    }

    public LiveData<Completable> getCompletable() {
        return mainRepository.getCompletable();
    }

    public LiveData<MainRepository.DownloadAttemptResult> getDownloadAttemptResult() {
        return mainRepository.getDownloadAttemptResult();
    }

    public LiveData<List<Country>> getAllCountries() {
        return mainRepository.getAllCountries();
    }

    public void clearDb() {
        mainRepository.clearDb();
    }

    public void downloadDataAndInsertIntoDb() {
        mainRepository.downloadDataAndInsertIntoDb();
    }
}
