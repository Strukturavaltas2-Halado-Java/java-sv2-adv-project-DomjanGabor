CREATE TABLE companies
(
    company_id          BIGINT AUTO_INCREMENT NOT NULL,
    name_of_company     VARCHAR(255) NULL,
    vat_number          VARCHAR(255) NULL UNIQUE,
    bank_account_number VARCHAR(255) NULL,
    CONSTRAINT pk_companies PRIMARY KEY (company_id)
);

CREATE TABLE invoices
(
    invoice_id     BIGINT AUTO_INCREMENT NOT NULL,
    invoice_number VARCHAR(255) NULL UNIQUE,
    date_of_issue  date NULL,
    due_date       date NULL,
    payment_status VARCHAR(255) NULL,
    amount_total   INT NULL,
    company_id     BIGINT NULL,
    CONSTRAINT pk_invoices PRIMARY KEY (invoice_id),
    CONSTRAINT FK_INVOICES_ON_COMPANY FOREIGN KEY (company_id) REFERENCES companies (company_id)
);

CREATE TABLE items_on_invoices
(
    invoice_id      BIGINT NOT NULL,
    name_of_item    VARCHAR(255) NULL,
    pieces_of_items INT NULL,
    total_price     INT NULL,
    CONSTRAINT fk_items_on_invoices_on_invoice FOREIGN KEY (invoice_id) REFERENCES invoices (invoice_id)
);
