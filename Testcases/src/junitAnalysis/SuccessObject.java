package junitAnalysis;

import security.Definition;
import security.Definition.*;

public class SuccessObject {
	
	@ParameterSecurity({"low"})
	public static void main(String[] args) {}
	
	@ReturnSecurity("low")
	@WriteEffect({"low", "high"})
	public int accessFieldLow() {
		FieldObject fo = new FieldObject();
		return fo.low;
	}
	
	@ReturnSecurity("low")
	@WriteEffect({"low", "high"})
	public int accessFieldLow2() {
		FieldObject fo = new FieldObject();
		fo = Definition.lowId(fo);
		return fo.low;
	}

	@ReturnSecurity("high")
	@WriteEffect({"low", "high"})
	public int accessFieldLow3() {
		FieldObject fo = new FieldObject();
		fo = Definition.highId(fo);
		return fo.low;
	}
	
	@ReturnSecurity("high")
	@WriteEffect({"low", "high"})
	public int accessFieldLow4() {
		FieldObject fo = new FieldObject();
		return fo.low;
	}
	
	@ReturnSecurity("high")
	@WriteEffect({"low", "high"})
	public int accessFieldHigh() {
		FieldObject fo = new FieldObject();
		return fo.high;
	}
	
	@ReturnSecurity("high")
	@WriteEffect({"low", "high"})
	public int accessFieldHigh2() {
		FieldObject fo = new FieldObject();
		fo = Definition.lowId(fo);
		return fo.high;
	}

	@ReturnSecurity("high")
	@WriteEffect({"low", "high"})
	public int accessFieldHigh3() {
		FieldObject fo = new FieldObject();
		fo = Definition.highId(fo);
		return fo.high;
	}
	
	@ReturnSecurity("low")
	public int accessStaticFieldLow() {
		return StaticFieldObject.low;
	}
	
	@ReturnSecurity("high")
	public int accessStaticFieldLow2() {
		return StaticFieldObject.low;
	}
	
	@ReturnSecurity("high")
	public int accessStaticFieldHigh() {
		return StaticFieldObject.high;
	}
	
	@WriteEffect({"low", "high"})
	public void assignFieldLow() {
		int low = Definition.lowId(42);
		FieldObject fo = new FieldObject();
		fo.low = low;
		return;
	}
	
	@WriteEffect({"low", "high"})
	public void assignFieldLow2() {
		int low = Definition.lowId(42);
		FieldObject fo = new FieldObject();
		fo = Definition.highId(fo);
		fo.low = low;
		return;
	}
	
	@WriteEffect({"low", "high"})
	public void assignFieldHigh() {
		int high = Definition.highId(42);
		FieldObject fo = new FieldObject();
		fo.high = high;
		return;
	}
	
	@WriteEffect({"low", "high"})
	public void assignFieldHigh2() {
		int high = Definition.highId(42);
		FieldObject fo = new FieldObject();
		fo = Definition.highId(fo);
		fo.high = high;
		return;
	}
	
	@WriteEffect({"low", "high"})
	public void assignFieldHigh3() {
		int low = Definition.lowId(42);
		FieldObject fo = new FieldObject();
		fo.high = low;
		return;
	}
	
	@WriteEffect({"low", "high"})
	public void assignFieldHigh4() {
		int low = Definition.lowId(42);
		FieldObject fo = new FieldObject();
		fo = Definition.highId(fo);
		fo.high = low;
		return;
	}
	
	@ReturnSecurity("low")
	public int invokeSimpleLowMethod() {
		MethodObject mo = new MethodObject();
		return mo.returnLowSecurity();
	}
	
	@ReturnSecurity("low")
	public int invokeSimpleLowMethod2() {
		MethodObject mo = new MethodObject();
		mo = Definition.lowId(mo);
		return mo.returnLowSecurity();
	}
	
	@ReturnSecurity("high")
	public int invokeSimpleLowMethod3() {
		MethodObject mo = new MethodObject();
		mo = Definition.highId(mo);
		return mo.returnLowSecurity();
	}
	
	@ReturnSecurity("high")
	public int invokeSimpleHighMethod() {
		MethodObject mo = new MethodObject();
		return mo.returnHighSecurity();
	}
	
	@ReturnSecurity("high")
	public int invokeSimpleHighMethod2() {
		MethodObject mo = new MethodObject();
		mo = Definition.lowId(mo);
		return mo.returnHighSecurity();
	}
	
	@ReturnSecurity("high")
	public int invokeSimpleHighMethod3() {
		MethodObject mo = new MethodObject();
		mo = Definition.highId(mo);
		return mo.returnHighSecurity();
	}
	
