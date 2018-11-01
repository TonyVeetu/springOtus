package uteevbkru.domain;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Collections.unmodifiableList;

public class Question {

    private final long id;
    private final String formulation;
    private final String answer;
    private final Collection<String> options;

    public Question(final long id, final String formulation, final String answer, final Collection<String> options) {
        this.id = id;
        this.formulation = formulation;
        this.answer = answer;
        this.options = unmodifiableList(new ArrayList<>(options));

        if (!this.options.contains(answer)) {
            throw new IllegalArgumentException("Options must be contains the answer");
        }
    }
}
