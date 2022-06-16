insert into companies (name_of_company, vat_number, bank_account_number)
values("Best Byte", "12345678-1-45", "12345876-86496452"), ("Euronics", "84512648-1-45", "84245689-12358698");

insert into invoices (invoice_number, date_of_issue, due_date, payment_status, amount_total, company_id)
values("123456AB", "2020-12-22", "2020-12-28", "UNPAYED", 1200, 1),("84568BB", "2021-01-12", "2021-02-01", "UNPAYED", 5000, 2);

insert into items_on_invoices(invoice_id, name_of_item, pieces_of_items, total_price)
values(1, "gyufa", 5, 1000), (1, "könyv", 1, 200), (2, "benzin", 1, 5000);
