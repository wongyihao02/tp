package command.studentcommands;

import exception.TASyncException;

public class StudentCommandHelper {

    /**
     * Parses the input string and splits it into parts
     *
     * @param parts The input string.
     * @return A string array containing the necessary number of parts
     * @throws TASyncException if the input format is invalid.
     */
    public static String[] parseInput(String parts, int length) throws TASyncException {
        if (parts == null || parts.trim().isEmpty()) {
            throw TASyncException.invalidCommand();
        }

        String[] partsArray = parts.split(",");
        if (partsArray.length != length) {
            throw TASyncException.invalidCommand();
        }

        return partsArray;
    }


}
