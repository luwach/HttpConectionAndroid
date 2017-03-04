package oo.max.httpexamples;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiClientFactory {

    public RetrofitApiClient create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://91.134.143.223:9000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(RetrofitApiClient.class);
    }

}
