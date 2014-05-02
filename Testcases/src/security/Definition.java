package security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

import annotation.IAnnotationDAO;
import constraints.ConstraintProgramCounterRef;
import constraints.ConstraintParameterRef;
import constraints.ConstraintReturnRef;
import constraints.IConstraint;
import constraints.IConstraintComponent;
import constraints.LEQConstraint;
import exception.AnnotationInvalidConstraintsException;
import security.Definition.Constraints;

@Constraints({ "@pc <= low" })
public class Definition extends ALevelDefinition {

	@Target({ ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Constraints {

		String[] value() default {};

	}

	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface FieldSecurity {

		String value();

	}

	@Target({ ElementType.METHOD, ElementType.CONSTRUCTOR })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface ParameterSecurity {

		String[] value();

	}

	@Target({ ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface ReturnSecurity {

		String value();

	}

	@Target({ ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface WriteEffect {

		String[] value();

	}

	public final class StringLevel extends ALevel {

		private final String level;

		public StringLevel(String level) {
			this.level = level;
		}

		@Override
		public String getName() {
			return level;
		}

		@Override
		public String toString() {
			return level;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) return true; 
			if (obj == null) return false;
			if (obj.getClass() != getClass()) return false;
			StringLevel lev = (StringLevel) obj;
			return lev.level.equals(level);
			
		}

	}

	private final ILevel low = new StringLevel("low");
	private final ILevel high = new StringLevel("high");

	public Definition() {
		super(FieldSecurity.class, ParameterSecurity.class, ReturnSecurity.class, WriteEffect.class, Constraints.class);
	}

	@Override
	public int compare(ILevel level1, ILevel level2) {
		if (level1.equals(level2)) {
			return 0;
		} else {
			if (level1.equals(high)) {
				return 1;
			} else {
				if (level1.equals(low) || level2.equals(high)) {
					return -1;
				} else {
					return 1;
				}
			}
		}
	}

	@Override
	public ILevel getGreatesLowerBoundLevel() {
		return low;
	}

	@Override
	public ILevel getLeastUpperBoundLevel() {
		return high;
	}

	@Override
	public ILevel[] getLevels() {
		return new ILevel[] { low, high };
	}

	@Override
	public ILevel extractFieldLevel(IAnnotationDAO dao) {
		return new StringLevel(dao.getStringFor("value"));
	}

	@Override
	public List<ILevel> extractParameterLevels(IAnnotationDAO dao) {
		List<ILevel> list = new ArrayList<ILevel>();
		for (String value : dao.getStringArrayFor("value")) {
			list.add(new StringLevel(value));
		}
		return list;
	}

	@Override
	public ILevel extractReturnLevel(IAnnotationDAO dao) {
		return new StringLevel(dao.getStringFor("value"));
	}

	@Override
	public List<ILevel> extractEffects(IAnnotationDAO dao) {
		List<ILevel> list = new ArrayList<ILevel>();
		for (String value : dao.getStringArrayFor("value")) {
			list.add(new StringLevel(value));
		}
		return list;
	}

	@Override
	public List<IConstraint> extractConstraints(IAnnotationDAO dao, String signature) {
		List<IConstraint> constraints = new ArrayList<IConstraint>();
		List<String> rawConstraints = dao.getStringArrayFor("value");
		for (String constraint : rawConstraints) {
			String errMsg = String.format("The specified constraint '%s' is invalid.", constraint);
			if (constraint.contains("<=")) {
				String[] components = constraint.split("<=");
				if (components.length == 2) {
					String lhs = components[0].trim();
					String rhs = components[1].trim();
					IConstraintComponent l = convertIntoConstraintComponent(lhs, signature);
					IConstraintComponent r = convertIntoConstraintComponent(rhs, signature);
					constraints.add(new LEQConstraint(l, r));
				} else {
					throw new AnnotationInvalidConstraintsException(errMsg);
				}
			} else if (constraint.contains("=")) {
				String[] components = constraint.split("=");
				if (components.length == 2) {
					String lhs = components[0].trim();
					String rhs = components[1].trim();
					IConstraintComponent l = convertIntoConstraintComponent(lhs, signature);
					IConstraintComponent r = convertIntoConstraintComponent(rhs, signature);
					constraints.add(new LEQConstraint(l, r));
					constraints.add(new LEQConstraint(r, l));
				} else {
					throw new AnnotationInvalidConstraintsException(errMsg);
				}
			} else if (constraint.contains(">=")) {
				String[] components = constraint.split(">=");
				if (components.length == 2) {
					String lhs = components[0].trim();
					String rhs = components[1].trim();
					IConstraintComponent l = convertIntoConstraintComponent(lhs, signature);
					IConstraintComponent r = convertIntoConstraintComponent(rhs, signature);
					constraints.add(new LEQConstraint(r, l));
				} else {
					throw new AnnotationInvalidConstraintsException(errMsg);
				}
			} else {
				throw new AnnotationInvalidConstraintsException(errMsg);
			}
		}
		return constraints;
	}

	private IConstraintComponent convertIntoConstraintComponent(String component, String signature) {
		if (component.startsWith("@")) {
			String position = component.substring(1);
			if (position.equals("return")) {
				return new ConstraintReturnRef(signature);
			} else if (position.equals("pc")) {
				return new ConstraintProgramCounterRef();
			} else {
				return new ConstraintParameterRef(Integer.valueOf(position), signature);
			}
		} else {
			return new StringLevel(component);
		}
	}

	@ParameterSecurity({ "high" })
	@ReturnSecurity("high")
	@Constraints({ "@pc <= low", "@0 <= high", "@return = high" })
	public static <T> T mkHigh(T object) {
		return object;
	}

	@ParameterSecurity({ "low" })
	@ReturnSecurity("low")
	@Constraints({ "@pc <= low", "@0 <= low", "@return = low" })
	public static <T> T mkLow(T object) {
		return object;
	}

}