<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.dao.impl;

import com.yufei.base.BaseDaoImpl;
import com.yufei.utils.IdGenerator;
import ${basepackage}.dao.${className}Dao;
import ${basepackage}.model.${className};
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository("${classNameLower}Dao")
public class ${className}DaoImpl extends BaseDaoImpl<${className},${table.idColumn.javaType}> implements ${className}Dao {

    @Override
    public String getIbatisSqlMapNamespace() {
        return "${className}";
    }

    @Override
    protected void prepareObjectForSave(${className} entity) {
        if(StringUtils.isEmpty(entity.get${table.idColumn.columnName}())) {
            entity.set${table.idColumn.columnName}(IdGenerator.genUUIDStr());
        }
    }

<#list table.columns as column>
    <#if column.unique && !column.pk>
    public ${className} getBy${column.columnName}(${column.javaType} v) {
        return (${className})getSqlSession().selectOne("${className}.getBy${column.columnName}",v);
    }
    </#if>
</#list>

}
