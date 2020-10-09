package exceptions;

import java.sql.SQLException;

public class CantFindVendorException extends CantFindException {

    public CantFindVendorException() {
        super();
    }

    public CantFindVendorException(SQLException cause) {
        super(cause);
    }
}
