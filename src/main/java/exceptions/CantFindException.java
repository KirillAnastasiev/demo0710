package exceptions;

import java.sql.SQLException;

public class CantFindException extends Exception {

    public CantFindException() {
        super();
    }

    public CantFindException(SQLException cause) {
        super(cause);
    }
}
