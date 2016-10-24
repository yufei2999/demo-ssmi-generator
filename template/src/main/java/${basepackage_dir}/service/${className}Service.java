<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.service;

import cn.org.rapid_framework.page.Page;
import ${basepackage}.model.${className};
import ${basepackage}.query.${className}Query;
import java.util.List;

public interface ${className}Service {

	/**
	 * 列表查询-分页
	 */
	public Page<${className}> findPage(${className}Query query);
	/**
	 * 列表查询
	 */
	public List<${className}> findList(${className}Query query);
	/**
	 * 条件查询总数
	 */
	public Integer findListCount(${className}Query query);
	/**
	 * 查询全部
	 */
	public List<${className}> findAll();
	/**
	 * 根据id查询
	 */
	public ${className} getById(${table.idColumn.javaType} id);
	/**
	 * 条件查询
	 */
	public ${className} getObject(${className}Query query);
	/**
	 * 新增
	 */
	public void save(${className} entity);
	/**
	 * 更新
	 */
	public void update(${className} entity);
	/**
	 * 删除
	 */
	public void removeByIds(List<String> ids);

}
