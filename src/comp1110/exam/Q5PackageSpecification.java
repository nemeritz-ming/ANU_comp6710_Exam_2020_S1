package comp1110.exam;

import java.util.Random;

/**
 * COMP1110 Exam, Question 5
 * <p>
 * This class represents a specification of a software package that can be used
 * by a package manager like the Debian package manager `dpkg`.
 * A package specification includes a package name, a major version number, and
 * a minor version number, for example:
 * - "openjdk-11 11.08"
 * - "gimp 2.8"
 * - "git 1.2"
 */
public class Q5PackageSpecification {

    /**
     * The package name is a string of ASCII characters.
     */
    String name;

    /**
     * The major version number is a number from 0 to 99
     */
    int majorVersionNumber;

    /**
     * The minor version number is a number from 0 to 99.
     */
    int minorVersionNumber;

    public Q5PackageSpecification(String name, int majorVersionNumber, int minorVersionNumber) {
        if (name == null) throw new IllegalArgumentException("Package name may not be null!");
        this.name = name;
        this.majorVersionNumber = majorVersionNumber;
        this.minorVersionNumber = minorVersionNumber;
    }

    /**
     * @return a hash code value for this object.
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new Random().nextInt(2); // FIXME complete this method
    }

    /**
     * @return true if this package specification is equal to the provided object
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        return false; // FIXME complete this method
    }

    @Override
    public String toString() {
        return name + " " + majorVersionNumber + "." + minorVersionNumber;
    }

    // DO NOT DELETE OR CALL THIS METHOD
    int passThroughHash() {
        return super.hashCode();
    }
}
