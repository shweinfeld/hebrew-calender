package weinfeld.dateConverter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;

public class DateController {

    private dateService service;
    private JLabel hebDate;
    private JLabel hebHebDate;
    private JLabel hebEvents;

    public DateController(dateService service, JLabel hebDate, JLabel hebHebDate, JLabel hebEvents) {
        this.service = service;
        this.hebDate = hebDate;
        this.hebHebDate = hebHebDate;
        this.hebEvents = hebEvents;
    }

    public void requestData(String year, String month, String day) {
        service.getDate(year, month, day).enqueue(new Callback<dateHebrew>() {
            @Override
            public void onResponse(Call<dateHebrew> call, Response<dateHebrew> response) {

                dateHebrew date = response.body();
                assert date != null;
                hebDate.setText(date.hd + " " + date.hm + " " + date.hy);
                hebHebDate.setText(date.hebrew);
                StringBuilder events = new StringBuilder();
                events.append("<html>");
                if (date.events != null) {
                    for (String event: date.events)
                    {
                        events.append(event).append("<br/>");
                    }
                }
                events.append("</html>");
               hebEvents.setText(events.toString());

            }

            @Override
            public void onFailure(Call<dateHebrew> call, Throwable t) {

                t.printStackTrace();

            }
        });


    }
}
