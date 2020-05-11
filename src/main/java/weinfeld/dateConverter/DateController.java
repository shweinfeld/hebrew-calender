package weinfeld.dateConverter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DateController {

    private dateService service;

    public DateController(dateService service) {
        this.service = service;
    }

    public void requestData(String year, String month, String day) {
        service.getDate(year, month, day).enqueue(new Callback<dateHebrew>() {
            @Override
            public void onResponse(Call<dateHebrew> call, Response<dateHebrew> response) {

                dateHebrew date = response.body();
                assert date != null;
                DateFrame.hebDate.setText(date.hd + " " + date.hm + " " + date.hy);
                DateFrame.hebHebDate.setText(date.hebrew);
                StringBuilder events = new StringBuilder();
                events.append("<html>");
                if (date.events != null) {
                    for (String event: date.events)
                    {
                        events.append(event).append("<br/>");
                    }
                }
                events.append("</html>");
                DateFrame.hebEvents.setText(events.toString());

            }

            @Override
            public void onFailure(Call<dateHebrew> call, Throwable t) {

                t.printStackTrace();

            }
        });


    }
}
