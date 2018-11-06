package org.mybatis.generator.plugins;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.JavaFormatter;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.util.StringUtility;


/**
 * 
* @Title: MapperPlugin.java 
* @Package core.generator.plugin 
* @Description: TODO( 生成 Mapper 类) 
* @author 刘伟  15818570028@163.com   
* @date 2016年9月24日 下午11:58:07 
* @version V1.0
 */
public class MapperPlugin extends PluginAdapter {

	private static final String DEFAULT_DAO_SUPER_CLASS = "core.base.BaseMapper";
	private static final String DEFAULT_EXPAND_DAO_SUPER_CLASS = "core.base.BaseExpandMapper";
	private String daoTargetDir;
	private String daoTargetPackage;

	private String daoSuperClass;

	// 扩展
	private String expandDaoTargetPackage;
	private String expandDaoSuperClass;

	private ShellCallback shellCallback = null;

	public MapperPlugin() {
		shellCallback = new DefaultShellCallback(false);
	}

	public boolean validate(List<String> warnings) {
		daoTargetDir = properties.getProperty("targetProject");
		boolean valid = StringUtility.stringHasValue(daoTargetDir);

		daoTargetPackage = properties.getProperty("targetPackage");
		boolean valid2 = StringUtility.stringHasValue(daoTargetPackage);

		daoSuperClass = properties.getProperty("daoSuperClass");
		if (!StringUtility.stringHasValue(daoSuperClass)) {
			daoSuperClass = DEFAULT_DAO_SUPER_CLASS;
		}

		expandDaoTargetPackage = properties.getProperty("expandTargetPackage");
		expandDaoSuperClass = properties.getProperty("expandDaoSuperClass");
		if (!StringUtility.stringHasValue(expandDaoSuperClass)) {
			expandDaoSuperClass = DEFAULT_EXPAND_DAO_SUPER_CLASS;
		}
		return valid && valid2;
	}

	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		JavaFormatter javaFormatter = context.getJavaFormatter();
		List<GeneratedJavaFile> mapperJavaFiles = new ArrayList<GeneratedJavaFile>();
		for (GeneratedJavaFile javaFile : introspectedTable.getGeneratedJavaFiles()) {
			CompilationUnit unit = javaFile.getCompilationUnit();
			FullyQualifiedJavaType baseModelJavaType = unit.getType();

			String shortName = baseModelJavaType.getShortName();

			GeneratedJavaFile mapperJavafile = null;
			if (shortName.endsWith("Mapper")) { // 扩展Mapper
				if (StringUtility.stringHasValue(expandDaoTargetPackage)) {
					Interface mapperInterface = new Interface(
							expandDaoTargetPackage + "." + shortName.replace("Mapper", "ExpandMapper"));
					mapperInterface.setVisibility(JavaVisibility.PUBLIC);
					mapperInterface.addJavaDocLine("/**");
					mapperInterface.addJavaDocLine(" * " + shortName + "扩展");
					mapperInterface.addJavaDocLine(" */");

					FullyQualifiedJavaType daoSuperType = new FullyQualifiedJavaType(expandDaoSuperClass);
					mapperInterface.addImportedType(daoSuperType);
					mapperInterface.addSuperInterface(daoSuperType);

					mapperJavafile = new GeneratedJavaFile(mapperInterface, daoTargetDir, javaFormatter);
					try {
						File mapperDir = shellCallback.getDirectory(daoTargetDir, daoTargetPackage);
						File mapperFile = new File(mapperDir, mapperJavafile.getFileName());
						// 文件不存在
						if (!mapperFile.exists()) {
							mapperJavaFiles.add(mapperJavafile);
						}
					} catch (ShellException e) {
						e.printStackTrace();
					}
				}
			} else if (!shortName.endsWith("Example")) { // CRUD Mapper
				Interface mapperInterface = new Interface(daoTargetPackage + "." + shortName + "Mapper");

				mapperInterface.setVisibility(JavaVisibility.PUBLIC);
				mapperInterface.addJavaDocLine("/**");
				mapperInterface.addJavaDocLine(" * 由MyBatis Generator工具自动生成，请不要手动修改");
				mapperInterface.addJavaDocLine(" */");

				FullyQualifiedJavaType daoSuperType = new FullyQualifiedJavaType(daoSuperClass);
				// 添加泛型支持
				daoSuperType.addTypeArgument(baseModelJavaType);
				mapperInterface.addImportedType(baseModelJavaType);
				mapperInterface.addImportedType(daoSuperType);
				mapperInterface.addSuperInterface(daoSuperType);

				mapperJavafile = new GeneratedJavaFile(mapperInterface, daoTargetDir, javaFormatter);
				mapperJavaFiles.add(mapperJavafile);
			}
		}
		return mapperJavaFiles;
	}
}
