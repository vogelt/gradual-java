package annotationValidity;

import security.Annotations.ParameterSecurity;
import security.Annotations.ReturnSecurity;

public class Invalid11 {
	
	@ParameterSecurity({"low"})
	public static void main(String[] args) {}

	// too few parameter security levels
	@ReturnSecurity("high")
	public int methodInclusivParameter(int arg) {
		return arg;
	}

}
// @error("For each method parameter should exist exactly one security level.")