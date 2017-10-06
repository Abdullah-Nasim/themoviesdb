package myown.themoviesdb.network;



import myown.themoviesdb.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Netaq on 10/5/2017.
 *
 * This class is responsible of configuring the Retrofit Rest Client
 */

public class RestClient {

    private static ServicesInterface servicesInterface;
    private static Retrofit retrofit;

    static {

        //Configuring the Rest Client
        setUpRestClient();
    }

    private static void setUpRestClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicesInterface = retrofit.create(ServicesInterface.class);


    }

    public static ServicesInterface getAdapter(){
        return servicesInterface;
    }

}
