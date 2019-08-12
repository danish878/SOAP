package org.danny.messenger.exception;

public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -8830404978971721276L;

    public DataNotFoundException(String message) {
        super(message);
    }

}
