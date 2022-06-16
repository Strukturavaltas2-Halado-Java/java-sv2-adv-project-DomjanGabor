package invoicekeeper.repositories;

import invoicekeeper.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findAllByInvoiceNumber(String invoiceNumber);
    @Query("select i from Invoice i where " +
            "(:companyName is null or i.company.companyName = :companyName) and" +
            "(:vatNumber is null or i.company.vatNumber = :vatNumber) and " +
            "(:issuedAfter is null or i.issueDate > :issuedAfter)")
    List<Invoice> findInvoicesByParameters(Optional<String> companyName, Optional<String> vatNumber,
                                           Optional<LocalDate> issuedAfter);
}