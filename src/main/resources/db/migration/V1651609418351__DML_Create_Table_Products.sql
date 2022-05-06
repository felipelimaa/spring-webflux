CREATE TABLE IF NOT EXISTS `Products` (
    Products_Id BIGINT NOT NULL AUTO_INCREMENT,

    Brand VARCHAR(255) NULL,

    Description VARCHAR(255) NULL,

    Color VARCHAR(255) NULL,

    Price decimal(11,2) NULL,

    PRIMARY KEY (`Products_Id`)
)
ENGINE = InnoDB;