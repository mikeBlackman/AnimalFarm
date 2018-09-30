package megacorp.animals

import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

data class AnimalCardModel(
        val animalImageResourceId: Int,
        val animalName: String,
        var listener: (View) -> Unit ) : KotlinModel(R.layout.animal_holder) {

    private val animalNameTextView by bind<TextView>(R.id.animal_name)
    private val animalImageView by bind<ImageView>(R.id.animal_image)
    private val cardView by bind<CardView>(R.id.card_view)

    override fun bind() {
        animalImageView.setImageResource(animalImageResourceId)
        animalNameTextView.text = animalName
        cardView.setOnClickListener { listener }
    }
}
