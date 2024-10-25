package lat.pam.utsproject

import lat.pam.utsproject.FoodAdapter
import lat.pam.utsproject.OrderActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFoodActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FoodAdapter
    private lateinit var foodList: List<Food>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_food)

        try {
            // Kode inisialisasi RecyclerView
            recyclerView = findViewById(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this)

            // Update the food list
            foodList = listOf(
                Food("Donat", "Berbagai macam rasa donat yang enak", R.drawable.donut),
                Food("Nasi Goreng", "Nasi dengan campuran telur dan ayam", R.drawable.nasigoreng),
                Food("Kopi Hitam", "Dibuat dari kopi pegunungan", R.drawable.kopi_hitam),
            )

            adapter = FoodAdapter(foodList) { selectedFood ->
                val intent = Intent(this, OrderActivity::class.java).apply {
                    putExtra("FOOD_NAME", selectedFood.name)
                    putExtra("FOOD_DESCRIPTION", selectedFood.description)
                    putExtra("FOOD_IMAGE_RES_ID", selectedFood.imageResourceId)
                }
                startActivity(intent)
            }
            recyclerView.adapter = adapter

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        } catch (e: Exception) {
            e.printStackTrace() // Cetak stack trace ke Logcat
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}

