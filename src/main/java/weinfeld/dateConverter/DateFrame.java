package weinfeld.dateConverter;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Properties;

public class DateFrame extends JFrame {

    JButton convertButton;
    JPanel pickDate;
    JDatePickerImpl datePicker;
    JPanel displayDate;
    protected static JLabel hebDate;
    protected static JLabel hebHebDate;
    protected static JLabel hebEvents;

    public DateFrame() {
        setSize(600, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hebrew Date Converter");
        setLayout(new BorderLayout());

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "month");
        p.put("text.year", "year");

        UtilDateModel model = new UtilDateModel();
        model.setSelected(true);
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
        convertButton = new JButton("Convert");
        convertButton.addActionListener(actionEvent-> { getDate(); });

        pickDate = new JPanel();
        pickDate.add(datePicker);
        pickDate.add(convertButton);
        add(pickDate, BorderLayout.WEST);

        displayDate = new JPanel();
        displayDate.setLayout(new BoxLayout(displayDate, BoxLayout.Y_AXIS));
        hebDate = new JLabel();
        hebHebDate = new JLabel();
        hebEvents = new JLabel();
        hebDate.setFont(new Font("Calibri", Font.PLAIN, 17));
        hebHebDate.setFont(new Font("Calibri", Font.PLAIN, 17));
        hebEvents.setFont(new Font("Calibri", Font.PLAIN, 17));
        displayDate.add(hebDate);
        displayDate.add(hebHebDate);
        displayDate.add(hebEvents);
        add(displayDate,BorderLayout.CENTER);



    }

    private void getDate() {

        int selectedYear = datePicker.getModel().getYear();
        int selectedMonth = datePicker.getModel().getMonth() + 1;
        int selectedDay = datePicker.getModel().getDay();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.hebcal.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dateService service = retrofit.create(dateService.class);

        DateController controller = new DateController(service);
        controller.requestData(String.valueOf(selectedYear), String.valueOf(selectedMonth), String.valueOf(selectedDay));

    }

    public static void main(String[] args) {
        new DateFrame().setVisible(true);
    }
}
