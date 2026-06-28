CREATE TABLE IF NOT EXISTS `cards` (
    `card_id` int NOT NULL AUTO_INCREMENT,
    `mobile_number` varchar(15) NOT NULL,
    `card_number` varchar(100) NOT NULL,
    `card_type` varchar(100) NOT NULL,
    `total_limit` numeric(9,2) NOT NULL,
    `amount_used` numeric(9,2) NOT NULL,
    `available_amount` numeric(9,2) NOT NULL,
    `created_at` date NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`card_id`)
    );