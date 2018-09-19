package cn.zhangxd.auth.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component

public class CustomLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler {

	@Autowired
	private TokenStore tokenStore;

	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String access_token = request.getParameter("access_token");
		if (access_token != null) {
			OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(access_token);
			tokenStore.removeAccessToken(oAuth2AccessToken);
		}
		// 退出信息插入日志记录表中
		// ResultUtil.writeJavaScript(httpServletResponse,
		// ErrorCodeEnum.SUCCESS, "退出系统成功."); // 自己封装的代码
		// 作用就是把信息返回给前端去
	}

}
