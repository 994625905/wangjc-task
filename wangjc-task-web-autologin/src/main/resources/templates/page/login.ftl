<@override name="body">
<body>
<div class="limiter">
    <div class="container-login100" style="background-image: url('${StaticServer}/images/bg-01.jpg');">
        <div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54" style="opacity: 0.7;">
            <form class="login100-form validate-form"  id="loginForm">
                <span class="login100-form-title p-b-49">登录</span>

                <div class="wrap-input100 validate-input m-b-23" data-validate="请输入用户名">
                    <span class="label-input100">用户名</span>
                    <input class="input100" type="text" name="userAccount" placeholder="请输入用户名" autocomplete="off">
                    <span class="focus-input100" data-symbol="&#xf206;"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="请输入密码">
                    <span class="label-input100">密码</span>
                    <input class="input100" type="password" name="password" placeholder="请输入密码">
                    <span class="focus-input100" data-symbol="&#xf190;"></span>
                </div>

                <div class="text-right p-t-8 p-b-31">
                    <a href="javascript:">忘记密码？</a>
                </div>

                <input type="hidden" name="redirect" value="${redirect!}">

                <div class="container-login100-form-btn">
                    <div class="wrap-login100-form-btn">
                        <div class="login100-form-bgbtn"></div>
                        <button class="login100-form-btn" type="button" id="loginButton">登 录</button>
                    </div>
                </div>

                <div class="txt1 text-center p-t-54 p-b-20">
                    <span>第三方登录</span>
                </div>

                <div class="flex-c-m">
                    <a href="#" class="login100-social-item bg1">
                        <i class="fa fa-wechat"></i>
                    </a>

                    <a href="#" class="login100-social-item bg2">
                        <i class="fa fa-qq"></i>
                    </a>

                    <a href="#" class="login100-social-item bg3">
                        <i class="fa fa-weibo"></i>
                    </a>
                </div>

                <div class="flex-col-c p-t-25">
                    <a href="javascript:" class="txt2">立即注册</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script>
    var redirect = "${redirect!''}";
</script>
<script type="text/javascript" src="${StaticServer}/js/login.js"></script>
</@override>
<@extends name="/base.ftl"/>