package invoicekeeper.controllers;

import invoicekeeper.dtos.CreateNewInvoiceCommand;
import invoicekeeper.dtos.InvoiceDto;
import invoicekeeper.dtos.PayInvoiceCommand;
import invoicekeeper.model.Company;
import invoicekeeper.model.InvoiceItem;
import invoicekeeper.model.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/cleartables.sql", "/testdata.sql"})
class InvoiceControllerIT {

    @Autowired
    WebTestClient webTestClient;

    CreateNewInvoiceCommand createCommand;

    @BeforeEach
    void init() {
        createCommand = new CreateNewInvoiceCommand("123456", LocalDate.parse("2022-06-17"), LocalDate.parse("2022-06-25"),
                PaymentStatus.UNPAYED, List.of(new InvoiceItem("gyufa", 1, 500)), 500,
                "Penny", "12345678-2-44", "11111111-22222222-33333333");
    }

    @Test
    @DisplayName("Test: save a new invoice to database.")
    void testSaveNewInvoice() {
        webTestClient.post()
                .uri("/api/invoices")
                .bodyValue(createCommand)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(InvoiceDto.class)
                .value(i -> assertEquals("123456", i.getInvoiceNumber()));
    }

    @Test
    @DisplayName("Test: finding an invoice with given ID.")
    void testGetInvoiceById() {
        InvoiceDto result = webTestClient.post()
                .uri("/api/invoices")
                .bodyValue(createCommand)
                .exchange()
                .expectBody(InvoiceDto.class)
                .returnResult().getResponseBody();

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/invoices/{id}").build(result.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectBody(InvoiceDto.class)
                .value(i -> assertEquals("123456", i.getInvoiceNumber()));
    }

    @Test
    @DisplayName("Test: find all invoices without using any filters.")
    void testGetAllInvoicesWithNoParameters() {
        webTestClient.get()
                .uri("/api/invoices")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(InvoiceDto.class)
                .hasSize(4)
                .value(i -> assertThat(i).extracting(InvoiceDto::getCompanyName).containsOnly("Best Byte", "Euronics", "Euro Family"));
    }

    @Test
    @DisplayName("Test: find all invoices where the company name equals the parameter.")
    void testGetAllInvoicesWithNameParameter() {
        webTestClient.get()
                .uri("/api/invoices?companyName=Euronics")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(InvoiceDto.class)
                .hasSize(2)
                .value(i -> assertThat(i).extracting(InvoiceDto::getInvoiceNumber).containsOnly("995468RS", "84568BB"));
    }

    @Test
    @DisplayName("Test: find all invoices issued after the given parameter. ")
    void testGetAllInvoicesWithIssueDateParameter() {
        webTestClient.get()
                .uri("/api/invoices?issuedAfter=2021-05-08")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(InvoiceDto.class)
                .hasSize(2)
                .value(i -> assertThat(i).extracting(InvoiceDto::getInvoiceNumber).containsOnly("995468RS", "45996EE"));
    }

    @Test
    @DisplayName("Test: Find all invoices which has the given item on it.")
    void testGetAllInvoicesWithItem() {
        webTestClient.get()
                .uri("/api/invoices/find-item?itemName=könyv")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(InvoiceDto.class)
                .hasSize(2)
                .value(l -> assertThat(l).extracting(InvoiceDto::getInvoiceNumber).containsOnly("123456AB","995468RS"));
    }

    @Test
    @DisplayName("Test: Pay an invoice.")
    void testPayInvoice() {
        webTestClient.post()
                .uri("/api/invoices")
                .bodyValue(createCommand)
                .exchange()
                .expectBody(InvoiceDto.class)
                .value(i -> assertEquals(PaymentStatus.UNPAYED, i.getPaymentStatus()));

        webTestClient.put()
                .uri("/api/invoices/payment")
                .bodyValue(new PayInvoiceCommand(createCommand.getInvoiceNumber(), createCommand.getAmount(), createCommand.getBankAccountNumber()))
                .exchange()
                .expectStatus().isOk()
                .expectBody(InvoiceDto.class)
                .value(i -> assertEquals(PaymentStatus.PAYED, i.getPaymentStatus()));
    }

    @Test
    @DisplayName("Test: delete an invoice with given ID.")
    void testDeleteInvoiceById() {
        webTestClient.get()
                .uri("/api/invoices")
                .exchange()
                .expectBodyList(InvoiceDto.class)
                .hasSize(4)
                .value(l -> assertThat(l).extracting(InvoiceDto::getInvoiceNumber).containsOnly("123456AB", "84568BB", "995468RS", "45996EE"));

        webTestClient.delete()
                .uri("/api/invoices/1")
                .exchange()
                .expectStatus().isNoContent();

        webTestClient.get()
                .uri("/api/invoices")
                .exchange()
                .expectBodyList(InvoiceDto.class)
                .hasSize(3)
                .value(l -> assertThat(l).extracting(InvoiceDto::getInvoiceNumber).containsOnly("84568BB", "995468RS", "45996EE"));
    }
}