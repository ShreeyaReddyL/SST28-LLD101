import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        System.out.println("--- 1. CREATING TICKET ---");
        IncidentTicket originalTicket = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Original: " + originalTicket);

        System.out.println("\n--- 2. UPDATING TICKET (Creates new instances) ---");
        IncidentTicket assignedTicket = service.assign(originalTicket, "agent@example.com");
        IncidentTicket escalatedTicket = service.escalateToCritical(assignedTicket);
        
        System.out.println("Latest Ticket: " + escalatedTicket);
        System.out.println("Notice the Original is untouched: " + originalTicket);

        System.out.println("\n--- 3. TESTING SECURITY (Defensive Copying) ---");
        try {
            List<String> tags = escalatedTicket.getTags();
            tags.add("HACKED_FROM_OUTSIDE"); // This will fail now!
        } catch (UnsupportedOperationException e) {
            System.out.println("SUCCESS! External code tried to hack the tags list, but it was blocked.");
        }
    }
}