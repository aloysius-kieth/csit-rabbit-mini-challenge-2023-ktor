import com.example.Flight
import com.example.FlightDto

fun Flight.toDto(): FlightDto =
    FlightDto(
        id = this.id.toString(),
        airline = this.airline,
        airlineid = this.airlineid,
        srcairport = this.srcairport,
        srcairportid = this.srcairportid,
        destairport = this.destairport,
        destairportid = this.destairportid,
        codeshare = this.codeshare,
        stop = this.stop,
        eq = this.eq,
        airlinename = this.airlinename,
        srccity = this.srccity,
        srccountry = this.srccountry,
        destairportname = this.destairportname,
        destcity = this.destcity,
        destcountry = this.destcountry,
        price = this.price,
        date = this.date
    )

fun FlightDto.toFlight(): Flight =
    Flight(
        airline = this.airline,
        airlineid = this.airlineid,
        srcairport = this.srcairport,
        srcairportid = this.srcairportid,
        destairport = this.destairport,
        destairportid = this.destairportid,
        codeshare = this.codeshare,
        stop = this.stop,
        eq = this.eq,
        airlinename = this.airlinename,
        srccity = this.srccity,
        srccountry = this.srccountry,
        destairportname = this.destairportname,
        destcity = this.destcity,
        destcountry = this.destcountry,
        price = this.price,
        date = this.date
    )