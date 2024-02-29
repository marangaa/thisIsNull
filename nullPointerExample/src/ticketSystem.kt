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

class TicketingSystem {
    private val tickets = mutableListOf<Ticket>()

    fun createTicket(title: String, description: String): Ticket {
        val newTicket = Ticket(
            id = generateTicketId(),
            title = title,
            description = description
        )
        tickets.add(newTicket)
        return newTicket
    }

    fun assignTicket(ticketId: String, agentName: String) {
        val ticket = findTicketById(ticketId)
        if (ticket != null && ticket.status == TicketStatus.OPEN) {
            ticket.assignedAgent = agentName
            ticket.status = TicketStatus.IN_PROGRESS
            println("Ticket ${ticket.id} assigned to $agentName")
        } else {
            println("Ticket not found or already assigned or closed.")
        }
    }

    fun resolveTicket(ticketId: String) {
        val ticket = findTicketById(ticketId)
        if (ticket != null && ticket.status == TicketStatus.IN_PROGRESS) {
            ticket.status = TicketStatus.RESOLVED
            println("Ticket ${ticket.id} resolved")
        } else {
            println("Ticket not found or not in progress.")
        }
    }

    fun closeTicket(ticketId: String) {
        val ticket = findTicketById(ticketId)
        if (ticket != null && ticket.status == TicketStatus.RESOLVED) {
            ticket.status = TicketStatus.CLOSED
            println("Ticket ${ticket.id} closed")
        } else {
            println("Ticket not found or not resolved.")
        }
    }

    private fun generateTicketId(): String {
        // In a real scenario, you might use a more sophisticated ID generation logic
        return "T-${System.currentTimeMillis()}"
    }

    private fun findTicketById(ticketId: String): Ticket? {
        return tickets.find { it.id == ticketId }
    }

    fun getAllTickets(): List<Ticket> {
        return tickets.toList()
    }
}


