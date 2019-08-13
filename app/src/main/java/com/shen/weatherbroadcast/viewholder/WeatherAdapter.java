package com.shen.weatherbroadcast.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shen.weatherbroadcast.R;
import com.shen.weatherbroadcast.Weather;

import java.util.List;


public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    List<Weather.ResultBean.FutureBean> futureWeather;

    public WeatherAdapter(List<Weather.ResultBean.FutureBean> futureWeathers) {
        this.futureWeather = futureWeathers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_future_detail, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Weather.ResultBean.FutureBean futureBean = futureWeather.get(position);
        holder.futureDate.setText("日期："+futureBean.getDate());
        holder.futureTemprature.setText("温度："+futureBean.getTemperature());
        holder.futureWeather.setText("天气："+futureBean.getWeather());
        holder.futureWind.setText("风向："+futureBean.getDirect());
        holder.futureWindForceDay.setText("白日风力："+futureBean.getWid().getDay());
        holder.futureWindForceNight.setText("晚上风力："+futureBean.getWid().getNight());
    }

    @Override
    public int getItemCount() {
        return futureWeather.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView futureDate;
        TextView futureTemprature;
        TextView futureWeather;
        TextView futureWind;
        TextView futureWindForceDay;
        TextView futureWindForceNight;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            futureDate = itemView.findViewById(R.id.future_date);
            futureTemprature = itemView.findViewById(R.id.future_temprature);
            futureWeather = itemView.findViewById(R.id.future_weather);
            futureWind = itemView.findViewById(R.id.future_wind);
            futureWindForceDay = itemView.findViewById(R.id.future_wind_force_day);
            futureWindForceNight = itemView.findViewById(R.id.future_wind_night);
        }
    }
}
