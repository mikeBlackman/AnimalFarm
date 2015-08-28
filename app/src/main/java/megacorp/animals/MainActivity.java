package megacorp.animals;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class MainActivity extends ActionBarActivity {

    boolean loaded = false;
    SoundPool soundPool;

    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        soundPool = buildSoundPool();

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if (loaded) {
                    soundPool.play(position+1, 1.0f, 1.0f, 1, 0, 1.0f);
                }
            }
        });
    }

    public SoundPool buildSoundPool(){

        SoundPool.Builder builder =  new SoundPool.Builder();
        builder.setMaxStreams(25);

        soundPool = builder.build();
        soundPool.load(this, R.raw.cat, 1);
        soundPool.load(this, R.raw.chicken2, 1);
        soundPool.load(this, R.raw.cow, 1);
        soundPool.load(this, R.raw.dog1, 1);
        soundPool.load(this, R.raw.duck, 1);
        soundPool.load(this, R.raw.horse, 1);
        soundPool.load(this, R.raw.pig, 1);
        soundPool.load(this, R.raw.sheep, 1);
        soundPool.load(this, R.raw.turkey2, 1);

        soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });

        return soundPool;
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        String name = "main screen";
        mTracker.setScreenName("Image~" + name);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

    }
}
