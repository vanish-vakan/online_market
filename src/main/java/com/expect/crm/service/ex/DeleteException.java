package com.expect.crm.service.ex;

public class DeleteException extends ServiceException{
    public DeleteException() {
        super();
    }

    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteException(Throwable cause) {
        super(cause);
    }

    @Override
    public void printStackTrace(String message) {
        super.printStackTrace(message);
    }
}
