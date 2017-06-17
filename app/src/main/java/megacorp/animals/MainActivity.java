package megacorp.animals;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AnimalAdapter.OnAnimalClickListener {

    boolean loaded;
    SoundPool soundPool;
    ArrayList<Animal> animals = new ArrayList<>();

    // Todo convert all classes to kotlin

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildAnimalData();
        soundPool = buildSoundPool();

        AnimalAdapter animalAdapter = new AnimalAdapter();
        animalAdapter.setAnimals(animals);
        animalAdapter.setOnAnimalClickListener(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(animalAdapter);

    }

    public SoundPool buildSoundPool() {
        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setMaxStreams(16);
        soundPool = builder.build();
        for (Animal animal : animals) {
            soundPool.load(this, animal.getSoundId(), 1);
        }
        soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });
        return soundPool;
    }

    void buildAnimalData() {
        animals.add(new Animal(R.raw.cat, R.drawable.cat));
        animals.add(new Animal(R.raw.chicken2, R.drawable.chicken));
        animals.add(new Animal(R.raw.cow, R.drawable.cow));
        animals.add(new Animal(R.raw.dog1, R.drawable.dog));
        animals.add(new Animal(R.raw.duck, R.drawable.duck));
        animals.add(new Animal(R.raw.horse, R.drawable.horse));
        animals.add(new Animal(R.raw.pig, R.drawable.pig));
        animals.add(new Animal(R.raw.sheep, R.drawable.sheep));
        animals.add(new Animal(R.raw.turkey2, R.drawable.turkey));
        animals.add(new Animal(R.raw.mouse2, R.drawable.mouse));
    }

    @Override
    public void onAnimalClick(Animal item, int position) {
        if (loaded) {
            soundPool.play(position + 1, 1.0f, 1.0f, 1, 0, 1.0f);
        }
    }

}

