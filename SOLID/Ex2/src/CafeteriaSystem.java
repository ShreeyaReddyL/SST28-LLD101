import java.util.*;

public class CafeteriaSystem {
    private final MenuRepository menu;
    private final PolicyRegistry policies;
    private final InvoiceFormatter formatter;
    private final InvoiceStore store;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(MenuRepository menu, PolicyRegistry policies, 
                           InvoiceFormatter formatter, InvoiceStore store) {
        this.menu = menu;
        this.policies = policies;
        this.formatter = formatter;
        this.store = store;
    }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);
        List<InvoiceLine> invoiceLines = new ArrayList<>();
        double subtotal = 0.0;

        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            invoiceLines.add(new InvoiceLine(item.name, l.qty, lineTotal));
        }

        double taxPct = policies.getTaxPolicy(customerType).getTaxPercent();
        double tax = subtotal * (taxPct / 100.0);

        double discount = policies.getDiscountPolicy(customerType).getDiscountAmount(subtotal, lines.size());
        double total = subtotal + tax - discount;

        Invoice invoice = new Invoice(invId, invoiceLines, subtotal, taxPct, tax, discount, total);
        
        String printable = formatter.format(invoice);
        System.out.print(printable);

        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}