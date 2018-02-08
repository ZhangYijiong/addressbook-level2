package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street, some unit, some postalcode";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses can only be in the format a/BLOCK, STREET, UNIT, POSTAL_CODE";
    public static final String ADDRESS_VALIDATION_REGEX = ".+, .+, .+, .+";

    public final String value;
    private boolean isPrivate;
    private Block block;
    private Street street;
    private Unit unit;
    private PostalCode postalCode;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();
        this.isPrivate = isPrivate;
        if (!isValidAddress(trimmedAddress)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        String[] splitedAddress = splitAddress(trimmedAddress);
        value = formAddress(splitedAddress);
    }

    private String[] splitAddress(String trimmedAddress) {
        return trimmedAddress.split(", ", 4);
    }

    private String formAddress(String[] splitAddress) {
        block = new Block(splitAddress[0]);
        street = new Street(splitAddress[1]);
        unit = new Unit(splitAddress[2]);
        postalCode = new PostalCode(splitAddress[3]);
        String result = block + ", " + street + ", " + unit + ", " + postalCode;
        return result;
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }

}

class Block{
    private String blockName;

    public Block(String name){
        blockName = name;
    }

    private String getBlockName(){
        return blockName;
    }

    private void setBlockName(String newBlockName){
        blockName = newBlockName;
    }

}
class Street {
    private String streetName;

    public Street(String name) {
        streetName = name;
    }
    private String getStreetName(){
        return streetName;
    }

    private void setStreetName(String newStreetName){
        streetName = newStreetName;
    }
}
class Unit {
    private String unitName;

    public Unit(String name) {
        unitName = name;

    }
    private String getUnitName(){
        return unitName;
    }

    private void setUnitName(String newUnitName){
        unitName = newUnitName;
    }
}
class PostalCode {
    private String postalCode;

    public PostalCode(String number) {
        postalCode = number;
    }
    private String getPostalCode(){
        return postalCode;
    }

    private void setPostalCode(String newPostalCode){
        postalCode = newPostalCode;
    }
}