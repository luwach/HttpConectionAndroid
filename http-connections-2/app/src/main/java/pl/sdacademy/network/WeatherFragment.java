package pl.sdacademy.network;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import pl.sdacademy.network.dto.Weather;

public class WeatherFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView cityText;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);

        cityText = (TextView) rootView.findViewById(R.id.city);
        textView = (TextView) rootView.findViewById(R.id.text_view);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light
        );

        onRefresh();

        return rootView;
    }

    @Override
    public void onRefresh() {
        new GetDataTask().execute();
    }

    private class GetDataTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                return getData();
            } catch (IOException e) {
                e.printStackTrace();
                return "Wystąpił błąd";
            }
        }

        @Override
        protected void onPostExecute(String data) {
            try {
                WeatherFragment.this.parseAndShowJsonData(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private String getData() throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?units=metric&id=3099424&APPID=f085d662f1e49841cd969130deda1b49");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return readStream(in);
        } finally {
            urlConnection.disconnect();
        }
    }

    private String readStream(InputStream stream) throws IOException {
        return IOUtils.toString(stream, StandardCharsets.UTF_8.name());
    }

    private void parseAndShowJsonData(String data) throws JSONException {
        if (TextUtils.isEmpty(data)) {
            return;
        }

        Gson gson = new Gson();

        Weather weather = gson.fromJson(data, Weather.class);

        String name = weather.getName();
        float temp = weather.getMain().getTemp();
        long sunrise = weather.getSys().getSunrise();
        long sunset = weather.getSys().getSunset();

        DateTime sunriseDateTime = new DateTime(sunrise * 1000);
        DateTime sunsetDateTime = new DateTime(sunset * 1000);

        Period period = new Period(sunriseDateTime, sunsetDateTime);
        PeriodFormatter formatter = new PeriodFormatterBuilder()
                .appendHours().appendSeparator(":").appendMinutes().toFormatter();

        cityText.setText(name);
        textView.setText(temp + "\n" + sunriseDateTime.toString("HH:mm") + "\n" + sunsetDateTime.toString("HH:mm") + "\n" + period.toString(formatter));
    }
}
