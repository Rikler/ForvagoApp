import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.forvagoapp.DetalleDeHotel
import com.squareup.picasso.Picasso
import com.example.forvagoapp.Hotel
import com.example.forvagoapp.R

class HotelAdapter(private val hotelList: List<Hotel>, private val context: Context) : RecyclerView.Adapter<HotelAdapter.HotelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hotel, parent, false)
        return HotelViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val hotel = hotelList[position]
        holder.textViewDescripcion.text = hotel.descripcion
        Picasso.get().load(hotel.imagen)
            .error(R.drawable.error_image)
            .into(holder.imageViewHotel)
        holder.buttonVerMas.setOnClickListener {
            // Se añade el redireccionamiento a la pantalla Detalle Hotel
            val intent = Intent(context, DetalleDeHotel::class.java)
            // Se envia el ID del hotel seleccionado
            intent.putExtra("HOTEL_ID", hotel.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = hotelList.size

    class HotelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewHotel: ImageView = itemView.findViewById(R.id.imageViewHotel)
        val textViewDescripcion: TextView = itemView.findViewById(R.id.textViewDescripcion)
        val buttonVerMas: Button = itemView.findViewById(R.id.buttonVerMas)
    }
}
