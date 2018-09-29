package megacorp.animals

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.media.SoundPool
import kotlinx.android.synthetic.main.activity_main.*

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
        animals.add(Animal(R.raw.cat, R.drawable.cat, "Cat"))
        animals.add(Animal(R.raw.chicken2, R.drawable.chicken, "Chicken"))
        animals.add(Animal(R.raw.cow, R.drawable.cow, "Cow"))
        animals.add(Animal(R.raw.dog1, R.drawable.dog, "Dog"))
        animals.add(Animal(R.raw.duck, R.drawable.duck, "Duck"))
        animals.add(Animal(R.raw.horse, R.drawable.horse, "Horse"))
        animals.add(Animal(R.raw.pig, R.drawable.pig, "Pig"))
        animals.add(Animal(R.raw.sheep, R.drawable.sheep, "Sheep"))
        animals.add(Animal(R.raw.turkey2, R.drawable.turkey, "Turkey"))
        animals.add(Animal(R.raw.mouse2, R.drawable.mouse, "Mouse"))
    }

    override fun onAnimalClick(item: Animal, position: Int) {
        if (loaded) {
            soundPool.play(position + 1, 1.0f, 1.0f, 1, 0, 1.0f)
        }
    }

}

