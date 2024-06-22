package com.example.practica4v2;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherApi {

    private static final String TAG = "WeatherAPI";
    private static final String API_KEY = "b99a1dfc11a9cfa8d114ace852cf0321";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public static void getWeather(String teacherName, final WeatherCallback callback, Context context) {

        String url = BASE_URL + "?q=" + teacherName + "&appid=" + API_KEY;

        // Crear una nueva solicitud JSON usando Volley
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parsear la respuesta JSON para obtener la descripción del tiempo
                            JSONArray weatherArray = response.getJSONArray("weather");
                            JSONObject weatherObject = weatherArray.getJSONObject(0);
                            String weatherDescription = weatherObject.getString("description");

                            // Llamar al callback con la descripción del tiempo obtenida
                            callback.onWeatherReceived(weatherDescription);

                        } catch (JSONException e) {

                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        // Agregar la solicitud a la cola de Volley
        queue.add(jsonObjectRequest);
    }

    public interface WeatherCallback {
        void onWeatherReceived(String weatherDescription);
        void onError(String message);
    }
}
