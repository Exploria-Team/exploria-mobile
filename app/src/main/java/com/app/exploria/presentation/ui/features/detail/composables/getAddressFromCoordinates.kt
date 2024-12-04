import android.content.Context
import android.location.Address
import android.location.Geocoder
import java.util.Locale

fun getAddressFromCoordinates(context: Context, latitude: Double, longitude: Double): String {
    val geocoder = Geocoder(context, Locale.getDefault())
    val addresses: List<Address>?

    try {
        addresses = geocoder.getFromLocation(latitude, longitude, 1) // Get 1 result
        if (addresses != null && addresses.isNotEmpty()) {
            val address = addresses[0]
            return address.getAddressLine(0) // First address line (street name, city, etc.)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return "Address not found"
}
