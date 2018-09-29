package megacorp.animals

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.ArrayList
import kotlinx.android.synthetic.main.animal_holder.view.*

class AnimalAdapter : RecyclerView.Adapter<AnimalAdapter.AnimalHolder>() {

    private lateinit var animals: ArrayList<Animal>
    private lateinit var animalClickListener: OnAnimalClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.animal_holder, parent, false)
        return AnimalHolder(itemView)
    }

    fun setAnimals(animals: ArrayList<Animal>) {
        this.animals = animals
    }

    fun setOnAnimalClickListener(animalClickListener: OnAnimalClickListener) {
        this.animalClickListener = animalClickListener
    }

    override fun onBindViewHolder(holder: AnimalHolder, position: Int) {
        holder.bind(animals[position], position, animalClickListener)
    }

    override fun getItemCount(): Int {
        return animals.size
    }

    interface OnAnimalClickListener {
        fun onAnimalClick(item: Animal, position: Int)
    }

    class AnimalHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val animalImageView = view.animalImageView
        private val animalName = view.animalName
        private val cardView = view.card_view

        fun bind(animal: Animal, position: Int, listener: OnAnimalClickListener) {
            animalImageView.setImageResource(animal.imageId)
            animalName.text = animal.animalName
            cardView.setOnClickListener { listener.onAnimalClick(animal, position) }
        }
    }
}
