-- Project phase 1
-- schema
-- Craig Donato - crd69
-- Sam Skupien - sss78
--
-- BoutiqueCoffee database

-- drop table statements
-- ------------------------------------------------------------------------------------------------------------------
DROP TABLE IF EXISTS Store CASCADE;
DROP TABLE IF EXISTS Coffee CASCADE;
DROP TABLE IF EXISTS Promotion CASCADE;
DROP TABLE IF EXISTS MemberLevel CASCADE;
DROP TABLE IF EXISTS Customer CASCADE;
DROP TABLE IF EXISTS Purchase CASCADE;
DROP TABLE IF EXISTS OfferCoffee CASCADE;
DROP TABLE IF EXISTS HasPromotion CASCADE;
DROP TABLE IF EXISTS PromoteFor CASCADE;
DROP TABLE IF EXISTS BuyCoffee CASCADE;
-- ------------------------------------------------------------------------------------------------------------------

-- ------------------------------------------------------------------------------------------------------------------
-- Table STORE
-- Primary Key - Store_ID
-- Foreign Key - none
-- Alt Key     - Name
-- Assumptions - All store names will be unique to the each chain of the BoutiqueCoffee chain.
--             - Address, Store_Type, GPS_Long, and GPS_Lat may be left null if a shop is in the middle of
--               construction.
-- ------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS Store(
    Store_ID     SERIAL NOT NULL,
    Name         VARCHAR(20) NOT NULL,
    Address      VARCHAR(20),
    Store_Type   VARCHAR(20),
    GPS_Long     FLOAT,
    GPS_Lat      FLOAT,
    CONSTRAINT Store_PK PRIMARY KEY (Store_ID),
    CONSTRAINT Store_AK UNIQUE (Name)
);

-- ------------------------------------------------------------------------------------------------------------------
-- Table COFFEE
-- Primary Key - Coffee_ID
-- Foreign Key - none
-- Alt Key     - Name
-- Assumptions - All coffees must have a unique name.
--             - The price of a cup coffee can not be less then $1.50.
--             - The price of a cup coffee will default to $1.50.
--             - The price can not be null.
--             - The intensity will default to 0 and can not be less then 0. (0 indicates it needs updated)
--             - The intensity can not be null.
--             - The reward points can not be more then 10% of its redeem points
-- ------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS Coffee(
    Coffee_ID     SERIAL NOT NULL,
    Name          VARCHAR(20) NOT NULL,
    Description   VARCHAR(20),
    Intensity     INTEGER DEFAULT ( 0 ) NOT NULL,
    Price         FLOAT DEFAULT ( 1.50 ) NOT NULL,
    Reward_Points FLOAT,
    Redeem_Points FLOAT,
    CONSTRAINT Coffee_PK PRIMARY KEY (Coffee_ID),
    CONSTRAINT Coffee_AK UNIQUE (Name),
    CONSTRAINT Coffee_CHK_Price CHECK ( Price >= 1.50 ),
    CONSTRAINT Coffee_CHK_Intensity CHECK ( Intensity >= 0 ),
    CONSTRAINT Coffee_CHK_Reward CHECK ( Reward_Points <= Redeem_Points * 0.10 )
);

-- ------------------------------------------------------------------------------------------------------------------
-- Table PROMOTION
-- Primary Key - Promotion_ID
-- Foreign Key - none
-- Assumptions - The end date can not be before the start date
--             - A promotion must have a start and end date
-- ------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS Promotion(
    Promotion_ID  SERIAL NOT NULL,
    Name          VARCHAR(20),
    Start_Date    date NOT NULL,
    End_Date      date NOT NULL,
    CONSTRAINT Promotion_PK PRIMARY KEY (Promotion_ID),
    CONSTRAINT Promotion_CHK_EndDate CHECK ( End_Date > Start_Date )
);

-- ------------------------------------------------------------------------------------------------------------------
-- Table MEMBERLEVEL
-- Primary Key - MemberLevel_ID
-- Foreign Key - none
-- Assumptions - A members booster factor can not be less then 0.00.
--             - A booster factor will default to 0.00.
-- ------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS MemberLevel(
    MemberLevel_ID    SERIAL NOT NULL,
    Name              VARCHAR(20),
    Booster_Factor    float DEFAULT ( 0.00 ),
    CONSTRAINT MemberLevel_PK PRIMARY KEY (MemberLevel_ID),
    CONSTRAINT MemberLevel_CHK_BF CHECK ( Booster_Factor >= 0 )
);

-- ------------------------------------------------------------------------------------------------------------------
-- Table CUSTOMER
-- Primary Key - Customer_ID
-- Foreign Key - MemberLevel_ID references MEMBERLEVEL table MemberLevel_ID
-- Assumptions - Customer first and last names must be entered.
--             - MemberLevel_ID will default to 1.
--             - The total_points can not be less then 0 and will default to 0.
-- ------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS Customer(
    Customer_ID        SERIAL NOT NULL,
    First_Name         VARCHAR(20) NOT NULL,
    Last_Name          VARCHAR(20) NOT NULL,
    Email              VARCHAR(20),
    MemberLevel_ID     INTEGER DEFAULT ( 1 ),
    Total_Points       FLOAT DEFAULT ( 0.00 ),
    CONSTRAINT Customer_PK PRIMARY KEY (Customer_ID),
    CONSTRAINT Customer_FK FOREIGN KEY (MemberLevel_ID) REFERENCES MemberLevel (MemberLevel_ID),
    CONSTRAINT Customer_CHK_Points CHECK ( Total_Points >= 0 )
);

