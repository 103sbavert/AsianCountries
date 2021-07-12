package com.sbeve.asiancountries.data.room;

import androidx.room.TypeConverter;

import com.sbeve.asiancountries.model.Country;

import java.util.Arrays;
import java.util.List;

public class Converters {

    @TypeConverter
    public static String fromStringListToString(List<String> stringList) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < stringList.size(); i += 1) {
            output.append(stringList.get(i));
            if (i < stringList.size() - 1) {
                output.append(", ");
            }
        }

        return output.toString();
    }

    @TypeConverter
    public static List<String> fromStringToStringList(String string) {
        string = string.trim();
        String[] output = string.isEmpty() ? new String[]{} : string.split(", ");
        return Arrays.asList(output);
    }

    @TypeConverter
    public static String fromLanguageListToString(List<Country.Language> stringList) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < stringList.size(); i += 1) {
            output.append(stringList.get(i).name);
            if (i < stringList.size() - 1) {
                output.append(", ");
            }
        }

        return output.toString();
    }

    @TypeConverter
    public static List<Country.Language> fromStringToLanguageList(String string) {
        string = string.trim();
        String[] stringList = string.isEmpty() ? new String[]{} : string.split(", ");
        Country.Language[] output = new Country.Language[stringList.length];

        for (int i = 0; i < output.length; i += 1) {
            output[i] = new Country.Language();
            output[i].name = stringList[i];
        }

        return Arrays.asList(output);
    }
}
