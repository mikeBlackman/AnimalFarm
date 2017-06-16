package megacorp.animals;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    boolean loaded;
    SoundPool soundPool;
    List<Animal> animals = new ArrayList<>();

    // TODO Use a recyclerview, proper adapter etc
    // Konvert to kotlin
    // Add new images

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        soundPool = buildSoundPool();
        buildAnimalData();
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
        for (Animal animal : animals){
            soundPool.load(this, animal.getSoundId(), 1);
        }
        soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });
        return soundPool;
    }

    void buildAnimalData(){
        animals.add(new Animal(R.raw.cat, R.drawable.cat));
        animals.add(new Animal(R.raw.chicken2, R.drawable.chicken));
        animals.add(new Animal(R.raw.cow, R.drawable.cow));
        animals.add(new Animal(R.raw.dog1, R.drawable.dog));
        animals.add(new Animal(R.raw.duck, R.drawable.duck));
        animals.add(new Animal(R.raw.horse, R.drawable.horse));
        animals.add(new Animal(R.raw.pig, R.drawable.pig));
        animals.add(new Animal(R.raw.sheep, R.drawable.sheep));
        animals.add(new Animal(R.raw.turkey2, R.drawable.turkey));
    }

}
