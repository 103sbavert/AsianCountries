package com.sbeve.asiancountries.data.retrofit;

import com.sbeve.asiancountries.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RetrofitInit {
    private static final String BASE_URL = "https://restcountries.eu/rest/v2/region/";

    public static AccessApi accessApi = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AccessApi.class);

    public interface AccessApi {
        @GET("{region}")
        Call<List<Country>> getCountriesByRegion(@Path("region") String region);
    }
}
