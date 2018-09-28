package megacorp.animals

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.media.SoundPool
import android.view.View

import java.util.ArrayList

class MainActivity : AppCompatActivity(), AnimalAdapter.OnAnimalClickListener {

    private var loaded = false
    private lateinit var soundPool: SoundPool
    private var animals = ArrayList<Animal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buildAnimalData()
        soundPool = buildSoundPool()

        val animalAdapter = AnimalAdapter()
        animalAdapter.setAnimals(animals)
        animalAdapter.setOnAnimalClickListener(this)

        val recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = animalAdapter
    }

    private fun buildSoundPool(): SoundPool {
        val builder = SoundPool.Builder()
        builder.setMaxStreams(16)
        soundPool = builder.build()
        for ((soundId) in animals) {
            soundPool.load(this, soundId, 1)
        }
        soundPool.setOnLoadCompleteListener { _, _, _ -> loaded = true }
        return soundPool
    }

    private fun buildAnimalData() {
        animals.add(Animal(R.raw.cat, R.drawable.cat))
        animals.add(Animal(R.raw.chicken2, R.drawable.chicken))
        animals.add(Animal(R.raw.cow, R.drawable.cow))
        animals.add(Animal(R.raw.dog1, R.drawable.dog))
        animals.add(Animal(R.raw.duck, R.drawable.duck))
        animals.add(Animal(R.raw.horse, R.drawable.horse))
        animals.add(Animal(R.raw.pig, R.drawable.pig))
        animals.add(Animal(R.raw.sheep, R.drawable.sheep))
        animals.add(Animal(R.raw.turkey2, R.drawable.turkey))
        animals.add(Animal(R.raw.mouse2, R.drawable.mouse))
    }

    override fun onAnimalClick(item: Animal, position: Int) {
        if (loaded) {
            soundPool.play(position + 1, 1.0f, 1.0f, 1, 0, 1.0f)
        }
    }

}

