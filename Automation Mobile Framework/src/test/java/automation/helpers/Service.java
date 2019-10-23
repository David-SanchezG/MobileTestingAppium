package automation.helpers;

import automation.helpers.domain.Animal;
import automation.helpers.domain.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.io.*;

public class Service {

    public static String API_URL;
    public Retrofit retrofit;

    public Service() {
        String runOn = System.getProperty("runOn");
        if (runOn.equals("local")) {
            API_URL = "http://0.0.0.0:3000/";
        } else {
            API_URL = "http://serverIP:3000/";
        }
        // Create a very simple REST adapter which points the my API.
        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public interface GetUserI {
        @GET("/user/{status}")
        Call<User> user(@Path("status") String status);
    }

    public interface GetAnimalI {
        @GET("/animal")
        Call<Animal> code();
    }

    public static void main(String... args) throws IOException {
        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        GetUserI getUser = retrofit.create(GetUserI.class);
        // Create a call instance for looking up Retrofit contributors.
        Call<User> call = getUser.user("angry");

        // Fetch and print a list of the contributors to the library.
        User user = call.execute().body();
        System.out.println("And the user is..->" + user.username);
        System.out.println("And the pass is..->" + user.password);
    }

    public User getUser(String status) throws IOException {
        // Create an instance of our GitHub API interface.
        GetUserI getUser = retrofit.create(GetUserI.class);
        // Create a call instance for looking up Retrofit contributors.
        Call<User> call = getUser.user(status);

        // Fetch and print a list of the contributors to the library.
        User user = call.execute().body();
        System.out.println("user is -> " + user.username);
        return user;
    }

    public Animal getIdentIDCode() throws IOException {
        GetAnimalI animalI = retrofit.create(GetAnimalI.class);
        Call<Animal> call = animalI.code();

        Animal identIDCode = call.execute().body();
        System.out.println("IdentId code received -> " + identIDCode);
        return identIDCode;
    }

}
