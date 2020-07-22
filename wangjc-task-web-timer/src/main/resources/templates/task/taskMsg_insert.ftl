<@override name="body">
<title>任务调度（任务新增）</title>
<body>
<br>
<div class="layui-fluid">
    <div style="width: 90%;">
        <br>
        <form class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">任务名称：</label>
                <div class="layui-input-block">
                    <input name="taskName" placeholder="请输入任务名称" lay-verify="required" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">任务描述：</label>
                <div class="layui-input-block">
                    <textarea placeholder="大致介绍……" name="taskExplain" lay-verify="required" class="layui-textarea"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">执行时间：</label>
                <div class="layui-input-block">
                    <input name="taskDate" id="taskDate" class="layui-input" placeholder="corn表达式">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">触发方式：</label>
                <div class="layui-input-block">
                    <input type="checkbox" checked name="autoDo" lay-skin="switch" lay-filter="autoDo" lay-text="自动|手动">
                </div>
            </div>
            <div class="layui-form-item allowDo">
                <label class="layui-form-label">手动权限：</label>
                <div class="layui-input-block">
                    <input type="checkbox" checked name="allowDo" lay-skin="switch" lay-filter="allowDo" lay-text="禁止|授权">
                </div>
            </div>
            <div class="layui-form-item taskUrl">
                <label class="layui-form-label">任务链接：</label>
                <div class="layui-input-block">
                    <input name="taskUrl" placeholder="请输入任务链接" lay-verify="url" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item isParam">
                <label class="layui-form-label">调用参数：</label>
                <div class="layui-input-block">
                    <input type="checkbox" checked name="isParam" lay-skin="switch" lay-filter="isParam" lay-text="无参|存在">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit="" lay-filter="save">立即提交</button>
                    <button type="button" class="layui-btn layui-btn-danger" onclick="parent.layer.closeAll()">关闭</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript" src="${StaticServer}/templates/task/taskMsg_insert.js?v=1.3"></script>
</@override>
<@extends name="/base.ftl"/>