<@override name="body">
<title>任务调度（任务管理）</title>
<body>
<!--分割线-->
<br>
<style itemscope>
    .font-w{font-weight: 800;}
    .nowrap{white-space: nowrap;}
    .prewrap{white-space: pre-wrap;width: auto}
    .pt-div{padding-top: 6px}
</style>
<div class="layui-fluid">

    <div class="layui-row layui-col-space10">

        <div class="layui-col-xs10">
            <blockquote class="layui-elem-quote layui-quote-nm font-small"><em><font style="font-family: 楷体">任务调度，</font></em><font style="font-family: 楷体">定时任务</font></blockquote>
        </div>
        <div class="layui-col-xs2">
            <div class="layui-inline">
                <button type="button" class="layui-btn layui-btn-lg search-button addTask" style="height:50px;">新增任务</button>
            </div>
        </div>

        <!--任务列表-->
        <#list list as task>
            <div class="layui-col-xs5 p-1 m-1 flex align-center bg-light rounded border border-dark position-relative" style="height: 400px">
                <img src="${StaticServer}/resource/image/task/task.png" title="定时任务:重剑无锋" class="position-absolute p-1" width="60px" style="right: 0;top: 0">
                <div class="flex flex-column px-3">
                    <div class="flex pt-div">
                        <span class="font-w nowrap">任务名称：</span>
                        <span class="flex-1 prewrap">${task.taskName}&nbsp;<i class="layui-icon layui-icon-edit" onclick="editTask(${task.id},'${task.taskName}','taskName')"></i></span>
                    </div>
                    <div class="flex pt-div">
                        <span class="font-w nowrap">唯一标识：</span>
                        <span class="flex-1 prewrap">${task.taskSsid}</span>
                    </div>
                    <div class="flex pt-div">
                        <span class="font-w nowrap">任务描述：</span>
                        <span class="flex-1 prewrap">${task.taskExplain}&nbsp;<i class="layui-icon layui-icon-edit" onclick="editTask(${task.id},'${task.taskExplain}','taskExplain')"></i></span>
                    </div>
                    <div class="flex pt-div">
                        <span class="font-w nowrap">执行时间：</span>
                        <span class="flex-1 prewrap">${task.taskDate}&nbsp;<i class="layui-icon layui-icon-edit" onclick="editTask(${task.id},'${task.taskDate}','taskDate')"></i></span>
                    </div>
                    <div class="flex pt-div">
                        <span class="font-w nowrap">执行规则：</span>
                        <div class="flex-1">
                            <#if task.autoDo == 1>
                                <span class="layui-badge ml-1" title="执行类型">手动触发</span>
                            <#else>
                                <span class="layui-badge layui-bg-green ml-1" title="执行类型">自动运行</span>
                            </#if>
                            <#if task.allowDo == 1>
                                <span class="layui-badge ml-1" title="是否允许手动触发">允许手动</span>
                            <#else>
                                <span class="layui-badge layui-bg-green ml-1" title="是否允许手动触发">禁止手动</span>
                            </#if>
                            <#if task.isParam == 1>
                                <span class="layui-badge ml-1" title="调用是否传递参数">带参</span>
                            <#else>
                                <span class="layui-badge layui-bg-green ml-1" title="调用是否传递参数">无参</span>
                            </#if>
                        </div>
                    </div>
                    <div class="flex pt-div">
                        <span class="font-w nowrap">执行成功：</span>
                        <div class="flex-1">
                            <span class="text-muted">自动：</span><span class="text-primary">${task.autoSuccess}</span>
                            <span class="text-muted">手动：</span><span class="text-primary">${task.handSuccess}</span>
                        </div>
                    </div>
                    <div class="flex pt-div">
                        <span class="font-w nowrap">执行失败：</span>
                        <div class="flex-1">
                            <span class="text-muted">自动：</span><span class="text-danger">${task.autoFail}</span>
                            <span class="text-muted">手动：</span><span class="text-danger">${task.handFail}</span>
                        </div>
                    </div>
                    <div class="flex pt-div">
                        <span class="font-w nowrap">外部token：</span>
                        <div class="flex-1">
                            <a class="i4_a font-italic" onclick="BaseUtil.clickCopy('${task.taskToken}','token复制成功')">点击复制</a>
                            <span class="text-muted">(后续如果分离过滤器，外部请求携带token)</span>
                        </div>
                    </div>
                    <div class="flex pt-div">
                        <span class="font-w nowrap">内部Web：</span>
                        <span class="flex-1 text-muted prewrap">${task.taskUrl}&nbsp;<i class="layui-icon layui-icon-edit" onclick="editTask(${task.id},'${task.taskUrl}','taskUrl')"></i></span>
                    </div>
                    <div class="flex pt-div">
                        <span class="font-w nowrap flex align-center">任务状态：</span>
                        <span class="flex-1 text-muted">
                            <#if task.status == 1>
                                <img src="${StaticServer}/resource/image/task/run.gif" style="width: 40px">
                                <span class="layui-badge ml-1">运行中</span>
                            <#else>
                                <img src="${StaticServer}/resource/image/task/stop.gif">
                                <span class="layui-badge layui-bg-green ml-1">已停机</span>
                            </#if>
                        </span>
                    </div>
                    <div class="flex pt-div">
                        <span class="font-w nowrap flex align-center">操作事项：</span>
                        <div class="flex-1 flex align-center">
                            <#if task.status == 1>
                                <span class="px-1">
                                    <a onclick="stopTask(${task.id},'${task.taskName}')" title="终止任务"><i class="layui-icon layui-icon-pause" style="font-size: 23px;"></i></a>
                                </span>
                            <#else>
                                <span class="px-1">
                                    <a onclick="startTask(${task.id},'${task.taskName}')" title="启动任务"><i class="layui-icon layui-icon-play" style="font-size: 23px;"></i></a>
                                </span>
                            </#if>
                            <#if task.allowDo == 1 && task.status == 1>
                                <span class="px-1">
                                    <a onclick="runTask(${task.id},'${task.taskName}',${task.isParam},'${task.taskUrl}')" title="手动执行">
                                        <i class="layui-icon layui-icon-release" style="font-size: 23px;"></i>
                                    </a>
                                </span>
                            </#if>
                            <span class="px-1">
                                <a onclick="delTask(${task.id},'${task.taskName}')" title="删除任务">
                                    <i class="layui-icon layui-icon-delete" style="font-size: 23px;"></i>
                                </a>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
       </#list>
    </div>
</div>

</body>
<script type="text/html" id="param">
    <div class="layui-inline">
        <label class="layui-form-label">JSON参数</label>
        <div class="layui-input-inline" style="width: 250px">
            <textarea name="param" class="layui-textarea"></textarea>
        </div>
    </div>
</script>
<script type="text/javascript" src="${StaticServer}/templates/task/taskMsg_index.js?v=1.2"></script>
</@override>
<@extends name="/base.ftl"/>