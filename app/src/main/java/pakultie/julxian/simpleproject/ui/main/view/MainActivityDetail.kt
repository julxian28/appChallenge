package pakultie.julxian.simpleproject.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pakultie.julxian.simpleproject.R
import pakultie.julxian.simpleproject.utils.CharacterDao

class MainActivityDetail : AppCompatActivity() {

    private lateinit var getItems: CharacterDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_detail)

//        val ss: String? = intent.getStringExtra("Index")
//        if (ss != null) {
//            val db = Room.databaseBuilder(
//                applicationContext,
//                AppDatabase::class.java, "DataItems.db"
//            ).build()
//            detail_txt_from.text =
//            detail_txt_to.text = datas.get(ss.toInt1()).sender.name
//            Glide.with(detail_img.context)
//                .load(datas[ss.toInt1()].goodsPicture)
//                .into(detail_img)
//            detail_txt_delivery_fee.text = df
//            detail_txt_from.text = datas.get(ss.toInt1()).remarks
//       }
    }
}