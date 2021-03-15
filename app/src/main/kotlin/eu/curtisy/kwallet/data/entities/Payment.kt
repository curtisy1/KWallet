package eu.curtisy.kwallet.data.entities

import eu.curtisy.kwallet.data.enums.PaymentType

data class Payment(
    var description: String? = null,
    var amount: Double? = null,
    var type: PaymentType = PaymentType.GENERIC,
    var credit: Boolean = false,
    var createdDate: Long? = null,
)
