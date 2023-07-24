import com.example.Hotel
import com.example.HotelDto

fun Hotel.toDto(): HotelDto =
    HotelDto(
        id = this.id.toString(),
        city = this.city,
        hotelName = this.hotelName,
        price = this.price,
        date = this.date
    )

fun HotelDto.toHotel(): Hotel =
    Hotel(
        city = this.city,
        hotelName = this.hotelName,
        price = this.price,
        date = this.date
    )