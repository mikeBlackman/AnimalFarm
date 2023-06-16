package megacorp.animals

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import java.util.ArrayList

class AnimalAdapter : RecyclerView.Adapter<AnimalAdapter.AnimalHolder>() {

    private lateinit var animals: ArrayList<Animal>
    private lateinit var animalClickListener: OnAnimalClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.animal_holder, parent, false)
        return AnimalHolder(itemView)
    }

    fun setAnimals(animals: ArrayList<Animal>) {
        this.animals = animals
    }

    fun setOnAnimalClickListener(animalClickListener: OnAnimalClickListener) {
        this.animalClickListener = animalClickListener
    }

    override fun onBindViewHolder(holder: AnimalHolder, position: Int) {
        holder.bind(animals[position], animalClickListener)
    }

    override fun getItemCount(): Int {
        return animals.size
    }

    interface OnAnimalClickListener {
        fun onAnimalClick(item: Animal)
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class AnimalHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val animalImageView: ImageView
        private val animalName: TextView
        private val cardView: CardView

        init {
            // Define click listener for the ViewHolder's View
            animalImageView = view.findViewById(R.id.animalImageView)
            animalName = view.findViewById(R.id.animalName)
            cardView = view.findViewById(R.id.cardView)
        }

        fun bind(animal: Animal, listener: OnAnimalClickListener) {
            animalImageView.setImageResource(animal.imageId)
            animalName.text = animal.animalName
            cardView.setOnClickListener { listener.onAnimalClick(animal) }
        }
    }
}
