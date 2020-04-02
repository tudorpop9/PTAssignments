CREATE TABLE Client (
    clientId INT NOT NULL,
    clientName VARCHAR(50) NOT NULL,
    clientEmail VARCHAR(1000) NOT NULL,
    clientAdr VARCHAR(1000) NOT NULL
);

CREATE TABLE Product (
    productId INT NOT NULL,
    productName VARCHAR(50) NOT NULL,
	productPrice INT NOT NULL,
    productQuant INT
);

CREATE TABLE OrderTable (
    ordertableId INT NOT NULL,
    clientId INT NOT NULL,
    clientAdr VARCHAR(1000) NOT NULL,
	productId INT NOT NULL,
    orderedQuant INT NOT NULL
);	

CREATE TABLE Bill (
    billId INT NOT NULL,
    productName VARCHAR(50) NOT NULL,
    clientName VARCHAR(1000) NOT NULL,
	clientEmail VARCHAR(1000) NOT NULL,
    orderedQuant INT NOT NULL,
	billValue INT
);

ALTER TABLE Client ADD CONSTRAINT clientId_pk PRIMARY KEY (clientId);
ALTER TABLE Product ADD CONSTRAINT productId_pk PRIMARY KEY (productId);
ALTER TABLE OrderTable ADD CONSTRAINT orderId_pk PRIMARY KEY (orderId);
ALTER TABLE Bill ADD CONSTRAINT billOrderId_pk PRIMARY KEY (billId);

ALTER TABLE  OrderTable
ADD CONSTRAINT OrderTable_clientId_fk FOREIGN KEY (clientId) REFERENCES  Client (clientId) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE  OrderTable
ADD CONSTRAINT OrderTable_productId_fk FOREIGN KEY (productId) REFERENCES  Product (productId) ON DELETE CASCADE ON UPDATE CASCADE;



ALTER TABLE  Bill
ADD CONSTRAINT Bill_orderId_fk FOREIGN KEY (billId) REFERENCES  OrderTable (orderId) ON DELETE CASCADE ON UPDATE CASCADE;