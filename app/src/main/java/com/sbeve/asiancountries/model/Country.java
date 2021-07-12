package com.sbeve.asiancountries.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.List;


@Entity(tableName = "countries")
public class Country {

    @PrimaryKey
    @NonNull
    public String name;
    public String capital;
    public String region;
    public String subregion;
    public int population;
    public List<String> borders;
    public List<Language> languages;
    public String flag;

    public Country(
            @NotNull String name,
            String capital,
            String region,
            String subregion,
            int population,
            List<Language> languages,
            List<String> borders,
            String flag
    ) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.languages = languages;
        this.borders = borders;
        this.flag = flag;
    }

    public static class Language {
        public String name;
    }
}
