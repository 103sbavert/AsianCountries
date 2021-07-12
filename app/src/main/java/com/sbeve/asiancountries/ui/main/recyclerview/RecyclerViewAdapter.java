package com.sbeve.asiancountries.ui.main.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.sbeve.asiancountries.databinding.RecyclerViewItemLayoutBinding;
import com.sbeve.asiancountries.model.Country;

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
        public RecyclerViewItemLayoutBinding binding;

        RVViewHolder(@NonNull RecyclerViewItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public static RVViewHolder getViewHolder(ViewGroup parent) {
            RecyclerViewItemLayoutBinding binding = RecyclerViewItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new RVViewHolder(binding);
        }

        public void bind(Country currentItem) {
            binding.nameTextview.setText(currentItem.name);
            binding.capitalTextview.setText("Capital: " + currentItem.capital);
            binding.regionTextview.setText("Region: " + currentItem.region);
            binding.subregionTextview.setText("Region: " + currentItem.subregion);
            binding.populationTextview.setText("Population: " + currentItem.population);
            binding.bordersTextview.setText("Borders: " + getBorders(currentItem.borders));
            binding.languagesTextview.setText("Languages: " + getLanguages(currentItem.languages));
        }

        public String getBorders(List<String> bordersList) {
            StringBuilder output = new StringBuilder();

            for (int i = 0; i < bordersList.size(); i += 1) {
                output.append(bordersList.get(i));
                if (i < bordersList.size() - 1) {
                    output.append(", ");
                }
            }

            return output.toString();
        }

        public String getLanguages(List<Country.Language> languagesList) {
            StringBuilder output = new StringBuilder();

            for (int i = 0; i < languagesList.size(); i += 1) {
                output.append(languagesList.get(i).name);
                if (i < languagesList.size() - 1) {
                    output.append(", ");
                }
            }

            return output.toString();
        }
    }
}
