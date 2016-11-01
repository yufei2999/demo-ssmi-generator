<#include "/custom.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.action;

<#include "/java_imports.include">

@Controller
@RequestMapping(value = "/${classNameLower}")
public class ${className}Action {

    private static final Logger logger = Logger.getLogger(${className}Action.class);

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
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listAjax")
    public String listAjax(${className}Query query, Model model) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Page<${className}> page = ${classNameLower}Service.findPage(query);
            map.put("total", page.getTotalCount());
            map.put("rows", page.getResult());
        } catch (Exception e) {
            logger.error("${className}列表查询异常", e);
        }
        return JsonUtil.toJSONString(map);
    }

    /**
     * 查看对象
     *
     * @return
     */
    @RequestMapping(value = "/show")
    public String show() {
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
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(${className} entity) {
        AjaxResult result = new AjaxResult();
        try {
            ${classNameLower}Service.save(entity);
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("${className}保存异常！");
            }
            logger.error(result.getMessage(), e);
        }
        return JsonUtil.toJSONString(result);
    }

    /**
     * 进入更新页面
     *
     * @return
     */
    @RequestMapping(value = "/edit")
    public String edit() {
        return "${jspFileBasePath}/edit";
    }

    /**
     * 更新
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update")
    public String update(${className} entity) {
        AjaxResult result = new AjaxResult();
        try {
            ${classNameLower}Service.update(entity);
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
                result.setMessage(e.getMessage());
            } else {
                result.setMessage("${className}更新异常！");
            }
            logger.error(result.getMessage(), e);
        }
        return JsonUtil.toJSONString(result);
    }

    /**
     * 删除
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(HttpServletRequest request) {
        AjaxResult result = new AjaxResult();
        try {
            String[] ids = request.getParameterValues("ids");
            if (ArrayUtils.isNotEmpty(ids)) {
                sysUserService.removeByIds(Arrays.asList(ids));
            }
            result.setCode(AjaxResult.RESULT_CODE_0000);
        } catch (Exception e) {
            result.setCode(AjaxResult.RESULT_CODE_0001);
            if (e.getMessage() != null) {
            result.setMessage(e.getMessage());
            } else {
            result.setMessage("${className}删除异常！");
            }
            logger.error(result.getMessage(), e);
        }
        return JsonUtil.toJSONString(result);
    }

}
