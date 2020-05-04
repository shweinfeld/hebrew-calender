package weinfeld.dateConverter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface dateService {

    @GET("/converter/?cfg=json&g2h=1")
    Call<dateHebrew> getDate(@Query("gy") String year,
                             @Query("gm") String month,
                             @Query("gd") String day);
}
