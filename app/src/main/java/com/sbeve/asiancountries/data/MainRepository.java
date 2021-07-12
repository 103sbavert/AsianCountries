package com.sbeve.asiancountries.data;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sbeve.asiancountries.data.retrofit.RetrofitInit;
import com.sbeve.asiancountries.data.room.CountriesDatabase;
import com.sbeve.asiancountries.data.room.CountriesDatabaseDao;
import com.sbeve.asiancountries.model.Country;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private final CountriesDatabaseDao dao;
    private final RetrofitInit.AccessApi accessApi = RetrofitInit.accessApi;
    private final MutableLiveData<DownloadAttemptResult> _downloadAttemptResult = new MutableLiveData<>();
    private final MutableLiveData<Completable> _completable = new MutableLiveData<>();
    public MainRepository(Context context) {
        dao = CountriesDatabase.getCountriesDatabaseInstance(context).getDao();
    }

    public LiveData<Completable> getCompletable() {
        return _completable;
    }

    public LiveData<DownloadAttemptResult> getDownloadAttemptResult() {
        return _downloadAttemptResult;
    }

    public LiveData<List<Country>> getAllCountries() {
        return dao.getAllCountries();
    }

    public Completable clearDb() {
        return dao.deleteAll();
    }

    public void downloadDataAndInsertIntoDb() {
        Call<List<Country>> retrofitCall = accessApi.getCountriesByRegion("asia");

        retrofitCall.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(@NotNull Call<List<Country>> call, @NotNull Response<List<Country>> response) {
                if (!response.isSuccessful()) {
                    if (response.code() > 399 && response.code() < 500) {
                        _downloadAttemptResult.setValue(DownloadAttemptResult.ClientSideFailure);
                    } else {
                        _downloadAttemptResult.setValue(DownloadAttemptResult.ServerSideFailure);
                    }
                } else {
                    _downloadAttemptResult.setValue(DownloadAttemptResult.Pass);
                    _completable.setValue(dao.insertCountries(response.body()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Country>> call, @NotNull Throwable t) {
                _downloadAttemptResult.setValue(DownloadAttemptResult.ClientSideFailure);
            }
        });
    }

    public enum DownloadAttemptResult {
        Pass(true), ClientSideFailure(false), ServerSideFailure(false);
        public Boolean wasDownloadSuccessful;

        DownloadAttemptResult(boolean wasDownloadSuccessful) {
            this.wasDownloadSuccessful = wasDownloadSuccessful;
        }
    }
}
