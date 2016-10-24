<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.dao.impl;

import cn.org.rapid_framework.page.Page;
import com.yufei.base.BaseDao;
import com.yufei.utils.IdGenerator;
import ${basepackage}.dao.${className}Dao;
import ${basepackage}.model.${className};
import ${basepackage}.query.${className}Query;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository("${classNameLower}Dao")
public class ${className}DaoImpl extends BaseDao<${className},${table.idColumn.javaType}> implements ${className}Dao {

    public Page findPage(${className}Query query) {
        return super.pageQuery("${className}.findPage",query);
    }
    public List<${className}> findList(${className}Query query) {
        return super.listQuery("${className}.findPage",query);
    }
    public Integer findListCount(${className}Query query) {
        return super.countQuery("${className}.findPage",query);
    }
    public List<${className}> findAll() {
        return super.findAll();
    }
    public ${className} getById(${table.idColumn.javaType} id) {
        return super.getById( id);
    }
    public ${className} getObject(${className}Query query) {
        return (${className})getSqlSession().selectOne("${className}.findPage",query);
    }
    public void save(${className} entity) {
        if(StringUtils.isEmpty(entity.get${table.idColumn.columnName}())) {
            entity.set${table.idColumn.columnName}(IdGenerator.genUUIDStr());
        }
        super.save(entity);
    }
    public void update(${className} entity) {
        super.update(entity);
    }
    public void removeByIds(List<String> ids) {
        super.removeByIds(ids);
    }

}
