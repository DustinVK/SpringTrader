INSERT INTO users (username, password, enabled, roles, permissions)
	values ('user','$2a$10$MPoamgwr5Qs9dWrJ9iCBPOTx0MPibADDxfhvIEvR1GimQ2.KOqGNm',true,'USER, user','');
		
INSERT INTO users (username, password, enabled, roles, permissions)
	values ('admin',
		'$2a$10$MPoamgwr5Qs9dWrJ9iCBPOTx0MPibADDxfhvIEvR1GimQ2.KOqGNm',
		true,
		'ADMIN',
		''
		);
		
INSERT INTO authorities (username, authority)
	values ('user', 'USER');
	
INSERT INTO authorities (username, authority)
	values ('admin', 'ADMIN');
	
INSERT INTO balances (username, amount, stamp)
	values ('user', 2000.0,'2021-03-26 12:47:52.69');
	
INSERT INTO balances (username, amount, stamp)
	values ('admin', 0.0,'2021-03-26 12:47:52.69');
	
INSERT INTO balances (username, amount, stamp)
	values ('user', 166.13, '2021-03-29 18:47:52.69');
	
INSERT INTO userPortfolios(username, name)
	values ('user', 'Test Portfolio');
	
INSERT INTO portfolios (username, id, symbol, amount, price, stamp)
	values ('user', 1, 'TSLA', 3, 611.29, '2021-03-29 18:47:52.69');
	
INSERT INTO portfolios (username, id, symbol, amount, price, stamp)
	values ('user', 1, 'GME', 4, 320.7, '2021-04-29 18:47:52.69');
	
INSERT INTO portfolios (username, id, symbol, amount, price, stamp)
	values ('user', 1, 'TSLA', 4, 703.94, '2021-04-04 18:47:52.69');
	
INSERT INTO userPortfolios(username, name)
	values ('user', 'Tech Stock Portfolio');
	
INSERT INTO portfolios (username, id, symbol, amount, price, stamp)
	values ('user', 2, 'NVDA', 5, 517.93, '2021-03-29 18:47:52.69');
	
INSERT INTO portfolios (username, id, symbol, amount, price, stamp)
	values ('user', 2, 'AMD', 2, 77.14, '2021-04-29 18:47:52.69');
	
INSERT INTO portfolios (username, id, symbol, amount, price, stamp)
	values ('user', 2, 'NVDA', -2, 554.94, '2021-04-04 18:47:52.69');

