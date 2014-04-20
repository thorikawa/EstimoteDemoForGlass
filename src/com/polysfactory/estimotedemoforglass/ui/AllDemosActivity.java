package com.polysfactory.estimotedemoforglass.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

/**
 * Shows all available demos.
 * 
 * @author wiktor@estimote.com (Wiktor Gworek)
 */
public class AllDemosActivity extends Activity {

    private CardScrollView menuListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menuListView = new CardScrollView(this);
        menuListView.setHorizontalScrollBarEnabled(true);
        menuListView.setAdapter(new CardAdapter());
        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(AllDemosActivity.this, ListBeaconsActivity.class);
                    intent.putExtra(ListBeaconsActivity.EXTRAS_TARGET_ACTIVITY, DistanceBeaconActivity.class.getName());
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(AllDemosActivity.this, ListBeaconsActivity.class);
                    intent.putExtra(ListBeaconsActivity.EXTRAS_TARGET_ACTIVITY, NotifyDemoActivity.class.getName());
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(AllDemosActivity.this, ListBeaconsActivity.class);
                    intent.putExtra(ListBeaconsActivity.EXTRAS_TARGET_ACTIVITY,
                            CharacteristicsDemoActivity.class.getName());
                    startActivity(intent);
                }
            }
        });
        setContentView(menuListView);
    }

    @Override
    protected void onResume() {
        menuListView.activate();
        super.onResume();
    }

    @Override
    protected void onPause() {
        menuListView.deactivate();
        super.onPause();
    }

    class CardAdapter extends CardScrollAdapter {

        String menus[] = new String[] { "Distance Demo", "Notify Demo", "Change Beacon's Minor Demo" };

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Object getItem(int position) {
            return menus[position];
        }

        @Override
        public int getPosition(Object item) {
            return -1;
        }

        @Override
        public View getView(int position, View arg1, ViewGroup arg2) {
            Card card = new Card(AllDemosActivity.this);
            card.setText(menus[position]);
            return card.getView();
        }
    }
}
