package weinfeld.dateConverter;

import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class dateServiceTest {

    @Test
    public void getDate() throws IOException {

        //given
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.hebcal.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dateService service = retrofit.create(dateService.class);

        //when
        dateHebrew date = service.getDate("2020", "05", "06").execute().body();

        //then
        assertNotNull(date);
        assertNotEquals(0, date.hy);
        assertNotNull(date.hm);
        assertNotEquals(0 ,date.hd);
        assertNotNull(Arrays.toString(date.events));
        assertNotNull(date.hebrew);
    }

}