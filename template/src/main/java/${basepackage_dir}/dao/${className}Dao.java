<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.dao;

import com.yufei.base.BaseDao;
import ${basepackage}.model.${className};

public interface ${className}Dao extends BaseDao<${className}, ${table.idColumn.javaType}>{

<#list table.columns as column>
    <#if column.unique && !column.pk>
    public ${className} getBy${column.columnName}(${column.javaType} v);
    </#if>
</#list>

}
