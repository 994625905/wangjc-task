/********************************Base路径*******************************/
var BasePath = "/wangjc-task-web-timer";

/******************************标准宽高*********************************************/
var BASE_WIDTH = $(window).width()*0.8+"px";
var BASE_HEIGHT = $(window).height()-100+"px";
/******************************小型宽高*********************************************/
var SM_WIDTH = $(window).width()*0.5+"px";
var SM_HEIGHT = $(window).height()-400+"px";
/******************************同级宽高*********************************************/
var LEVEL_WIDTH = $(window).width()+"px";
var LEVEL_HEIGHT = $(window).height()+"px";

/******************************layui全局配置第三方插件使用*****************/
layui.config({
    base: BasePath+'/resource/plugin/layui_third/' //可以使用绝对路径
}).extend({
    formSelects: 'formSelects-v4',//多选下拉框
    treeGrid:'treeGrid',//树级table
    treetable:"treetable"//树形table
});