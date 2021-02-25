package lk.ijse.studentManagement.assets.validator;

import java.util.regex.Pattern;

public class Validator {

    public boolean Contact(String value) {
        if (Pattern.compile("^[0-9]{10}$").matcher(value).matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean Name(String value) {
        if (Pattern.compile("^[A-z]{1,}|[A-z]{1,}( )[A-z]{1,}$").matcher(value).matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean Double(String value) {
        if (Pattern.compile("^[0-9]{1,}(.)$").matcher(value).matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean DOB(String value) {
        if (Pattern.compile("^[0-9]{4}[-][0-9]{2}[-][0-9]{2}$").matcher(value).matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean Password(String value) {
        if (Pattern.compile("^[A-z0-9]{1,}$").matcher(value).matches()) {
            return true;
        } else {
            return false;
        }
    }
}
