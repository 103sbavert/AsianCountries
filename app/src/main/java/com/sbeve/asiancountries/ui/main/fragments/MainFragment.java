package com.sbeve.asiancountries.ui.main.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sbeve.asiancountries.R;
import com.sbeve.asiancountries.databinding.FragmentMainBinding;
import com.sbeve.asiancountries.ui.main.MainActivity;
import com.sbeve.asiancountries.ui.main.recyclerview.RecyclerViewAdapter;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static com.sbeve.asiancountries.ui.main.MainActivity.IS_DATA_DOWNLOADED_KEY;

public class MainFragment extends Fragment {

    FragmentMainBinding binding;
    MainViewModel mainViewModel;
    MainActivity mainActivity;
    RecyclerViewAdapter recyclerViewAdapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainActivity = (MainActivity) requireActivity();
        MainViewModelFactory viewModelFactory = new MainViewModelFactory(mainActivity);
        mainViewModel = new ViewModelProvider(mainActivity, viewModelFactory).get(MainViewModel.class);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<>());

        binding.recyclerview.setAdapter(recyclerViewAdapter);

        setHasOptionsMenu(true);

        if (!mainActivity.sharedPreferences.getBoolean(IS_DATA_DOWNLOADED_KEY, false)) {
            mainViewModel.downloadDataAndInsertIntoDb();
        }

        mainViewModel.getCompletable().observe(
                getViewLifecycleOwner(),
                completable -> compositeDisposable.add(
                        completable
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe()
                )
        );

        mainViewModel.getDownloadAttemptResult().observe(
                getViewLifecycleOwner(),
                downloadAttemptResult -> mainActivity
                        .sharedPreferences
                        .edit()
                        .putBoolean(IS_DATA_DOWNLOADED_KEY, downloadAttemptResult.wasDownloadSuccessful)
                        .apply()
        );

        mainViewModel.getAllCountries().observe(getViewLifecycleOwner(), listOfCountries ->
                recyclerViewAdapter.setDataSet(listOfCountries)
        );

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.delete_all) {
            Completable completable = mainViewModel.clearDb();
            compositeDisposable.add(completable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            );
            mainActivity.sharedPreferences.edit().putBoolean(IS_DATA_DOWNLOADED_KEY, false).apply();
            Toast.makeText(mainActivity, "The data will be downloaded again when the activity is restarted", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onStop() {
        super.onStop();
        compositeDisposable.dispose();
    }
}
