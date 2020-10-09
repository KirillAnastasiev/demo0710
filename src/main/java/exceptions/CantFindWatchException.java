package exceptions;

import java.sql.SQLException;

public class CantFindWatchException extends CantFindException {

    public CantFindWatchException() {
        super();
    }

    public CantFindWatchException(SQLException cause) {
        super(cause);
    }
}
