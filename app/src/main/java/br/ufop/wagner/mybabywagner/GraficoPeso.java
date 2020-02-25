package br.ufop.wagner.mybabywagner;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class GraficoPeso extends AppCompatActivity{


        private LineChart mChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_peso);

        mChart = (LineChart) findViewById(R.id.linechart);
  /*      mChart.setOnChartGestureListener(GraficoPeso.this);
        mChart.setOnChartValueSelectedListener(GraficoPeso.this);

*/
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();
        for (int i = 0; i < SharedResources.getInstance().getBaby().getPeso().size(); i++){
            yValues.add(new Entry (i, SharedResources.getInstance().getBaby().getPeso().get(i).getPeso()));
        }

        LineDataSet set1 =new LineDataSet(yValues, "Peso");

        set1.setFillAlpha(110);
        set1.setColor(Color.GREEN);
        set1.setLineWidth(3f);


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);

        mChart.setData(data);


        String[] values = new String[SharedResources.getInstance().getBaby().getPeso().size()];
        XAxis xAxis = mChart.getXAxis();
        for (int i = 0; i < SharedResources.getInstance().getBaby().getPeso().size(); i++){
            values[i] = SharedResources.getInstance().getBaby().getPeso().get(i).getData();
        }
        xAxis.setValueFormatter(new MyXAxisValueFormatter(values));
     //   xAxis.setGranularity(1f);
    }

    public class MyXAxisValueFormatter implements IAxisValueFormatter{
        private String [] mValues;

        public MyXAxisValueFormatter(String[] values){
            this.mValues = values;
        }


        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int)value];
        }
    }
}


