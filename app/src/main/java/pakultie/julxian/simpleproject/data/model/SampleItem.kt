package pakultie.julxian.simpleproject.data.model

data class SampleItem(
    val id: String,
    val remarks: String,
    val pickupTime: String,
    val goodsPicture: String,
    val deliveryFee: String,
    val surcharge: String,
    val route: route,
    val sender: sender
)

data class route(
    val start: String,
    val end: String,
)

data class sender(
    val phone: String,
    val name: String,
    val email: String
)
