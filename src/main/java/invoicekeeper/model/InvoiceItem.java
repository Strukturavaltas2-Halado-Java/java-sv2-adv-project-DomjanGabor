package invoicekeeper.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InvoiceItem {
    @Column(name = "name_of_item")
    private String name;

    @Column(name = "pieces_of_items")
    private int pieces;

    @Column(name = "total_price")
    private int priceTotal;
}
