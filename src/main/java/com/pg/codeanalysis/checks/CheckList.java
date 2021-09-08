package com.pg.codeanalysis.checks;

import java.util.ArrayList;
import java.util.List;

public class CheckList {
	public static List<Check> getAllChecks(){
		List<Check> checks = new ArrayList<>();
		checks.add(new NotHardcodedCheck());
		return checks;
	}
}
