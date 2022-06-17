package invoicekeeper.repositories;

import invoicekeeper.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("select c from Company c where :textInCompanyName is null or c.companyName like %:textInCompanyName%")
    List<Company> findAllCompanies(Optional<String> textInCompanyName);

    Optional<Company> findCompanyByVatNumber(String vatNumber);

}
