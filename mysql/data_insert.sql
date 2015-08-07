use iotags;

#INSERTS ANALOG DATA
Insert into analog_data (time, value, tag)  values (20140506, 11.01, 1);
Insert into analog_data (time, value, tag)  values ("2014-05-06 10:24:00", 22.02, 1);

SELECT * FROM analog_data;

#INSERTS DIGITAL DATA
Insert into digital_data (time, value, tag)  values ("2015/08/03", FALSE, 2);
Insert into digital_data (time, value, tag)  values ("2015/08/03 00:00:01", true, 2);
Insert into digital_data (time, value, tag)  values ("2015/08/03 00:00:02", 0, 2);
Insert into digital_data (time, value, tag)  values ("2015/08/03 00:00:03", 1, 2);

SELECT * FROM digital_data;