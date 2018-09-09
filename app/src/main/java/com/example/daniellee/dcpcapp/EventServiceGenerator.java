package com.example.daniellee.dcpcapp;

import android.text.TextUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
* Created by Paul Crane on 1/09/18.
*
* @author Paul Crane <paul@crane.net.nz>
*/
public class EventServiceGenerator {

	private static final String API_BASE_URL = "http://192.168.1.198:8080/api/v1/";

	private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

	private static Retrofit.Builder builder =
			new Retrofit.Builder()
					.baseUrl(API_BASE_URL)
					.addConverterFactory(GsonConverterFactory.create());

	private static Retrofit retrofit = builder.build();

	public static <S> S createService(Class<S> serviceClass, final String authToken) {

		if (!TextUtils.isEmpty(authToken)) {
			AuthenticationInterceptor interceptor =  new AuthenticationInterceptor(authToken);

			if (!httpClient.interceptors().contains(interceptor)) {
				httpClient.addInterceptor(interceptor);

				builder.client(httpClient.build());
				retrofit = builder.build();
			}

		}

		return retrofit.create(serviceClass);
	}
}
