package eu.ase.ro.damapp.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@SuppressLint("ViewConstructor")
public class ChartView extends View {
    private Map<String, Integer> source;
    private Paint paint;
    private List<String> labels;
    private Random random;

    public ChartView(Context context, Map<String, Integer> source) {
        super(context);
        this.source = source;
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        labels = new ArrayList<>(source.keySet());
        random = new Random();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (source != null && source.size() > 0) {

            paint.setStrokeWidth((float) (getHeight() * 0.01));

            //calculam distanta minima pe o lasam fata de margini
            float paddW = (float) (getWidth() * 0.1);
            float paddH = (float) (getHeight() * 0.1);

            //calculam suprafata efectiva de lucru, pentru desenarea graficului. Scadem toate cele 4 margini.
            float availableWidth = getWidth() - 2 * paddW;
            float availableHeight = getHeight() - 2 * paddH;

            //desenam axele graficului
            canvas.drawLine(paddW, paddH, paddW, paddH + availableHeight, paint);
            canvas.drawLine(paddW, paddH + availableHeight, paddW + availableWidth, paddH + availableHeight, paint);

            //calculam valoarea maxima, o vom folosi ca sa putem calcula inaltimea fiecarei bare
            double maxValue = calculateMaxim();
            //calculam grosimea unei bare(din dimensiune disponibila, ar trebui sa scadem cate un spatiu ptr fiecare bara, la final impartind la nr total de valori
            float widthOfElement = availableWidth / source.size();
            for (int i = 0; i < labels.size(); i++) {
                //calculam o culoare aleator
                int color = Color.argb(100,
                        1 + random.nextInt(254),
                        1 + random.nextInt(254),
                        1 + random.nextInt(254));
                paint.setColor(color);

                float x1 = paddW + i * widthOfElement;
                float y1 = (float) ((1 - source.get(labels.get(i)) / maxValue) * availableHeight + paddH);
                float x2 = x1 + widthOfElement;
                float y2 = paddH + availableHeight;

                canvas.drawRect(x1, y1, x2, y2, paint);
                drawLabel(canvas, x1, widthOfElement, paddH, availableHeight, labels.get(i));
            }
        }
    }

    private void drawLabel(Canvas canvas, float x1, float widthOfElement, float paddH, float availableHeight, String label) {
        paint.setColor(Color.BLACK);
        paint.setTextSize((float) (0.15 * widthOfElement));
        float x = x1 + widthOfElement / 2;
        float y = 1 / 2 * paddH + availableHeight;
        canvas.rotate(270, x, y);
        canvas.drawText(label + " - " + source.get(label), x, y, paint);
        canvas.rotate(-270, x, y); // readucem canvasul in pozitia initiala. ce se scrie inainte de rotatire ramane in aceeasi pozitie.
    }

    private double calculateMaxim() {
        double max = 0;
        for (double value : source.values()) {
            if (max < value) {
                max = value;
            }
        }
        return max;
    }
}
