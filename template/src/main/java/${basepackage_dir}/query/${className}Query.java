<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.query;

import com.yufei.base.BaseQuery;
import ${basepackage}.model.${className};
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ${className}Query extends BaseQuery<${className}> {
    
    private static final long serialVersionUID = 1L;

    <@generateFields/>
	<@generateProperties/>

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

<#macro generateFields>
	<#list table.columns as column>
	/**
	 * ${column.columnAlias}
	 */
	private ${column.javaType} ${column.columnNameLower};
	</#list>
</#macro>

<#macro generateProperties>
	<#list table.columns as column>
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}
	</#list>
</#macro>