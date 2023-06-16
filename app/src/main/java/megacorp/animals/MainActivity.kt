package megacorp.animals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import android.media.SoundPool
import androidx.recyclerview.widget.RecyclerView

import java.util.ArrayList

class MainActivity : AppCompatActivity(), AnimalAdapter.OnAnimalClickListener {

    private var loaded: Boolean = false
    private lateinit var soundPool: SoundPool
    private var animals = ArrayList<Animal>()
    private lateinit var soundPoolMap: HashMap<Int, Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buildAnimals()
        buildAndPopulateSoundPool()

        val animalAdapter = AnimalAdapter()
        animalAdapter.setAnimals(animals)
        animalAdapter.setOnAnimalClickListener(this)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager =
            GridLayoutManager(
                this,
                this.resources.getInteger(R.integer.span_count)
            )
        recyclerView.adapter = animalAdapter
    }

    private fun buildAndPopulateSoundPool() {
        soundPool = SoundPool.Builder().setMaxStreams(16).build()
        soundPool.setOnLoadCompleteListener { _, _, _ -> loaded = true }
        soundPoolMap = HashMap()
        animals.forEach {
            soundPoolMap[it.soundId] = soundPool.load(this, it.soundId, 1)
        }
    }

    private fun buildAnimals() {
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

    override fun onAnimalClick(item: Animal) {
        if (loaded && soundPoolMap.containsKey(item.soundId)) {
            val soundId: Int = soundPoolMap[item.soundId] ?: return
            soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f)
        }
    }

}

data class Animal(val soundId: Int, val imageId: Int, val animalName: String)

