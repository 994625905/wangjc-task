var layer,element,laydate;//声明组件

layui.use(["layer","element","laydate"],()=>{

    layer = layui.layer
    element = layui.element
    laydate = layui.laydate

    /** 新增任务按钮 */
    $(".addTask").click(()=>{
        Feng.loadWindow("新增任务",BasePath+"/taskMsg/insertPage")
    })

})
/**********************************启动任务********************************/
function startTask(taskId,taskName){
    Feng.confirm("确定激活该定时任务吗？",()=>{
        Request.async(BasePath+"/taskMsg/startTask",{taskId:taskId}).then(res=>{
            Feng.success("激活成功")
            location.reload()
        })
    })
}
/**********************************停止任务********************************/
function stopTask(taskId,taskName){
    Feng.confirm("确定停止该定时任务吗？",()=>{
        Request.async(BasePath+"/taskMsg/stopTask",{taskId:taskId}).then(res=>{
            Feng.success("停止成功")
            location.reload()
        })
    })
}
/**********************************执行任务********************************/
function runTask(taskId,taskName,isParam,taskUrl){
    if(isParam == 0){
        Request.asyncBody(taskUrl).then(res=>{
            Feng.msg("手动调用成功")
        })
    }
    if(isParam == 1){
        var content = template("param",{});
        Feng.infoDetail(taskName+"--任务执行设置",content,null,index=>{
            var param = $("textarea[name='param']").val()
            if(BaseUtil.isEmpty(param)){
                return Feng.info("参数不可为空")
            }
            Request.asyncBody(taskUrl,JSON.parse(param)).then(res=>{
                Feng.close(index)
                location.reload()
                Feng.msg("手动调用成功")
            })
        })
    }
}
/******************************删除任务******************************/
function delTask(taskId,taskName){
    Feng.confirm("确定删除“"+taskName+"”吗？",()=>{
        Request.async(BasePath+"/taskMsg/delTask",{taskId:taskId}).then(res=>{
            Feng.success("删除成功")
            location.reload()
        })
    })
}
/*****************************编辑任务****************************/
function editTask(taskId,init,field){
    Feng.input("编辑任务项",value=>{
        Request.async(BasePath+"/taskMsg/editTask",{
            taskId:taskId,
            fieldValue:value,
            fieldName:field
        }).then(res=>{
            Feng.success("更新成功")
            location.reload()
        })
    },init)
}