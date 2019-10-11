package ${packageName};

public class ${underscoreToCamelCase(classToResource(fragmentClass))}Presenter extends ${underscoreToCamelCase(classToResource(fragmentClass))}Contract.Presenter {

    @Override
    protected ${underscoreToCamelCase(classToResource(fragmentClass))}Contract.Model createModel() {
        return new ${underscoreToCamelCase(classToResource(fragmentClass))}Model();
    }
}
