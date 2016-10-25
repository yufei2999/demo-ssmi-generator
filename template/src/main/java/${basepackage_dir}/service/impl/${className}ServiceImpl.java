<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.service.impl;

import com.yufei.base.BaseDao;
import com.yufei.base.BaseServiceImpl;
import ${basepackage}.dao.${className}Dao;
import ${basepackage}.model.${className};
import ${basepackage}.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("${classNameLower}Service")
@Transactional
public class ${className}ServiceImpl extends BaseServiceImpl<${className},${table.idColumn.javaType}> implements ${className}Service {

	@Autowired
	private ${className}Dao ${classNameLower}Dao;

	public BaseDao<${className}, ${table.idColumn.javaType}> getEntityDao() {
		return this.${classNameLower}Dao;
	}

<#list table.columns as column>
	<#if column.unique && !column.pk>
	@Transactional(readOnly=true)
	public ${className} getBy${column.columnName}(${column.javaType} v) {
		return ${classNameLower}Dao.getBy${column.columnName}(v);
	}
	</#if>
</#list>

}
