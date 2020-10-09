package exceptions;

import java.sql.SQLException;

public class CantFindCountryException extends CantFindException {

    public CantFindCountryException() {
        super();
    }

    public CantFindCountryException(SQLException cause) {
        super(cause);
    }
}
