package interfaces;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * Interface for the LogIn Lambda expression
 */
public interface LogInTracker {
    String string(String currentUser, Timestamp ts, String message);
}
