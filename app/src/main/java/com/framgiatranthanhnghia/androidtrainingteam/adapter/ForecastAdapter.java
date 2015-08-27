package com.framgiatranthanhnghia.androidtrainingteam.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgiatranthanhnghia.androidtrainingteam.R;
import com.framgiatranthanhnghia.androidtrainingteam.api.WeatherAPI;
import com.framgiatranthanhnghia.androidtrainingteam.application.MainApplication;
import com.framgiatranthanhnghia.androidtrainingteam.model.DayWeatherData;
import com.framgiatranthanhnghia.androidtrainingteam.model.WeatherData;
import com.framgiatranthanhnghia.androidtrainingteam.utils.ConvertUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by FRAMGIA\tran.thanh.nghia on 27/08/2015.
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    private List<DayWeatherData> mList;

    public ForecastAdapter(List<DayWeatherData> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        ViewHolder viewholder = new ViewHolder(v);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Context context= MainApplication.get();
        DayWeatherData dayWeatherData= mList.get(position);
        Date date= ConvertUtils.convertUnixToDate(dayWeatherData.dt);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("EEEE");
        holder.txtDayOfWeek.setText(simpleDateFormat.format(date));
        simpleDateFormat=new SimpleDateFormat("MMMM dd");
        holder.txtShortDate.setText(simpleDateFormat.format(date));
        holder.txtTemp.setText((dayWeatherData.temp==null?
                        "" :ConvertUtils.convertKelvinToCelsius(dayWeatherData.temp.min)+"/"+ConvertUtils.convertKelvinToCelsius(dayWeatherData.temp.max))
                    + context.getString(R.string.celsius)
        );
        holder.txtHumidity.setText((int)dayWeatherData.humidity+context.getString(R.string.percent));
        if(dayWeatherData.weather!=null && dayWeatherData.weather.size()>0){
            WeatherData weatherData=dayWeatherData.weather.get(0);
            if(weatherData.icon!=null){
                String url= WeatherAPI.getIconWeather(weatherData.icon);
                ImageLoader.getInstance().displayImage(url,holder.imgWeather);
            }
        }

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtDayOfWeek;
        public TextView txtShortDate;
        public TextView txtTemp;
        public TextView txtHumidity;

        public ImageView imgWeather;

        public ViewHolder(View itemView) {
            super(itemView);
            txtDayOfWeek = (TextView) itemView.findViewById(R.id.txt_day_of_week);
            txtShortDate = (TextView) itemView.findViewById(R.id.txt_short_date);
            txtTemp = (TextView) itemView.findViewById(R.id.txt_temp);
            txtHumidity = (TextView) itemView.findViewById(R.id.txt_humidity);

            imgWeather=(ImageView)itemView.findViewById(R.id.img_weather);
        }
    }
}
