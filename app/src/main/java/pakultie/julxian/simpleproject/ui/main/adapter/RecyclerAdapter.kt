package pakultie.julxian.simpleproject.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.recyclerview_card.view.*
import pakultie.julxian.simpleproject.R
import pakultie.julxian.simpleproject.data.model.SampleItem
import java.math.RoundingMode
import java.text.DecimalFormat


class RecyclerAdapter(private val items: ArrayList<SampleItem>) :
    RecyclerView.Adapter<RecyclerAdapter.DataViewHolder>() {

    val item: MutableList<String> = arrayListOf()

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(sampleItem: SampleItem) {
            itemView.apply {
                val df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.CEILING
                txt_item_from.text = sampleItem.remarks
                txt_item_to.text = sampleItem.sender.name
                txt_item_price.text = "$" + (df.format(
                    sampleItem.deliveryFee.replace("$", "").toDouble() +
                            sampleItem.surcharge.replace("$", "").toDouble()
                ))
                Glide.with(img_get_image.context)
                    .load(sampleItem.goodsPicture)
                    .into(img_get_image)
                //Animation
                txt_item_from.startAnimation(AnimationUtils.loadAnimation(context, R.anim.move))
                txt_item_to.startAnimation(AnimationUtils.loadAnimation(context, R.anim.move))
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recyclerview_card,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {

        }
    }

    fun addUsers(users: List<SampleItem>) {
        this.items.apply {
            clear()
            addAll(users)
        }

    }


}