-- ------------------------------------------------------------------------------------------------------------------
-- Table PURCHASE
-- Primary Key - Purchase_ID
-- Foreign Key - 1. Customer_ID references CUSTOMER table Customer_ID
--             - 2. Store_ID references STORE table Store_ID
-- Assumptions - The purchase time will default to the current database system timestamp
-- ------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS Purchase(
    Purchase_ID       SERIAL NOT NULL,
    Customer_ID       INTEGER,
    Store_ID          INTEGER,
    Purchase_Time     timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT Purchase_PK PRIMARY KEY (Purchase_ID),
    CONSTRAINT Purchase_FK_1 FOREIGN KEY (Customer_ID) REFERENCES Customer (Customer_ID),
    CONSTRAINT Purchase_FK_2 FOREIGN KEY (Store_ID) REFERENCES Store (Store_ID)
);

-- ------------------------------------------------------------------------------------------------------------------
-- Table OFFERCOFFEE
-- Primary Key - (Store_ID, Coffee_ID)
-- Foreign Key - 1. Store_ID references STORE table Store_ID
--             - 2. Coffee_ID references COFFEE table Coffee_ID
-- Assumptions - this is a table to link coffee being sold to a store.
-- ------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS OfferCoffee(
    Store_ID     INTEGER NOT NULL,
    Coffee_ID    INTEGER NOT NULL,
    CONSTRAINT OfferCoffee_PK PRIMARY KEY (Store_ID, Coffee_ID),
    CONSTRAINT OfferCoffee_FK_1 FOREIGN KEY (Store_ID) REFERENCES Store (Store_ID),
    CONSTRAINT OfferFoffee_FK_2 FOREIGN KEY (Coffee_ID) REFERENCES Coffee (Coffee_ID)
);

-- ------------------------------------------------------------------------------------------------------------------
-- Table HASPROMOTION
-- Primary Key - (Store_ID, Promotion_ID)
-- Foreign Key - 1. Store_ID references STORE table Store_ID
--             - 2. Promotion_ID refernces PROMOTION table Promotion_ID
-- Assumptions - this table links promotions available to a store.
-- ------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS HasPromotion(
    Store_ID      INTEGER NOT NULL,
    Promotion_ID  INTEGER NOT NULL,
    CONSTRAINT HasPromotion_PK PRIMARY KEY (Store_ID, Promotion_ID),
    CONSTRAINT HasPromotion_FK_1 FOREIGN KEY (Store_ID) REFERENCES Store (Store_ID),
    CONSTRAINT HasPromotion_FK_2 FOREIGN KEY (Promotion_ID) REFERENCES Promotion (Promotion_ID)
);

-- ------------------------------------------------------------------------------------------------------------------
-- Table PROMOTEFOR
-- Primary Key - (Promotion_ID, Coffee_ID)
-- Foreign Key - 1. Promotion_ID references PROMOTION table Promotion_ID
--             - 2. Coffee_ID references COFFEE table Coffee_ID
-- Assumptions - this table just links specific coffee to available coffees.
-- ------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS PromoteFor(
    Promotion_ID   INTEGER NOT NULL,
    Coffee_ID      INTEGER NOT NULL,
    CONSTRAINT PromoteFor_PK PRIMARY KEY (Promotion_ID, Coffee_ID),
    CONSTRAINT PromoteFor_FK_1 FOREIGN KEY (Promotion_ID) REFERENCES Promotion (Promotion_ID),
    CONSTRAINT PromoteFor_FK_2 FOREIGN KEY (Coffee_ID) REFERENCES Coffee (Coffee_ID)
);

-- ------------------------------------------------------------------------------------------------------------------
-- Table BUYCOFFEE
-- Primary Key - (Purchase_ID, Coffee_ID)
-- Foreign Key - 1. Purchase_ID references PURCHASE table Purchase_ID
--             - 2. Coffee_ID references COFFEE table Coffee_ID
-- ------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS BuyCoffee(
    Purchase_ID         INTEGER NOT NULL,
    Coffee_ID           INTEGER NOT NULL,
    Purchase_Quantity   INTEGER,
    Redeem_Quantity     INTEGER,
    CONSTRAINT BuyCoffee_PK PRIMARY KEY (Purchase_ID, Coffee_ID),
    CONSTRAINT BuyCoffee_FK_1 FOREIGN KEY (Purchase_ID) REFERENCES Purchase (Purchase_ID),
    CONSTRAINT BuyCoffee_FK_2 FOREIGN KEY (Coffee_ID) REFERENCES Coffee (Coffee_ID)
);
-- ------------------------------------------------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------------


