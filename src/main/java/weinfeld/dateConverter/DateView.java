package weinfeld.dateConverter;

import javax.swing.*;
import java.awt.*;

public class DateView extends JComponent {

    public void setDate(dateHebrew date) {
        this.date = date;
        repaint();
    }

    private dateHebrew date;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (date == null) {
            return;
        }

        Font f = new Font("Dialog", Font.PLAIN, 16);
        g.setFont(f);
        //Hebrew Date in English
        g.drawString(date.hd + " " + date.hm + " " + date.hy, getWidth()/4, 25);

        //Hebrew Date in Hebrew
        g.drawString(date.hebrew, getWidth()/4, 50);

        //Hebrew Date Events
        int height = 75;

        if (date.events != null){
            for (String event: date.events) {
                g.drawString(event, getWidth()/4, height);
                height += 25;
            }
        }

    }

}
