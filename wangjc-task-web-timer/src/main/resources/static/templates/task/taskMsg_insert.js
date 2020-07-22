var layer,form;//声明组件

layui.use(["layer","form","laydate"],()=>{

        layer = layui.layer
        form = layui.form
        form.render()

        /** 提交 */
        form.on("submit(save)",obj=>{
                var param = obj.field
                param.autoDo = BaseUtil.isEmpty(param.autoDo)?1:0
                param.allowDo = BaseUtil.isEmpty(param.allowDo)?1:0
                param.isParam = BaseUtil.isEmpty(param.isParam)?1:0
                Request.asyncBody(BasePath+"/taskMsg/insertTask", {model:param}).then(res=>{
                        BaseUtil.reloadParent();
                        parent.layer.closeAll()
                })
                return false;
        })

})
