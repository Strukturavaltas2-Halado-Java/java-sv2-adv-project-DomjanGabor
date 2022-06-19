CREATE TABLE companies
(
    company_id          BIGINT AUTO_INCREMENT NOT NULL,
    name_of_company     VARCHAR(255) NULL,
    vat_number          VARCHAR(255) NULL UNIQUE,
    bank_account_number VARCHAR(255) NULL,
    CONSTRAINT pk_companies PRIMARY KEY (company_id)
);