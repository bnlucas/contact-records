package com.bnlucas.contactrecords.util;

import com.bnlucas.contactrecords.domain.Contact;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private static final Logger LOG = LogManager.getLogger();

    public static List<Contact> readContactFile(MultipartFile csvFile) {
        try {
            String csvFileContents = new String(csvFile.getBytes());
            // Strip out hidden characters added from MultipartFile
            csvFileContents = csvFileContents.replaceAll("^[\\W]","");

            CsvMapper csvMapper = new CsvMapper();
            CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();

            MappingIterator<Contact> contactMappingIterator = csvMapper
                    .readerFor(Contact.class)
                    .with(csvSchema)
                    .readValues(csvFileContents);

            return contactMappingIterator.readAll();
        } catch (IOException e) {
            LOG.error("Unable to process csv file \"{}\"", csvFile.getOriginalFilename(), e.getMessage(), e);
            return new ArrayList<>();
        }
    }
}
