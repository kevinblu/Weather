package com.shen.weatherbroadcast;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shen.weatherbroadcast.viewholder.WeatherAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static String key = "这个是自己的key";

    @BindView(R.id.btn_quest_for_weather)
    Button btnQuestForWeather;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.cityName)
    TextView cityName;
    @BindView(R.id.weather_detail)
    TextView weatherDetail;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.realtime_temp)
    TextView realtimeTemp;
    @BindView(R.id.realtime_humidity)
    TextView realtimeHumidity;
    @BindView(R.id.realtime_direction)
    TextView realtimeDirection;
    @BindView(R.id.realtime_wind_force)
    TextView realtimeWindForce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btnQuestForWeather.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apis.juhe.cn/simpleWeather/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        String city = editText.getText().toString().trim();
        Call<Weather> call = service.getWeather(city, key);
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                //tvToShow.setText(response.body().getReason());
                showAllWeather(response);
                Log.d(" connect", response.body().toString());
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.d("request", " failed");
            }
        });
    }

    private void showAllWeather(Response<Weather> response) {
        assert response.body() != null;
        Weather.ResultBean result = response.body().getResult();
        Weather.ResultBean.RealtimeBean realtime = result.getRealtime();
        cityName.setText("城市："+result.getCity());
        weatherDetail.setText("天气："+realtime.getInfo());
        realtimeWindForce.setText("风力："+realtime.getPower());
        realtimeTemp.setText("温度："+realtime.getTemperature());
        realtimeDirection.setText("风向："+realtime.getDirect());
        realtimeHumidity.setText("湿度：" +realtime.getHumidity());

        List<Weather.ResultBean.FutureBean> futureWeathers = result.getFuture();

        WeatherAdapter adapter = new WeatherAdapter(futureWeathers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
