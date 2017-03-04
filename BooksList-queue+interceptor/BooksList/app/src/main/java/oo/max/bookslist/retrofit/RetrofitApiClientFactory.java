package oo.max.bookslist.retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiClientFactory {

    public BookApiClient create() {

        //blok odpowiedzialny za dodanie logowania requestów
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //uwaga, może być wiele interceptorów!
        builder.addInterceptor(httpLoggingInterceptor);
        builder.addInterceptor(createRequestInterceptor());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://91.134.143.223:9000/") //adres serwera, można wyjąć do stałej,
                                                        // albo pola w konfiguracji
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        return retrofit.create(BookApiClient.class); // BookApiClient - klasa z retrofita
    }

    private Interceptor createRequestInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                //tutaj możemy manipulować requestem, dodawać nowe nagłówki itp.
                request = request.newBuilder()
                        .addHeader("X-BAASBOX-APPCODE", "1234567890")
                        .build();

                Response response = chain.proceed(request); //w tym miejscu faktycznie wywołujemy request

                //tutaj możemy podejrzeć response

                return response; // zwracamy request dalej
            }
        };
    }

}
