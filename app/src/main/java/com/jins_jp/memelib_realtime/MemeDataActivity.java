package com.jins_jp.memelib_realtime;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import com.jins_jp.meme.*;


public class MemeDataActivity extends Activity {

    MemeLib memeLib;
    ListView dataItemListView;
    MemeDataItemAdapter dataItemAdapter;

    final MemeRealtimeListener memeRealtimeListener = new MemeRealtimeListener() {
        @Override
        public void memeRealtimeCallback(final MemeRealtimeData memeRealtimeData) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //setSupportProgressBarIndeterminateVisibility(true);
                    dataItemAdapter.updateMemeData(memeRealtimeData);
                    dataItemAdapter.notifyDataSetChanged();
                    //setSupportProgressBarIndeterminateVisibility(false);
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_meme_data);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_meme_data, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_disconnect) {
            //disconnect JINS MEME
            memeLib.disconnect();

            // By setting autoConnect false, this app won't connect automatically last connected JINS MEME.
            // Otherwise, the app start to establish the connection with it just after disconnection.
            memeLib.setAutoConnect(false);
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void init() {
        memeLib = MemeLib.getInstance();

        dataItemListView = (ListView)findViewById(R.id.data_item_list_view);
        dataItemAdapter = new MemeDataItemAdapter(this);
        dataItemListView.setAdapter(dataItemAdapter);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //Starts receiving realtime data
        memeLib.startDataReport(memeRealtimeListener);
    }
}
