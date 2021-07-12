package com.sbeve.asiancountries.data.room;

import com.sbeve.asiancountries.model.Country.Language;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConvertersTest {

    @Test
    public void fromStringListToString_emptyStringList_returnsEmptyString() {
        List<String> stringList = List.of();

        String result = Converters.fromStringListToString(stringList);

        assertEquals(0, result.length());
    }

    @Test
    public void fromStringListToString_stringListOf10_returnsLikeGivenString() {
        List<String> stringList = List.of(
                "item1",
                "item2",
                "item3",
                "item4",
                "item5",
                "item6",
                "item7",
                "item8",
                "item9",
                "item10"
        );

        String expected = "item1, item2, item3, item4, item5, item6, item7, item8, item9, item10";

        String result = Converters.fromStringListToString(stringList);

        assertEquals(expected, result);

    }

    @Test
    public void fromStringToStringList_emptyString_returnsEmptyList() {
        String string = "";

        List<String> result = Converters.fromStringToStringList(string);

        assertEquals(0, result.size());

    }

    @Test
    public void fromStringToStringList_stringWithWhiteSpaces_returnsEmptyList() {
        String string = "     ";

        List<String> result = Converters.fromStringToStringList(string);

        assertEquals(0, result.size());
    }

    @Test
    public void fromStringToStringList_givenString_returnsLikeGivenList() {
        String string = "item1, item2, item3, item4, item5, item6, item7, item8, item9";

        List<String> expected = Arrays.asList("item1",
                "item2",
                "item3",
                "item4",
                "item5",
                "item6",
                "item7",
                "item8",
                "item9");

        List<String> result = Converters.fromStringToStringList(string);

        assertEquals(expected, result);
    }

    @Test
    public void fromStringToLanguageList_emptyString_returnsEmptyList() {
        String string = "";

        List<Language> result = Converters.fromStringToLanguageList(string);

        assertEquals(0, result.size());
    }

    @Test
    public void fromStringToLanguageList_stringWithWhiteSpace_returnsEmptyList() {
        String string = "    ";

        List<Language> result = Converters.fromStringToLanguageList(string);

        assertEquals(0, result.size());
    }

    @Test
    public void fromStringToLanguageList_givenString_returnsLikeGivenList() {
        String string = "item1, item2, item3";

        Language item1 = new Language();
        item1.name = "item1";

        Language item2 = new Language();
        item2.name = "item2";

        Language item3 = new Language();
        item3.name = "item3";

        List<Language> expected = Arrays.asList(item1, item2, item3);

        List<Language> result = Converters.fromStringToLanguageList(string);

        assertEquals(expected.size(), result.size());
        for (int i = 0; i < result.size(); i += 1) {
            assertEquals(expected.get(i).name, result.get(i).name);
        }
    }

    @Test
    public void fromLanguageListToString_emptyList_returnsEmptyString() {
        List<Language> languageList = Collections.emptyList();

        String result = Converters.fromLanguageListToString(languageList);

        assertEquals("", result);
    }

    @Test
    public void fromLanguageListToString_languageListOf10_returnsLikeGivenString() {
        List<Language> stringList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Language item = new Language();
            item.name = "item" + (i + 1);
            stringList.add(item);
        }

        String expected = "item1, item2, item3, item4, item5, item6, item7, item8, item9, item10";

        String result = Converters.fromLanguageListToString(stringList);

        assertEquals(expected, result);
    }
}
