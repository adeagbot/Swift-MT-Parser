create table mt_message{
	 id 			STRING,
	 record_date	TIMESTAMP,
	 direction      STRING,
	 message_type   STRING,
	 region         STRING,
	 block          STRING,
	 field          STRING,
	 values         ARRAY<STRING> 
}ROW FORMAT DELIMITED FIELDS TERMINATED BY "," ESCAPED BY '\\'
COLLECTION ITEMS TERMINATED BY '|'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE 

