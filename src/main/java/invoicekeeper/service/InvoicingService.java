package invoicekeeper.service;

import invoicekeeper.dtos.*;
import invoicekeeper.model.Company;
import invoicekeeper.model.Invoice;
import invoicekeeper.model.PaymentStatus;
import invoicekeeper.repositories.CompanyRepository;
import invoicekeeper.repositories.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InvoicingService {
    private InvoiceRepository invoiceRepository;
    private CompanyRepository companyRepository;
    private ModelMapper modelMapper;

    @Transactional //validálni, hogy ha már fent lévő company van, akkor ne akarjon új company-t felvinni
    public InvoiceDto saveNewInvoice(CreateNewInvoiceCommand command) {
        Invoice newInvoice = modelMapper.map(command, Invoice.class);
        Company newCompany = newInvoice.getCompany();
        newCompany.addInvoice(newInvoice);
        companyRepository.save(newCompany);
        InvoiceDto resultDto = modelMapper.map(newInvoice, InvoiceDto.class);
        resultDto.setCompanyName(newInvoice.getCompany().getCompanyName());
        return resultDto;
    }

    @Transactional
    public InvoiceDto payInvoice(PayInvoiceCommand command) {
        Invoice invoiceFound = invoiceRepository.findAllByInvoiceNumber(command.getInvoiceNumber());
        invoiceFound.setPaymentStatus(PaymentStatus.PAYED);
        InvoiceDto resultDto = modelMapper.map(invoiceFound, InvoiceDto.class);
        resultDto.setCompanyName(invoiceFound.getCompany().getCompanyName());
        return resultDto;
    }

    public InvoiceDto getInvoiceById(long id) {
        Invoice invoiceFound = findInvoiceById(id);
        return modelMapper.map(invoiceFound, InvoiceDto.class);
    }

    public List<InvoiceDto> getAllInvoices(Optional<String> companyName, Optional<String> vatNumber,
                                           Optional<LocalDate> issuedAfter, Optional<String> overDue) {
        List<Invoice> filtered = invoiceRepository.findInvoicesByParameters(companyName, vatNumber, issuedAfter);
        if (overDue.isPresent()) {
            filtered = filterOverDueInvoices(filtered, overDue.get());
        }
        Type resultList = new TypeToken<List<InvoiceDto>>(){}.getType();
        return modelMapper.map(filtered, resultList);
    }

    @Transactional
    public boolean deleteInvoiceById(long id) {
        invoiceRepository.deleteById(id);
        return true;
    }

    @Transactional
    public CompanyDto addNewCompany(AddNewCompanyCommand command) {
        Company newCompany = modelMapper.map(command, Company.class);
        companyRepository.save(newCompany);
        return modelMapper.map(newCompany, CompanyDto.class);
    }

    @Transactional
    public InvoiceDto addNewInvoiceToCompany(long id, AddNewInvoiceCommand command) {
        Company companyFound = findCompanyById(id);
        Invoice newInvoice = modelMapper.map(command, Invoice.class);
        companyFound.addInvoice(newInvoice);
        InvoiceDto result = modelMapper.map(newInvoice, InvoiceDto.class);
        result.setCompanyName(companyFound.getCompanyName());
        return result;
    }

    @Transactional
    public CompanyDto updateAccountNumber(long id, UpdateAccountNumberCommand command) {
        Company companyFound = findCompanyById(id);
        companyFound.setBankAccountNumber(command.getBankAccountNumber());
        return modelMapper.map(companyFound, CompanyDto.class);
    }

    public CompanyDto getCompanyById(long id) {
        return modelMapper.map(findCompanyById(id), CompanyDto.class);
    }

    public List<CompanyDto> findAllCompanies(Optional<String> companyName, Optional<String> vatNumber) {
        List<Company> companiesFound = companyRepository.findAllCompanies(companyName, vatNumber);
        Type resultList = new TypeToken<List<CompanyDto>>(){}.getType();
        return modelMapper.map(companiesFound, resultList);
    }

    @Transactional
    public boolean deleteCompanyById(long id) {
        Company companyFound = findCompanyById(id);
        companyRepository.delete(companyFound);
        return true;
    }

    private List<Invoice> filterOverDueInvoices(List<Invoice> invoices, String filterBy) {
        if ("yes".equals(filterBy)) {
            return invoices.stream()
                    .filter(i -> i.getDueDate().isBefore(LocalDate.now()))
                    .collect(Collectors.toList());
        } else if ("no".equals(filterBy)) {
            return invoices.stream()
                    .filter(i -> !i.getDueDate().isBefore(LocalDate.now()))
                    .collect(Collectors.toList());
        }
        throw new IllegalArgumentException("Invalid filter criteria: "+ filterBy);
    }

    private Invoice findInvoiceById(long id) {
        Optional<Invoice> invoiceFound = invoiceRepository.findById(id);
        if (invoiceFound.isEmpty()) {
            throw new IllegalArgumentException("Invoice not found by id: " + id);
        }
        return invoiceFound.get();
    }

    private Company findCompanyById(long id) {
        Optional<Company> companyFound = companyRepository.findById(id);
        if (companyFound.isEmpty()) {
            throw new IllegalArgumentException("Company not found by id: " + id);
        }
        return companyFound.get();
    }
}
