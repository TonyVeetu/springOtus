package uteevbkru.repository;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import uteevbkru.domain.Question;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.apache.commons.csv.CSVFormat.DEFAULT;
import static uteevbkru.repository.QuestionSchema.*;

public class QuestionCsvRepos {
    private static final CSVFormat FORMAT = DEFAULT.withHeader(QuestionSchema.class).withSkipHeaderRecord(true);
    private final Collection<Question> cache = new HashSet<>();
    private static String FILE_PATH = "./resources/csv/question.csv";

    public QuestionCsvRepos(final String questionCsvUri) throws IOException {
        try (final Reader reader = new FileReader(FILE_PATH);
             final CSVParser parser = FORMAT.parse(reader)) {
            int nextId = 0;
            for (final CSVRecord record : parser.getRecords()) {
                cache.add(toQuestion(nextId++, record));
            }
        }
    }

    private Question toQuestion(final int id, final CSVRecord record) {
        return new Question(
                id,
                record.get(FORMULATION),
                record.get(ANSWER),
                asList(record.get(OPTION_1), record.get(OPTION_2), record.get(OPTION_3))
        );
    }
}
