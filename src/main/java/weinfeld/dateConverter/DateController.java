package weinfeld.dateConverter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DateController {

    private dateService service;
    private DateView view;

    public DateController(dateService service, DateView view) {
        this.service = service;
        this.view = view;
    }

    public void requestData(String year, String month, String day) {
        service.getDate(year, month, day).enqueue(new Callback<dateHebrew>() {
            @Override
            public void onResponse(Call<dateHebrew> call, Response<dateHebrew> response) {

                dateHebrew date = response.body();
                view.setDate(date);

            }

            @Override
            public void onFailure(Call<dateHebrew> call, Throwable t) {

                t.printStackTrace();

            }
        });


    }
}
