<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.service.impl;

import cn.org.rapid_framework.page.Page;
import ${basepackage}.dao.${className}Dao;
import ${basepackage}.model.${className};
import ${basepackage}.query.${className}Query;
import ${basepackage}.service.${className}Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("${classNameLower}Service")
@Transactional
public class ${className}ServiceImpl implements ${className}Service {

	@Autowired
	private ${className}Dao ${classNameLower}Dao;

	@Transactional(readOnly=true)
	public Page<${className}> findPage(${className}Query query) {
		return ${classNameLower}Dao.findPage(query);
	}
	@Transactional(readOnly=true)
	public List<${className}> findList(${className}Query query) {
		return ${classNameLower}Dao.findList(query);
	}
	public Integer findListCount(${className}Query query) {
		return ${classNameLower}Dao.findListCount(query);
	}
	@Transactional(readOnly=true)
	public List<${className}> findAll() {
		return ${classNameLower}Dao.findAll();
	}
	@Transactional(readOnly=true)
	public ${className} getById(${table.idColumn.javaType} id) {
		return ${classNameLower}Dao.getById( id);
	}
	@Transactional(readOnly=true)
	public ${className} getObject(${className}Query query) {
		return ${classNameLower}Dao.getObject(query);
	}
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void save(${className} entity) {
		${classNameLower}Dao.save(entity);
	}
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void update(${className} entity) {
		${classNameLower}Dao.update(entity);
	}
	@Transactional
	public void removeByIds(List<String> ids) {
		${classNameLower}Dao.removeByIds(ids);
	}

}
