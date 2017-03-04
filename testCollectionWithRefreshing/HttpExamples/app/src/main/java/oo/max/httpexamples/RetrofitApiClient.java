package oo.max.httpexamples;

import okhttp3.ResponseBody;
import oo.max.httpexamples.model.TestEntry;
import oo.max.httpexamples.model.TestResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitApiClient {

    @GET("/plugin/test.getTestCollection")
    @Headers({
            "X-BAASBOX-APPCODE: 1234567890"
    })
    Call<TestResponse> getTestCollection();

    @POST("/plugin/test.createTestEntry")
    @Headers({
            "X-BAASBOX-APPCODE: 1234567890",
            "Content-type: application/json"
    })
    Call<ResponseBody> createTestEntry(@Body TestEntry testEntry);

}
