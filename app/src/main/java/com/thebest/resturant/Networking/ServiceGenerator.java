package com.thebest.resturant.Networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thebest.resturant.AppController;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Project ${PROJECT}
 * Created by asamy on 4/1/2018.
 */

public class ServiceGenerator {
    // No need to instantiate this class.
    private ServiceGenerator() {
    }

    private static HttpLoggingInterceptor logging =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);


//    public static <S> S createService(Class<S> serviceClass, String baseUrl, final OAuthParameters oAuthParams) {
//        Retrofit.Builder builder = new Retrofit.Builder();
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(new OAuthInterceptor(oAuthParams)).build();
//        builder.client(client);
//        builder.baseUrl(baseUrl);
//        builder.addConverterFactory(GsonConverterFactory.create());
//        Retrofit adapter = builder.build();
//        return adapter.create(serviceClass);
//    }


    public static <S> S createService(Class<S> serviceClass, String baseUrl, String userToken) {
        Retrofit.Builder builder = new Retrofit.Builder();

        final String basic = "";

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("Authorization", basic)
                        .addHeader("Accept", "application/json");

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        clientBuilder.connectTimeout(50, TimeUnit.SECONDS);
        clientBuilder.readTimeout(50, TimeUnit.SECONDS);

        clientBuilder.addInterceptor(logging);

        OkHttpClient client = clientBuilder.build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        builder.baseUrl(baseUrl);
        builder.client(client);
        builder.addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit adapter = builder.build();
        return adapter.create(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl, boolean value) {

        Retrofit.Builder builder = new Retrofit.Builder();
        final String authToken = Credentials.basic("api_access", "$uper12345");

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        Interceptor headerAuthorizationInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Headers headers = request.headers().newBuilder().add("Authorization", authToken).build();
                request = request.newBuilder().headers(headers).build();
                return chain.proceed(request);
            }
        };
        clientBuilder.addInterceptor(headerAuthorizationInterceptor);
        clientBuilder.connectTimeout(50, TimeUnit.SECONDS);
        clientBuilder.readTimeout(50, TimeUnit.SECONDS);

//        clientBuilder.addInterceptor(logging);

        OkHttpClient client = clientBuilder.build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        builder.baseUrl(baseUrl);
        builder.client(client);
        builder.addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit adapter = builder.build();
        return adapter.create(serviceClass);
    }


//    public static <S> S createService(Class<S> serviceClass,String baseUrl){
//        final String authToken = Credentials.basic("api_access", "$uper12345");
//
//        Retrofit.Builder builder = new Retrofit.Builder();
//        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
//        clientBuilder.addInterceptor(logging);
//
//        try {
//            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//            trustManagerFactory.init((KeyStore) null);
//            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
//            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
//                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
//            }
//            X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
//            SSLContext sslContext = SSLContext.getInstance("SSL");
//            sslContext.init(null, new TrustManager[] { trustManager }, null);
//            clientBuilder.sslSocketFactory(new TLSSocketFactory(),(X509TrustManager) trustManager);
//        } catch (KeyManagementException e) {
//            Log.e("DataIs", "Failed to get Areas"+ e.toString());
////            Toast.makeText(,"Check Internet And try again",Toast.LENGTH_LONG).show();
//
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            Log.e("DataIs", "Failed to get Areas"+ e.toString());
////            Toast.makeText(,"Check Internet And try again",Toast.LENGTH_LONG).show();
//            e.printStackTrace();
//        } catch (KeyStoreException e) {
//            Log.e("DataIs", "Failed to get Areas"+ e.toString());
////            Toast.makeText(,"Check Internet And try again",Toast.LENGTH_LONG).show();
//
//            e.printStackTrace();
//        }
//        clientBuilder.connectTimeout(50, TimeUnit.SECONDS);
//        clientBuilder.readTimeout(50,TimeUnit.SECONDS);
//        OkHttpClient client = clientBuilder.build();
//        builder.baseUrl(baseUrl);
//        builder.addConverterFactory(GsonConverterFactory.create());
//        builder.client(client);
//        Retrofit adapter = builder.build();
//        return adapter.create(serviceClass);
//    }
public static <S> S createService(Class<S> serviceClass, String baseUrl){
    Retrofit.Builder builder = new Retrofit.Builder();
    OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
    Interceptor headerAuthorizationInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Headers headers = request.headers().newBuilder().add("Authorization", "Bearer "+ AppController.accesstoken).build();
            request = request.newBuilder().headers(headers).build();
            return chain.proceed(request);
        }
    };
    clientBuilder.addInterceptor(headerAuthorizationInterceptor);
    clientBuilder.connectTimeout(50, TimeUnit.SECONDS);
    clientBuilder.readTimeout(50, TimeUnit.SECONDS);
    OkHttpClient client = clientBuilder.build();


    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    builder.baseUrl(baseUrl);
    builder.client(client);
    builder.addConverterFactory(GsonConverterFactory.create(gson));
    Retrofit adapter = builder.build();
    return adapter.create(serviceClass);
}

}

class BearerAuthInterceptor implements Interceptor {

    private String token;

    public BearerAuthInterceptor(String token) {
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticatedRequest = request.newBuilder()
                .header("Authorization", "Bearer" + token).build();
        return chain.proceed(authenticatedRequest);
    }

}
