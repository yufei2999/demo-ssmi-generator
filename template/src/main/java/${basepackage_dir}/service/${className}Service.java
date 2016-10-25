<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.service;

import com.yufei.base.BaseService;
import ${basepackage}.model.${className};

public interface ${className}Service extends BaseService<${className}, ${table.idColumn.javaType}> {

<#list table.columns as column>
	<#if column.unique && !column.pk>
	@Transactional(readOnly=true)
	public ${className} getBy${column.columnName}(${column.javaType} v);
	</#if>
</#list>

}
