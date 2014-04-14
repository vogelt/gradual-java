package security;

import java.lang.annotation.Annotation;
import java.util.List;

import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import annotation.IAnnotationDAO;
import constraints.Constraints;

public interface ILevelDefinition {

	public int compare(ILevel level1, ILevel level2);

	public Constraints extractConstraints(IAnnotationDAO dao);

	public List<ILevel> extractEffects(IAnnotationDAO dao);

	public ILevel extractFieldLevel(IAnnotationDAO dao);

	public List<ILevel> extractParameterLevels(IAnnotationDAO dao);

	public ILevel extractReturnLevel(IAnnotationDAO dao);

	public Class<? extends Annotation> getAnnotationClassConstraints();

	public Class<? extends Annotation> getAnnotationClassEffects();

	public Class<? extends Annotation> getAnnotationClassFieldLevel();

	public Class<? extends Annotation> getAnnotationClassParameterLevel();

	public Class<? extends Annotation> getAnnotationClassReturnLevel();

	public ILevel getDefaultVariableLevel();

	public ILevel getGreatesLowerBoundLevel();

	public ILevel getGreatestLowerBoundLevel(ILevel level1, ILevel level2);

	public ILevel getLeastUpperBoundLevel();

	public ILevel getLeastUpperBoundLevel(ILevel level1, ILevel level2);

	public ILevel[] getLevels();

	public List<ILevel> getLibraryClassWriteEffects(SootClass sootClass);

	public Constraints getLibraryConstraints(SootMethod sootMethod);

	public ILevel getLibraryFieldLevel(SootField sootField);

	public List<ILevel> getLibraryMethodWriteEffects(SootMethod sootMethod);

	public List<ILevel> getLibraryParameterLevel(SootMethod sootMethod);

	public ILevel getLibraryReturnLevel(SootMethod sootMethod, List<ILevel> levels);

}