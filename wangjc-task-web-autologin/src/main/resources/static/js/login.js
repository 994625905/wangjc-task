/******************************点击登录按钮*****************************/
$("#loginButton").click(function(){
    var param = {
        userAccount:$("input[name='userAccount']").val(),
        password:$("input[name='password']").val()
    };
    debugger
    if(BaseUtil.isEmpty(param.userAccount) || BaseUtil.isEmpty(param.password)){
        Feng.info("请输入用户名或密码");
        return false;
    }else{
        Request.async(BasePath+"/autoLogin/login",{
            ...param,
            redirect:redirect
        }).then(res=>{
            if(BaseUtil.isEmpty(res)){
                Feng.msg("重定向地址不存在")
            }else{
                BaseUtil.redirect(res)
            }
        })
    }
});