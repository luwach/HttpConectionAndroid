package pl.sdacademy.network.dto;

import com.google.gson.annotations.SerializedName;

public class WeatherMain {

    private Float temp;
    private Integer pressure;

    @SerializedName("temp_min")
    private Float tempMin;

    @SerializedName("temp_max")
    private Float tempMax;

    public Float getTemp() {
        return temp;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Float getTempMin() {
        return tempMin;
    }

    public void setTempMin(Float tempMin) {
        this.tempMin = tempMin;
    }

    public Float getTempMax() {
        return tempMax;
    }

    public void setTempMax(Float tempMax) {
        this.tempMax = tempMax;
    }
}