	@ReturnSecurity("low")
	public int invokeLowMethod() {
		int low = Definition.lowId(42);
		MethodObject mo = new MethodObject();
		return mo.returnLowSecurity(low);
	}
	
	@ReturnSecurity("low")
	public int invokeLowMethod2() {
		int low = Definition.lowId(42);
		MethodObject mo = new MethodObject();
		mo = Definition.lowId(mo);
		return mo.returnLowSecurity(low);
	}
	
	@ReturnSecurity("high")
	public int invokeLowMethod3() {
		int low = Definition.lowId(42);
		MethodObject mo = new MethodObject();
		mo = Definition.highId(mo);
		return mo.returnLowSecurity(low);
	}
	
	@ReturnSecurity("high")
	public int invokeHighMethod() {
		int high = Definition.highId(42);
		MethodObject mo = new MethodObject();
		return mo.returnHighSecurity(high);
	}
	
	@ReturnSecurity("high")
	public int invokeHighMethod2() {
		int low = Definition.lowId(42);
		MethodObject mo = new MethodObject();
		return mo.returnHighSecurity(low);
	}
	
	@ReturnSecurity("high")
	public int invokeHighMethod3() {
		int high = Definition.highId(42);
		MethodObject mo = new MethodObject();
		mo = Definition.lowId(mo);
		return mo.returnHighSecurity(high);
	}
	
	@ReturnSecurity("high")
	public int invokeHighMethod4() {
		int high = Definition.highId(42);
		MethodObject mo = new MethodObject();
		mo = Definition.highId(mo);
		return mo.returnHighSecurity(high);
	}
	
	@ReturnSecurity("high")
	public int invokeHighMethod5() {
		int low = Definition.lowId(42);
		MethodObject mo = new MethodObject();
		mo = Definition.highId(mo);
		return mo.returnHighSecurity(low);
	}
	
	@ReturnSecurity("low")
	public int invokeSimpleLowStaticMethod() {
		return StaticMethodObject.returnLowSecurity();
	}
	
	@ReturnSecurity("high")
	public int invokeSimpleLowStaticMethod2() {
		return StaticMethodObject.returnLowSecurity();
	}
	
	@ReturnSecurity("high")
	public int invokeSimpleHighStaticMethod() {
		return StaticMethodObject.returnHighSecurity();
	}
	
	@ReturnSecurity("low")
	public int invokeLowStaticMethod() {
		int low = Definition.lowId(42);
		return StaticMethodObject.returnLowSecurity(low);
	}
	
	@ReturnSecurity("high")
	public int invokeLowStaticMethod2() {
		int low = Definition.lowId(42);
		return StaticMethodObject.returnLowSecurity(low);
	}
	
	@ReturnSecurity("high")
	public int invokeHighStaticMethod() {
		int high = Definition.highId(42);
		return StaticMethodObject.returnHighSecurity(high);
	}
	
	@ReturnSecurity("high")
	public int invokeHighStaticMethod2() {
		int low = Definition.lowId(42);
		return StaticMethodObject.returnHighSecurity(low);
	}

	public static class FieldObject {
		
		@FieldSecurity("low")
		public int low = 42;
		
		@FieldSecurity("high")
		public int high = 42;
		
		@WriteEffect({"low", "high"})
		public FieldObject() {
			super();
		}
		
	}

	@WriteEffect({"low", "high"})
	public static class StaticFieldObject {
		
		@FieldSecurity("low")
		public static int low = 42;
		
		@FieldSecurity("high")
		public static int high = 42;
		
	}

	public static class MethodObject {
		
		@ReturnSecurity("low")
		public int returnLowSecurity() {
			return Definition.lowId(42);
		}
		
		@ReturnSecurity("high")
		public int returnHighSecurity() {
			return Definition.highId(42);
		}
		
		@ParameterSecurity({"low"})
		@ReturnSecurity("low")
		public int returnLowSecurity(int low) {
			return low;
		}
		
		@ParameterSecurity({"high"})
		@ReturnSecurity("high")
		public int returnHighSecurity(int high) {
			return high;
		}
		
	}

	public static class StaticMethodObject {
		
		@ReturnSecurity("low")
		public static int returnLowSecurity() {
			return Definition.lowId(42);
		}
		
		@ReturnSecurity("high")
		public static int returnHighSecurity() {
			return Definition.highId(42);
		}
		
		@ParameterSecurity({"low"})
		@ReturnSecurity("low")
		public static int returnLowSecurity(int low) {
			return low;
		}
		
		@ParameterSecurity({"high"})
		@ReturnSecurity("high")
		public static int returnHighSecurity(int high) {
			return high;
		}
		
	}
	
}