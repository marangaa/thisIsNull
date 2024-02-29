data class Ticket(
    val id: String,
    val title: String,
    val description: String,
    var status: TicketStatus = TicketStatus.OPEN,
    var assignedAgent: String? = null
)

enum class TicketStatus {
    OPEN,
    IN_PROGRESS,
    RESOLVED,
    CLOSED
}

