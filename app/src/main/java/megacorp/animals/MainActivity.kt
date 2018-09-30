package megacorp.animals

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.media.SoundPool
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var loaded = false
    private lateinit var soundPool: SoundPool
    private var animals = ArrayList<Animal>()
    private lateinit var recyclerView: EpoxyRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)

        buildAnimalData()
        soundPool = buildSoundPool()

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.withModels {
            animals.forEachIndexed { index , element ->
                // callback never fires???
                AnimalCardModel(element.imageId, element.animalName) { onAnimalClick(element.soundId) }.id("data class $index").addTo(this)
            }
        }
    }

    private fun buildSoundPool(): SoundPool {
        soundPool = SoundPool.Builder().setMaxStreams(16).build()
        soundPool.setOnLoadCompleteListener { _, _, _ -> loaded = true }
        animals.forEach {
            soundPool.load(this, it.soundId, 1)
        }
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

    private fun onAnimalClick(soundId: Int) {
        if (loaded) {
            soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f)
        }
    }
}

fun EpoxyRecyclerView.withModels(buildModelsCallback: EpoxyController.() -> Unit) {
    setControllerAndBuildModels(object : EpoxyController() {
        override fun buildModels() {
            buildModelsCallback()
        }
    })
}

