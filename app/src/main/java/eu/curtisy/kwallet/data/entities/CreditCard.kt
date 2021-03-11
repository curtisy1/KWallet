package eu.curtisy.kwallet.data.entities

import java.util.*

data class CreditCard(
    var name: String,
    var iban: Int,
    var expiry: Calendar,
    var cvv: Int,
    var isVisa: Boolean,
    var balance: Double,
    var color: String,
)