package megacorp.animals;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

public class MainActivity extends AppCompatActivity {

    boolean loaded;
    SoundPool soundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

}
