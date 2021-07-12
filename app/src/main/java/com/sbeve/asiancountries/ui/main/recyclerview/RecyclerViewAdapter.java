package com.sbeve.asiancountries.ui.main.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.sbeve.asiancountries.databinding.CountryItemLayoutBinding;
import com.sbeve.asiancountries.model.Country;
import com.sbeve.asiancountries.utils.Converters;
import com.sbeve.asiancountries.utils.Utils;

import java.util.List;

public class RecyclerViewAdapter extends Adapter<RecyclerViewAdapter.RVViewHolder> {
    private List<Country> dataSet;

    public RecyclerViewAdapter(List<Country> dataSet) {
        setDataSet(dataSet);
    }

    public void setDataSet(List<Country> value) {
        dataSet = value;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return RVViewHolder.getViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder holder, int position) {
        holder.bind(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class RVViewHolder extends RecyclerView.ViewHolder {
        public CountryItemLayoutBinding binding;

        RVViewHolder(@NonNull CountryItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public static RVViewHolder getViewHolder(ViewGroup parent) {
            CountryItemLayoutBinding binding = CountryItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new RVViewHolder(binding);
        }

        public void bind(Country currentItem) {
            binding.nameTextview.setText(currentItem.name);
            binding.capitalTextview.setText("Capital: " + currentItem.capital);
            binding.regionTextview.setText("Region: " + currentItem.region);
            binding.subregionTextview.setText("Region: " + currentItem.subregion);
            binding.populationTextview.setText("Population: " + currentItem.population);
            binding.bordersTextview.setText("Borders: " + Converters.fromStringListToString(currentItem.borders));
            binding.languagesTextview.setText("Languages: " + Converters.fromLanguageListToString(currentItem.languages));
            Utils.fetchSvg(binding.getRoot().getContext(), currentItem.flag, binding.flagImageview);
        }
    }
}
