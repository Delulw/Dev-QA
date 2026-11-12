package com.base_student.constants;

public final class Constants {

    public static final String RECORD_NOT_FOUND = "Record not found";
    public static final String MAIL_NOT_VALID_FORMAT = "The email is not in a valid format";
    public static final String NAME_MUST_NOT_BE_EMPTY = "The name must not be empty";
    public static final String UTILITY_CLASS = "This is a utility class and cannot be instantiated.";

    private Constants() {
        throw new UnsupportedOperationException(UTILITY_CLASS);
    }

}
