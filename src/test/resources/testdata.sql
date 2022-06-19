insert into companies (name_of_company, vat_number, bank_account_number)
values("Best Byte", "12345678-1-45", "12345876-86496452-11111111"),
      ("Euronics", "84512648-1-45", "84245689-12358698-22222222"),
      ("Euro Family", "98765432-2-55", "48695842-45236874-88888888");

insert into invoices (invoice_number, date_of_issue, due_date, payment_status, amount_total, company_id)
values("123456AB", "2020-12-22", "2020-12-28", "UNPAYED", 1200, 1),
      ("84568BB", "2021-01-12", "2021-02-01", "PAYED", 5000, 2),
      ("995468RS", "2021-05-09", "2021-05-19", "UNPAYED", 2080, 2),
      ("45996EE", "2022-06-15", "2022-06-30", "UNPAYED", 5400, 3);

insert into items_on_invoices(invoice_id, name_of_item, pieces_of_items, total_price)
values(1, "gyufa", 5, 1000),
      (1, "ceruza", 1, 200),
      (2, "benzin", 1, 5000),
      (3, "ceruza", 3, 1080),
      (3, "ceruza", 1, 1000),
      (4, "kalap", 1, 5400);
