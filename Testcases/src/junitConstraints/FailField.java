package junitConstraints;

import static security.Definition.*;

@Constraints({ "low <= @pc" })
public class FailField {

	@FieldSecurity("low")
	public static int lowField = 42;

	@FieldSecurity("high")
	public static int highField = 42;

	@Constraints({ "low <= @pc", "@0 <= low" })
	public static void main(String[] args) {}

	@Constraints({ "low <= @pc" })
	public FailField() {}

	@Constraints({ "low <= @pc", "low <= @return" })
	public int failField1() {
		int i = highField;
		// @security("Missing signature constraint: high <= @return")
		return i;
	}

	@Constraints({ "low <= @pc" })
	public void failField2() {
		int i = mkHigh(42);
		// @security("Inequality --> high <= i, i <= low")
		lowField = i;
		return;
	}

}