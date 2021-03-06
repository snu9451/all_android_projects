package com.example.ch7fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    ListView listview = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        final String[] items = {"WHITE", "RED", "GREEN", "BLUE", "BLACK"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);

        listview = (ListView) findViewById(R.id.drawer_menulist);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                TextView contentTextview = (TextView) findViewById(R.id.drawer_content);

                switch (position) {
                    case 0: // WHITE
                        contentTextview.setBackgroundColor(Color.rgb(0xFF, 0xFF, 0xFF));
                        contentTextview.setTextColor(Color.rgb(0x00, 0x00, 0x00));
                        contentTextview.setText("WHITE");
                        break;
                    case 1: // RED
                        contentTextview.setBackgroundColor(Color.rgb(0xFF, 0x00, 0x00));
                        contentTextview.setTextColor(Color.rgb(0xFF, 0xFF, 0xFF));
                        contentTextview.setText("RED");
                        break;
                    case 2: // GREEN
                        contentTextview.setBackgroundColor(Color.rgb(0x00, 0xFF, 0x00));
                        contentTextview.setTextColor(Color.rgb(0x00, 0x00, 0x00));
                        contentTextview.setText("GREEN");
                        break;
                    case 3: // BLUE
                        contentTextview.setBackgroundColor(Color.rgb(0x00, 0x00, 0xFF));
                        contentTextview.setTextColor(Color.rgb(0xFF, 0xFF, 0xFF));
                        contentTextview.setText("BLUE");
                        break;
                    case 4: // BLACK
                        contentTextview.setBackgroundColor(Color.rgb(0x00, 0x00, 0x00));
                        contentTextview.setTextColor(Color.rgb(0xFF, 0xFF, 0xFF));
                        contentTextview.setText("BLACK");
                        break;
                }
            }

//            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer)
//            drawer.closeDrawer(Gravity.LEFT);
        });
    }
}