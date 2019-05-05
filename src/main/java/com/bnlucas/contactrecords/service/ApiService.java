package com.bnlucas.contactrecords.service;

import com.bnlucas.contactrecords.domain.Contact;
import com.bnlucas.contactrecords.util.CsvReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ApiService {

    private static final Logger LOG = LogManager.getLogger();

    public Map<String, List<Contact>> processContactFile(MultipartFile multipartFile) {
        List<Contact> contacts = CsvReader.readContactFile(multipartFile);
        List<Contact> potentialDuplicateContacts = new ArrayList<>();

        // First, identify all contacts that could be potential duplicates using the metaphone tokens of
        // first_name, last_name, street name (street number removed), city, state for phonetically identical records
        List<Contact> hashCodeDuplicates = getDuplicatesByHashCode(contacts);
        contacts.removeAll(hashCodeDuplicates);
        potentialDuplicateContacts.addAll(hashCodeDuplicates);

        // Second, identify all contacts that could be phonetically different but share the same email address
        List<Contact> emailDuplicates = getDuplicatesByEmail(contacts);
        contacts.removeAll(emailDuplicates);
        potentialDuplicateContacts.addAll(emailDuplicates);

        // This could possibly be refined further, depending on how specific you want to get. You could check matching
        // addresses, phone numbers, ect. But you could possibly run into having records of Jane and John Smith that
        // share the same address and phone number which are not duplicates.

        Map<String, List<Contact>> contactsMap = new HashMap<>();
        contactsMap.put("unique", getSortedList(contacts));
        contactsMap.put("duplicates", getSortedList(potentialDuplicateContacts));

        LOG.info("Found the following potentially duplicated contacts:\n {}", potentialDuplicateContacts);
        LOG.info("Found the following unique contacts:\n {}", contacts);

        return contactsMap;
    }

    private List<Contact> getSortedList(List<Contact> contacts) {
        return new HashSet<>(contacts).stream()
                .sorted(Comparator.comparing(Contact::getId))
                .collect(Collectors.toList());
    }

    private List<Contact> getDuplicatesByHashCode(List<Contact> contacts) {
        List<Object> duplicates = getDuplicatesByKey(Contact::hashCode, contacts); // List of duplicate hashCodes

        // Only return Contact records that match the duplicated key
        return contacts.stream()
                .filter(contact -> duplicates.contains(contact.hashCode()))
                .collect(Collectors.toList());
    }

    private List<Contact> getDuplicatesByEmail(List<Contact> contacts) {
        List<Object> duplicates = getDuplicatesByKey(Contact::getEmail, contacts); // List of duplicate email addresses

        // Only return Contact records that match the duplicated key
        return contacts.stream()
                .filter(contact -> duplicates.contains(contact.getEmail()))
                .collect(Collectors.toList());
    }

    private <T> List<Object> getDuplicatesByKey(Function<? super T, Object> classifier, List<T> items) {
        // Generates a list of a specific property in the object that is seen more than once to be used as a filter later
        return items.stream()
                .collect(Collectors.groupingBy(classifier, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
