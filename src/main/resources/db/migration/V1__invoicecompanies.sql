CREATE TABLE companies
(
    company_id          BIGINT AUTO_INCREMENT NOT NULL,
    name_of_company     VARCHAR(255) NULL,
    vat_number          VARCHAR(255) NULL,
    bank_account_number VARCHAR(255) NULL,
    CONSTRAINT pk_companies PRIMARY KEY (company_id)
);

CREATE TABLE invoices
(
    invoice_id      BIGINT AUTO_INCREMENT NOT NULL,
    invoice_number  VARCHAR(255) NULL,
    date_of_issue   date NULL,
    due_date        date NULL,
    payment_status  VARCHAR(255) NULL,
    amount_total    INT NULL,
    company_id      BIGINT NULL,
    CONSTRAINT pk_invoices PRIMARY KEY (invoice_id),
    CONSTRAINT fk_invoices_companies FOREIGN KEY (company_id) references companies (company_id)
);

CREATE TABLE items_on_invoices
(
    invoice_id      BIGINT NOT NULL,
    name_of_item    VARCHAR(255) NULL,
    pieces_of_items INT NULL,
    total_price     INT NULL,
    CONSTRAINT fk_items_invoices FOREIGN KEY (invoice_id) references invoices (invoice_id)
);