package org.chat.domain.valueObjects;

import org.chat.application.exception.InvalidChatNameException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Name {
    private String value;

    public Name(String value) {
        setValue(value);
    }

    public String getName() {
        return value;
    }

    public void setValue(String value) {
        Pattern pattern = Pattern.compile("[^0-9^a-z^A-Z]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(value);

        boolean matchFound = matcher.find();
        if (value.length() > 3 && !matchFound) {
            this.value = value;
        } else {
            throw new InvalidChatNameException("Name must be bigger than 3 characters and have only letters and numbers");
        }
    }
}
