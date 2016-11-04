<#include "/custom.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.action;

<#include "/java_imports.include">

@Controller
@RequestMapping(value = "/${classNameLower}")
public class ${className}Action {

    private static final Log log = LogFactory.getLog(${className}Action.class);

    @Autowired
    private ${className}Service ${classNameLower}Service;

    /**
     * 列表
     */
    @RequestMapping(value = "/list")
    public String list() {
        return "${jspFileBasePath}/list";
    }

    /**
     * 列表查询
     *
     * @param query
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listAjax")
    public String listAjax(${className}Query query) {
        Map<String, Object> map = new HashMap<>();
        try {
            Page<${className}> page = ${classNameLower}Service.findPage(query);
            map.put("total", page.getTotalCount());
            map.put("rows", page.getResult());
        } catch (Exception e) {
            log.error("${className}列表查询异常", e);
        }
        return JsonUtil.toJSONString(map);
    }

    /**
     * 查看对象
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/show")
    public String show(Model model, String id) {
        model.addAttribute("model", ${classNameLower}Service.getById(id));
        return "${jspFileBasePath}/show";
    }

    /**
     * 进入新增页面
     *
     * @return
     */
    @RequestMapping(value = "/create")
    public String create() {
        return "${jspFileBasePath}/create";
    }

    /**
     * 保存
     *
     * @param entity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(${className} entity) {
        AjaxResult result = new AjaxResult();
        try {
            CommonUtil.initSetProperties(entity, DataTypeUtils.OPERATION_ADD);
            ${classNameLower}Service.save(entity);
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("${className}保存异常！");
            }
            log.error(result.getMessage(), e);
        }
        return JsonUtil.toJSONString(result);
    }

    /**
     * 进入更新页面
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit")
    public String edit(Model model, String id) {
        model.addAttribute("model", ${classNameLower}Service.getById(id));
        return "${jspFileBasePath}/edit";
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update")
    public String update(${className} entity) {
        AjaxResult result = new AjaxResult();
        try {
            ${className} ${classNameLower} = ${classNameLower}Service.getById(entity.getId());
            CommonUtil.initSetProperties(${classNameLower}, DataTypeUtils.OPERATION_UPDATE);
            ${classNameLower}Service.update(${classNameLower});
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("${className}更新异常！");
            }
            log.error(result.getMessage(), e);
        }
        return JsonUtil.toJSONString(result);
    }

    /**
     * 删除
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(HttpServletRequest request) {
        AjaxResult result = new AjaxResult();
        try {
            String[] ids = request.getParameterValues("ids");
            if (ArrayUtils.isNotEmpty(ids)) {
                ${classNameLower}Service.deleteByIds(Arrays.asList(ids));
            }
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
            result.setMessage(e.getMessage());
            } else {
            result.setMessage("${className}删除异常！");
            }
            log.error(result.getMessage(), e);
        }
        return JsonUtil.toJSONString(result);
    }

}
