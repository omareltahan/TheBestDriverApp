package com.thebest.resturant.Networking;

import com.thebest.resturant.Models.DateModel;
import com.thebest.resturant.Models.MenuModel;
import com.thebest.resturant.Models.Requests.AddMenuItem;
import com.thebest.resturant.Models.Requests.AddProductRequest;
import com.thebest.resturant.Models.Requests.GetCitiesMainModel;
import com.thebest.resturant.Models.Requests.GetDistrictsMainModel;
import com.thebest.resturant.Models.Requests.RequestLogin;
import com.thebest.resturant.Models.Requests.RequestRegister;
import com.thebest.resturant.Models.Requests.RequestResturantReports;
import com.thebest.resturant.Models.Responses.FoodMainItemResponse;
import com.thebest.resturant.Models.Responses.FoodShopsCategoriesProductsResponse;
import com.thebest.resturant.Models.Responses.MainLoginResponse;
import com.thebest.resturant.Models.Responses.MainResponseOrders;
import com.thebest.resturant.Models.Responses.MyMenusItemModel;
import com.thebest.resturant.Models.Responses.MyMenusMainResponse;
import com.thebest.resturant.Models.Responses.OldOrdersMainResponse;
import com.thebest.resturant.Models.StatusModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface StoreAPIInterface {
    @POST("AddMenu")
    Call<MenuModel> AddMenu(@Body AddMenuItem request);
    @POST("DeleteMenu/{ID}")
    Call<String> DeleteMenu(@Path("ID") int ID);
    @POST("DeleteProduct/{ID}")
    Call<String> DeleteProduct(@Path("ID") int ID);
    @GET("DistrictsById/{ID}")
    Call<GetDistrictsMainModel> DistrictsById(@Path("ID") String ID);
    @GET("AllCategories")
    Call<FoodMainItemResponse> GetAllCategories();
    @GET("markettypes")
    Call<FoodMainItemResponse> GetAllMarketsTypes();
    @GET("markettypes")
    Call<FoodMainItemResponse> GetAllShabraTypes();
    @GET("allCities")
    Call<GetCitiesMainModel> GetALLCities();
    @POST("AddProduct")
    Call<String> AddProduct(@Body AddProductRequest request);
    @POST("RestaurantsReports")
    Call<MainResponseOrders> GetReports(@Body RequestResturantReports request);
    @POST("login")
    Call<MainLoginResponse> MakeLogin(@Body RequestLogin request);
    @POST("register")
    Call<MainLoginResponse> MakeRegister(@Body RequestRegister request);
    @GET("OldOrders")
    Call<OldOrdersMainResponse> ShowOldOrders();
    @GET("MyMenus")
    Call<MyMenusMainResponse> ShowMyMenus();
    @GET("NewOrders")
    Call<OldOrdersMainResponse> ShowNewOrders();
    @GET("MyPlace")
    Call<RequestLogin> GetMyPlace();
    @POST("ScheduleTrip/{ID}")
    Call<String> setScheduleTrip(@Path("ID") int ID,@Body DateModel model);
    @POST("ChangeOrderStatus/{ID}")
    Call<String> ChangeOrderStatus(@Path("ID") int ID, @Body StatusModel statusModel);
    @GET("CancelOrder/{ID}")
    Call<String> CancelOrderStatus(@Path("ID") int ID);
    @GET("MenuItems/{ID}")
    Call<FoodShopsCategoriesProductsResponse> GetMenuItems(@Path("ID") int ID);



    @GET("UpdateMenu/{ID}")
    Call<MenuModel> MakeEditCategory(@Path("ID") int ID,@Body AddMenuItem request);


}