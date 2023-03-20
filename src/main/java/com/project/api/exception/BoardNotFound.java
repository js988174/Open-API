package com.project.api.exception;

public class BoardNotFound extends Exception {

    private static final String MESSAGE = "존재하지 않는 글입니다.";

    public BoardNotFound() {
        super(MESSAGE);
    }

    @Override
    public int statusCode() {
        return 404;
    }
}
