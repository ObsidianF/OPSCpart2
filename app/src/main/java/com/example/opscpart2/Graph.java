package com.example.opscpart2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class Graph extends AppCompatActivity {
    private Button Back;
    private Button click;
    private PieChart chart;
    private int i1 = 40;
    private int i2 = 4;
    private int i3 = MainActivity.holder.getGoal();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        click = findViewById(R.id.btnclick);
        Back = findViewById(R.id.btnBack);
        chart = findViewById(R.id.pie_chart);


        int percent =(MainActivity.holder.getNumberOfItems()/ MainActivity.holder.getGoal())*100;

        TextView textView = (TextView)findViewById(R.id.textView4);
        textView.setText(Integer.toString(percent));





        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToChart();
            } // sets an OnClickListener to run a method
        });



     Back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            goBack();
        } // sets an OnClickListener to run a method
    });
}

    public void goBack() {

        startActivity(new Intent(Graph.this, CollectionDetailActivity.class)); // will sigh the user out and take them to the login screen

    }

    private void addToChart(){
         //for (int i = 0; i < 5;i++) {
             chart.addPieSlice(new PieModel("integer 1", (MainActivity.holder.getNumberOfItems()), Color.parseColor("#FFA726")));
             chart.addPieSlice(new PieModel("integer 1", MainActivity.holder.getGoal()-MainActivity.holder.getNumberOfItems(), Color.parseColor("#66BB6A")));
             //chart.addPieSlice(new PieModel("integer 2",i2, Color.parseColor("#66BB6A")));
             //chart.addPieSlice(new PieModel("integer 2",i3, Color.parseColor("##FF0000")));


             chart.startAnimation();
             click.setClickable(false);
       //  }
    }

    }

