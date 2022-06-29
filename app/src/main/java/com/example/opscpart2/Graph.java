package com.example.opscpart2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

        double noItemPer = MainActivity.holder.getNumberOfItems();
        double noGoalPer = MainActivity.holder.getGoal();

        double percent =noItemPer/noGoalPer;
        double roundDbl = Math.round(percent*100.0)/100.0;

        TextView textView = (TextView)findViewById(R.id.textView4);
        textView.setText(Double.toString(roundDbl * 100) + "%");
        //sets the total % of the collection





        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToChart();
            } // sets an OnClickListener to run a method
        });



     Back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            goToDetail();
        } // sets an OnClickListener to run a method
    });
}

    public void goToDetail() {

        startActivity(new Intent(Graph.this, MainActivity.class)); // will sigh the user out and take them to the login screen

    }

    private void addToChart(){

             chart.addPieSlice(new PieModel("integer 1", (MainActivity.holder.getNumberOfItems()), Color.parseColor("#FFA726")));
             chart.addPieSlice(new PieModel("integer 1", MainActivity.holder.getGoal()-MainActivity.holder.getNumberOfItems(), Color.parseColor("#66BB6A")));
                // sets the vlaue of the graph

             chart.startAnimation();
             click.setClickable(false);

             //Youtube.com. 2022. How to implement simple Pie Chart in app | Android Studio | Java | Android App Development. [online] Available at: <https://www.youtube.com/watch?v=Ge11WEKCCPE> [Accessed 29 June 2022].

    }

    }

