<@extends name="/assign.ftl"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="pragma" content="no-cache"  />
    <meta http-equiv="content-type" content="no-cache, must-revalidate" />
    <meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT"/>
    <link rel="icon" href="${StaticServer}/resource/image/i4.ico">

    <!--引入jQuery-->
    <script type="text/javascript" src="${StaticServer}/resource/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${StaticServer}/resource/jquery/jquery.cookie.js"></script>
<#--    <script src="https://cdn.bootcdn.net/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>-->

    <!--引入layUI-->
    <link rel="stylesheet" href="${StaticServer}/resource/layui/css/layui.css">
    <script type="text/javascript" src="${StaticServer}/resource/layui/layui.js"></script>

    <!--引入JS模板引擎-->
    <script type="text/javascript" src="${StaticServer}/resource/template/template.js"></script>

    <!--引入路径资源-->
    <script type="text/javascript" src="${StaticServer}/templates/assign.js?v=1.0"></script>

    <!--引入自定义工具-->
    <script type="text/javascript" src="${StaticServer}/resource/base/BaseUtil.js?v=1.0"></script>
    <script type="text/javascript" src="${StaticServer}/resource/base/Feng.js?v=1.2"></script>

    <!--引入自定义样式-->
    <link rel="stylesheet" href="${StaticServer}/resource/definedcss/definedcore.css">
    <link rel="stylesheet" href="${StaticServer}/resource/definedcss/free.css">

    <!--引入封装的专属的异步请求-->
    <script type="text/javascript" src="${StaticServer}/resource/base/Request.js?v=1.3"></script>

    <@block name="body" >base_body_content</@block>

</html>