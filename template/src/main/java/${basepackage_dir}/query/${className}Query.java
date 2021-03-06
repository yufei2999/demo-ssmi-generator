<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.query;

import com.yufei.base.BaseQuery;
import ${basepackage}.model.${className};
import java.util.Calendar;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

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
	/** ${column.columnAlias} */
	<#if column.isDateTimeColumn && !column.contains("begin,start,end")>
	@DateTimeFormat(pattern = DATE_FORMAT)
	private ${column.javaType} ${column.columnNameLower}Begin;
	@DateTimeFormat(pattern = DATE_FORMAT)
	private ${column.javaType} ${column.columnNameLower}End;
	<#else>
	private ${column.javaType} ${column.columnNameLower};
	</#if>
</#list>
</#macro>

<#macro generateProperties>
<#list table.columns as column>
	<#if column.isDateTimeColumn && !column.contains("begin,start,end")>
	public ${column.javaType} get${column.columnName}Begin() {
		return this.${column.columnNameLower}Begin;
	}
	public void set${column.columnName}Begin(${column.javaType} value) {
		this.${column.columnNameLower}Begin = value;
	}
	public ${column.javaType} get${column.columnName}End() {
		return this.${column.columnNameLower}End;
	}
	public void set${column.columnName}End(${column.javaType} value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.${column.columnNameLower}End = calendar.getTime();
		}else {
			this.${column.columnNameLower}End = value;
		}
	}
	<#else>
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}
	</#if>
</#list>
</#macro>